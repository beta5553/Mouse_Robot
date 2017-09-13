package application;

import java.awt.Robot;
import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import application.MouseRobot;

public class CompactMode {
	
	@FXML 
	Button startButton;
	
	@FXML
	ProgressIndicator progressIndicator; 
	
	Timeline timeline;
	
	Robot robot;
	
	MouseRobot mouseRobot = new MouseRobot();
	
	public void startAction() 
	{
		//toggle  button action. 
		System.out.println("Starting CompactMode");
		if (startButton.getText().equals("Start")) {
			System.out.println("Start button clicked");
			startButton.setText("Stop");
			timeline = new Timeline(new KeyFrame(Duration.millis(5000), ae -> mouseRobot.activateMouse()));
			timeline.setCycleCount(Timeline.INDEFINITE);			
			timeline.play();
			progressIndicator.setVisible(true);
			mouseRobot.setRunning(true);
		} 
		else {
			System.out.println("Stop button clicked");
			startButton.setText("Start");
			timeline.stop();
			progressIndicator.setVisible(false);
			System.out.println("Stopping CompactMode");
			mouseRobot.setRunning(false);
		}
	}
	
	public void automaticStart()
	{
		System.out.println("automaticStart() CM");
		startButton.setText("Stop");
		timeline = new Timeline(new KeyFrame(Duration.millis(5000), ae -> mouseRobot.activateMouse()));
		timeline.setCycleCount(Timeline.INDEFINITE);			
		timeline.play();
		progressIndicator.setVisible(true);
		mouseRobot.setRunning(true);
	}
	
	
	public void toggle2ExpandedMode(ActionEvent event){
		 try {
			  System.out.println("toggle");
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
		        /* 
		         * if "fx:controller" is not set in fxml
		         * fxmlLoader.setController(NewWindowController);
		         */
		        
		        
		        Scene scene = new Scene(fxmlLoader.load(), 260, 300);
		        Stage stage = new Stage();
		        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
		        stage.setTitle("Mouse Robot Expanded Mode");
		        
		        if (mouseRobot.isRunning()){
		        	Main em = (Main)fxmlLoader.getController();
		        	em.automaticStart();
		        }
		        
		        stage.setScene(scene);
		        stage.show();
		        
		        if (timeline != null) {
		        	timeline.stop();
		        }
		        
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
}
