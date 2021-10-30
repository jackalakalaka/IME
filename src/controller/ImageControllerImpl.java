package controller;

<<<<<<< HEAD
import java.util.Objects;

import model.IModel;
import model.iImage;
=======
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;

import factory.ImageModelFactory;
import model.ImageModel;
>>>>>>> 266e5074b2ed59cca12d59214ba9238efbe623e9
import view.ImageView;

/**
 * Represents a controller for IME.
 */
public class ImageControllerImpl implements ImageController{
<<<<<<< HEAD
  IModel model;
  ImageView view;
=======
  ImageModelFactory fac = new ImageModelFactory();
  HashMap<String,ImageModel> imgModels;
  HashMap<String,ImageView> imgViews;
>>>>>>> 266e5074b2ed59cca12d59214ba9238efbe623e9
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
<<<<<<< HEAD
  public void addImage(String alias, iImage image) throws IllegalArgumentException {
    this.model.addImage(alias,image);
=======
  public void addImg(String alias, String format, String filePath) throws FileNotFoundException,
          IllegalArgumentException  {
    imgModels.put(alias, fac.createImageModel(format, filePath));
    imgViews.put(alias, fac.createImageModel(format, filePath));
>>>>>>> 266e5074b2ed59cca12d59214ba9238efbe623e9
  }

  @Override
  public void runIME() throws IllegalStateException {

  }
}
