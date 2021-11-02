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
  private final Readable readable;

  /**
   * This is the default constructor which sets all fields to their default and the scanner.
   */
  public IMEControllerCompact() {
    this.readable = new InputStreamReader(System.in);
    this.model = new IMEModelImpl();
    this.view = new IMEViewImpl();
  }

  /**
   * This is the constructor that takes in all the parameters.
   *
   * @param model Model which has the commands and list of images.
   * @param view  View has the appendable.
   * @param readable    Scanner gives info to the controller.
   */
  public IMEControllerCompact(IMEModel model, IMEView view, Readable readable) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.readable = Objects.requireNonNull(readable);
  }

  @Override
  public void runIME() throws IllegalStateException, IOException {
    Scanner scanner =new Scanner(this.readable);
    this.view.printMenu(this.model.getCommandList());
    this.view.renderMsg("\nPlease enter a command:\n");

    while (scanner.hasNext()) {

      String command = scanner.next();

      if (command.equals("quit")) {
        this.view.renderMsg("\nThank you for using IME!\n");
        break;
      }

      String oldName = scanner.next();

      if (this.model.containsCommand(command) && this.model.containsImage(oldName)) {

        this.view.renderMsg("Please wait...");
        String newName = scanner.next();
        this.model.applyCommand(command, oldName, newName);

      }
      else if (!command.equals("load") && !this.model.containsImage(oldName)) {

        this.view.renderMsg("Image not found. Please try again.");
        scanner.nextLine();

      }
      else {
        this.view.renderMsg("Please wait...");
        switch (command) {
          case "brightness":
            int change = scanner.nextInt();
            String newName = scanner.next();
            if (this.model.containsImage(oldName)) {
              Image oldVersion = this.model.getImageFromModel(oldName);
              this.model.addImage(newName, oldVersion.changeBrightness(change));
            } else {
              this.view.renderMsg("Image not found. Please try again.");
              scanner.nextLine();
            }
            break;
          case "load":
            try {
              String path = scanner.next();
              this.model.addImage(oldName, new ImagePpm(path));
              break;
            } catch (FileNotFoundException e) {
              this.view.renderMsg("File name was incorrect.");
              scanner.nextLine();
              break;
            }
          case "save":
            if (this.model.containsImage(oldName)) {
              String path = scanner.next();
              this.model.getImageFromModel(oldName).saveImageToFile(path);
              break;
            } else {
              this.view.renderMsg("Image not found. Please try again.");
              scanner.nextLine();
            }
          default:
            this.view.renderMsg("\nCommand not recognized. Please try again.");
            scanner.nextLine();
        }
      }
      this.view.renderMsg("\nPlease enter a command:\n");
    }
  }
}
