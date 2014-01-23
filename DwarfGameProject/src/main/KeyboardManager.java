package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardManager implements KeyListener{

	//This is the array that will hold non-movement keys that are currently pressed.
	private ArrayList<Integer> otherPressedKeys;
	
	//This is the array that will hold movement related keys
	private ArrayList<Integer> pressedMovementKeys;

	//Just used for now to monitor performance
	private int eventsProccessed = 0;

	public KeyboardManager(){
		otherPressedKeys = new ArrayList<Integer>(); 
		pressedMovementKeys = new ArrayList<Integer>();
	}

	public synchronized ArrayList<Integer> getOtherPressedKeys(){
		return otherPressedKeys;
	}
	
	public synchronized ArrayList<Integer> getPressedMovementKeys(){
		return pressedMovementKeys;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();

		if(keyCode == 65 || keyCode == 68 || keyCode == 83 || keyCode == 87){
			if(getPressedMovementKeys().contains(keyCode) == false){
				getPressedMovementKeys().add(keyCode);
			}
			
		}else{
			if(getOtherPressedKeys().contains(keyCode) == false){
				getOtherPressedKeys().add(keyCode);
			}
	
		}
		eventsProccessed++;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if(keyCode == 65 || keyCode == 68 || keyCode == 83 || keyCode == 87){
			if(getPressedMovementKeys().contains(keyCode) == true){
				getPressedMovementKeys().remove((Integer)keyCode);
			}
		}else{
			if(getOtherPressedKeys().contains(keyCode) == true){
				getOtherPressedKeys().remove((Integer)keyCode);
			}
	
		}
		eventsProccessed++;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		/*Not used*/
	}


	/**
	 * Just used for performance monitoring.
	 * @return
	 */
	public int getEventsSinceLastCall(){
		int retInt = eventsProccessed;
		eventsProccessed = 0;
		return retInt;
	}

}
