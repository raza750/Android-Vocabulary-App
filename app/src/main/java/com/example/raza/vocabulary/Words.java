package com.example.raza.vocabulary;

public class Words {
 private String word,sentence;
 private int id;
    public Words(String word, String sentence)
    {
        this.word = word;
        this.sentence = sentence;
    }
    public Words(int id, String word, String sentence){

        this.id = id;
        this.word = word;
        this.sentence = sentence;
    }
    public String getWord()
    {
        return word;
    }

    public String getSentence()
    {
        return sentence;
    }

    public int getId(){return id;}
}
