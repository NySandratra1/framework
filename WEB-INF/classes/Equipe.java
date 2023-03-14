package contenue;
public class Equipe extends Zavatra{
    String Id_equipe;
    String Nom;
    public Equipe(){}
    public Equipe(String i,String n){
        this.setId_equipe(i);
        this.setNom(n);
    }
    public void setId_equipe(String ie){
        this.Id_equipe = ie;
    }
    public void setNom(String n){
        this.Nom = n;
    }
    public String getNom(){
        return this.Nom;
    }
    public String getId_equipe(){
        return this.Id_equipe ;
    }
}