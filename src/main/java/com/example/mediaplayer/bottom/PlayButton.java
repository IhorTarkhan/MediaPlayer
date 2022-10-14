package com.example.mediaplayer.bottom;

import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;

public class PlayButton extends Button {
  public PlayButton(MediaPlayer mediaPlayer) {
    super("||");
    setPrefWidth(30);
    setOnAction(
        e -> {
          MediaPlayer.Status status = mediaPlayer.getStatus();
          if (status == MediaPlayer.Status.PLAYING) {
            if (mediaPlayer.getCurrentTime().greaterThanOrEqualTo(mediaPlayer.getTotalDuration())) {
              mediaPlayer.seek(mediaPlayer.getStartTime());
              mediaPlayer.play();
            } else {
              mediaPlayer.pause();
              setText(">");
            }
          } else {
            mediaPlayer.play();
            setText("||");
          }
        });
  }
}
