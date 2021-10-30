package controller;

import model.ImageModel;

public interface ImageController {

  /**
   * Adds an image model with the specified alias.
   *
   * @param alias user-input name of given model
   * @param model model for a processable image
   */
  void addModel(String alias, ImageModel model) throws IllegalArgumentException;

  /**
   * This method uses the controller to edit and view images.
   */
  void runIME() throws IllegalStateException;
}
