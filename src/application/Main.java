package application;
	
import java.awt.Robot;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

import application.MouseRobot;

public class Main extends Application {
	
	//1 minute.
	public static int CYCLE_TIME_FREQUENCY = 60_000;
	
	@FXML
	Button startButton;
	
	@FXML
	Label timerLabel;
	
	@FXML
	Label counterLabel;
	
	@FXML
	ProgressIndicator progressIndicator;
	
	@FXML
	TextArea textArea;
	
	@FXML
	CheckBox checkBox;
	
	Timeline timeline;
	
	Robot robot;
	
	MouseRobot mouseRobot = new MouseRobot();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setHeight(340);
			primaryStage.setWidth(280);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startAction() 
	{
		//toggle  button action. 
		if (startButton.getText().equals("Start")) {
			System.out.println("Start button clicked");
			startButton.setText("Stop");
			timerLabel.setText("Status: running");
			//timeline = new Timeline(new KeyFrame(Duration.millis(Main.CYCLE_TIME_FREQUENCY), ae -> activateMouse()));
			timeline = new Timeline(new KeyFrame(Duration.millis(Main.CYCLE_TIME_FREQUENCY), ae -> mouseRobot.moveMouseInExpandedMode(counterLabel,textArea)));
			timeline.setCycleCount(Timeline.INDEFINITE);			
			timeline.play();
			progressIndicator.setVisible(true);
			mouseRobot.setRunning(true);
		} 
		else {
			System.out.println("Stop button clicked");
			startButton.setText("Start");
			timeline.stop();
			timerLabel.setText("Status: stoped");
			progressIndicator.setVisible(false);
			mouseRobot.setRunning(false);
		}
	}
	
	public void automaticStart()
	{
		System.out.println("automaticStart() EM");
		startButton.setText("Stop");
		timerLabel.setText("Status: running");
		//timeline = new Timeline(new KeyFrame(Duration.millis(Main.CYCLE_TIME_FREQUENCY), ae -> activateMouse()));
		timeline = new Timeline(new KeyFrame(Duration.millis(Main.CYCLE_TIME_FREQUENCY), ae -> mouseRobot.moveMouseInExpandedMode(counterLabel,textArea)));
		timeline.setCycleCount(Timeline.INDEFINITE);			
		timeline.play();
		progressIndicator.setVisible(true);
		mouseRobot.setRunning(true);
	}
	
	public void toggleLogTextArea(){
		if (textArea.isVisible()) {
			textArea.setVisible(false);
		}
		else {
			textArea.setVisible(true);
		}
	}
	
	public void toggle2CompactMode(ActionEvent event){
		System.out.println("toggle1");
		 try {
		        FXMLLoader fxmlLoader = new FXMLLoader();
		        fxmlLoader.setLocation(getClass().getResource("CompactMode.fxml"));
		        /* 
		         * if "fx:controller" is not set in fxml
		         * fxmlLoader.setController(NewWindowController);
		         */
		        Scene scene = new Scene(fxmlLoader.load(), 170, 50);
		        Stage stage = new Stage();
		        stage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
		        stage.setTitle("Compact Mode");
		        
		        if (mouseRobot.isRunning()){
		        	CompactMode cm = (CompactMode)fxmlLoader.getController();
		        	cm.automaticStart();
		        }
		        
		        stage.setScene(scene);
		        stage.show();
		        
		        //Stop main time line if is already present. 
		        if (timeline != null) {
		        	timeline.stop();	
		        }
		        
		        ((Node)(event.getSource())).getScene().getWindow().hide();
		        
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}
}