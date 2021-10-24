package controller;

import java.util.Objects;

import model.ImageModel;
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
  ImageModel model;
  ImageView view;
  Readable readable;

  public ImageControllerImpl(ImageModel model, ImageView view,Readable readable) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(view);
    Objects.requireNonNull(readable);

    this.model = model;
    this.view = view;
    this.readable = readable;

  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
