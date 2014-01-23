package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import entities.Dwarf;

public class EntityDrawer {

	private static Image goldImage;

	private static Image emptyGroundImage;

	private static Image dwarfBack;
	
	private static Image dwarfFront;
	
	private static Image dwarfLeft;

	private static Image dwarfRight;
	
	/**
	 * This method draws a dwarf
	 * @param graphicsObject
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param width
	 * @param height
	 * @param dwarf
	 */
	public static void drawDwarf(Graphics graphicsObject, int xCoordinate, int yCoordinate, int width, int height, Dwarf dwarf){
		
		int facing = dwarf.getFacingDirection();
		Image drawImage;
		switch(facing){
		case 8:
			if(dwarfBack == null){//if its null lets load the image
				dwarfBack = new ImageIcon("Resources\\Entityimages\\dwarf\\DwarfSpritesBack.png").getImage();
			}
			drawImage = dwarfBack;
			break;
		case 2:
			if(dwarfFront == null){//if its null lets load the image
				dwarfFront = new ImageIcon("Resources\\Entityimages\\dwarf\\DwarfSpritesFront.png").getImage();
			}
			drawImage = dwarfFront;
			break;
		case 4:
			if(dwarfLeft == null){//if its null lets load the image
				dwarfLeft = new ImageIcon("Resources\\Entityimages\\dwarf\\DwarfSpritesLeft.png").getImage();
			}
			drawImage = dwarfLeft;
			break;
		case 6:
			if(dwarfRight == null){//if its null lets load the image
				dwarfRight = new ImageIcon("Resources\\Entityimages\\dwarf\\DwarfSpritesRight.png").getImage();
			}
			drawImage = dwarfRight;
			break;
		default:
			if(dwarfBack == null){//if its null lets load the image
				dwarfFront = new ImageIcon("Resources\\Entityimages\\dwarf\\DwarfSpritesFront.png").getImage();
			}
			drawImage = dwarfFront;
			break;
		}
		graphicsObject.drawImage(drawImage, xCoordinate, yCoordinate, width, height, null);
	}
	
	public static void drawGold(Graphics graphicsObject, int xCoordinate, int yCoordinate, int width, int height){
		if(goldImage == null){//if its null lets load the image
			goldImage = new ImageIcon("Resources\\Entityimages\\Gold\\gold_bar.png").getImage();
		}
		graphicsObject.drawImage(goldImage, xCoordinate, yCoordinate, width, height, null);
	}

	public static void drawEmptyGround(Graphics graphicsObject, int xCoordinate, int yCoordinate,int width, int height) {
		// TODO Auto-generated method stub
		if(emptyGroundImage == null){//if its null lets load the image
			emptyGroundImage = new ImageIcon("Resources\\Entityimages\\Ground\\dirtGround.jpg").getImage();
		}
	
		graphicsObject.drawImage(emptyGroundImage, xCoordinate, yCoordinate, width, height, null);
	}

	public static void drawDarkTile(Graphics graphicsObject, int xCoordinate, int yCoordinate,
			int width, int height) {
		graphicsObject.setColor(Color.black);
		graphicsObject.fillRect(xCoordinate, yCoordinate, width, height);
	}
	
	public static void drawFog(Graphics graphicsObject, int xCoordinate, int yCoordinate,int width, int height,float darkness){
		graphicsObject.setColor(new Color(0f,0f,0f,1-darkness));
		graphicsObject.fillRect(xCoordinate, yCoordinate, width, height);
	}
}
