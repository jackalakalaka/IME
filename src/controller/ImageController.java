package controller;

import model.ImageModel;

public interface ImageController {

  /**
   * Adds an image model with the specified alias.
   *
   * @param alias user-input name of given model
   * @param format image format
   */
  void addImg(String alias, String format) throws IllegalArgumentException;

  /**
   * This method uses the controller to edit and view images.
   */
  void runIME() throws IllegalStateException;
}
