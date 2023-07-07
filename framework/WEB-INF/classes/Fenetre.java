package affiche;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import listen.*;
import contenue.*;
import steps.*;
import evenement.*;

public class Fenetre extends JFrame{
    Joueur joueur;
    Joueur newjoueur;
    Quart q = new Quart(this);
    int action = 0;
    int newaction = 0;
    int tempsinit = 0;
    int tempsnext = 0;
    int play = 0;
    Match m = new Match();
    public void setPlay(int i){
        this.play = i;
    }
    public int getPlay(){
        return this.play;        
    }
    public void setQuart(Quart q){
        this.q = q;
    }
    public Quart getQuart(){
        return this.q;
    }
    public void setTempsinit(int ti){
        this.tempsinit = ti;
    }
    public int getTempsinit(){
        return this.tempsinit;
    }
    public void setTempsnext(int tn){
        this.tempsnext = tn;
    }
    public int getTempsnext(){
        return this.tempsnext;
    }
    public void setMatch(Match m){
        this.m = m;
    }
    public Match getMatch(){
        return this.m; 
    }
    public void setJoueur(Joueur j){
        this.joueur = j;
    }
    public Joueur getJoueur(){
        return this.joueur;
    }
    public void setNewjoueur(Joueur j){
        this.newjoueur = j;
    }
    public Joueur getNewjoueur(){
        return this.newjoueur;
    }
    public void setNewaction(int na){
        this.newaction = na;
    }
    public int getNewaction(){
        return this.newaction;
    }
    public void setAction(int a){
        this.action = a;
    }
    public int getAction(){
        return this.action;
    }
    public JButton[] getButtons(Vector<Joueur> j){
        JButton[] bouton = new JButton[5];
        for(int i = 0;i<5;i++){
            bouton[i] = new JButton(j.get(i).getNom());
        }
        return bouton;
    }
    public Fenetre(ConnectP cop){
        this.setVisible(true);
        this.setLayout(null);
        this.setSize(700,500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getMatch().setPrefixe("MA");
        this.getMatch().setIdLength(5);
        this.getMatch().setFuncName("getseqmatch()");
        this.getMatch().setId_match(this.getMatch().construirePK(cop));
        //this.getMatch().setId_match("MA001");
        Joueur appelle = new Joueur();
        Vector<Joueur> ok = appelle.getTeamPlayers(cop,"EQU01");
        Vector<Joueur> ok1 = appelle.getTeamPlayers(cop,"EQU02");
        Match m = new Match(this.getMatch().getId_match(),"J0001","EQU01",null,"debut",0,"0");
        m.insert(cop);
        Match ma = new Match(this.getMatch().getId_match(),"J0006","EQU02",null,"debut",0,"0");
        ma.insert(cop);
        JButton[] b = this.getButtons(ok);
        JButton[] b1 = this.getButtons(ok1);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        for(int i = 0;i<b.length;i++){
            b[i] = new JButton(ok.get(i).getNom());
            b[i].setBounds(120,i*50,80,50);
            b[i].setFocusable(false);
            b[i].addMouseListener(new Mouse(this,ok.get(i),this.getQuart(),cop));
            p1.add(b[i]);
        }
        p1.setBounds(0,0,350,500);
        p1.setBackground(Color.blue);
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        for(int i = 0;i<b1.length;i++){
            b1[i] = new JButton(ok1.get(i).getNom());
            b1[i].setBounds(120,i*50,80,50);
            b1[i].setFocusable(false);
            b1[i].addMouseListener(new Mouse(this,ok1.get(i),this.getQuart(),cop));
            p2.add(b1[i]);
        }
        p2.setBounds(350,0,350,500);
        p2.setBackground(Color.green);
        this.add(p1);
        this.add(p2);
        System.out.println(this.getJoueur() + " ookokokokoko");
        this.addKeyListener(new Key(this,cop,this.getQuart()));
    }
}
