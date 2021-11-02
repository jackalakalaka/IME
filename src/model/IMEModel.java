package model;

import java.util.ArrayList;

import model.funcobjs.ICommands;

/**
 * Interface for models used by IME.
 */
public interface IMEModel {

  /**
   * Returns an image in the model.
   *
   * @param imageName The name given to the image.
   * @return An Image .
   */
  Image getImageFromModel(String imageName);

  /**
   * Adds an image to the model.
   *  @param name The name for the image.
   * @param image The actual image,
   */
  void addImage(String name, Image image);

  /**
   * Applies a command in the model to an image creating a new image in the model.
   *
   * @param command A string representing the command's name.
   * @param original The name of the original image.
   * @param newName The name of the new image post application of the command.
   */
  void applyCommand(String command, String original, String newName);

  /**
   * Gets the list of commands from the model.
   *
   * @return An ArrayList of ICommands.
   */
  ArrayList<ICommands> getCommandList();

  /**
   * Simple boolean for if a command is in the model.
   *
   * @param command A string representing the command's name.
   * @return True if the command is in the model, false otherwise.
   */
  boolean containsCommand(String command);

  /**
   * Simple boolean for if an image is in the model.
   *
   * @param imageName A string representing the image's name.
   * @return True if the image is in the model, false otherwise.
   */
  boolean containsImage(String imageName);
}
