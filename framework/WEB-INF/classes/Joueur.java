package contenue;
import generale.*;
import steps.*;
import java.util.*;
import java.sql.*;
public class Joueur extends Zavatra{
    String Id_joueur;
    String Id_equipe;
    String Nom;
    int nbtir;
    double pour;
    public Joueur(){}
    public Joueur(int i,String Id_equipe){
        this.setNbtir(i);
        this.setId_joueur(Id_equipe);
    }
    public Joueur(double i,String Id_equipe){
        this.setPour(i);
        this.setId_joueur(Id_equipe);
    }
    public void setPour(double p){
        this.pour = p;
    }
    public double getPour(){
        return this.pour;
    }
    public void setNbtir(int i){
        this.nbtir = i;
    }
    public int getNbtir(){
        return this.nbtir;
    }
    public Joueur(String s,String s1,String s2){
        this.setId_joueur(s);
        this.setId_equipe(s1);
        this.setNom(s2);
    }
    public void setId_equipe(String ie){
        this.Id_equipe = ie;
    }
     public void setId_joueur(String ij){
        this.Id_joueur = ij;
    }
    public void setNom(String n){
        this.Nom = n;
    }
    public String getNom(){
        return this.Nom;
    }
    public String getId_equipe(){
        return this.Id_equipe;
    }
    public String getId_joueur(){
        return this.Id_joueur;
    }
    public Vector<Joueur> getTeamPlayers(ConnectP co,String team){
        Vector<Objet> o = this.selectTeam(co,team);
        Vector<Joueur> t = new Vector<Joueur>();
        for(int i = 0;i<5;i++){
            t.add((Joueur)(o.get(i)));
        }
        return t;
    }
    public int getNbTir(ConnectP cop,String team){
        int tire = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"'");
            while(rs.next()){
                tire = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return tire;
    }
    public int getNbTirMaty(ConnectP cop,String team){
        int tire = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and maty!= 0 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"'");
            while(rs.next()){
                tire = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return tire;
    }
    public int getPasseDec(ConnectP cop,String team){
        int passe = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='passe' and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"'");
            while(rs.next()){
                passe = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return passe;
    }
    public int getReboundDef(ConnectP cop,String team){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='rebound' and maty=0 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getReboundOf(ConnectP cop,String team){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='rebound' and maty=1 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getTireEquipe(Connect cop,String team){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_match='"+team+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getTireMaty(Connect cop,String team){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_ and id_match='"+team+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getPossession(ConnectP cop,String id_match){
        int possession = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match='" + id_match + "' and id_joueur='" + this.getId_joueur() + "'");
            while(rs.next()){
                possession = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return possession;
    }
     public int getNbTirQuart(ConnectP cop,String team,String quart){
        int tire = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                tire = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return tire;
    }
    public int getNbTirMatyQuart(ConnectP cop,String team,String quart){
        int tire = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and maty!= 0 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                tire = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return tire;
    }
    public int getPasseDecQuart(ConnectP cop,String team,String quart){
        int passe = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='passe' and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                passe = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return passe;
    }
    public int getReboundDefQuart(ConnectP cop,String team,String quart){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='rebound' and maty=0 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getReboundOfQuart(ConnectP cop,String team,String quart){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='rebound' and maty=1 and id_joueur='"+this.getId_joueur()+"' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getTireEquipeQuart(Connect cop,String team,String quart){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getTireMatyQuart(Connect cop,String team,String quart){
        int rebond = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where act='tire' and id_ and id_match='"+team+"' and quart='"+ quart+"'");
            while(rs.next()){
                rebond = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return rebond;
    }
    public int getPossessionQuart(ConnectP cop,String id_match,String quart){
        int possession = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match='" + id_match + "' and id_joueur='" + this.getId_joueur() + "' and quart='"+ quart+"'");
            while(rs.next()){
                possession = rs.getInt(1);
            }
            cop.commitConnection();
            cop.closeConnection();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cop.closeConnection();
        }
        return possession;
    }
}