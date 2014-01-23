package Utilities;

public class Distance {

	public static int range(int x1, int y1, int x2, int y2){
//		return Math.abs(x1-x2) + Math.abs(y1-y2);
		
		return (int) Math.round(Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2))));
	}
	
	public static double[] getPixleOffsets(double distance, int direction){
		double[] retArray = new double[2];
		switch(direction){
		case 8:
			retArray[1] = distance;
			break;
		case 6:
			retArray[0] = 0-distance;
			break;
		case 4:
			retArray[0] = distance;
			break;
		case 2:
			retArray[1] = 0-distance;
			break;
		case 7:
			retArray[0] = ((1/Math.sqrt(2))*distance);
			retArray[1] = ((1/Math.sqrt(2))*distance);
			break;
		case 9:
			retArray[0] = 0-((1/Math.sqrt(2))*distance);
			retArray[1] = ((1/Math.sqrt(2))*distance);
			break;
		case 1:
			retArray[0] = ((1/Math.sqrt(2))*distance);
			retArray[1] = 0-((1/Math.sqrt(2))*distance);
			break;
		case 3:
			retArray[0] = 0-((1/Math.sqrt(2))*distance);
			retArray[1] = 0-((1/Math.sqrt(2))*distance);
			break;
		}
		
		return retArray;
	}
}
