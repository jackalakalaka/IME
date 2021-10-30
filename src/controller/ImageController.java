package controller;

import model.iImage;

public interface ImageController {

  /**
   * Adds an image model with the specified alias.
   *
   * @param alias user-input name of given model
   * @param format image format
   */
<<<<<<< HEAD
  void addImage(String alias, iImage model) throws IllegalArgumentException;
=======
  void addImg(String alias, String format) throws IllegalArgumentException;
>>>>>>> 266e5074b2ed59cca12d59214ba9238efbe623e9

  /**
   * This method uses the controller to edit and view images.
   */
  void runIME() throws IllegalStateException;
}
