package graphics;

import java.awt.Color;
import java.awt.Point;

public interface Light {

	public int getLightRadius();
	
	public Point getLightPosition();
	
	public Color getLightColor();
	
	public float getIntensity();
}
