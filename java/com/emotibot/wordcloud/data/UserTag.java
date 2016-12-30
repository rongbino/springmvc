package com.emotibot.wordcloud.data;

public class UserTag implements Comparable<UserTag> 
{
    String name;
    Double value;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public Double getValue()
    {
        return value;
    }
    public void setValue(Double value)
    {
        this.value = value;
    }
    
    public int compareTo(UserTag o) {
        // TODO Auto-generated method stub
        if (o.getValue() == null && this.getValue() == null) {
            return 0;
        } else if (o.getValue() == null && this.getValue() != null){
            return -1;
        } else if (o.getValue() != null && this.getValue() == null) {
            return 1;
        } else {
            return this.getValue().compareTo(o.getValue());
        }
    }
}
