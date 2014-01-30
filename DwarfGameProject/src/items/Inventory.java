package items;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

	private int width;

	private int height;

	private Item[][] itemGrid;

	private List<Item> items;

	public Inventory(){
		width = 5;
		height = 5;
		itemGrid = new Item[width][height];
		items = new ArrayList<Item>();
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getWidth(){
		return this.width;
	}

	public void addItem(Item item){
		boolean itemAdded = false;

		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(itemGrid[x][y] == null){

					boolean isValidPlacement = true;

					for(int iy = 0; iy < item.width; iy++){
						for(int ix = 0; ix < item.width; ix++){
							if(itemGrid[ix][iy] != null){
								isValidPlacement = false;
								break;
							}
						}	
						if(isValidPlacement == false){
							break;
						}
					}

					if(isValidPlacement == true){
						for(int iy = 0; iy < item.width; iy++){
							for(int ix = 0; ix < item.width; ix++){
								itemGrid[ix][iy] = item;
							}
						}
						itemAdded = true;
					}

					if(itemAdded == true){
						break;
					}
				}
			}
			if(itemAdded == true){
				break;
			}
		}
	}
}
