package com.example.mediaplayer;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
  Media media;
  MediaPlayer mediaPlayer;
  Pane pane;
  BottomBar bottomBar;

  public Player(String file) {
    media = new Media(file);
    mediaPlayer = new MediaPlayer(media);
    pane = new Pane();
    pane.getChildren().add(new MediaView(mediaPlayer));

    setCenter(pane);
    bottomBar = new BottomBar(mediaPlayer);
    setBottom(bottomBar);
    setStyle("-fx-background-color:#005DFF");
    mediaPlayer.play();
  }
}
