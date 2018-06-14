package com.vedangbhardwaj.interviewpreperation;


public class ItemClass {
    private String question;
    private String answer;

    public ItemClass(String ques, String ans){
        question = ques;
        answer = ans;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }

}
