package com.amazon.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class AthletesSolution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inform the number of athletes: ");
		int numAthletes = sc.nextInt();
		
		int [] massAthletes = new int[numAthletes];
		int [] strengthAthletes = new int[numAthletes];
		List<Athlete> athletes = new ArrayList<Athlete>();
		List<Stack<Athlete>> stacks = new ArrayList<Stack<Athlete>>(); 
		
		for (int i = 0; i < numAthletes; i++)
		{
			System.out.println("Configuration of athlete " + (i + 1) + " (mass   strength):");
			int mass = sc.nextInt();
			int strength = sc.nextInt();
			massAthletes[i] = mass;
			strengthAthletes[i] = strength;
			
			athletes.add(new Athlete(i, mass, strength));
			
			System.out.println(mass + " - " + strength);
		}
		
		Collections.sort(athletes);
		System.out.println(athletes);

		Stack<Athlete> stack1 = new Stack<Athlete>();
		
		
		for (Athlete a: athletes)
		{
			stack1.push(a);
		}
		System.out.println(stack1);
		System.out.println(stack1.pop());
		Iterator<Athlete> iterator = stack1.iterator();
		while (iterator.hasNext())
		{
			Athlete athlete = iterator.next();
		}
		
//		for (Athlete a: stack1.elements())
//		{
//			Athlete a = athletes.get(i);
//			if (!a.getAthletes().isEmpty())
//			{
//				if (a.getStrength() >= a.getAthletes().get(0).getMass())
//				{
//					
//				}
//			}
//		}
		
		
	}

}
