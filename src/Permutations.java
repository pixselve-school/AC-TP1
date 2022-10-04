import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public static void permutation(String target, String original,boolean afficher){
	    int i;
	    String target1, original1;
	    if (original.length() == 0 && afficher){
	        System.out.println(target);
	    }
		else {
			i = 0;
			while (i < original.length()){
			    target1 = target + original.substring(i,i+1);
			    original1 = original.substring(0,i) + original.substring(i+1,original.length());
			    permutation(target1,original1,afficher);
			    i = i + 1;
			}
		} 
	}
	public static void main(String[] args) {
		String x="";
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		List<Integer> tab_tailles = new ArrayList<Integer>();
		List<Integer> tab_temps = new ArrayList<Integer>();

		for(int n=1; n<12; n++){
			long t1 = System.currentTimeMillis();
			String y=alphabet.substring(0, n);
			permutation(x,y,false);
			long t2 = System.currentTimeMillis();

			System.out.println(n + ": " + (t2-t1) + "ms");

			tab_tailles.add(n);
			tab_temps.add((int) (t2-t1));
		}

		Graph g = new Graph(tab_tailles,tab_temps);
		g.display();
	}

}
