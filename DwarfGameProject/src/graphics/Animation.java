package graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public abstract class Animation {

	protected Image[] imageSequences;

	protected int sequenceIndex = 0;

	protected Point position;

	protected long timeDelay;

	protected long lastUpdatedTime;
	
	protected double width = 1;
	
	protected double height = 1;

	public void update(){
		long currentTime = System.currentTimeMillis();
		if((currentTime - lastUpdatedTime) >= timeDelay){
			if(sequenceIndex >= imageSequences.length-1){
				sequenceIndex = 0;
			}else{
				sequenceIndex++;
			}
			lastUpdatedTime = currentTime;
		}
	}

	public void draw(Graphics2D g, int width, int height){
		g.drawImage(imageSequences[sequenceIndex], position.x, position.y, width, height, null);
	}
	
	public void draw(Graphics2D g,int x, int y, int width, int height){
		g.drawImage(imageSequences[sequenceIndex], x, y, width, height, null);
	}
	
	public double getWidth(){
		return this.width;
	}
	
	public double getHeight(){
		return this.height;
	}

	public int getYCoordinate() {
		// TODO Auto-generated method stub
		return this.position.y;
	}

	public int getXCoordinate() {
		// TODO Auto-generated method stub
		return this.position.x;
	}
	
	public void setPosition(Point p){
		this.position = p;
	}
}
