package com.example.mediaplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
  Player player;
  FileChooser fileChooser;

  @Override
  public void start(Stage stage) {
    MenuItem menuItemOpen = new MenuItem("Open");
    Menu menuFile = new Menu("File");
    MenuBar menuBar = new MenuBar();

    menuFile.getItems().add(menuItemOpen);
    menuBar.getMenus().add(menuFile);

    fileChooser = new FileChooser();
    menuItemOpen.setOnAction(
        e -> {
          player.mediaPlayer.pause();
          File newFile = fileChooser.showOpenDialog(stage);
          if (newFile == null) {
            return;
          }
          try {
            player = new Player(newFile.toURI().toURL().toExternalForm());
            Scene scene = new Scene(player, 720, 535, Color.BLACK);
            stage.setScene(scene);
          } catch (MalformedURLException e1) {
            e1.printStackTrace();
          }
        });

    player = new Player("file:///Users/ihor/Desktop/test.mp4");

    player.setTop(menuBar);

     Scene scene = new Scene(player, 720, 405);
//    Scene scene = new Scene(player);

    stage.setScene(scene);
    stage.show();
  }
}
