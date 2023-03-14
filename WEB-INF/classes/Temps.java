package evenement;
import contenue.Zavatra;
public class Temps extends Zavatra{
    String Id_match;
    String Id_joueur;
    String Id_equipe;
    String Quart;
    int Duree;
    public void setQuart(String q){
        this.Quart = q;
    }
    public String getQuart(){
        return this.Quart;
    }
    public void setId_match(String m){
        this.Id_match = m;
    }
    public void setId_joueur(String idj){
        this.Id_joueur = idj;
    }
    public void setId_equipe(String ide){
        this.Id_equipe = ide;
    }
    public void setDuree(int d){
        this.Duree = d;
    }
    public String getId_joueur(){
        return this.Id_joueur;
    }
    public String getId_equipe(){
        return this.Id_equipe;
    }
    public int getDuree(){
        return this.Duree;
    }
    public String getId_match(){
        return this.Id_match;
    }
    public Temps(String m,String idj,String ide,String q,int duree){
        this.setId_match(m);
        this.setId_joueur(idj);
        this.setId_equipe(ide);
        this.setQuart(q);
        this.setDuree(duree);
    }
}