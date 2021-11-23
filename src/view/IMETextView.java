package view;

import java.util.List;

import model.funcobjs.ICommands;

/**
 * Interface for rendering messages for a text-based view.
 */
public interface IMETextView extends IMEView {

  /**
   * Appends all the commands' signatures to the appendable.
   *
   * @param commandsHashMap HashMap of all the ICommands.
   * @throws IllegalStateException If it can't append to the appendable.
   */
  void printMenu(List<ICommands> commandsHashMap) throws IllegalStateException;

}
