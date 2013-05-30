package com.zizik.life;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {
	private int width;
	private int height;
	private int deltaTime;
	private int cellSize=Gdx.graphics.getWidth()/32;
	private int cellMatrix [][] = new int [32][18];
	private int neighborMatrix[][] = new int [32][18];
	ArrayList<Cell> cells = new ArrayList();
	private SpriteBatch batch;
	private BitmapFont font = new BitmapFont();
	private int alive;
	private CharSequence s;
	private int state=0;
	private int died;
	private int newborn;
	private int generation=0;
	

	public Map(int width, int height, int deltaTime) {
		batch = new SpriteBatch();
		
		this.width=width;
		this.height=height;
		this.deltaTime=deltaTime;
		for (int i=0;i<width*cellSize;i+=cellSize){
			for (int j=0; j<height*cellSize;j+=cellSize) { 
				cells.add(new Cell(i,j,1,cellSize));
				cellMatrix[i%cellSize][j%cellSize]=0;
				neighborMatrix[i%cellSize][j%cellSize]=0;
			}
		}
		
	}
	
	public void changeState(int x, int y) {
		if (y<Gdx.graphics.getHeight()-35&&x<Gdx.graphics.getWidth()) {
		
		cellMatrix[x/cellSize][y/cellSize]=1-cellMatrix[x/cellSize][y/cellSize];
		System.out.println(neighborMatrix[x/cellSize][y/cellSize]);
		}
		
	}
		
	
	public void dispose() {
		batch.dispose();
		
		
	}
	
	public void draw() {
		
		int k=0;
		batch.begin();
		
		for (int i=0;i<width;i++){
			for (int j=0; j<height;j++) { 
				
				if (cellMatrix[i][j]==1) {batch.draw(cells.get(k).update(), cells.get(k).getX(), cells.get(k).getY(), cellSize, cellSize);
				
				}
				k++;
			}
		}
		if (state==0) {s="Start!";} else { s="Pause!";}
		
		font.draw(batch, s,  10, Gdx.graphics.getHeight()-10);
		font.draw(batch, "Speed:  +  "+this.getDeltaTime()/1000+"  -", 80, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "Alive: "+this.alive, 210, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "Died: "+this.died, 310, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "New-born: "+this.newborn, 410, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "Generation: "+this.generation, 510, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "Clear", 610, Gdx.graphics.getHeight()-10);	
		font.draw(batch, "Exit", Gdx.graphics.getWidth()-50, Gdx.graphics.getHeight()-10);	
		batch.end();
		
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getDeltaTime() {
		return this.deltaTime;
	}
	
	public void setDeltaTime(int deltatime) {
		this.deltaTime=deltatime;
	}
	
	public void clear() {
		for (int i=0;i<width;i++){
			for (int j=0; j<height;j++) { 
				cellMatrix[i][j]=0;
				
			}
				}
	}
	

	
	public void update() {
		alive=0;
		died=0;
		newborn=0;
		for (int i=0;i<width;i++) {
			for (int j=0;j<height;j++) {
				if ((cellMatrix[i][j]==0) && (checkTurn(i,j)==3)) {
					cellMatrix[i][j]=1; newborn++;
				} else {if ((cellMatrix[i][j]==1) && (checkTurn(i,j)<2 || checkTurn(i,j)>3)) {
					cellMatrix[i][j]=0; died++;
				}}
				if (cellMatrix[i][j]==1) {alive++;}
				
				
			}
		}
		generation++;
	}
	
	public int checkTurn(int i, int j)
    {	int power=0;
		try{power += cellMatrix[i+1][j];}
		catch (ArrayIndexOutOfBoundsException e){
			power += cellMatrix[i-32+1][j];
			
		}
		try {power += cellMatrix[i-1][j];}
		catch (ArrayIndexOutOfBoundsException e){
			power += cellMatrix[i-1+32][j];
		    }    
		
	    try {power += cellMatrix[i][j+1];}
	    catch (ArrayIndexOutOfBoundsException e){
			power += cellMatrix[i][j-17];
		    }
	    try {power += cellMatrix[i][j-1];}
	    catch (ArrayIndexOutOfBoundsException e){
			power += cellMatrix[i][j-1+18];
		    }
	    try {power += cellMatrix[i+1][j+1];}
	    catch (ArrayIndexOutOfBoundsException e){
	    	 try {power += cellMatrix[i+1-32][j+1-18];}
	 	    catch (ArrayIndexOutOfBoundsException en){
	 	    	try {power += cellMatrix[i+1-32][j+1];}
	 	    	catch (ArrayIndexOutOfBoundsException ens){
	 	    		power += cellMatrix[i+1][j+1-18];
	 	    	}
	 	    	
	 	    }
	 	    } 	    	
		    
	    try { power += cellMatrix[i-1][j-1];}
	    catch (ArrayIndexOutOfBoundsException e){
	    
	    		try {power += cellMatrix[i-1+32][j-1];}
	    		catch (ArrayIndexOutOfBoundsException en){
	    			try {power += cellMatrix[i-1][j-1+17];}
	    			catch (ArrayIndexOutOfBoundsException ens){
	    				power += cellMatrix[i-1+32][j-1+17];
	    			}
	    		}
	    	
		    }
	    try { power += cellMatrix[i-1][j+1];}
	    catch (ArrayIndexOutOfBoundsException e){
	    	try {power += cellMatrix[i-1+32][j+1];}
	    	catch (ArrayIndexOutOfBoundsException en){
	    		try {power += cellMatrix[i-1][j+1-18];}
	    		catch (ArrayIndexOutOfBoundsException ens){
	    			power += cellMatrix[i-1+32][j+1-18];
	    		}
	    	}
		    }
	    try {power += cellMatrix[i+1][j-1];}
	    catch (ArrayIndexOutOfBoundsException e){
	    	try {power += cellMatrix[i+1-32][j-1];}
	    	catch (ArrayIndexOutOfBoundsException en){
	    		try {power += cellMatrix[i+1][j-1+18];}
	    		catch (ArrayIndexOutOfBoundsException ens){
	    			power += cellMatrix[i+1-32][j-1+18];
	    		}
	    	}
		    }
	    
        return power;
        
    }

	public int getAlive() {
		return alive;
	}

	public void setAlive(int alive) {
		this.alive = alive;
	}

	public void setState(int i) {
		
		this.state=i;
		
	}

	public int getState() {
		// TODO Auto-generated method stub
		return this.state;
	}

	

}
