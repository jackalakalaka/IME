package controller;

import java.util.Objects;

import model.IModel;
import model.iImage;
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
  IModel model;
  ImageView view;
  Readable readable;

  public ImageControllerImpl(ImageView view,Readable readable) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(readable);

    this.view = view;
    this.readable = readable;
  }

  @Override
  public void addImage(String alias, iImage image) throws IllegalArgumentException {
    this.model.addImage(alias,image);
  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
