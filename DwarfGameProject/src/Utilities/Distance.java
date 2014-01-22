package Utilities;

public class Distance {

	public static int range(int x1, int y1, int x2, int y2){
//		return Math.abs(x1-x2) + Math.abs(y1-y2);
		
		return (int) Math.round(Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))));
	}
}
