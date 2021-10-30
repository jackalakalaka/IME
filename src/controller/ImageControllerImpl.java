package controller;

import java.io.InputStreamReader;
import java.util.Objects;

import factory.ImageModelFactory;
import model.IModel;
import model.iImage;
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
  ImageModelFactory fac = new ImageModelFactory();
  IModel model;
  ImageView view;
  Readable readable;

  public ImageControllerImpl(IModel model, ImageView view){
    this(model,view, new InputStreamReader(System.in));
  }

  public ImageControllerImpl(IModel model, ImageView view, Readable readable) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.readable = Objects.requireNonNull(readable);
  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
