package controller;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
   * @param model    Model which has the commands and list of images.
   * @param view     View has the appendable.
   * @param readable Scanner gives info to the controller.
   */
  public IMEControllerCompact(IMEModel model, IMEView view, Readable readable) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.readable = Objects.requireNonNull(readable);
  }

  @Override
  public void runIME() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);
    this.view.printMenu(this.model.getCommandList());
    this.view.renderMsg("\nPlease enter a command:\n");

    while (sc.hasNext()) {

      String command = sc.next();

      if (command.equalsIgnoreCase("quit")) {
        this.view.renderMsg("\nThank you for using IME!\n");
        break;
      }

      String oldName = sc.next();

      if (this.model.containsCommand(command) && this.model.containsImage(oldName)) {

        this.view.renderMsg("Please wait...");
        this.model.applyCommand(command, oldName, sc.next());

      } else if (!command.equalsIgnoreCase("load") && !this.model.containsImage(oldName)) {

        errorAndReset("Image not found. Please try again.", sc);

      } else {
        this.view.renderMsg("Please wait...");
        switch (command) {
          case "brightness":
            int change = sc.nextInt();
            String newName = sc.next();
            if (this.model.containsImage(oldName)) {
              Image oldVersion = this.model.getImageFromModel(oldName);
              this.model.addImage(newName, oldVersion.changeBrightness(change));
            } else {
              errorAndReset("Image not found. Please try again.", sc);
            }
            break;
          case "load":
            String path = sc.next();
            File f = new File(path);
            if (f.exists()) {
              this.model.addImage(oldName, new ImagePpm(path));
            } else {
              errorAndReset("\nFile name was not correct.\n", sc);
            }
            break;
          case "save":
            String filePath = sc.next();
            if (this.model.containsImage(oldName) && filePathAvailable(filePath)) {
                this.model.getImageFromModel(oldName).saveImageToFile(sc.next());
                break;
            } else {
              if (!this.model.containsImage(oldName)) {
                errorAndReset("Image not found. Please try again.", sc);
              } else {
                errorAndReset("Filepath does not exist. Please try again.", sc);
              }
            }
          default:
            errorAndReset("\nCommand not recognized. Please try again.\n", sc);
        }
      }
      this.view.renderMsg("\nPlease enter a command:\n");
    }
    if (!sc.hasNext()) {
      throw new IllegalStateException("The readable contains nothing.");
    }
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
   * Helper for determining if the file path exists.
   *
   * @param filePath Given file path.
   * @return True if the file path exists, otherwise false.
   */
  private boolean filePathAvailable(String filePath) {
      final Path path = Paths.get(filePath);
      return Files.exists(path);
  }


}
