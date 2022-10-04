import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MesureTriFusion {

  /* Affiche un tableau d'entiers
   */
  public static void afficher(int [] t){
    System.out.print("[");
    for(int k=0;k<t.length;k++)
      System.out.print(" "+t[k]+" ");
    System.out.println("]");
  }

  public static void pireCas() {
    //Vérification de l'implémentation des tris
    int [] t = new int[10];
    int [] t2 = new int[10];
    for(int k=0;k<t.length;k++){ //initialisation aléatoire
      t[k]=(int) (Math.random()*100);
      t2[k]=t[k];
    }
    System.out.print("Avant le tri : "); afficher(t);
    Tri.triInsertion(t);
    System.out.print("Après le tri par insertion: "); afficher(t);
    Tri.triFusion(t2);
    System.out.print("Après le tri fusion:        "); afficher(t2);

    //Analyse du temps d'exécution

    //A FAIRE : adapter les 3 valeurs suivantes pour avoir des mesures significatives
    // surtout la valeurs de la variable taille_fin
    int taille_init = 10000;
    int taille_fin  = 5000000;
    int nbrMesures = 100;

    int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);

    List<Integer> tab_tailles = new ArrayList<Integer>();
    List<Integer> tab_temps = new ArrayList<Integer>();
    for(int n = taille_init; n<taille_fin; n=n+taille_incr){
      tab_tailles.add(n); //on sauvegarde la taille
      t = new int[n];

      //A FAIRE : initialisation de t
      // pire cas
      for(int k=0;k<t.length;k++){
        t[k]=t.length-k;
      }

      long date1 = System.currentTimeMillis(); //on lance le chrono
      Tri.triFusion(t); //on trie le tableau
      long date2 = System.currentTimeMillis(); //on arrête le chrono
      tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
      System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
    }

    // regression linéraire entre tab_temps et tab_tailles

      double moyenne_tailles = 0;
      double moyenne_temps = 0;
      for(int i=0; i<tab_tailles.size(); i++){
        moyenne_tailles += tab_tailles.get(i);
        moyenne_temps += tab_temps.get(i);
      }
      moyenne_tailles /= tab_tailles.size();
      moyenne_temps /= tab_temps.size();

      double a = 0;
      double b = 0;
      for(int i=0; i<tab_tailles.size(); i++){
        a += (tab_tailles.get(i) - moyenne_tailles) * (tab_temps.get(i) - moyenne_temps);
        b += (tab_tailles.get(i) - moyenne_tailles) * (tab_tailles.get(i) - moyenne_tailles);
      }
      a /= b;
      b = moyenne_temps - a * moyenne_tailles;

      System.out.println("Temps = "+a+" * taille + "+b);



    // Affichage des mesures effectuées
    Graph g = new Graph(tab_tailles,tab_temps);
    g.display();
    g.addLine(a);


  }

  public static void meilleurCas() {
    int [] t = new int[10];

    //Analyse du temps d'exécution

    //A FAIRE : adapter les 3 valeurs suivantes pour avoir des mesures significatives
    // surtout la valeurs de la variable taille_fin
    int taille_init = 10000;
    int taille_fin  = 5000000;
    int nbrMesures = 100;

    int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);

    List<Integer> tab_tailles = new ArrayList<Integer>();
    List<Integer> tab_temps = new ArrayList<Integer>();
    for(int n = taille_init; n<taille_fin; n=n+taille_incr){
      tab_tailles.add(n); //on sauvegarde la taille
      t = new int[n];

      //A FAIRE : initialisation de t
      // meilleur cas
      for(int k=0;k<t.length;k++){
        t[k]=k;
      }

      long date1 = System.currentTimeMillis(); //on lance le chrono
      Tri.triFusion(t); //on trie le tableau
      long date2 = System.currentTimeMillis(); //on arrête le chrono
      tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
      System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
    }

    // regression linéraire entre tab_temps et tab_tailles

    double moyenne_tailles = 0;
    double moyenne_temps = 0;
    for(int i=0; i<tab_tailles.size(); i++){
      moyenne_tailles += tab_tailles.get(i);
      moyenne_temps += tab_temps.get(i);
    }
    moyenne_tailles /= tab_tailles.size();
    moyenne_temps /= tab_temps.size();

    double a = 0;
    double b = 0;
    for(int i=0; i<tab_tailles.size(); i++){
      a += (tab_tailles.get(i) - moyenne_tailles) * (tab_temps.get(i) - moyenne_temps);
      b += (tab_tailles.get(i) - moyenne_tailles) * (tab_tailles.get(i) - moyenne_tailles);
    }
    a /= b;
    b = moyenne_temps - a * moyenne_tailles;

    System.out.println("Temps = "+a+" * taille + "+b);



    // Affichage des mesures effectuées
    Graph g = new Graph(tab_tailles,tab_temps);
    g.display();
    g.addLine(a);
  }

  public static void casRandom() {
    int [] t = new int[10];

    //Analyse du temps d'exécution

    //A FAIRE : adapter les 3 valeurs suivantes pour avoir des mesures significatives
    // surtout la valeurs de la variable taille_fin
    int taille_init = 10000;
    int taille_fin  = 5000000;
    int nbrMesures = 100;

    int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);

    List<Integer> tab_tailles = new ArrayList<Integer>();
    List<Integer> tab_temps = new ArrayList<Integer>();
    for(int n = taille_init; n<taille_fin; n=n+taille_incr){
      tab_tailles.add(n); //on sauvegarde la taille
      t = new int[n];

      //A FAIRE : initialisation de t
      // cas random
      for(int k=0;k<t.length;k++){
        t[k]=(int)(Math.random()*t.length);
      }

      long date1 = System.currentTimeMillis(); //on lance le chrono
      Tri.triFusion(t); //on trie le tableau
      long date2 = System.currentTimeMillis(); //on arrête le chrono
      tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
      System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
    }

    // Régression logarithmique entre tab_temps et tab_tailles

    double moyenne_tailles = 0;
    double moyenne_temps = 0;
    for(int i=0; i<tab_tailles.size(); i++){
      moyenne_tailles += Math.log(tab_tailles.get(i));
      moyenne_temps += Math.log(tab_temps.get(i));
    }
    moyenne_tailles /= tab_tailles.size();
    moyenne_temps /= tab_temps.size();

    double a = 0;
    double b = 0;
    for(int i=0; i<tab_tailles.size(); i++){
      a += (Math.log(tab_tailles.get(i)) - moyenne_tailles) * (Math.log(tab_temps.get(i)) - moyenne_temps);
      b += (Math.log(tab_tailles.get(i)) - moyenne_tailles) * (Math.log(tab_tailles.get(i)) - moyenne_tailles);
    }
    a /= b;
    b = moyenne_temps - a * moyenne_tailles;

    System.out.println("Temps = "+a+" * log(taille) + "+b);

    a = Math.exp(a);
    b = Math.exp(b);

    System.out.println("Temps = "+a+" * taille^"+b);

    // Affichage des mesures effectuées
    Graph g = new Graph(tab_tailles,tab_temps);
    g.display();
    g.addnLogn(b);
  }

  public static void main(String[] args) {
//    pireCas();
//    meilleurCas();
    casRandom();
//    //Vérification de l'implémentation des tris
//    int [] t = new int[10];
//    int [] t2 = new int[10];
//    for(int k=0;k<t.length;k++){ //initialisation aléatoire
//      t[k]=(int) (Math.random()*100);
//      t2[k]=t[k];
//    }
//    System.out.print("Avant le tri : "); afficher(t);
//    Tri.triInsertion(t);
//    System.out.print("Après le tri par insertion: "); afficher(t);
//    Tri.triFusion(t2);
//    System.out.print("Après le tri fusion:        "); afficher(t2);
//
//    //Analyse du temps d'exécution
//
//    //A FAIRE : adapter les 3 valeurs suivantes pour avoir des mesures significatives
//    // surtout la valeurs de la variable taille_fin
//    int taille_init = 10000;
//    int taille_fin  = 5000000;
//    int nbrMesures = 100;
//
//    int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);
//
//    List<Integer> tab_tailles = new ArrayList<Integer>();
//    List<Integer> tab_temps = new ArrayList<Integer>();
//    for(int n = taille_init; n<taille_fin; n=n+taille_incr){
//      tab_tailles.add(n); //on sauvegarde la taille
//      t = new int[n];
//
//      //A FAIRE : initialisation de t
//      // pire cas
//      for(int k=0;k<t.length;k++){
//        t[k]=t.length-k;
//      }
//
//      long date1 = System.currentTimeMillis(); //on lance le chrono
//      Tri.triFusion(t); //on trie le tableau
//      long date2 = System.currentTimeMillis(); //on arrête le chrono
//      tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
//      System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
//    }
//
//    // regression linéraire entre tab_temps et tab_tailles
//    {
//      double moyenne_tailles = 0;
//      double moyenne_temps = 0;
//      for(int i=0; i<tab_tailles.size(); i++){
//        moyenne_tailles += tab_tailles.get(i);
//        moyenne_temps += tab_temps.get(i);
//      }
//      moyenne_tailles /= tab_tailles.size();
//      moyenne_temps /= tab_temps.size();
//
//      double a = 0;
//      double b = 0;
//      for(int i=0; i<tab_tailles.size(); i++){
//        a += (tab_tailles.get(i) - moyenne_tailles) * (tab_temps.get(i) - moyenne_temps);
//        b += (tab_tailles.get(i) - moyenne_tailles) * (tab_tailles.get(i) - moyenne_tailles);
//      }
//      a /= b;
//      b = moyenne_temps - a * moyenne_tailles;
//
//      System.out.println("Temps = "+a+" * taille + "+b);
//    }
//
//
//    // Affichage des mesures effectuées
//    Graph g = new Graph(tab_tailles,tab_temps);
//    g.display();
//
//
//    // Régression manuelle
//    double a=0;
//    Scanner in = new Scanner(System.in);
//    System.out.println("------------------------------------------");
//    System.out.println("Attention ! les nombres décimaux doivent utiliser une virgule (et pas un point) !");
//    System.out.println("------------------------------------------");
//    do {
//      System.out.print("Entrez le coefficient de la droite/parabole/nLogn : ");
//      if (in.hasNextDouble()) {
//        a = in.nextDouble();
//        g.addLine(a);
//        // g.addQuadratic(a);
//        // g.addnLogn(a);
//      }
//      else
//        System.out.println(in.next()+" n'a pas été reconnu comme un double.");
//    } while(a!=-1);
//    in.close();
  }
}
