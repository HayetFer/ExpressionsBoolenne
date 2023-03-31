import java.util.HashMap;

public class ExpressionBoolenne {
    final int k=5;
    private Noeud racine;
    private HashMap<Noeud, Boolean> bools;
    public ExpressionBoolenne(){
        racine=null;
    }
    public ExpressionBoolenne(char c){
        if((c>='a' && c<='z') || (c>='A' && c<='Z')){
            racine=new Noeud(c);
        }
        else{
            System.out.println("Erreur de char");
        }
    }
    public boolean estVide(){
        return racine==null;
    }

    public ExpressionBoolenne disjonction(ExpressionBoolenne e){
        try{
            Noeud nouvelleRacine = new Noeud("∧");
            nouvelleRacine.setDroite(racine);
            nouvelleRacine.setGauche(e.racine);
            racine = nouvelleRacine;
            return this;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }
    public ExpressionBoolenne conjonction(ExpressionBoolenne e){
        try {
            Noeud nouvelleRacine = new Noeud("∨");
            nouvelleRacine.setDroite(racine);
            nouvelleRacine.setGauche(e.racine);
            racine = nouvelleRacine;
            return this;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }
    public ExpressionBoolenne negation(ExpressionBoolenne e){
        try {
            Noeud nouvelleRacine = new Noeud("¬");
            nouvelleRacine.setDroite(racine);
            nouvelleRacine.setGauche(e.racine);
            racine = nouvelleRacine;
            return this;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }

    public String affichage (){
        return affichage(racine);
    }
        public String affichage(Noeud courrant) {
            if (courrant == null) {
                return "";
            }

            if (courrant.isFeuille()) {
                return (" " + courrant + " ");
            } else if (affichage(courrant.GetGauche()).isEmpty()) {
                return  " ( " + courrant +  affichage(courrant.GetDroite()) + " ) ";
            } else if (affichage(courrant.GetDroite()).isEmpty()) {
                return " ( " + affichage(courrant.GetGauche())  + courrant + " ) ";
            } else {
                return  " ( " + (affichage(courrant.GetGauche())  + courrant
                          + affichage(courrant.GetDroite())) + " ) " ;
            }
        }

       public boolean evaluer(boolean[] e){
            return evaluer(e, racine);
        }
        public boolean evaluer(boolean[] e, Noeud courrant) {

            if (courrant.isFeuille()) {
                return bools.get(courrant);
            }
            else{
                if(courrant.getContenu()="¬"){
                    
                }
            }
        }

    public static void main(String[] args) {
        ExpressionBoolenne a = new ExpressionBoolenne('a');
        ExpressionBoolenne b = new ExpressionBoolenne('b');
        ExpressionBoolenne c = new ExpressionBoolenne('d');
        ExpressionBoolenne d = new ExpressionBoolenne('e');
        d.conjonction(c);
        System.out.println(d.affichage());
        d.negation(c);
        System.out.println(d.affichage());
        d.conjonction(b);
        System.out.println(d.affichage());
    }

}
