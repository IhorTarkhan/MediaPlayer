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

  public void start(final Stage primaryStage) {
    MenuItem open = new MenuItem("Open");
    Menu file = new Menu("File");
    MenuBar menu = new MenuBar();

    file.getItems().add(open);
    menu.getMenus().add(file);

    fileChooser = new FileChooser();
    open.setOnAction(
        e -> {
          player.mediaPlayer.pause();
          File file1 = fileChooser.showOpenDialog(primaryStage);

          if (file1 != null) {
            try {
              player = new Player(file1.toURI().toURL().toExternalForm());
              Scene scene = new Scene(player, 720, 535, Color.BLACK);
              primaryStage.setScene(scene);
            } catch (MalformedURLException e1) {
              e1.printStackTrace();
            }
          }
        });

    player = new Player("file:///Users/ihor/Desktop/test.mp4");

    player.setTop(menu);

    Scene scene = new Scene(player, 720, 405, Color.BLACK);

    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
