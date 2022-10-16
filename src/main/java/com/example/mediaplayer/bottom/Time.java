package com.example.mediaplayer.bottom;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class Time extends HBox {
  public Time(MediaPlayer mediaPlayer) {

    setPadding(new Insets(10));

    Label label = new Label("Time: ");
      label.setMinWidth(50);

      Slider slider = new Slider();
      slider.setPrefWidth(2000);

    slider.setPadding(new Insets(0));
    mediaPlayer
        .currentTimeProperty()
        .addListener(
            x ->
                Platform.runLater(
                    () ->
                        slider.setValue(
                            mediaPlayer
                                .getCurrentTime()
                                .divide(mediaPlayer.getTotalDuration().toMillis())
                                .multiply(100)
                                .toMillis())));

    slider
        .valueProperty()
        .addListener(
            x -> {
              if (isPressed()) {
                mediaPlayer.seek(
                    mediaPlayer.getMedia().getDuration().multiply(slider.getValue()).divide(100));
              }
            });
    getChildren().addAll(label, slider);
  }
}
