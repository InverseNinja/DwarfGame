package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import items.Inventory;

import javax.swing.JPanel;

public class InventoryPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3268042544412615456L;

	private Inventory inventory;
	
	public int cellWidth = 60;
	
	public int cellHeight = 60;
	
	public InventoryPanel(Inventory inventory){
		this.inventory = inventory;
		this.setSize(cellWidth * inventory.getWidth(), cellHeight * inventory.getHeight());
	}
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;        
		super.paintComponent(g2d);
		for(int x = 0; x < inventory.getWidth(); x++ ){
			for(int y = 0; y < inventory.getHeight(); y++ ){
				//draw the grid.
				g2d.setColor(Color.red);
				g2d.drawRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
			}
		}
	}
}
