import java.util.ArrayList;

public class Day12 {
	
	public static int increase = 0;

	public static void main(String[] args) {
		long start = System.nanoTime();
		String[] input = getInput().split("\r\n");
		ArrayList<PlantRule> Rules = new ArrayList<PlantRule>();
		for(String s : input) {
			String[] split = s.split(" ");
			String state = split[0];
			char out = split[2].charAt(0);
			Rules.add(new PlantRule(state, out));
		}
		
		
		
		String init = "..##.#######...##.###...#..#.#.#..#.##.#.##....####..........#..#.######..####.#.#..###.##..##..#..#";
		ArrayList<Character> state = new ArrayList<Character>();
		final int buffer = 2000;
		for(int i = 0; i < buffer; i++) {
			state.add(0, '.');
		}
		for(int i = 0; i < init.length(); i++) {
			state.add(init.charAt(i));
		}
		for(int i = 0; i < buffer; i++) {
			state.add('.');
		}
		
		//state = buffer(state);
		ArrayList<ArrayList<Character>> states = new ArrayList<ArrayList<Character>>();
		states.add(state);
		for(int gen = 0; gen < 2000; gen++) {
			ArrayList<Character> newState = new ArrayList<Character>();
			newState.add('.');
			newState.add('.');
			for(int i = 2; i < state.size() - 2; i++) {
				char LL = state.get(i-2);
				char L = state.get(i-1);
				char C = state.get(i);
				char R = state.get(i+1);
				char RR = state.get(i+2);
				String surroundings = "" + LL + L + C + R + RR;
				boolean ruleFound = false;
				for(PlantRule p : Rules) {
					if(p.getState().equals(surroundings)) {
						newState.add(p.getOutput());
						ruleFound = true;
						break;
					}
				}
				if(!ruleFound) {
					newState.add('.');
				}
			}
			//state = buffer(newState);
			newState.add('.');
			newState.add('.');
			

			for(int i = 0; i < states.size(); i++) {
				if(newState.equals(states.get(i))) {
					//System.out.println("Gen " + gen + " is the same state as state " + i);
					break;
					
				}
			}
			states.add(newState);
			state = newState;
			int sum = 0;
			for(int i = 0; i < state.size(); i++) {
				if(state.get(i).equals('#')) {
					sum += i - buffer;
				}
			}
			System.out.println(sum);
			//System.out.print(gen);
			//printState(state);
			//System.out.println(gen);

		}
		printState(state);
		
	}
	
	public static void printState(ArrayList<Character> state) {
		for(Character c : state) {
			System.out.print(c);
		}
	}
	
	public static ArrayList<Character> buffer(ArrayList<Character> state) {
		if(state.get(0).equals('#')) {
			state.add(0, '.');
			state.add(0, '.');
			increase++;
			increase++;
		}else if(state.get(1).equals('#')) {
			state.add(0, '.');
			increase++;
		}
		
		if(state.get(state.size()-1).equals('#')) {
			state.add('.');
			state.add('.');
		}else if(state.get(state.size()-2).equals('#')) {
			state.add('.');
		}
		
		return state;
	}
	
	private static String getInput(){
		return "#..#. => .\r\n" + 
				"..#.. => .\r\n" + 
				"..#.# => #\r\n" + 
				"##.#. => .\r\n" + 
				".#... => #\r\n" + 
				"#.... => .\r\n" + 
				"##### => #\r\n" + 
				".#.## => .\r\n" + 
				"#.#.. => .\r\n" + 
				"#.### => #\r\n" + 
				".##.. => #\r\n" + 
				"##... => .\r\n" + 
				"#...# => #\r\n" + 
				"####. => #\r\n" + 
				"#.#.# => .\r\n" + 
				"#..## => .\r\n" + 
				".#### => .\r\n" + 
				"...## => .\r\n" + 
				"..### => #\r\n" + 
				".#..# => .\r\n" + 
				"##..# => #\r\n" + 
				".#.#. => .\r\n" + 
				"..##. => .\r\n" + 
				"###.. => .\r\n" + 
				"###.# => #\r\n" + 
				"#.##. => #\r\n" + 
				"..... => .\r\n" + 
				".##.# => #\r\n" + 
				"....# => .\r\n" + 
				"##.## => #\r\n" + 
				"...#. => #\r\n" + 
				".###. => .";
		/*File file = new File("in");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");
		return str;*/
	}

}
