package com.example.mediaplayer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;

public class Main extends Application {
  MediaPlayer mediaPlayer;
  String initialFile = "file:///Users/ihor/Desktop/BigBuckBunny.mp4";

  @Override
  public void start(Stage stage) {
    MenuItem menuItemOpen = new MenuItem("Open");
    menuItemOpen.setOnAction(
        x -> {
          mediaPlayer.pause();
          File newFile = new FileChooser().showOpenDialog(stage);
          if (newFile == null) {
            return;
          }
          try {
            String location = newFile.toURI().toURL().toExternalForm();
            extracted(stage, location);
          } catch (MalformedURLException e) {
            e.printStackTrace();
          }
        });

    BorderPane player = extracted(stage, initialFile);
    player.setTop(new MenuBar(new Menu("File", null, menuItemOpen)));

    stage.show();
  }

  private BorderPane extracted(Stage stage, String location) {
    BorderPane player = new BorderPane();
    mediaPlayer = new MediaPlayer(new Media(location));

    MediaView mediaView = new MediaView(mediaPlayer);
    mediaView.setFitWidth(100);
    mediaView.setFitHeight(100);
    player.setCenter(mediaView);
    player.setBottom(new BottomBar(mediaPlayer));
    player.setStyle("-fx-background-color:#000000");
    mediaPlayer.play();

    // player = new Player(location);
    Scene scene = new Scene(player, 720, 405);
    stage.setScene(scene);

    stage.widthProperty().addListener((obs, oldVal, newVal) -> System.out.println(newVal));
    stage.heightProperty().addListener((obs, oldVal, newVal) -> System.out.println(newVal));
    return player;
  }
}
