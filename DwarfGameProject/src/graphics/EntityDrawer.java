package graphics;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class EntityDrawer {

	public static Image goldImage;
	
	public static Image dwarfImage;
	
	public static void drawDwarf(Graphics graphicsObject, int xCoordinate, int yCoordinate, int width, int height){
		if(dwarfImage == null){//if its null lets load the image
			dwarfImage = new ImageIcon("Resources\\Entityimages\\dwarf\\dwarf.png").getImage();
		}
		graphicsObject.drawImage(dwarfImage, xCoordinate, yCoordinate, width, height, null);
	}
	
	public static void drawGold(Graphics graphicsObject, int xCoordinate, int yCoordinate, int width, int height){
		if(goldImage == null){//if its null lets load the image
			goldImage = new ImageIcon("Resources\\Entityimages\\Gold\\gold_bar.png").getImage();
		}
		graphicsObject.drawImage(goldImage, xCoordinate, yCoordinate, width, height, null);
	}
}
