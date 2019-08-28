package application;
	
import java.awt.Robot;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.paint.Color;
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

	private static Stage mainStage;

	public static Stage getStage(){
		return mainStage;
	}

	public void setStage(Stage stage){
		this.mainStage = stage;
	}
	
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

			//Avoiding memory leak and cloning object reference.
			if(getStage() == null){
				setStage(primaryStage);
			}

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//primaryStage.setHeight(340);
			//primaryStage.setWidth(280);

			mainStage.setScene(scene);
			mainStage.getIcons().add(new Image(Main.class.getResourceAsStream("greenClock.png")));
			mainStage.show();
			mainStage.setTitle("MEOWS V1.0 is running");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void startAction() {
		//toggle  button action. 
		if (startButton.getText().equals("Start")) {
			System.out.println("Start button clicked");
			getStage().getIcons().remove(0);
			getStage().getIcons().add(new Image(getClass().getResourceAsStream("greenClock.png")));
			mainStage.setTitle("MEOWS V1.0 is running");
			timerLabel.setTextFill(Color.web("#074A0D"));
			meowsStart();
		}
		else {
			System.out.println("Stop button clicked");
			startButton.setText("Start");
			timeline.stop();
			timerLabel.setText("Status: stopped");
			timerLabel.setTextFill(Color.web("#BB1113"));
			progressIndicator.setVisible(false);
			mouseRobot.setRunning(false);
			getStage().getIcons().remove(0);
			getStage().getIcons().add(new Image(getClass().getResourceAsStream("redClock.png")));
			mainStage.setTitle("MEOWS V1.0 is stopped");
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

	public void copyToClipboard(){
		final Clipboard clipboard = Clipboard.getSystemClipboard();
		final ClipboardContent content = new ClipboardContent();
		content.putString("beta5553@gmail.com");
		clipboard.setContent(content);

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("MEOWS v1.0 About info");
		alert.setHeaderText("MEOWS V1.0 is developed by Israel Morales");
		alert.setContentText("What is MEOWS? \n\n" +
				"MEOWS will prevent your computer to go to sleep or to get blocked when you are away due inactivity, the counter wil tell you for how long your computer has been active represented in minutes. \n\n"+
				"How does it work? MEOWS will be chasing your mouse to making it move every minute and putting it back where it was before, causing a loop of activity that will keep your session on.\n\n" +
				"Contact me if you want to contribute to my github repo.\n\n" +
				"My email: beta5553@gmail.com has been copied to your clipboard. Use Ctrl+V to paste it anywhere.\n\n" +
				"This software is compatible with PC, MAC and Linux. \n");

		alert.showAndWait();
	}
}