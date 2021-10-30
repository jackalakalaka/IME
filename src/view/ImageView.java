package view;

import java.io.IOException;

import model.iImage;

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
  void createImage(String model) throws IOException;

  void printMenu() throws IOException;

}
