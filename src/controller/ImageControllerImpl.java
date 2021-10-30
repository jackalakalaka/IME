package controller;

import java.util.HashMap;
import java.util.Objects;

import model.ImageModel;
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
  HashMap<String,ImageModel> imgModels;
  ImageView view;
  Readable readable;

  public ImageControllerImpl(ImageView view,Readable readable) {
    Objects.requireNonNull(view);
    Objects.requireNonNull(readable);

    this.view = view;
    this.readable = readable;
  }

  @Override
  public void addModel(String alias, ImageModel model) throws IllegalArgumentException {
    imgModels.put(alias, model);
  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
