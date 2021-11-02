package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import model.FuncObjs.ICommands;
import model.IMEModel;
import model.IMEModelImpl;
import model.ImagePpm;
import view.IMEView;
import view.IMEViewImpl;

public class IMEControllerCompact implements IMEController {
  private final IMEModel model;
  private final IMEView view;
  private final Scanner sc;

  public IMEControllerCompact() {
    Readable input = new InputStreamReader(System.in);
    this.sc = new Scanner(input);
    this.model = new IMEModelImpl();
    this.view = new IMEViewImpl();
  }

  public IMEControllerCompact(IMEModel model, IMEView view, Scanner sc, HashMap<String, ICommands> commands) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.sc = Objects.requireNonNull(sc);
  }

  @Override
  public void runIME() throws IllegalStateException, IOException {
    this.view.printMenu(this.model.getCommandList());
    this.view.renderMsg("\nPlease enter a command:\n");

    while (sc.hasNext()) {

      String command = sc.next();
      if(command.equals("quit")){
        this.view.renderMsg("\nThank you for using IME!\n");
        break;
      }
      if (this.model.containsCommand(command)) {
        this.view.renderMsg("Please wait...");
        this.model.applyCommand(command, sc.next(), sc.next());
      } else {
        this.view.renderMsg("Please wait...");
        switch (command) {
          case "load":
            try {
              this.model.addImage(sc.next(), new ImagePpm(sc.next()));
              break;
            } catch (FileNotFoundException e) {
              this.view.renderMsg("File name was incorrect.");
              break;
            }
          case "save":
            String imageName = sc.next();
            if (this.model.containsImage(imageName)) {
              this.model.getImageFromModel(imageName).saveImageToFile(sc.next());
              break;
            } else {
              this.view.renderMsg("Image not found. Please try again.");
            }
          default:
            this.view.renderMsg("\nCommand not recognized. Please try again.");
        }
      }
      this.view.renderMsg("\nPlease enter a command:\n");
    }
  }

  private String getNextIfExists() {
    if (!this.sc.hasNext()) { // Shouldn't happen for a System.in-based readable
      throw new NoSuchElementException("There is nothing to read.");
    }
    return this.sc.next();
  }
}
