package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;

import model.IMEModel;
import model.IMEModelImpl;
import model.Image;
import model.ImagePpm;
import view.IMEView;
import view.IMEViewImpl;

/**
 * This is the controller class for IME.
 */
public class IMEControllerCompact implements IMEController {
  private final IMEModel model;
  private final IMEView view;
  private final Scanner sc;

  /**
   * This is the default constructor which sets all fields to their default and the scanner.
   */
  public IMEControllerCompact() {
    Readable input = new InputStreamReader(System.in);
    this.sc = new Scanner(input);
    this.model = new IMEModelImpl();
    this.view = new IMEViewImpl();
  }

  /**
   * This is the constructor that takes in all the parameters.
   *
   * @param model Model which has the commands and list of images.
   * @param view  View has the appendable.
   * @param sc    Scanner gives info to the controller.
   */
  public IMEControllerCompact(IMEModel model, IMEView view, Scanner sc) {
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

      if (command.equals("quit")) {
        this.view.renderMsg("\nThank you for using IME!\n");
        break;
      }

      String oldName = sc.next();

      if (this.model.containsCommand(command) && this.model.containsImage(oldName)) {

        this.view.renderMsg("Please wait...");
        String newName = sc.next();
        this.model.applyCommand(command, oldName, newName);

      }
      else if (!command.equals("load") && !this.model.containsImage(oldName)) {

        this.view.renderMsg("Image not found. Please try again.");
        sc.nextLine();

      }
      else {
        this.view.renderMsg("Please wait...");
        switch (command) {
          case "brightness":
            int change = sc.nextInt();
            String newName = sc.next();
            if (this.model.containsImage(oldName)) {
              Image oldVersion = this.model.getImageFromModel(oldName);
              this.model.addImage(newName, oldVersion.changeBrightness(change));
            } else {
              this.view.renderMsg("Image not found. Please try again.");
              sc.nextLine();
            }
            break;
          case "load":
            try {
              String path = sc.next();
              this.model.addImage(oldName, new ImagePpm(path));
              break;
            } catch (FileNotFoundException e) {
              this.view.renderMsg("File name was incorrect.");
              sc.nextLine();
              break;
            }
          case "save":
            if (this.model.containsImage(oldName)) {
              String path = sc.next();
              this.model.getImageFromModel(oldName).saveImageToFile(path);
              break;
            } else {
              this.view.renderMsg("Image not found. Please try again.");
              sc.nextLine();
            }
          default:
            this.view.renderMsg("\nCommand not recognized. Please try again.");
            sc.nextLine();
        }
      }
      this.view.renderMsg("\nPlease enter a command:\n");
    }
  }
}
