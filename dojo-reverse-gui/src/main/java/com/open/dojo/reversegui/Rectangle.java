package com.open.dojo.reversegui;


public class Rectangle {

	private int x;
	private int y;
	private int width;
	private int height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getMiddleY(){
		return y + height/2;
	}
	
	public int getMiddleX(){
		return x + width/2;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + width + "," + height + ")";
	}
	
	public boolean isOnRight( Rectangle other ){
		return x + width < other.getX();
	}
	
	public boolean isOnSameLine( Rectangle other ){
		if( y<other.getMiddleY() && other.getMiddleY()<y+height){
			if(other.getY()<getMiddleY() && getMiddleY()<other.getY()+other.getMiddleY()){
				return true;
			}
		}
		return false;
	}

	public boolean isOnTopEdge(Rectangle other) {
		if(other.getY() > y 
				&& other.getY() < y + height
				&& other.getX() < x 
				&& other.getX() + other.getWidth() > x + width){
			return true;
		}
		return false;
	}

}
