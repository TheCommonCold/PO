package main;

import java.io.IOException;
import aktywa.waluta;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Set;
import java.lang.String;

public class Main extends Application {


    private BorderPane GUI;
    private Stage primaryStage;
    private ObservableSet<waluta> listaWalut;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        initGUI();
        walutaCreator();
    }

    public void initGUI(){
        try{
            GUI= (BorderPane)FXMLLoader.load(getClass().getResource("GUI/GUI.fxml"));
            primaryStage.setTitle("GJEUDA");
            Scene scene = new Scene(GUI);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e) {
        e.printStackTrace();
        }
    }

    public void walutaCreator() {
        try {
            // Load person overview.
            AnchorPane walutaCreator = (AnchorPane)FXMLLoader.load(getClass().getResource("GUI/walutaCreator.fxml"));

            // Set person overview into the center of root layout.
            GUI.setCenter(walutaCreator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
