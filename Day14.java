import java.util.ArrayList;

public class Day14 {
	public static void main(String[] args) {
		ArrayList<Integer> recipies = new ArrayList<Integer>();
		int elfA = 0;
		int elfB = 1;
		recipies.add(3);
		recipies.add(7);
		String sequence = "793031";
		while(true){
			//Add all thos recipies
			String toAdd = "" + (recipies.get(elfA) + recipies.get(elfB));
			for(int j = 0; j < toAdd.length(); j++) {
				recipies.add(Integer.parseInt(""+toAdd.charAt(j)));
				String curSequence = "";
				for(int k = sequence.length(); k > 0; k--) {
					if(recipies.size() >= k) {
						curSequence += "" + recipies.get(recipies.size()-k);
					}
				}
				
				if(curSequence.equals(sequence)) {
					System.out.println(recipies.size()-sequence.length());
					break;
				}
			}
			
			//move the elves
			int cur = recipies.get(elfA);
			elfA++;
			for(int j = 0; j < cur; j++) {
				if(elfA >= recipies.size()) {
					elfA = 0;
				}
				elfA++;
				if(elfA >= recipies.size()) {
					elfA = 0;
				}
			}
			
			cur = recipies.get(elfB);
			elfB++;
			for(int j = 0; j < cur; j++) {
				if(elfB >= recipies.size()) {
					elfB = 0;
				}
				elfB++;
				if(elfB >= recipies.size()) {
					elfB = 0;
				}
			}
			
			//printRecipies(recipies);
		}
		
		
		
	}
	
	public static void printRecipies(ArrayList<Integer> recipies){
		for(Integer i : recipies) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}
