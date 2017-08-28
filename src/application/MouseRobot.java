package application;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * 
 * @author moraleix
 *
 */

public class MouseRobot  {
	
	//To pass control between screens.
	private boolean isRunning; 
	
	Robot robot;
	
	//Global counter. 
    static int counter;
    
    
    public boolean isRunning(){
    	return isRunning;
    }
    
    public void setRunning(boolean isRunning){
    	System.out.println("setRunning="+isRunning);
    	this.isRunning = isRunning;
    }
    
    public void logThisInCompactMode(String log) {
		System.out.println(log + counter + "(CM)");
}
	
	public void logThisInExpandedMode(String log, Label counterLabel, TextArea textArea) {
			textArea.appendText(log + "\n");
			System.out.println(log + counter + "(EM)");
			counterLabel.setText(String.valueOf(counter));
	}
	
	public void moveMouseInExpandedMode(Label counterLabel, TextArea textArea){
		try {
			//if (robot == null) { robot = new Robot(); } //saving some heap. 
			counter++; 
			robot = new Robot();
			int x = MouseInfo.getPointerInfo().getLocation().x;
			int y = MouseInfo.getPointerInfo().getLocation().y;
			robot.mouseMove(x+1, y+1);
			robot.mouseMove(x, y);
			
			logThisInExpandedMode("Mouse X: "+ x + " Mouse Y: " + y + " At Counter: "+counter, counterLabel, textArea);
		}
		catch (AWTException e) {
			e.printStackTrace();
		} 
	}
	
	public void activateMouse()
	{
		try {
			counter++;
			robot = new Robot();
			int x = MouseInfo.getPointerInfo().getLocation().x;
			int y = MouseInfo.getPointerInfo().getLocation().y;
			robot.mouseMove(x+1, y+1);
			robot.mouseMove(x, y);
			
			logThisInCompactMode("Mouse X: "+ x + " Mouse Y: " + y + " At Counter: ");
		} 
		catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
