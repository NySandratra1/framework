package listen;
import java.awt.*;  
import java.awt.event.*;
import contenue.*;  
import affiche.*;
import evenement.*;
import steps.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class Mouse implements MouseListener{  
    Fenetre f;
    Joueur j;
    Quart quart;
    ConnectP c;

    public void setQuart(Quart q){
        this.quart = q;
    }
    public Quart getQuart(){
        return this.quart;
    }
    public void setFenetre(Fenetre f){
        this.f = f;
    } 
    public void setJoueur(Joueur j){
        this.j = j;
    }
    public Fenetre getFenetre(){
        return this.f;
    } 
    public Joueur getJoueur(){
        return this.j;
    }
    public void setConnectP(ConnectP c){
        this.c = c;
    }
    public ConnectP getConnectP(){
        return this.c;
    }
    public Mouse(Fenetre f,Joueur j,Quart quart,ConnectP co){  
        this.setFenetre(f);
        this.setJoueur(j);
        this.setQuart(quart);
        this.setConnectP(co);
    }  
    public void mouseClicked(MouseEvent e) {
        if(this.getFenetre().getPlay()==0){
            try{
                throw new Exception("Veuiller commencer le prochain carton");
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog((new JPanel()),ex.getMessage());
                return;
            }
        }
        else{
            this.getFenetre().setJoueur(this.getFenetre().getNewjoueur());
            this.getFenetre().setNewjoueur(this.getJoueur());
            this.getFenetre().setTempsinit(this.getFenetre().getTempsnext());
            this.getFenetre().setTempsnext(LocalDateTime.now().getSecond());
            if(this.getFenetre().getTempsinit()!=0&&this.getFenetre().getTempsnext()!=0){
                int duree = this.getFenetre().getTempsnext() - this.getFenetre().getTempsinit();
                Temps t = new Temps(this.getFenetre().getMatch().getId_match(),this.getFenetre().getJoueur().getId_joueur(),this.getFenetre().getJoueur().getId_equipe(),this.getQuart().getQuart()+"",duree);
                t.insert(this.getConnectP());
            }
        }
        // System.out.println(this.getFenetre().getTempsinit());
        // System.out.println(this.getFenetre().getTempsnext());
        // System.out.println(this.getFenetre().getJoueur().getNom());
        // System.out.println(this.getJoueur().getId_joueur());
    }  
    public void mouseEntered(MouseEvent e) {    
    }  
    public void mouseExited(MouseEvent e) {   
    }  
    public void mousePressed(MouseEvent e) {   
    }  
    public void mouseReleased(MouseEvent e) {   
    }  
}