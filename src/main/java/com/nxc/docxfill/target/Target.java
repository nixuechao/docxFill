package com.nxc.docxfill.target;

import java.util.ArrayList;
import java.util.List;

/**
 * @author niXueChao
 * @date 2019/4/24 15:58.
 */
public class Target {
    private List<Sentence> sentences=new ArrayList<>();
    private Target(){
    }

    public static Target builder(){
        return new Target();
    }

    public Sentence withText(String text){
        return new Sentence(text,this);
    }

    void addSentence(Sentence sentence){
        this.sentences.add(sentence);
    }

    public List<Sentence> getSentences(){
        return this.sentences;
    }
}
