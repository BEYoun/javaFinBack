package fr.younes.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	private Parent root;
	private Scene scene,scene2;
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		openEmpIndex();	
	}
	public void openEmpIndex() throws IOException {
		root = FXMLLoader.load(getClass().getResource("/fr/younes/presentation/view/employer/IndexEmployer.fxml"));
		scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
