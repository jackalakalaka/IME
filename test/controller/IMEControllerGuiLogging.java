package controller;

import java.io.File;
import java.util.List;
import java.util.Vector;

import model.IMEModel;
import view.ViewListener;

/**
 * Decorator class, for a IMEControllerGUI object, that logs when commands are run.
 */
public class IMEControllerGuiLogging implements IMEController, ViewListener {
  private final IMEControllerGUI delegate;
  private final IMEModel delegateModel;
  private String logs = "";

  /**
   * Set delegate and significant references.
   * @param delegate decorated object
   * @param delegateModel decorated IMEControllerGUI object's model
   * @param delegateViewLoadedImgs decorated IMEControllerGUI object's view's loaded images
   */
  public IMEControllerGuiLogging(IMEControllerGUI delegate, IMEModel delegateModel,
                                 Vector<String> delegateViewLoadedImgs) {
    this.delegate = delegate;
    this.delegateModel = delegateModel;
  }

  /**
   * Gets the current logs.
   * @return  logs thus far
   */
  public String getLogs() {
    return this.logs;
  }

  /**
   * This method uses the controller to load, edit, and save images.
   */
  @Override
  public void runIME() throws IllegalStateException {
    this.delegate.runIME();
  }

  /**
   * Log whether the model not containing the given image name contains it after delegate
   * invocation.
   *
   * @param filepath file path
   * @param name     image name
   */
  @Override
  public void loadFileEvent(String filepath, String name) {
    this.delegate.loadFileEvent(filepath, name);
    boolean containsName = this.delegateModel.containsImage(name);
    if (containsName) {
      this.logs += String.format("Success loading image '%1$s'.", name);
    } else {
      this.logs += String.format("Failure loading image '%1$s'.", name);
    }
  }

  /**
   * Log whether the image was saved to a not-previously-existing filepath after delegate
   * invocation.
   *
   * @param filepath file name
   */
  @Override
  public void saveFileEvent(String filepath) {
    this.delegate.saveFileEvent(filepath);
    if (new File(filepath).exists()) {
      this.logs += "Success saving image.";
    } else {
      this.logs += "Failure saving image.";
    }
  }

  /**
   * Perform an action when the given image is chosen.
   *
   * @param name image name
   */
  @Override
  public void selectImageEvent(String name) {
    this.delegate.selectImageEvent(name);
    this.logs += "Success selecting image.";
  }

  /**
   * Gets this session's registered images.
   * @return image names of this session
   */
  @Override
  public List<String> getImageNames() {
    return this.delegate.getImageNames();
  }

  /**
   * Handles the given command event.
   *
   * @param actionCommand command name
   */
  @Override
  public void commandEvent(String actionCommand) {
    this.delegate.commandEvent(actionCommand);
    this.logs += String.format("Success applying command '%1$s'.", actionCommand);
  }
}
