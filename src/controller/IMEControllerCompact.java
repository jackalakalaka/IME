package controller;

import java.io.File;
import java.io.InputStreamReader;
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
   * @param model    Model which has the commands and list of images.
   * @param view     View has the appendable.
   * @param readable Scanner gives info to the controller.
   */
  public IMEControllerCompact(IMEModel model, IMEView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Controller cannot take null as an argument.");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  @Override
  public void runIME() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);
    this.view.printMenu(this.model.getCommandList());
    this.view.renderMsg("\nPlease enter a command:\n");

    checkScanner(sc);
    while (sc.hasNext()) {

      String command = sc.next();

      if (command.equalsIgnoreCase("quit")) {
        break;
      } else {

        String imageName = sc.next();

        if (this.model.containsCommand(command) && this.model.containsImage(imageName)) {

          this.view.renderMsg("Please wait...");
          this.model.applyCommand(command, imageName, sc.next());

        } else if (!command.equalsIgnoreCase("load") && !this.model.containsImage(imageName)) {

          errorAndReset("Image not found. Please try again.", sc);

        } else {
          String input = sc.next();
          this.view.renderMsg("Please wait...");
          switch (command) {
            case "brightness":
              int change = Integer.parseInt(input);
              String name = sc.next();
              if (this.model.containsImage(imageName)) {
                Image oldVersion = this.model.getImageFromModel(imageName);
                this.model.addImage(name, oldVersion.changeBrightness(change));
              } else {
                errorAndReset("Image not found. Please try again.", sc);
              }
              break;
            case "load":
              File f = new File(input);
              if (f.exists()) {
                this.model.addImage(imageName, new ImagePpm(input));
              } else {
                errorAndReset("\nFile name was not correct.\n", sc);
              }
              break;
            case "save":
              if (this.model.containsImage(imageName)) {
                this.model.getImageFromModel(imageName).saveImageToFile(input);
                break;
              } else {
                if (!this.model.containsImage(imageName)) {
                  errorAndReset("Image not found. Please try again.", sc);
                } else {
                  errorAndReset("IME only saves .ppm files.", sc);
                }
              }
              break;
            default:
              errorAndReset("\nCommand not recognized. Please try again.\n", sc);
          }
        }
        this.view.renderMsg("\nPlease enter a command:\n");
      }
      checkScanner(sc);
    }
    this.view.renderMsg("\nThank you for using IME!");
  }

  /**
   * Simple helper for {@link #runIME()} when an error or mistype occurs.
   *
   * @param errorMessage String of what to print to the user.
   * @param scanner      The scanner that is progressed to the next line.
   */
  private void errorAndReset(String errorMessage, Scanner scanner) {
    this.view.renderMsg(errorMessage);
    scanner.nextLine();
  }

  /**
   * Simple helper for checking the state of the scanner.
   *
   * @param scanner The scanner used in the controller.
   */
  private void checkScanner(Scanner scanner) {
    if (!scanner.hasNext()) {
      throw new IllegalStateException("The readable contains nothing.");
    }
  }

}
