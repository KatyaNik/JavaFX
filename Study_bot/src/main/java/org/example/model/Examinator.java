package org.example.model;

import org.example.model.Question;

public class Examinator {
    public Question action(){
        return new Question();
    }
    public boolean check(String answer){
        return true;
    }
    public String end(){
        return "";
    }
}
