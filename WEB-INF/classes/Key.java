package listen;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import contenue.*;
import affiche.*;
import evenement.*;
import steps.*;
import javax.swing.*;
import java.time.LocalDateTime;
public class Key implements KeyListener{
    Fenetre f;
    ConnectP cop;
    Quart q;
    
    public void setConnectP(ConnectP f){
        this.cop = f;
    }
    public ConnectP getConnectP(){
        return this.cop;
    }
    public void setFenetre(Fenetre f){
        this.f = f;
    }
    public Fenetre getFenetre(){
        return this.f;
    }
    public void setQuart(Quart q){
        this.q = q;
    }
    public Quart getQuart(){
        return this.q;
    }
    public Key(){}
    public Key(Fenetre f,ConnectP cop,Quart q){
        this.setFenetre(f);
        this.setConnectP(cop);
        this.setQuart(q);
    }
    public void passe() throws Exception{
        if(this.getFenetre().getPlay()==0){
                throw new Exception("Veuiller commencer le prochain quart-temps");
        }
        if(this.getFenetre().getJoueur()!=null||this.getFenetre().getNewjoueur()!=null){
            System.out.println(this.getFenetre().getNewjoueur().getNom() + " passe");
            this.getFenetre().setAction(this.getFenetre().getNewaction());
            this.getFenetre().setNewaction(1);
            if(this.getFenetre().getAction()==2&&this.getFenetre().getNewaction()==1){
                Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"rebound",0,this.getQuart().getQuart()+"");
                m.insert(this.getConnectP());
            }
        }
        else System.out.println("Aucun joueur selectionne");
    }
    public void tire() throws Exception{
        if(this.getFenetre().getPlay()==0){
                throw new Exception("Veuiller commencer le prochain quart-temps");
        }
        if(this.getFenetre().getAction()==2&&this.getFenetre().getNewaction()==1&&!this.getFenetre().getJoueur().getId_equipe().equals(this.getFenetre().getNewjoueur().getId_equipe())){
            throw new Exception("Irrealiste");
        }
        if(this.getFenetre().getJoueur()!=null||this.getFenetre().getNewjoueur()!=null){ //tire
            System.out.println(this.getFenetre().getNewjoueur().getNom() + " tire");
            //System.out.println(this.getFenetre().getAction());
            this.getFenetre().setAction(this.getFenetre().getNewaction());
            this.getFenetre().setNewaction(2);
            if(this.getFenetre().getAction()==2&&this.getFenetre().getNewaction()==2){
                Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"rebound",1,this.getQuart().getQuart()+"");
                m.insert(this.getConnectP());    
            }
            Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"tire",0,this.getQuart().getQuart()+"");
            m.insert(this.getConnectP());
        }
        else System.out.println("Aucun joueur selectionne");
    }
    public void tireMaty() throws Exception{
        if(this.getFenetre().getPlay()==0){
                throw new Exception("Veuiller commencer le prochain quart-temps");
        }
        if(this.getFenetre().getAction()==2&&this.getFenetre().getNewaction()==1&&!this.getFenetre().getJoueur().getId_equipe().equals(this.getFenetre().getNewjoueur().getId_equipe())){
            throw new Exception("Irrealiste");
        }
        if(this.getFenetre().getJoueur()!=null||this.getFenetre().getNewjoueur()!=null){ //tire maty
            System.out.println(this.getFenetre().getNewjoueur().getNom() + " tire maty");
            //System.out.println(this.getFenetre().getAction());
            this.getFenetre().setAction(this.getFenetre().getNewaction());
            this.getFenetre().setNewaction(3);
            if(this.getFenetre().getAction()==2&&this.getFenetre().getNewaction()==3){
                Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"rebound",1,this.getQuart().getQuart()+"");
                m.insert(this.getConnectP());
            }
            if(this.getFenetre().getAction()==1&&this.getFenetre().getNewaction()==3&&this.getFenetre().getNewjoueur().getId_equipe().equals(this.getFenetre().getJoueur().getId_equipe())){
                Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getJoueur().getId_joueur(),this.getFenetre().getJoueur().getId_equipe(),null,"passe",0,this.getQuart().getQuart()+""); 
                m.insert(this.getConnectP());
                Match m1 = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"tire",2,this.getQuart().getQuart()+"");
                m1.insert(this.getConnectP());
                if(this.getFenetre().getNewjoueur().getId_equipe().equals("EQU01")){
                    System.out.println("lol");
                    Joueur o = new Joueur("J0006","EQU02","JA1");
                    this.getFenetre().setAction(0);
                    this.getFenetre().setNewaction(0);
                    this.getFenetre().setJoueur(null);
                    this.getFenetre().setNewjoueur(o);
                    this.getFenetre().setTempsinit(this.getFenetre().getTempsnext());
                    this.getFenetre().setTempsnext(LocalDateTime.now().getSecond());
                    System.out.println(o.getNom() + " a le ballon");
                }
                else{
                    System.out.println("lol");
                    Joueur o = new Joueur("J0001","EQU01","J1");
                    this.getFenetre().setAction(0);
                    this.getFenetre().setNewaction(0);
                    this.getFenetre().setJoueur(null);
                    this.getFenetre().setNewjoueur(o);
                    this.getFenetre().setTempsinit(this.getFenetre().getTempsnext());
                    this.getFenetre().setTempsnext(LocalDateTime.now().getSecond());
                    System.out.println(o.getNom() + " a le ballon");
                }
            }
            else{
                Match m = new Match(this.getFenetre().getMatch().getId_match(),this.getFenetre().getNewjoueur().getId_joueur(),this.getFenetre().getNewjoueur().getId_equipe(),null,"tire",2,this.getQuart().getQuart()+"");
                m.insert(this.getConnectP());
                if(this.getFenetre().getNewjoueur().getId_equipe().equals("EQU01")){
                    System.out.println("lol");
                    Joueur o = new Joueur("J0006","EQU02","JA1"); 
                    this.getFenetre().setAction(0);
                    this.getFenetre().setNewaction(0);
                    this.getFenetre().setJoueur(null);
                    this.getFenetre().setNewjoueur(o);
                    this.getFenetre().setTempsinit(this.getFenetre().getTempsnext());
                    this.getFenetre().setTempsnext(LocalDateTime.now().getSecond());
                    System.out.println(o.getNom() + " a le ballon");
                }
                else{
                    System.out.println("lol");
                    Joueur o = new Joueur("J0001","EQU01","J1");
                    this.getFenetre().setAction(0);
                    this.getFenetre().setNewaction(0);
                    this.getFenetre().setJoueur(null);
                    this.getFenetre().setNewjoueur(o);
                    this.getFenetre().setTempsinit(this.getFenetre().getTempsnext());
                    this.getFenetre().setTempsnext(LocalDateTime.now().getSecond());
                    System.out.println(o.getNom() + " a le ballon");
                }
            }                
        }
        else System.out.println("Aucun joueur selectionne");
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_A){ //passe 
             try{
                this.passe();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog((new JPanel()),ex.getMessage());
            }  
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            try{
                this.tire();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog((new JPanel()),ex.getMessage());
            }            
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            try{
                this.tireMaty();
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog((new JPanel()),ex.getMessage());
            }           
        }
        if(e.getKeyCode()==KeyEvent.VK_P){
            //System.out.println(this.getQuart().getTemps());
            this.getFenetre().setPlay(1);
            Thread t = new Thread(this.getQuart());
            t.start();
        }
        //System.out.println(this.getFenetre().getMatch().getId_match());
    }
    public void keyReleased(KeyEvent e){

    }
    public void keyTyped(KeyEvent e){

    }
}