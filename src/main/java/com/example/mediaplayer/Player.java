package com.example.mediaplayer;

import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Player extends BorderPane {
  MediaPlayer mediaPlayer;

  public Player(String file) {
    mediaPlayer = new MediaPlayer(new Media(file));

    MediaView mediaView = new MediaView(mediaPlayer);
    mediaView.setFitWidth(100);
    mediaView.setFitHeight(100);
    setCenter(mediaView);
    setBottom(new BottomBar(mediaPlayer));
    setStyle("-fx-background-color:#000000");
    mediaPlayer.play();
  }
}
