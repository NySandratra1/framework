package contenue;
import steps.*;
import java.sql.*; 
import generale.*;
import java.util.*;
import java.lang.reflect.*;
public class Zavatra extends Objet{
    String prefixe = "PRS";
    int idLength = 7;
    String funcName ="getZavatraSeq";
    String id;
    String idvalue;
    public void setPrefixe(String pre){
        this.prefixe = pre;
    }
    public void setIdLength(int id){
        this.idLength = id;
    }
    public void setFuncName(String func){
        this.funcName = func;
    }
    public void setIdValue(String id){
        this.idvalue = id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public String getIdValue(){
        return this.idvalue;
    }
    public String getPrefixe(){
        return this.prefixe;
    }
    public int getIdLength(){
        return this.idLength;
    }
    public String getFuncName(){
        return this.funcName ;
    }
    public int getSequence(ConnectP con){
        int seq = 0;
        try{
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select " + this.getFuncName());
            while(rs.next()){
                seq = rs.getInt(1);
            }
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            con.closeConnection();            
        }
        return seq;
    }
    public String construirePK(ConnectP con){
        String id = "";
        try{
            int seq = this.getSequence(con);
            id = this.fillZero(seq); 
            System.out.println(id);
            Connection tempcon = con.getConnection();
            /*Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("insert into zavatra(id) values('"+ id +"')");*/
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            con.closeConnection();            
        }
        return id;
    }
    public String fillZero(int seq){
        String all = this.getPrefixe();
        String sequ = seq + "";
        int zerotofill = this.getIdLength() - all.length() - sequ.length();
        for(int i = 0;i<zerotofill;i++){
            all += "0"; 
        }
        all = all + sequ;
        return all;
    }
    public String getTableValue(ConnectP co){
        Vector<Objet> o = this.select(co,this.getId(),this.getIdValue().toUpperCase());
        System.out.println("objet:"+o);
        Field[] fields = this.getClass().getDeclaredFields();
        String all = "";
        try{
            //System.out.println("nety ihany");
            for(int i = 0;i<fields.length;i++){
                Method m = o.get(0).getClass().getMethod("get"+fields[i].getName());
                all += fields[i].getName() + ":" + m.invoke(o.get(0)) + "; ";
            }
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        return all;
    }
}