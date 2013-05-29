package com.zizik.life;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Cell {
	private int state;
	private int neighbors;
	private int x;
	private int y;
	private int size;
	private Texture texture;
	
	
	public Cell(int x, int y, int state,int size) {
		this.x=x;
		this.y=y;
		this.state=state;
		this.size=size;
		
	}
	
	
	
	public Texture update() {
		
		texture = new Texture(Gdx.files.internal("data/cell.png"));
		
		
		
		return texture;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getState() {
		return this.state;
	}
	
	public void setState(int state) {
		this.state=state;
	}
			
	public int getSize() {
		return this.size;
	}
	public void dispose() {
		try {texture.dispose();}
 	    	catch (NullPointerException ens){
 	    		
 	    	}
		
	}
}
