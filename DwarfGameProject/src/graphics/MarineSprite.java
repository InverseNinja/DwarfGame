package graphics;

import java.awt.Image;

import javax.swing.ImageIcon;

public class MarineSprite extends Animation{

	public boolean moving = false;

	public int direction = 2;
	
	protected Image[] moving2;
	
	protected Image[] still;
	
	protected int lastState = 1;
	
	public int unitDistance = 40;
	
	public int pixelsPerTick = 10;
	
	public int timePerTick = 10;

	public MarineSprite(){
		still = new Image[10];
		still[2] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2.png").getImage();
		still[3] = new ImageIcon("Resources\\Entityimages\\marine\\marine_3.png").getImage();
		still[6] = new ImageIcon("Resources\\Entityimages\\marine\\marine_6.png").getImage();
		still[8] = new ImageIcon("Resources\\Entityimages\\marine\\marine_8.png").getImage();
		still[9] = new ImageIcon("Resources\\Entityimages\\marine\\marine_9.png").getImage();
		
		this.moving2 = new Image[11];
		moving2[0] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m0.png").getImage();
		moving2[1] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m1.png").getImage();
		moving2[2] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m2.png").getImage();
		moving2[3] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m3.png").getImage();
		moving2[4] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m4.png").getImage();
		moving2[5] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m5.png").getImage();
		moving2[6] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m6.png").getImage();
		moving2[7] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m7.png").getImage();
		moving2[8] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m8.png").getImage();
		moving2[9] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m6.png").getImage();
		moving2[10] = new ImageIcon("Resources\\Entityimages\\marine\\marine_2_m5.png").getImage();
		setImageSequence(still);
		this.width = 2;
		this.height = 2;
	}
	
	private void setImageSequence(Image[] images){
		this.timeDelay = (long) ((((unitDistance/(images.length*1f))/(pixelsPerTick*1f)))*timePerTick);
		this.imageSequences = images;
	}

	@Override
	public void update(){
		if(moving){
			if(this.lastState!= 0){
				sequenceIndex = 0;
			}
			this.lastState = 0;
			setImageSequence(moving2);
			long currentTime = System.currentTimeMillis();
			if((currentTime - lastUpdatedTime) >= timeDelay){
				if(sequenceIndex >= imageSequences.length-1){
					sequenceIndex = 0;
				}else{
					sequenceIndex++;
				}
				lastUpdatedTime = currentTime;
			}
		}else{
			setImageSequence(still);
			sequenceIndex = direction;
			this.lastState = 1;
		}
	}
}
