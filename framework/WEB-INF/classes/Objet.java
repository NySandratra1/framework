package generale;
import steps.*;
import java.lang.reflect.*;
import java.util.*;
import java.sql.*;
public class Objet{
    public Vector<Objet> select(ConnectP con){
        Vector<Objet> obj = new Vector<Objet>();
        //String package = this.getClass().getSimpleName();
        try{ 
            Field[] f = this.getClass().getDeclaredFields();
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + this.getClass().getSimpleName());
            Objet o = null;
            while(rs.next()){
                o = this.getClass().newInstance();
                for(int i = 1;i<f.length+1;i++){
                    if(f[i-1].getType().getName().contains("String")){
                        Method m = this.getClass().getMethod("set" + f[i-1].getName(),String.class);
                        //System.out.println(rs.getString(i) + " = " +f[i-1].getName());
                        System.out.println(rs.getRow());
                        m.invoke(o,rs.getString(i));
                    }
                    else{
                        Method m = this.getClass().getMethod("set" + f[i-1].getName(),int.class);
                        //System.out.println(rs.getString(i) +" = "+f[i-1].getName());
                        System.out.println(rs.getRow());
                        m.invoke(o,rs.getInt(i));
                    }
                }
                obj.add(o);
            }
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
        return obj;
    }
    public Vector<Objet> selectTeam(ConnectP con,String team){
        Vector<Objet> obj = new Vector<Objet>();
        //String package = this.getClass().getSimpleName();
        try{ 
            Field[] f = this.getClass().getDeclaredFields();
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + this.getClass().getSimpleName() + " where id_equipe = '"+ team +"'");
            Objet o = null;
            while(rs.next()){
                o = this.getClass().newInstance();
                for(int i = 1;i<3+1;i++){
                        Method m = this.getClass().getMethod("set" + f[i-1].getName(),String.class);
                        //System.out.println(rs.getString(i) + " = " +f[i-1].getName());
                        System.out.println(rs.getRow());
                        m.invoke(o,rs.getString(i));
                }
                obj.add(o);
            }
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
        return obj;
    }
    public Vector<Objet> select(ConnectP con,String id,String value){
        Vector<Objet> obj = new Vector<Objet>();
        System.out.println("objet Objet p:"+obj);
        //String package = this.getClass().getSimpleName();
        try{
            Field[] f = this.getClass().getDeclaredFields();
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            System.out.println("select * from " + this.getClass().getSimpleName() + " where " + id + "='" + value +"'");
            ResultSet rs = stmt.executeQuery("select * from " + this.getClass().getSimpleName() + " where " + id + "='" + value +"'");
            System.out.println("tafidina eto aho");
            //Objet o = null;
            while(rs.next()){
                //System.out.println("tafidina eto aho 13");
                //o = this.getClass().newInstance();
                for(int i = 1;i<f.length+1;i++){
                    if(f[i-1].getType().getName().contains("String")){
                        Method m = this.getClass().getMethod("set" + f[i-1].getName(),String.class);
                        //System.out.println(rs.getString(i) + " = " +f[i-1].getName());
                        //System.out.println(rs.getRow());
                        m.invoke(this,rs.getString(i));
                    }
                    else{
                        Method m = this.getClass().getMethod("set" + f[i-1].getName(),int.class);
                        //System.out.println(rs.getString(i) +" = "+f[i-1].getName());
                        //System.out.println(rs.getRow());
                        m.invoke(this,rs.getInt(i));
                    }
                }
                //System.out.println("tafidina eto aho lolololololo");
                obj.add(this);
            }
            System.out.println("objet Objet :"+obj);
            //System.out.println("tafidina eto aho 2 lol ");
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
        return obj;
    }
    public void insert(ConnectP con){
        try{ 
            Field[] f = this.getClass().getDeclaredFields();
            String all = "";
            String values = "";
            for(int i = 0;i<f.length;i++){
                if(i!=f.length - 1){
                    all = all + f[i].getName() + ",";
                }
                else all = all + f[i].getName();
            }
            for(int i = 0;i<f.length;i++){
                Method m = this.getClass().getMethod("get"+f[i].getName());
                if(i!=f.length-1){
                    if(f[i].getType().getName().contains("String")){
                        values = values + "'" +m.invoke(this) + "',";
                    }
                    else values =values + m.invoke(this) + ",";
                }
                else{
                    if(f[i].getType().getName().contains("String")){
                        values = values + "'" +m.invoke(this) + "'";
                    }
                    else values =values + m.invoke(this);
                }
            }
            System.out.println(all);
            System.out.println(values);          
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            int rs = stmt.executeUpdate("insert into "+this.getClass().getSimpleName() + "(" + all + ") values("+values+")");
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
    }
    public void insertJoueur(ConnectP con){
        try{ 
            Field[] f = this.getClass().getDeclaredFields();
            String all = "";
            String values = "";
            for(int i = 0;i<f.length-2;i++){
                if(i!=f.length - 3){
                    all = all + f[i].getName() + ",";
                }
                else all = all + f[i].getName();
            }
            for(int i = 0;i<f.length-2;i++){
                Method m = this.getClass().getMethod("get"+f[i].getName());
                if(i!=f.length-3){
                    if(f[i].getType().getName().contains("String")){
                        values = values + "'" +m.invoke(this) + "',";
                    }
                    else values =values + m.invoke(this) + ",";
                }
                else{
                    if(f[i].getType().getName().contains("String")){
                        values = values + "'" +m.invoke(this) + "'";
                    }
                    else values =values + m.invoke(this);
                }
            }
            System.out.println(all);
            System.out.println(values);          
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            int rs = stmt.executeUpdate("insert into "+this.getClass().getSimpleName() + "(" + all + ") values("+values+")");
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
    }
    public void update(ConnectP con,String column,String newValue,String column1,String existingValue){
        //System.out.println(newValues.length);
        try{
            Field[] f = this.getClass().getDeclaredFields();
            String colonne = "";
            String colonne1 = "";
            String value = "";
            String value1 = "";
            String afterWhere = "";
            for(int i = 0;i<f.length;i++){
                if(f[i].getName().toLowerCase().equals(column.toLowerCase())){
                    colonne = f[i].getName();
                    if(f[i].getType().getName().contains("String")){
                        value = "'" + newValue + "'";
                    }
                    else value = newValue;
                }
            }
            for(int i = 0;i<f.length;i++){
                if(f[i].getName().toLowerCase().equals(column1.toLowerCase())){
                    colonne1 = f[i].getName();
                    if(f[i].getType().getName().contains("String")){
                        value1 = "'" + existingValue + "'";
                    }
                    else value1 = existingValue;
                }
            }
            if(colonne.equals("")) return;
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            ResultSet rs = stmt.executeQuery("update " + this.getClass().getSimpleName() + " set " + colonne + " = " + value + " where " + column1 + "=" + value1);
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
    }
    public void update(ConnectP con){
        try{    
            Connection tempcon = con.getConnection();
            Statement stmt = tempcon.createStatement();
            //ResultSet rs = stmt.executeQuery("update " + this.getClass().getSimpleName() + " set " + colonne + " = " + value + " where " + column1 + "=" + value1);
            con.commitConnection();
            con.closeConnection();
        }
        catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            con.closeConnection();
        }
        finally{
            con.closeConnection();
        }
    }
}