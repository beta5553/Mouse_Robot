package application;
	
import java.awt.Robot;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;


public class Main extends Application {
	
	// 1 minute - 60,000 milliseconds.
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

	@FXML
	Hyperlink hyperlink;

	Timeline timeline;
	
	Robot robot;
	
	MouseRobot mouseRobot = new MouseRobot();
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@FXML
	public void initialize() throws Exception { 
		super.init();
		System.out.println("initialize()");
		mouseRobot.setRunning(true);
		this.meowsStart();
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//primaryStage.setHeight(340);
			//primaryStage.setWidth(280);
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));
			primaryStage.show();
			primaryStage.setTitle("MEOWS V2.0");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startAction() 
	{
		//toggle  button action. 
		if (startButton.getText().equals("Start")) {
			System.out.println("Start button clicked");
			meowsStart();
		}
		else {
			System.out.println("Stop button clicked");
			startButton.setText("Start");
			timeline.stop();
			timerLabel.setText("Status: stopped");
			progressIndicator.setVisible(false);
			mouseRobot.setRunning(false);
		}
	}
	
	public void meowsStart()
	{
		System.out.println("meowsStart() - Starting catching the mouse");
		startButton.setText("Stop");
		timerLabel.setText("Status: running");
		timeline = new Timeline(new KeyFrame(Duration.millis(Main.CYCLE_TIME_FREQUENCY), ae -> mouseRobot.moveMouse(counterLabel)));
		timeline.setCycleCount(Timeline.INDEFINITE);			
		timeline.play();
		progressIndicator.setVisible(true);
		mouseRobot.setRunning(true);
	}
}