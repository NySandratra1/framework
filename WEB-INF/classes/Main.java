package affiche;
import generale.*;
import contenue.*;
import steps.*;
import evenement.*;
import java.util.*;
public class Main{
    public static void main(String [] args){
        ConnectP cop = new ConnectP();
        cop.setUrl("jdbc:postgresql://localhost/nba");
        cop.setUser("postgres");
        cop.setPasswrd("1234");
        /*Equipe e = new Equipe();
        e.setPrefixe("EQU");
        e.setIdLength(5);
        e.setFuncName("getSeqEquipe()");
        e = new Equipe(e.construirePK(cop),"E2");
        e.insert(cop);
        Joueur j = new Joueur();
        j.setPrefixe("J");
        j.setIdLength(5);
        j.setFuncName("getSeqJoueur()");
        String[] ok = {"JA1","JA2","JA3","JA4","JA5"};
        for(int i = 0;i<ok.length;i++){
            Joueur jo = new Joueur(j.construirePK(cop),e.getId_equipe(),ok[i]);
            jo.insertJoueur(cop);
        }*/
        Match m = new Match();
        System.out.println(m.pourcentage(1,2));
        //Joueur j = m.getMeilleurTireur(cop,"MA035"); 
        //System.out.println(m.getJoueurName(cop,j.getId_joueur()));
        /*Thread t = new Thread(q);
        t.start();*/
        //String team = "MA001";
        //Vector<Match> score = m.getScore(cop,team);
        //System.out.print(score.get(0).getId_equipe());
        // Joueur appel = new Joueur();
        // Vector<Joueur> teams = appel.getTeamPlayers(cop,"EQU01");
        // System.out.println(teams);
        // double pour1 = m.getPourcentageTeam(cop,"EQU03","MA020");
        // double pour2 = m.getPourcentageTeam(cop,"EQU04","MA020");
        // System.out.println(pour1);
        //new Fenetre(cop);
    }
}