package model;

import java.util.HashMap; 
import etu2018.framework.annotation.Annotation;

public class Maison{

    private String adresse;
    @Annotation(url = "/adresse-maison")
    public ModelView getAdresse(){
        ModelView ok = new ModelView();
        ok.setView("maison.jsp");
        ok.addItem("lol","petite");
        return ok;
    }

    @Annotation(url = "/test1")
    public ModelView getTest1(){
        ModelView ok = new ModelView();
        ok.setView("maison.jsp");
        return ok;
    } 

    @Annotation(url = "/test2")
    public ModelView getTest2(){
        ModelView ok = new ModelView();
        ok.setView("maison.jsp");
        return ok;
    }

}