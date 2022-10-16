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
  public static final double INITIAL_WIDTH = 720;
  public static final double INITIAL_HEIGHT = 405;
  public static final String INITIAL_FILE = "file:///Users/ihor/Desktop/BigBuckBunny.mp4";
  MediaView mediaView;

  @Override
  public void start(Stage stage) {
    MenuItem menuItemOpen = new MenuItem("Open");
    MenuBar topBar = new MenuBar(new Menu("File", null, menuItemOpen));
    menuItemOpen.setOnAction(
        x -> {
          mediaView.getMediaPlayer().pause();
          File newFile = new FileChooser().showOpenDialog(stage);
          if (newFile == null) {
            return;
          }
          try {
            String location = newFile.toURI().toURL().toExternalForm();
            BorderPane root = extracted(stage, location);
            root.setTop(topBar);
          } catch (MalformedURLException e) {
            e.printStackTrace();
          }
        });

    BorderPane root = extracted(stage, INITIAL_FILE);
    root.setTop(topBar);

    stage
        .widthProperty()
        .addListener((obs, oldVal, newVal) -> mediaView.setFitWidth(newVal.doubleValue()));
    stage
        .heightProperty()
        .addListener((obs, oldVal, newVal) -> mediaView.setFitHeight(newVal.doubleValue() - 100));

    stage.show();
  }

  private BorderPane extracted(Stage stage, String location) {
    BorderPane root = new BorderPane();
    MediaPlayer mediaPlayer = new MediaPlayer(new Media(location));

    mediaView = new MediaView(mediaPlayer);
    root.setCenter(mediaView);
    root.setBottom(new BottomBar(mediaPlayer));
    root.setStyle("-fx-background-color:#000000");
    mediaPlayer.play();

    stage.setScene(new Scene(root, INITIAL_WIDTH, INITIAL_HEIGHT + 70));
    mediaView.setFitWidth(INITIAL_WIDTH);
    mediaView.setFitHeight(INITIAL_HEIGHT);

    return root;
  }
}
