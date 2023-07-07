package model;

import etu2018.framework.annotation.Annotation;

public class Maison{
    private String adresse;
    

    @Annotation(url = "/adresse-maison")
    public String getAdresse(){
        return "VF 167";
    }

    @Annotation(url = "/test1")
    public String getTest1(){
        return "VF 167";
    } 

    @Annotation(url = "/test2")
    public String getTest2(){
        return "VF 167";
    } 
}
