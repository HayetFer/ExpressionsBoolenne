public class ExpressionBoolenne {
    //nombre ma de feuilles allouées
    static final int k=5;
    private Noeud racine;
    public ExpressionBoolenne(){
        racine=null;
    }
    //Une Expression booléenne à sa création est seulement vraie ou fausse
    public ExpressionBoolenne(char c){
        if((c>='a' && c<='z')){
            racine=new Noeud(c);
        }
        else{
            System.out.println("Erreur de char");
        }
    }
    //Constructeur par copie
    public ExpressionBoolenne(ExpressionBoolenne e) {
        if (e.estVide()) {
            racine = null;
        } else {
            racine = copierNoeud(e.racine);
        }
    }
    
    private Noeud copierNoeud(Noeud e) {
        if (e == null) {
            return null;
        }
        Noeud copy = new Noeud(e.getContenu());
        copy.setGauche(copierNoeud(e.GetGauche()));
        copy.setDroite(copierNoeud(e.GetDroite()));
        return copy;
    }
    
    public boolean estVide(){
        return racine==null;
    }

    public boolean estFeuille(){
        return (racine.GetDroite()==null && racine.GetGauche()==null);
    }
    //On crée un noeud, puis le mettons en racine avec l'expression booléene actuelle à droite et e à gauche
    public ExpressionBoolenne disjonction(ExpressionBoolenne e){
        try{
            Noeud nouvelleRacine = new Noeud("∧");
            ExpressionBoolenne a = new ExpressionBoolenne(this);
            ExpressionBoolenne b = new ExpressionBoolenne(e);
            nouvelleRacine.setDroite(a.racine);
            nouvelleRacine.setGauche(b.racine);
            a.racine = nouvelleRacine;
            return a;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }
        //On crée un noeud, puis le mettons en racine avec l'expression booléene actuelle à droite et e à gauche
    public ExpressionBoolenne conjonction(ExpressionBoolenne e){
        try {
            Noeud nouvelleRacine = new Noeud("∨");
            ExpressionBoolenne a = new ExpressionBoolenne(this);
            ExpressionBoolenne b = new ExpressionBoolenne(e);
            nouvelleRacine.setDroite(a.racine);
            nouvelleRacine.setGauche(b.racine);
            a.racine = nouvelleRacine;
            return a;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }
    //On crée une racine et on met l'expression booléenne à droite
    public ExpressionBoolenne negation(){
        try {
            Noeud nouv= new Noeud("¬");
            ExpressionBoolenne a = new ExpressionBoolenne(this);
            nouv.setDroite(racine);
            a.racine=nouv;
            return a;
        }
        catch(NullPointerException exception){
            System.out.println("Erreur Expression nulle");
        }
        return null;
    }
    //afficher avec les parenthèses correctes
    public String affichage (){
        return affichage(racine);
    }
    public String affichage(Noeud courrant) {
        if (courrant == null) {
            return "";
        }
        if (courrant.isFeuille()) {
            return (" " + courrant + " ");
        }
        if(courrant==racine){
            if (affichage(courrant.GetGauche()).isEmpty()) {
                return  "  " + courrant +  affichage(courrant.GetDroite()) + "  ";

                } else if (affichage(courrant.GetDroite()).isEmpty()) {
                 return "  " + affichage(courrant.GetGauche())  + courrant + "  ";
             } else {
                 return  "  " + (affichage(courrant.GetGauche())  + courrant
                           + affichage(courrant.GetDroite())) + "  " ;
             }
          }
           if (affichage(courrant.GetGauche()).isEmpty()) {
              return  " ( " + courrant +  affichage(courrant.GetDroite()) + " ) ";
          } else if (affichage(courrant.GetDroite()).isEmpty()) {
              return " ( " + affichage(courrant.GetGauche())  + courrant + " ) ";
          } else {
              return  " ( " + (affichage(courrant.GetGauche())  + courrant + affichage(courrant.GetDroite())) + " ) " ;
         }
     }
        //On utilise les !, || et && de java pour retourner la bonne solution
    public boolean evaluer(boolean[] e){
            return evaluer(e, racine);
        }
        //on return l'évaluation entre les feuilles puis entre expressions booléennes
    public boolean evaluer(boolean[] e, Noeud courrant) {
        if (courrant.isFeuille()) {
            //On initialise ça dans le main grâce en joignant les lettres à leur numéro en table ascii
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
        //On crée le tableau et on initialise les indices avec la table ascii
        for(int i = 97;i<97+k-1;i++){
            test[i]=false;
        }
        test['d']=true;
        //test['a']=true;
        test['b']=true;
        ExpressionBoolenne e = a.conjonction(d);
        System.out.println(e.evaluer(test));
        System.out.println(e.affichage());
        ExpressionBoolenne f = a.disjonction(e);
        System.out.println(f.evaluer(test));
        System.out.println(f.affichage());
        ExpressionBoolenne g = f.disjonction(c);
        System.out.println(g.evaluer(test));
        System.out.println(g.affichage());
        ExpressionBoolenne i = c.disjonction(f);
        System.out.println(i.evaluer(test));
        System.out.println(i.affichage());
        ExpressionBoolenne h = g.negation();
        System.out.println(h.evaluer(test));
        System.out.println(h.affichage());
        ExpressionBoolenne j = h.conjonction(g);
        System.out.println(j.evaluer(test));
        System.out.println(j.affichage());
        ExpressionBoolenne k = j.negation();
        System.out.println(k.evaluer(test));
        System.out.println(k.affichage());
        }
}
