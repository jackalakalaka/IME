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
    //Initialize scanner and print menu.
    Scanner sc = new Scanner(this.readable);
    this.view.printMenu(this.model.getCommandList());
    this.view.renderMsg("\nPlease enter a command:\n");
    //See if the scanner is empty off the bat.
    checkScanner(sc);
    //Run while loop while the scanner still has something.
    while (sc.hasNext()) {
      String command = getNextIfExists(sc);//Helper will throw if the scanner is ever empty.
      //Quit case if the command quit is entered.
      if (command.equalsIgnoreCase("quit")) {
        break;
      }
      String imageName = getNextIfExists(sc);
      //If the model isn't in the model and the command isn't load the remaining inputs are skipped.
      if (checkImageCommandModel(imageName,command,sc)) {
        continue;
      }
      //If the command and image are in the model the command is applied.
      if (this.model.containsCommand(command) && this.model.containsImage(imageName)) {
        this.view.renderMsg("Please wait...");
        this.model.applyCommand(command, imageName, sc.next());
      } else { //If the command isn't in the list it goes to default commands.
        this.view.renderMsg("Please wait...");
        handleDefaults(command,imageName,sc); //Also handles mistypes or non-existent commands.
      }
      this.view.renderMsg("\nPlease enter a command:\n");
      //See if the scanner is empty after completing the operation.
      checkScanner(sc);
    }
    //Exit message.
    this.view.renderMsg("\nThank you for using IME!");
  }

  /**
   * Checks to see if the model has the image or resets the scanner and prints error.
   *
   * @param imageName The name of the image.
   * @param command The command in the list.
   * @param sc The scanner to be reset.
   * @return True if the image/command are not in the model, false if they are.
   */
  private boolean checkImageCommandModel(String imageName, String command, Scanner sc) {
    if (!this.model.containsImage(imageName) && !command.equalsIgnoreCase("load")) {
      errorAndReset("Image not found. Please try again.", sc);
      return true;
    }
    return false;
  }

  /**
   * Returns the next item in the scanner if it exists.
   *
   * @param scanner The scanner in question.
   * @return The next item in the scanner if it exists.
   */
  private String getNextIfExists(Scanner scanner) {
    if (!scanner.hasNext()) { // Shouldn't happen for a System.in-based readable
      throw new IllegalStateException("There is nothing to read.");
    }
    return scanner.next();
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

  /**
   * A helper for handling commands that aren't function objects.
   *
   * @param command The name of the command.
   * @param imageName The name of the image.
   * @param scanner The scanner.
   */
  private void handleDefaults(String command, String imageName, Scanner scanner){
    String input = getNextIfExists(scanner);
    switch (command) {
      case "brightness":
        int change = Integer.parseInt(input);
        String name = getNextIfExists(scanner);
        if (this.model.containsImage(imageName)) {
          Image oldVersion = this.model.getImageFromModel(imageName);
          this.model.addImage(name, oldVersion.changeBrightness(change));
        } else {
          errorAndReset("Image not found. Please try again.", scanner);
        }
        break;
      case "load":
        File f = new File(input);
        if (f.exists()) {
          this.model.addImage(imageName, new ImagePpm(input));
        } else {
          errorAndReset("\nFile name was not correct.\n", scanner);
        }
        break;
      case "save":
        if (this.model.containsImage(imageName)) {
          this.model.getImageFromModel(imageName).saveImageToFile(input);
          break;
        } else {
          if (!this.model.containsImage(imageName)) {
            errorAndReset("Image not found. Please try again.", scanner);
          } else {
            errorAndReset("IME only saves .ppm files.", scanner);
          }
        }
      default:
        errorAndReset("\nCommand not recognized. Please try again.\n", scanner);
    }
  }

}
