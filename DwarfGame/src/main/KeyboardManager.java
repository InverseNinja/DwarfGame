package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardManager implements KeyListener{

	//This is the array that will hold the keys that are currently pressed.
	private ArrayList<Integer> pressedKeys;

	//Just used for now to monitor performance
	private int eventsProccessed = 0;

	public KeyboardManager(){
		pressedKeys = new ArrayList<Integer>(); 
	}

	public synchronized ArrayList<Integer> getPressedKeys(){
		return pressedKeys;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();

		if(getPressedKeys().contains(keyCode) == false){
			getPressedKeys().add(keyCode);
		}

		eventsProccessed++;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		getPressedKeys().remove((Object)arg0.getKeyCode());
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
