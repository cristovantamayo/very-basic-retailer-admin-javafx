package com.cristovantamayo.veryBasicRetailerAdmin.application;

import java.io.IOException;
import java.sql.SQLException;

import com.cristovantamayo.veryBasicRetailerAdmin.db.Mysql;

import ch.vorburger.exec.ManagedProcessException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	private static Scene mainScene;
	private Mysql mysql;

	@Override
	public void start(Stage primaryStage) {
		Mysql mysql = new Mysql();
		mysql.mountdb();
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/MainView.fxml"));
			Pane pane = loader.load();
			
			mainScene = new Scene(pane);
			mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(mainScene);
			
			primaryStage.setTitle("Sample JavaFX application");
			//primaryStage.setMaximized(true);
			primaryStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Scene getMainScene() {
		return mainScene;
	}
	
	@Override
	public void stop() throws Exception {
		if(mysql != null) Mysql.unmoutdb();
		super.stop();
	}

	public static void main(String[] args) throws ClassNotFoundException, ManagedProcessException, SQLException {
		launch(args);
	}
}