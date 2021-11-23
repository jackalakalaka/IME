package view;

import java.util.List;

/**
 * View event listening functionality.
 */
public interface ViewListener {

  /**
   * Perform an action when the given filename is chosen for loading.
   * @param filepath file path
   * @param name file name
   */
  void loadFileEvent(String filepath, String name);

  /**
   * Perform an action when the given filename is chosen for saving.
   * @param filepath file name
   */
  void saveFileEvent(String filepath);

  /**
   * Perform an action when the given image is chosen.
   * @param name image name
   */
  void selectImageEvent(String name);

  /**
   * Get this view listener's registered images.
   * @return image names of this session
   */
  List<String> getImageNames();

  /**
   * Handles the given command event.
   * @param actionCommand command name
   */
  void commandEvent(String actionCommand);
}
