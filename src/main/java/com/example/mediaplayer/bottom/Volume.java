package com.example.mediaplayer.bottom;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;

public class Volume extends HBox {
  public Volume(MediaPlayer mediaPlayer) {
      setPadding(new Insets(10));

    Label volumeLabel = new Label("Volume: ");
    Slider volumeSlider = new Slider();

    volumeSlider.setPrefWidth(70);
    volumeSlider.setMinWidth(30);
    volumeSlider.setValue(100);

    volumeSlider
        .valueProperty()
        .addListener(
            x -> {
              if (volumeSlider.isPressed()) {
                mediaPlayer.setVolume(volumeSlider.getValue() / 100);
              }
            });

    getChildren().addAll(volumeLabel, volumeSlider);
  }
}
