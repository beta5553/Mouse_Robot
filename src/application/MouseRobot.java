package application;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import javafx.scene.control.Label;

/**
 *
 * @author moraleix
 * beta5553@gmail.com
 *
 */

public class MouseRobot  {

	//Is robot running?
	private boolean isRunning;

	//AWT Robot.
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
	
	public void log(String log, Label counterLabel) {
			System.out.println(log + counter + "(EM)");
			counterLabel.setText(counter+"m");
	}
	
	public void moveMouse(Label counterLabel){
		try {

			// Avoid memory leak.
			if (robot == null) {
				robot = new Robot();
			}

			counter++;
			int x = MouseInfo.getPointerInfo().getLocation().x;
			int y = MouseInfo.getPointerInfo().getLocation().y;
			robot.mouseMove(x+1, y+1);
			robot.mouseMove(x, y);
			
			log("Mouse X: "+ x + " Y: " + y + " Count: "+counter+"m", counterLabel);
		}
		catch (AWTException e) {
			e.printStackTrace();
		} 
	}
}
