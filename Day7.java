import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day7 {
	public static Map<Character, Node> m = new HashMap<Character, Node>();
	public static List<Node> open = new ArrayList<Node>();
	public static List<Node> closed = new ArrayList<Node>();
	public static List<Node> offTheOven = new ArrayList<Node>();

	public static int time = 0;

	public static void main(String[] args){
		String in = getInput();
		/*in = "Step C must be finished before step A can begin.\r\n" + 
				"Step C must be finished before step F can begin.\r\n" + 
				"Step A must be finished before step B can begin.\r\n" + 
				"Step A must be finished before step D can begin.\r\n" + 
				"Step B must be finished before step E can begin.\r\n" + 
				"Step D must be finished before step E can begin.\r\n" + 
				"Step F must be finished before step E can begin.";*/
		String[] input = in.split("\r\n");
		
		Set<Node> all = new HashSet<Node>();
		for(String s : input) {
			String[] split = s.split(" ");
			Character first = split[1].charAt(0);
			Node firstNode = getNode(first);
			Character second = split[7].charAt(0);
			Node secondNode = getNode(second);
			secondNode.addPrereq(firstNode);
			all.add(firstNode);
			all.add(secondNode);
		}
		
		for(Node n : all) {
			if(n.getPrereqs().isEmpty()) {
				open.add(n);
			}
		}
		
		
		//Get to work workers! Start them all on open
		/*int N = 2; // Number of threads 

		Worker[] workers = new Worker[N];
        for(int i = 0; i < N; i++) 
        { 
        	workers[i] = new Worker();
            workers[i].start(); 
            System.out.println("Starting worker " + i);
        } 
		
		while(closed.size() < all.size()) {
			System.out.println("time " + time);
			//Expand everything off the oven
			for(Node finished : offTheOven) {
				closed.add(finished);
				open.remove(finished);
				//Add its succ_nodes to open
				ArrayList<Node> succ_nodes = new ArrayList<Node>();
				for(Node n : AdventOfCode.m.values()) {
					//Does this node have a prereq for finished?
					boolean isSucc = n.getPrereqs().contains(finished);
					//is the only prereq not in closed finished?
					for(Node prereq : n.getPrereqs()) {
						if(!AdventOfCode.closed.contains(prereq) && !prereq.equals(finished)) {
							isSucc = false;
						}
					}
					
					if(isSucc) {
						succ_nodes.add(n);
					}
				}
				
				for(Node succ : succ_nodes) {
					if(!AdventOfCode.closed.contains(succ)) {
						AdventOfCode.open.add(succ);
					}
				}					
			}
			//Go through each worker
			for(int i = 0; i < N; i++) {
				//is their workload empty?
				if(workers[i].getWorkingOn() == null) {
					//Get em to work if theres something to work on
					if(!open.isEmpty()) {
						//pick a node to expand!
						Collections.sort(open);
						Node toExpand = open.get(0);
						open.remove(toExpand);
						//Give it to the worker
						workers[i].setWorkingOn(toExpand);
						System.out.println("Assigned " + toExpand.getData() + " to worker " + i);
						//Wait for the worker to be up to date
						while(!workers[i].isCurrent());
					}
				}else {
					//Wait for the worker to be up to date
					while(!workers[i].isCurrent());
				}
			}
			time++;
		}*/
		
		List<Node> working = new ArrayList<Node>();
		while(!open.isEmpty() || !working.isEmpty() || !offTheOven.isEmpty()) {
			
			System.out.println("TIME: " + time);
			//Open first one in open that is ready
			while(working.size() < 5 && !open.isEmpty()) {
				Collections.sort(open);
				Node toExpand = open.get(0);
				working.add(toExpand);
				open.remove(toExpand);
				System.out.println("starting work on " + toExpand);
				if(toExpand.workOn()) {
					
				}

			}
			for(Node w : working) {
				if(w.isDone()) {
					System.out.println("finished " + w);
					Node toExpand = w;
					//Determine successors to toExpand
					ArrayList<Node> succ_nodes = new ArrayList<Node>();
					for(Node n : m.values()) {
						//Does this node have a prereq for toExpand?
						boolean isSucc = n.getPrereqs().contains(toExpand);
						//is the only prereq not in closed toExpand?
						for(Node prereq : n.getPrereqs()) {
							if(!closed.contains(prereq) && !prereq.equals(toExpand)) {
								isSucc = false;
							}
						}
						
						if(isSucc) {
							succ_nodes.add(n);
						}
					}
					for(Node succ : succ_nodes) {
						if(!closed.contains(succ)) {
							open.add(succ);
						}
					}
					offTheOven.add(w);
					closed.add(w);
					Collections.sort(open);
				}else {
					w.workOn();
				}
			}
			
			
			//Add off the oven to closed
			for(Node n : offTheOven) {
				//closed.add(n);
				working.remove(n);
			}
			
			time++;
			
			offTheOven.clear();
		}
		System.out.println(time);
	}
	
	private static Node getNode(Character c) {
		if(m.get(c) == null) {
			Node newNode = new Node(c);
			m.put(c, newNode);
		}
		return m.get(c);
	}


	private static String getInput(){
		return "Step C must be finished before step P can begin.\r\n" + 
				"Step V must be finished before step Q can begin.\r\n" + 
				"Step T must be finished before step X can begin.\r\n" + 
				"Step B must be finished before step U can begin.\r\n" + 
				"Step Z must be finished before step O can begin.\r\n" + 
				"Step P must be finished before step I can begin.\r\n" + 
				"Step D must be finished before step G can begin.\r\n" + 
				"Step A must be finished before step Y can begin.\r\n" + 
				"Step R must be finished before step O can begin.\r\n" + 
				"Step J must be finished before step E can begin.\r\n" + 
				"Step N must be finished before step S can begin.\r\n" + 
				"Step X must be finished before step H can begin.\r\n" + 
				"Step F must be finished before step L can begin.\r\n" + 
				"Step S must be finished before step I can begin.\r\n" + 
				"Step W must be finished before step Q can begin.\r\n" + 
				"Step H must be finished before step K can begin.\r\n" + 
				"Step K must be finished before step Q can begin.\r\n" + 
				"Step E must be finished before step L can begin.\r\n" + 
				"Step Q must be finished before step O can begin.\r\n" + 
				"Step U must be finished before step G can begin.\r\n" + 
				"Step L must be finished before step O can begin.\r\n" + 
				"Step Y must be finished before step G can begin.\r\n" + 
				"Step G must be finished before step I can begin.\r\n" + 
				"Step M must be finished before step I can begin.\r\n" + 
				"Step I must be finished before step O can begin.\r\n" + 
				"Step A must be finished before step N can begin.\r\n" + 
				"Step H must be finished before step O can begin.\r\n" + 
				"Step T must be finished before step O can begin.\r\n" + 
				"Step H must be finished before step U can begin.\r\n" + 
				"Step A must be finished before step I can begin.\r\n" + 
				"Step B must be finished before step R can begin.\r\n" + 
				"Step V must be finished before step T can begin.\r\n" + 
				"Step H must be finished before step M can begin.\r\n" + 
				"Step C must be finished before step A can begin.\r\n" + 
				"Step B must be finished before step G can begin.\r\n" + 
				"Step L must be finished before step Y can begin.\r\n" + 
				"Step T must be finished before step J can begin.\r\n" + 
				"Step A must be finished before step R can begin.\r\n" + 
				"Step X must be finished before step L can begin.\r\n" + 
				"Step B must be finished before step L can begin.\r\n" + 
				"Step A must be finished before step F can begin.\r\n" + 
				"Step K must be finished before step O can begin.\r\n" + 
				"Step W must be finished before step M can begin.\r\n" + 
				"Step Z must be finished before step N can begin.\r\n" + 
				"Step Z must be finished before step S can begin.\r\n" + 
				"Step R must be finished before step K can begin.\r\n" + 
				"Step Q must be finished before step L can begin.\r\n" + 
				"Step G must be finished before step O can begin.\r\n" + 
				"Step F must be finished before step Y can begin.\r\n" + 
				"Step V must be finished before step H can begin.\r\n" + 
				"Step E must be finished before step I can begin.\r\n" + 
				"Step W must be finished before step Y can begin.\r\n" + 
				"Step U must be finished before step I can begin.\r\n" + 
				"Step F must be finished before step K can begin.\r\n" + 
				"Step M must be finished before step O can begin.\r\n" + 
				"Step Z must be finished before step H can begin.\r\n" + 
				"Step X must be finished before step S can begin.\r\n" + 
				"Step J must be finished before step O can begin.\r\n" + 
				"Step B must be finished before step I can begin.\r\n" + 
				"Step F must be finished before step H can begin.\r\n" + 
				"Step D must be finished before step U can begin.\r\n" + 
				"Step E must be finished before step M can begin.\r\n" + 
				"Step Z must be finished before step X can begin.\r\n" + 
				"Step P must be finished before step L can begin.\r\n" + 
				"Step W must be finished before step H can begin.\r\n" + 
				"Step C must be finished before step D can begin.\r\n" + 
				"Step A must be finished before step X can begin.\r\n" + 
				"Step Q must be finished before step I can begin.\r\n" + 
				"Step R must be finished before step Y can begin.\r\n" + 
				"Step B must be finished before step A can begin.\r\n" + 
				"Step N must be finished before step L can begin.\r\n" + 
				"Step H must be finished before step G can begin.\r\n" + 
				"Step Y must be finished before step M can begin.\r\n" + 
				"Step L must be finished before step G can begin.\r\n" + 
				"Step G must be finished before step M can begin.\r\n" + 
				"Step Z must be finished before step R can begin.\r\n" + 
				"Step S must be finished before step Q can begin.\r\n" + 
				"Step P must be finished before step J can begin.\r\n" + 
				"Step V must be finished before step J can begin.\r\n" + 
				"Step J must be finished before step I can begin.\r\n" + 
				"Step J must be finished before step X can begin.\r\n" + 
				"Step W must be finished before step O can begin.\r\n" + 
				"Step B must be finished before step F can begin.\r\n" + 
				"Step R must be finished before step M can begin.\r\n" + 
				"Step V must be finished before step S can begin.\r\n" + 
				"Step H must be finished before step E can begin.\r\n" + 
				"Step E must be finished before step U can begin.\r\n" + 
				"Step R must be finished before step W can begin.\r\n" + 
				"Step X must be finished before step Q can begin.\r\n" + 
				"Step N must be finished before step G can begin.\r\n" + 
				"Step T must be finished before step I can begin.\r\n" + 
				"Step L must be finished before step M can begin.\r\n" + 
				"Step H must be finished before step I can begin.\r\n" + 
				"Step U must be finished before step M can begin.\r\n" + 
				"Step C must be finished before step H can begin.\r\n" + 
				"Step P must be finished before step H can begin.\r\n" + 
				"Step J must be finished before step F can begin.\r\n" + 
				"Step A must be finished before step O can begin.\r\n" + 
				"Step X must be finished before step M can begin.\r\n" + 
				"Step H must be finished before step L can begin.\r\n" + 
				"Step W must be finished before step K can begin.";
		/*File file = new File("in");
		FileInputStream fis = new FileInputStream(file);
		byte[] data = new byte[(int) file.length()];
		fis.read(data);
		fis.close();

		String str = new String(data, "UTF-8");
		return str;*/
	}

}
