package view;

import model.ImageModel;

/**
 * Interface for displaying images.
 */
public interface ImageView {

  /**
   * Creates a string representation of a model.
   *
   * @param model The model that is represented.
   * @return String that represents the image.
   */
  String createImage(ImageModel model);

}
