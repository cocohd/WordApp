package com.example.shadow.wordapp;

import org.litepal.crud.DataSupport;

public class Word extends DataSupport{
    private String id;
    private String pronounce;
    private String mean1;
    private String mean2;
    private String mean3;
    private String exam;//例句
    private String mean4;


    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String  getPronounce(){
        return pronounce;
    }
    public void setPronounce(String pronounce){
        this.pronounce=pronounce;
    }

    public String getMean1(){
        return mean1;
    }
    public void setMean1(String mean1){
        this.mean1=mean1;
    }

    public String getMean2(){
        return mean2;
    }
    public void setMean2(String mean2){
        this.mean2=mean2;
    }
    public String getMean3(){
        return mean3;
    }
    public void setMean3(String mean3){
        this.mean3=mean3;
    }
    public String getMean4(){
        return mean4;
    }
    public void setMean4(String mean4){
        this.mean4=mean4;
    }
    public String getExam(){
        return exam;
    }
    public void setExam(String example){
        this.exam=example;
    }
}
