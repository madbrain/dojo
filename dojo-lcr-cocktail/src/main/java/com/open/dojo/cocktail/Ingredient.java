package com.open.dojo.cocktail;

public class Ingredient {

	private int id;
	private String name;
	private Unit unit = Unit.LITER;
	private double alcoolRate;
	private Origin origin = Origin.NONE;
	private double unitPrice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public double getAlcoolRate() {
		return alcoolRate;
	}

	public void setAlcoolRate(double alcoolRate) {
		this.alcoolRate = alcoolRate;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public boolean isExotic() {
		return origin != Origin.NONE && origin != Origin.EUROPE;
	}
	
	@Override
	public boolean equals(Object o) {
		return o instanceof Ingredient && ((Ingredient)o).id == id;
	}
}
