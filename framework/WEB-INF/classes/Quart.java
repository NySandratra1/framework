package evenement;
import affiche.*;
public class Quart implements Runnable{
    int quart = 1;
    int temps = 0;
    Fenetre f;
    public Quart(Fenetre f){
        this.setFenetre(f);
    }
    public void setFenetre(Fenetre f){
        this.f = f;
    }
    public Fenetre getFenetre(){
        return this.f;
    }
    public void setTemps(int i){
        this.temps = i;
    }
    public int getTemps(){
        return this.temps;
    }
    public void setQuart(int i){
        this.quart = i;
    }
    public int getQuart(){
        return this.quart;
    }
    public void run(){
        System.out.println("Debut du carton");
        while(true){    
            try{
                Thread.sleep(15000);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            this.quart++;
            this.getFenetre().setPlay(0);
            this.getFenetre().setTempsinit(0);
            this.getFenetre().setTempsnext(0);
            System.out.println(temps);
            break;
        }
    }
}