package graphics;

import java.awt.Point;

public class PlainLight implements Light{

	private int radius = 0;
	
	private Point position;
	
	public void setLightRadius(int radius){
		this.radius = radius;
	}
	
	public void setPosistion(Point position){
		this.position = position;
	}
	
	@Override
	public int getLightRadius() {
		
		return radius;
	}

	@Override
	public Point getLightPosition() {
		// TODO Auto-generated method stub
		return position;
	}

}
