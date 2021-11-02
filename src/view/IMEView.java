package view;

import java.io.IOException;
import java.util.List;

import model.FuncObjs.ICommands;

/**
 * Interface for displaying images.
 */
public interface IMEView {

  void renderMsg(String str) throws IOException;

  void printMenu(List<ICommands> commandsHashMap) throws IOException;

}
