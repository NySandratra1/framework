package evenement;
public class Passe{
    String Id_joueur;
    String Id_equipe;
    int Decisive;
    public void setId_joueur(String ij){
        this.Id_joueur = ij;
    }
    public void setId_equipe(String ij){
        this.Id_equipe = ij;
    }
    public void setDecisive(int d){
        this.Decisive = d;
    }
    public String setId_joueur(){
        return this.Id_joueur;
    }
    public String setId_equipe(){
        return this.Id_equipe;
    }
    public int setDecisive(){
        return this.Decisive;
    }
}
