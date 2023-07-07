package model;
import java.util.HashMap;
public class ModelView{
    private String view;
    private HashMap<String,Object> data = new HashMap<>();

    public void setData(HashMap<String,Object> d){
        this.data = d;
    }

    public HashMap<String,Object> getData(){
        return this.data;
    }

    public void setView(String view){
        this.view = view;
    }

    public String getView(){
        return this.view;
    }

    public void addItem(String key,Object value){
        this.data.put(key,value);
    }

}