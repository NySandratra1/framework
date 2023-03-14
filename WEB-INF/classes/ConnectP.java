package steps;
import java.sql.*;
public class ConnectP{
    String url;
    String user;
    String passwrd;
    Connection con;
    public String getUrl(){
        return this.url;
    }
    public String getUser(){
        return this.user;
    }
    public String getPasswrd(){
        return this.passwrd;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setUser(String user){ 
        this.user = user;
    }
    public void setPasswrd(String passwrd){
        this.passwrd = passwrd;
    }
    public Connection getConnection(){
        this.con = null;
        try{
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection(this.getUrl(),this.getUser(),this.getPasswrd());
            
        }catch (Exception e){
        }
        return con;
    }
    public void closeConnection(){
        try{
            this.con.close();
        }
        catch(Exception e){

        }
    }
    public void commitConnection(){
        try{
            this.con.commit();
        }
        catch(Exception e){

        }
    }
}