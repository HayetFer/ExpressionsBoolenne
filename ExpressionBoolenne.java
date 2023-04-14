import java.util.HashMap;

public class ExpressionBoolenne {
    static final int k=5;
    private Noeud racine;
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
    public boolean estFeuille(){
        return (racine.GetDroite()==null && racine.GetGauche()==null);
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
    public ExpressionBoolenne negation(){
        try {
            Noeud nouv= new Noeud("¬");
            nouv.setDroite(racine);
            racine=nouv;

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
                return e[(char) courrant.getContenu()];
            }
            else{
                if(courrant.getContenu()=="¬"){
                    if(courrant.GetGauche()==null){
                        return !evaluer(e,courrant.GetDroite());
                    }
                    else if(courrant.GetDroite()==null){
                        return !evaluer(e,courrant.GetGauche());
                    }
                }
                else if(courrant.getContenu()=="∨"){
                    return (evaluer(e,courrant.GetGauche())||evaluer(e,courrant.GetDroite()));
                }
                else {
                    return (evaluer(e,courrant.GetGauche())&&evaluer(e,courrant.GetDroite()));
                }
            }
            return false;
        }


    public static void main(String[] args) throws Exception {
        ExpressionBoolenne a = new ExpressionBoolenne('a');
        ExpressionBoolenne b = new ExpressionBoolenne('b');
        ExpressionBoolenne c = new ExpressionBoolenne('c');
        ExpressionBoolenne d = new ExpressionBoolenne('d');
        boolean [] test = new boolean[122];
        for(int i = 97;i<97+k-1;i++){
            test[i]=false;
        }
        test['d']=true;
        //test['a']=true;
        test['b']=true;
        d.conjonction(c);
        System.out.println(d.evaluer(test));
        System.out.println(d.affichage());
        d.negation();
        System.out.println(d.evaluer(test));
        System.out.println(d.affichage());
        d.disjonction(a);
        System.out.println(d.evaluer(test));
        System.out.println(d.affichage());
        d.conjonction(b);
        System.out.println(d.evaluer(test));
        System.out.println(d.affichage());
        c.conjonction(d);
        System.out.println(c.evaluer(test));
        System.out.println(c.affichage());
        c.negation();
        System.out.println(c.evaluer(test));
        System.out.println(c.affichage());
        c.disjonction(b);
        System.out.println(c.evaluer(test));
        System.out.println(c.affichage());
        c.conjonction(a);
        System.out.println(c.evaluer(test));
        System.out.println(c.affichage());

    }

}
