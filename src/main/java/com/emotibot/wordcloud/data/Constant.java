/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emotibot.wordcloud.data;

/**
 *
 * @author rongbin
 */
public class Constant {
    public static final String path = "/Users/rongbin/Desktop/tag";
	//public static final String path = "/Users/jonny/Documents/tag";
    public static final String fileName = "all.txt"; //从数据库导入的所有用户的Q
    // folder to save the files with each user conversations
    public static final String eachUserpath = path + "/eachUser";
    // folder to save the result for NLP parsed sentence
    public static final String eachNLPpath = path + "/eachNLP";
    // folder to save the TFIDF statistic
    public static final String tfidfPath = path + "/TFIDF";
    // file to save the word tfidf value
    public static final String tfidf_file = "wordsTFIDF.txt";
    // folder to save the final result
    public static final String resultPath = path + "/Final";
    // file to save the final result
    public static final String result_file = "final.txt";
    // file to save the final tag result
    public static final String tag_file = "tag.txt";
    // folder to save the result for work statis
    public static final String wordStatisticPath = path + "/wordStatistic";
    // file to save the word statistic 
    public static final String wordstatistic_file = "wordstatistic.txt";
    
    public static final String nlpFile_Number = "Number:";
    public static final String nlpFile_Pos = "Pos:";
    public static final String nlpFile_ReservedPos = "reservedPos:";
    public static final String nlpFile_NLPKeyWord = "NLPKeyWord:";
    public static final String newLine = "\n";

   public static final String nlp_postfix = "_NLP";

//    public static final String stopword_file = "stopwords.txt";

    public static final String stopword_file2 = "stopwords2.txt";
    public static final String reserved_singleWord = "reservedSingleWord.txt";
    
    // parameter name for word statistic
    public static final String wordStatistic_userid = "Userid:";
    public static final String wordStatistic_reservedPos = "reservedPos:";
    public static final String wordStatistic_nlpkeywords = "NLPKeyword:";
    
    // threshold number for chat lines
    public static final int chatnumber_threshold = 30;
    
    public static final String wordstatistic_TFIDF = "TFIDF:";
    // constant for TextRank
    public static final String wordstatistic_TextRank = "TextRank:";
    public static final int max_iter = 1000;
    public static final double default_d = 0.85;
    public static final double min_diff = 0.001;
    
    // constant for Top A
    public static final String topA_match3 = "Match=3";
    public static final String topA_match2 = "Match=2";
    public static final String topA_match1 = "Match=1";
    // top parameter
    public static final String topA = "top";
    public static final double weight_textRank = 0.4f;
    public static final double weight_tfidf = 0.4f;
    public static final double weight_nlpKeyword = 0.2f;
    
}
