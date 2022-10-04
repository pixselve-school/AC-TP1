import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


interface GenerateTab {
    int[] run(int taille);
}

public class MesureTriFusion {
  public static void timeAndGraph(GenerateTab generateTab){
    int taille_init = 10000;
    int taille_fin  = 5000000;
    int nbrMesures = 100;

    int taille_incr = (taille_fin-taille_init)/(nbrMesures-1);

    List<Integer> tab_tailles = new ArrayList<Integer>();
    List<Integer> tab_temps = new ArrayList<Integer>();
    for(int n = taille_init; n<taille_fin; n=n+taille_incr){
      tab_tailles.add(n); //on sauvegarde la taille
      int[] t = generateTab.run(n); // on génère le tableau
      long date1 = System.currentTimeMillis(); //on lance le chrono
      Tri.triFusion(t); //on trie le tableau
      long date2 = System.currentTimeMillis(); //on arrête le chrono
      tab_temps.add((int)(date2 - date1)); //on sauvegarde le temps
      System.out.println("Temps de calcul pour n="+n+" : "+ tab_temps.get(tab_temps.size()-1)+" millisecondes.");
    }

    Graph g = new Graph(tab_tailles,tab_temps);
    g.display();

    // Régression manuelle
    double a=0;
    Scanner in = new Scanner(System.in);
    System.out.println("------------------------------------------");
    System.out.println("Attention ! les nombres décimaux doivent utiliser une virgule (et pas un point) !");
    System.out.println("------------------------------------------");
    do {
      System.out.print("Entrez le coefficient de la droite/parabole/nLogn : ");
      if (in.hasNextDouble()) {
        a = in.nextDouble();
//        g.addLine(a);
        // g.addQuadratic(a);
         g.addnLogn(a);
      }
      else
        System.out.println(in.next()+" n'a pas été reconnu comme un double.");
    } while(a!=-1);
    in.close();
  }

  public static void decroissant(){
    timeAndGraph((taille) -> {
      int[] t = new int[taille];
      for(int i=0; i<taille; i++){
        t[i] = taille-i;
      }
      return t;
    });
  }
  public static void croissant(){
    timeAndGraph((taille) -> {
      int[] t = new int[taille];
      for(int i=0; i<taille; i++){
        t[i] = i;
      }
      return t;
    });
  }

  public static void random(){
    timeAndGraph((taille) -> {
      int[] t = new int[taille];
      for(int i=0; i<taille; i++){
        t[i] = (int)(Math.random()*taille);
      }
      return t;
    });
  }

  public static void main(String[] args) {
//    decroissant();
//    croissant();
    random();
  }
}
