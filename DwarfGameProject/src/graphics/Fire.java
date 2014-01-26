package graphics;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Fire extends Animation implements Light{

	private int lightRadius = 12;
	
	public Fire(){
		this.imageSequences = new Image[12];
		imageSequences[0] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire5.png").getImage();
		imageSequences[1] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire6.png").getImage();
		imageSequences[2] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire7.png").getImage();
		imageSequences[3] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire8.png").getImage();
		imageSequences[4] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire9.png").getImage();
		imageSequences[5] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire10.png").getImage();
		imageSequences[6] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire11.png").getImage();
		imageSequences[7] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire12.png").getImage();
		imageSequences[8] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire13.png").getImage();
		imageSequences[9] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire14.png").getImage();
		imageSequences[10] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire15.png").getImage();
		imageSequences[11] = new ImageIcon("Resources\\AnimationImages\\Fire\\fire16.png").getImage();
		this.timeDelay = 100;
		this.width = 2;
		this.height = 2;
	}

	@Override
	public int getLightRadius() {
		return lightRadius;
	}

	@Override
	public Point getLightPosition() {
		return this.position;
	}
}
