package com.example.mediaplayer.bottom;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

public class TimeSlider extends Slider {
  public TimeSlider(MediaPlayer mediaPlayer) {
    mediaPlayer
        .currentTimeProperty()
        .addListener(
            x ->
                Platform.runLater(
                    () ->
                        setValue(
                            mediaPlayer
                                .getCurrentTime()
                                .divide(mediaPlayer.getTotalDuration().toMillis())
                                .multiply(100)
                                .toMillis())));

    valueProperty()
        .addListener(
            x -> {
              if (isPressed()) {
                mediaPlayer.seek(
                    mediaPlayer.getMedia().getDuration().multiply(getValue()).divide(100));
              }
            });
  }
}
