/*
ID: sarimal3
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("milk2.in"));
		PrintWriter out = new PrintWriter(new File("milk2.out"));
		ArrayList<Interval> set = new ArrayList<Interval>();
		int sets = in.nextInt();
		for(int i = 0; i < sets; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			Interval temp = new Interval(a, b);
			set.add(temp);
		}
		
		bubbleSort(set);
		
		ArrayList<Interval> finalset = new ArrayList<Interval>();
		finalset.add(set.get(0));
		
		for(int i = 1; i < set.size(); i++) {
			int a = finalset.get(finalset.size()-1).getendInterval();
			int b = set.get(i).getstartInterval();
			if(a >= b) {
				Interval z = merge(finalset.get(finalset.size()-1), set.get(i));
				finalset.remove(finalset.size()-1);
				finalset.add(z);
			} else {
				finalset.add(set.get(i));
			}
		}
		
		bubbleSort(finalset);
		
		int maxinterval = 0;
		
		for(int i = 0; i < finalset.size(); i++) {
			int temp = finalset.get(i).getendInterval();
			int temp2 = finalset.get(i).getstartInterval();
			int x = temp - temp2;
			maxinterval = Math.max(maxinterval, x);
		}
		
		int maxinbetween = 0;
		
		for(int i = 0; i < finalset.size()-1; i++) {
			int j = i+1;
			int temp = finalset.get(j).getstartInterval() - finalset.get(i).getendInterval();
			maxinbetween = Math.max(maxinbetween, temp);
		}
		System.out.println(maxinterval + " " + maxinbetween);
		out.println(maxinterval + " " + maxinbetween);
		
		in.close();
		out.close();
	}
	
	public static Interval merge(Interval x, Interval y) {
		int minstart = Math.min(x.getstartInterval(), y.getstartInterval());
		int maxend = Math.max(x.getendInterval(), y.getendInterval());
		Interval z = new Interval(minstart, maxend);
		return z;
	}
	
	public static void bubbleSort(ArrayList<Interval> x) {
		for(int i = 0; i < x.size(); i++) {
			for(int j = i+1; j < x.size(); j++) {
				if(x.get(i).getstartInterval() > x.get(j).getstartInterval()) {
					Interval temp = x.get(i);
					x.set(i, x.get(j));
					x.set(j, temp);
				}
			}
		}
	}	
}

class Interval {
	private int startInterval;
	private int endInterval;
	
	public Interval(int startInterval, int endInterval) {
		this.startInterval = startInterval;
		this.endInterval = endInterval;
	}
	
	public int getstartInterval() {
		return startInterval;
	}
	
	public int getendInterval() {
		return endInterval;
	}
}