package evenement;
import java.util.*;
import contenue.*;
import steps.*;
import java.sql.*;
import java.time.*; 
public class Match extends Zavatra{
    String Id_match;
    String Id_joueur;
    String Id_equipe;
    Time Temps;
    String Act;
    int Maty;
    String Quart;
    public Match(){}
    public Match(int score,String team){
        this.setMaty(score);
        this.setId_equipe(team);
    }
    public Match(String id){
        this.setId_match(id);
    }
    public Match(String idm,String idj,String ide,Time t,String a,int m,String quart){
        this.setId_match(idm);
        this.setId_joueur(idj);
        this.setId_equipe(ide);
        this.setTemps(t);
        this.setAct(a);
        this.setMaty(m);
        this.setQuart(quart);
    }
    public void setId_match(String im){
        this.Id_match = im;
    }
    public void setId_joueur(String ij){
        this.Id_joueur = ij;
    }
    public void setId_equipe(String ie){
        this.Id_equipe = ie;
    }
    public void setTemps(Time d){
        this.Temps = d;
    }
    public void setAct(String a){
        this.Act = a;
    }
    public void setMaty(int m){
        this.Maty = m;
    }
    public void setQuart(String q){
        this.Quart = q;
    }
    public String getId_match(){
        return this.Id_match;
    }
    public String getId_joueur(){
        return this.Id_joueur ;
    }
    public String getId_equipe(){
        return this.Id_equipe ;
    }
    public Time getTemps(){
        return this.Temps;
    }
    public String getAct(){
        return this.Act;
    }
    public int getMaty(){
        return this.Maty;
    }
    public String getQuart(){
        return this.Quart;
    }
    public int getTires(ConnectP cop,String team,String ideq){
        int tires = 1;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where id_equipe='" + ideq + "' and id_match='"+team+"' and act='tire'");
            while(rs.next()){
                tires = rs.getInt(1);
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
        return tires;
    }
    public int getTiresMaty(ConnectP cop,String team,String ideq){
        int tires = 1;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act) from match where id_equipe='" + ideq + "' and id_match='"+team+"' and act='tire' and maty!=0");
            while(rs.next()){
                tires = rs.getInt(1);
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
        return tires;
    }
    public Vector<Match> getAllMatches(ConnectP cop){
        Vector <Match> match = new Vector <Match>();
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select id_match from match group by id_match");
            while(rs.next()){
                match.add(new Match(rs.getString(1)));
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
        return match;
    }
    public Match getScore(ConnectP cop,String team,String ideq){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(maty),id_equipe from match where act = 'tire' and id_match = '"+team+"' and id_equipe='" + ideq + "' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getReboundDef(ConnectP cop,String team,String ideq){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'rebound' and maty = 0 and id_match = '"+team+"' and id_equipe='" + ideq +"' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getReboundOf(ConnectP cop,String team,String ideq){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'rebound' and maty = 1 and id_match = '"+team+"' and id_equipe='" + ideq + "'group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getPasseDec(ConnectP cop,String team,String ideq){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'passe' and id_match = '"+team+"' and id_equipe='" + ideq+ "'group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Vector<String> getTwoTeams(ConnectP cop,String team){
        Vector <String> match = new Vector <String>();
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select id_equipe from match where id_match='" + team + "'group by id_equipe");
            while(rs.next()){
                match.add(rs.getString(1));
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
        return match;
    }
    public String getEqName(ConnectP cop,String eq){
        String match = "";
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select nom from equipe where id_equipe='" + eq + "'");
            while(rs.next()){
                match = rs.getString(1);
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
        return match;
    }
    public Vector<Joueur> getJoueurNitifitra(ConnectP cop,String match){
        Vector<Joueur> j = new Vector<Joueur>();
        try{
            System.out.println("select count(act),id_joueur from match where id_match='" + match + "' and act='tire' group by id_joueur");
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_joueur from match where id_match='" + match + "' and act='tire' group by id_joueur");
            while(rs.next()){
                j.add(new Joueur(rs.getInt(1),rs.getString(2)));
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
        return j;
    }
    public double pourcentage(double un,double deux){
        double pour = (un/deux)*100;
        System.out.println(pour+"pourcentage");
        return pour;
    }
    public String getJoueurName(ConnectP cop, String id){
        String j = "tsisy";
        try{
            System.out.println("select nom from joueur where id_joueur='" + id +"'");
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select nom from joueur where id_joueur='" + id +"'");
            while(rs.next()){
                j = rs.getString(1);
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
        return j;
    }
    public Joueur getMeilleurTireur(ConnectP cop,String match){
        Vector<Joueur> j = this.getJoueurNitifitra(cop,match);
        Joueur[] joueur = new Joueur[j.size()];
        Joueur meilleur = new Joueur();
        for(int i = 0;i<j.size();i++){
            int ok = j.get(i).getNbTirMaty(cop,match);
            System.out.println(j.get(i).getId_joueur() + "lol ++");
            int ok1 = j.get(i).getNbtir();
            System.out.println(ok1 + "lol +++");
            
            double ok2 = Double.valueOf(ok);
            System.out.println(ok2 + "valeur double");
            double ok21 = Double.valueOf(ok1);
            double p = this.pourcentage(ok2,ok21);
            joueur[i] = new Joueur(p,j.get(i).getId_joueur());
        }
        meilleur = joueur[0];
        //System.out.println("isan'ny joueur " + joueur.length);
        for(int i = 0;i<joueur.length;i++){
            if(joueur[i].getPour()>meilleur.getPour()&&joueur[i].getNbTirMaty(cop,match)>meilleur.getNbTirMaty(cop,match)){
                System.out.println(meilleur.getId_joueur() + " taloha = " + meilleur.getNbTirMaty(cop,match));
                System.out.println(joueur[i].getId_joueur() + " vaovao = " + joueur[i].getNbTirMaty(cop,match));
                meilleur = joueur[i];
            }
        } 
        return meilleur; 
    }
    public Joueur getPireTireur(ConnectP cop,String match){
        Vector<Joueur> j = this.getJoueurNitifitra(cop,match);
        Joueur[] joueur = new Joueur[j.size()];
        Joueur pire = new Joueur();
        for(int i = 0;i<j.size();i++){
            int ok = j.get(i).getNbTirMaty(cop,match);
            int ok1 = j.get(i).getNbtir();
            double p = this.pourcentage(ok,ok1);
            joueur[i] = new Joueur(p,j.get(i).getId_joueur());
        }
        pire = joueur[0];
        for(int i = 0;i<joueur.length;i++){
            if(joueur[i].getPour()<pire.getPour()){
                pire = joueur[i];
            }
        } 
        return pire; 
    }
    public int getTotalMatchDuration(ConnectP cop, String idm){
        int duration = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match = '"+ idm +"'");
            while(rs.next()){
                duration = rs.getInt(1);
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
        return duration;
    }
    public int getTeamPossessionDuration(ConnectP cop,String idt,String idm){
        int duration = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match = '"+ idm +"' and id_equipe = '" + idt + "'");
            while(rs.next()){
                duration = rs.getInt(1);
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
        return duration;
    }
    public double getPourcentageTeam(ConnectP cop,String idt,String idm){
        double pour = 0;
        int dureetotal = this.getTotalMatchDuration(cop,idm);
        int dureeteam = this.getTeamPossessionDuration(cop,idt,idm);
        if(dureeteam!=0){
            pour = ((double)dureeteam/(double)dureetotal)*100;
        }
        return pour;
    }
    public Match getScoreQuart(ConnectP cop,String team,String ideq,String quart){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(maty),id_equipe from match where act = 'tire' and id_match = '"+team+"' and id_equipe='" + ideq + "' and quart='" + quart + "' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getReboundDefQuart(ConnectP cop,String team,String ideq,String quart){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'rebound' and maty = 0 and id_match = '"+team+"' and id_equipe='" + ideq +"'and quart='" + quart + "' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getReboundOfQuart(ConnectP cop,String team,String ideq,String quart){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'rebound' and maty = 1 and id_match = '"+team+"' and id_equipe='" + ideq + "' and quart='" + quart + "' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Match getPasseDecQuart(ConnectP cop,String team,String ideq,String quart){
        Match match = new Match(0,ideq);
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where act = 'passe' and id_match = '"+team+"' and id_equipe='" + ideq+ "' and quart='" + quart + "' group by id_equipe");
            while(rs.next()){
                match = new Match(rs.getInt(1),rs.getString(2));
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
        return match;
    }
    public Vector<Joueur> getJoueurNitifitraQuart(ConnectP cop,String match,String quart){
        Vector<Joueur> j = new Vector<Joueur>();
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select count(act),id_equipe from match where id_match='" + match + "' and act='tire' and quart='" + quart + "' group by id_equipe");
            while(rs.next()){
                j.add(new Joueur(rs.getInt(1),rs.getString(2)));
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
        return j;
    }
    public int getTotalMatchDurationQuart(ConnectP cop, String idm,String quart){
        int duration = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match = '"+ idm +"' and quart='" + quart + "'");
            while(rs.next()){
                duration = rs.getInt(1);
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
        return duration;
    }
    public int getTeamPossessionDurationQuart(ConnectP cop,String idt,String idm,String quart){
        int duration = 0;
        try{
            Connection tempcon = cop.getConnection();
            Statement stmt = tempcon.createStatement();  
            ResultSet rs = stmt.executeQuery("select sum(duree) from temps where id_match = '"+ idm +"' and id_equipe = '" + idt + "' and quart='" + quart + "'");
            while(rs.next()){
                duration = rs.getInt(1);
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
        return duration;
    }
    public double getPourcentageTeamQuart(ConnectP cop,String idt,String idm,String quart){
        double pour = 0;
        int dureetotal = this.getTotalMatchDurationQuart(cop,idm,quart);
        int dureeteam = this.getTeamPossessionDurationQuart(cop,idt,idm,quart);
        if(dureeteam!=0){
            pour = ((double)dureeteam/(double)dureetotal)*100;
        }
        return pour;
    }
    public double pourcentage(int actu,int total){
        double un = Double.valueOf(actu);
        double deux = Double.valueOf(total);
        double pour = (un/deux)*100;
        return pour;
    }
}