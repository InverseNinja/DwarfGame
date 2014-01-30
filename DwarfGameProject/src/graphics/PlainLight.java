package graphics;

import java.awt.Color;
import java.awt.Point;

public class PlainLight implements Light{

	private int radius = 0;
	
	private Point position;
	
	private Color color;
	
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

	@Override
	public Color getLightColor() {
		if(color == null){
			color = new Color(0f,0f,0f);
		}
		return color;
	}

	@Override
	public float getIntensity() {
		// TODO Auto-generated method stub
		return .7f;
	}

}
