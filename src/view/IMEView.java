package view;

import java.util.List;

import model.FuncObjs.ICommands;

/**
 * Interface for rendering messages and possibly eventually for displaying images.
 */
public interface IMEView {

  /**
   * Renders a message to the view's appendable.
   *
   * @param str The message to be appended.
   */
  void renderMsg(String str);

  /**
   * Appends all the commands' signatures to the appendable.
   *
   * @param commandsHashMap HashMap of all the ICommands.
   * @throws IllegalStateException If it can't append to the appendable.
   */
  void printMenu(List<ICommands> commandsHashMap) throws IllegalStateException;

}
