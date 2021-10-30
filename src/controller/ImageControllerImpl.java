package controller;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

import factory.ImageModelFactory;
import model.ImageModel;
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
  ImageModelFactory fac = new ImageModelFactory();
  HashMap<String,ImageModel> imgModels;
  HashMap<String,ImageView> imgViews;
  Readable readable;

  public ImageControllerImpl(Readable readable) {
    Objects.requireNonNull(readable);
    this.readable = readable;
  }

  /**
   * Adds an image model with the specified alias.
   *
   * @param alias  user-input name of given model
   * @param format image format
   */
  @Override
  public void addImg(String alias, String format, String filePath) throws FileNotFoundException,
          IllegalArgumentException  {
    imgModels.put(alias, fac.createImageModel(format, filePath));
    imgViews.put(alias, fac.createImageModel(format, filePath));
  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
