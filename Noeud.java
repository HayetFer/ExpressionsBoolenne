public class Noeud {
    public Object cont;
    public Noeud gauche;
    public Noeud droite;
    public Noeud(Object e){
        cont = e;
        gauche = null;
        droite = null;
    }
    public void setContenu(Object e){

    }
    public boolean isFeuille(){
        return(gauche==null && droite ==null);
    }
    public void setGauche(Noeud g){
        gauche = g;
    }
    public void setDroite(Noeud d){
        droite = d;
    }
    public Noeud GetGauche(){
        return gauche;
    }
    public Noeud GetDroite(){
        return droite;
    }
    public Object getContenu(){
        return cont;
    }
    public String toString(){
        return cont.toString();
    }
}
