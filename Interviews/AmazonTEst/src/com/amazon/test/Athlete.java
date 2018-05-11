package com.amazon.test;

import java.util.ArrayList;
import java.util.List;

public class Athlete implements Comparable<Athlete> {

	private int id;
	private int mass;
	private int strength;
	private List<Athlete> athletes;
	
	
	public Athlete(int id, int mass, int strength) {
		super();
		this.id = id;
		this.mass = mass;
		this.strength = strength;
		athletes = new ArrayList<>();
	}
	
	public int getMass() {
		return mass;
	}
	public void setMass(int mass) {
		this.mass = mass;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	@Override
	// inverted sort
	public int compareTo(Athlete o) {
		return (o.getMass() - this.mass) + (o.getStrength() - this.strength); 
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%d/%d/%d", id, mass, strength);
	}
	
	public List<Athlete> getAthletes() {
		return athletes;
	}
}
