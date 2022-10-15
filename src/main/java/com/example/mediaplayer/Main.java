package com.example.mediaplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
  Player player;
  String initialFile = "file:///Users/ihor/Desktop/BigBuckBunny.mp4";

  @Override
  public void start(Stage stage) {
    MenuItem menuItemOpen = new MenuItem("Open");
    MenuBar menuBar = new MenuBar(new Menu("File", null, menuItemOpen));

    menuItemOpen.setOnAction(
        e -> {
          player.mediaPlayer.pause();
          File newFile = new FileChooser().showOpenDialog(stage);
          if (newFile == null) {
            return;
          }
          try {
            player = new Player(newFile.toURI().toURL().toExternalForm());
            Scene scene = new Scene(player, 720, 405);
            stage.setScene(scene);
          } catch (MalformedURLException e1) {
            e1.printStackTrace();
          }
        });

    player = new Player(initialFile);
    player.setTop(menuBar);

    Scene scene = new Scene(player, 720, 405);

    stage.setScene(scene);
    stage.show();

    stage.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(newVal));
    stage.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(newVal));
  }
}
