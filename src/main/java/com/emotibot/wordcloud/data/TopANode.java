/*
 * Copyright (c) 2016 by Emotibot Corporation. All rights reserved.
 * EMOTIBOT CORPORATION CONFIDENTIAL AND TRADE SECRET
 *
 * Primary Owner: binrong@emotibot.com
 * Secondary Owner:
 */

package com.emotibot.wordcloud.data;


public class TopANode implements Comparable<TopANode> {

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public Double getNLPKeywordsValue() {
        return NLPKeywordsValue;
    }

    public void setNLPKeywordsValue(Double nLPKeywordsValue) {
        NLPKeywordsValue = nLPKeywordsValue;
        updateNode();
    }

    public Double getTextRankValue() {
        return TextRankValue;
    }

    public void setTextRankValue(Double textRankValue) {
        TextRankValue = textRankValue;
        updateNode();
    }

    public Double getTFIDFValue() {
        return TFIDFValue;
    }

    public void setTFIDFValue(Double tFIDFValue) {
        TFIDFValue = tFIDFValue;
        updateNode();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
    
    /**
     * 1. 出现次数+1
     * 2. 当前时间update
     */
    private void updateNode() {
        this.weight++;
        this.setTime(System.currentTimeMillis());
        if (weight == 3) {
            totalValue = Constant.weight_textRank*TextRankValue
                    + Constant.weight_tfidf*TFIDFValue + Constant.weight_nlpKeyword*NLPKeywordsValue;
        }
    }
    
    /*
     * 如果三个值都是非空
     */
    public int getWeight() {
        return weight;
    }

    private String node = null;
    private Double NLPKeywordsValue = null;
    private Double TextRankValue = null;
    private Double TFIDFValue = null;
    private Double totalValue = null;
    private Long time = null; // The time when NLP/TextRank/TFIDF are not null
    private int weight = 0;

    public int compareTo(TopANode o) {
        // TODO Auto-generated method stub
        if (o.getTime() == null && this.getTime() == null) {
            return 0;
        } else if (o.getTime() == null && this.getTime() != null){
            return -1;
        } else if (o.getTime() != null && this.getTime() == null) {
            return 1;
        } else {
            return this.getTime().compareTo(o.getTime());
        }
    }

}
