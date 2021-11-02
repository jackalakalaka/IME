//package controller;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.HashMap;
//import java.util.NoSuchElementException;
//import java.util.Objects;
//import java.util.Scanner;
//
//import factory.ConvertFactory;
//import factory.IMEModelFactory;
//import factory.IMEViewFactory;
//import factory.ImageFactory;
//import model.FuncObjs.ICommands;
//import model.IMEModel;
//import model.Image;
//import view.IMEView;
//
///**
// * Represents a controller for IME.
// */
//public class IMEControllerImpl implements IMEController {
//  private static final ImageFactory imgFac = new ImageFactory();
//  private static final IMEModelFactory modelFac = new IMEModelFactory();
//  private static final IMEViewFactory viewFac = new IMEViewFactory();
//  private HashMap<String, String> twoArgCmds; // Default 2-arg commands w/ instructions
//  private HashMap<String, String> threeArgCmds; // Default 3-arg commands w/ instructions
//  private HashMap<String, ConvertFactory> conversionCmds; // Unique 2-arg commands w/ corresponding func objs
//  private final IMEModel model;
//  private final IMEView view;
//  private final Scanner sc;
//
//  private static final String imgNotFoundMsg = "Image name not found. " +
//          "Please create an image with that" +
//          " name, or enter again.\n";
//
//  public IMEControllerImpl(IMEModel model) throws FileNotFoundException {
//    this.twoArgCmds = new HashMap<>();
//    String loadInstr = "- To load an img from a file, type 'load <file_path> <img_name>'.\n";
//    this.twoArgCmds.put("load", loadInstr);
//    String saveInstr = "- To save an img to a file, type 'save <file_path> <img_name>'.\n";
//    this.twoArgCmds.put("save", saveInstr);
//
//    this.threeArgCmds = new HashMap<>();
//    String brightnessInstr = "- To chg brightness of the img, type 'brightness <value_chg>" +
//            "<img_former> <img_new>'.\n";
//    this.threeArgCmds.put("brightness", brightnessInstr);
//
//    this.conversionCmds = new HashMap<>();
//    this.conversionCmds.put("horizontal-flip", new ConvertFactory("horizontal-flip"));
//    this.conversionCmds.put("vertical-flip", new ConvertFactory("vertical-flip"));
////    this.conversionCmds.put("flip-both", new ConvertFactory("flip-both"));
//    this.conversionCmds.put("blue-component", new ConvertFactory("blue-component"));
//    this.conversionCmds.put("green-component", new ConvertFactory("green-component"));
//    this.conversionCmds.put("red-component", new ConvertFactory("red-component"));
//    this.conversionCmds.put("intensity-component", new ConvertFactory("intensity-component"));
//    this.conversionCmds.put("value-component", new ConvertFactory("value-component"));
//    this.conversionCmds.put("luma", new ConvertFactory("luma"));
//
//    Readable input = new InputStreamReader(System.in);
//    this.sc = new Scanner(input);
//
//    this.model = Objects.requireNonNull(model);
//    this.view = viewFac.createIMEView("standard",
//            this.twoArgCmds, this.threeArgCmds, this.conversionCmds);
//  }
//
//  public IMEControllerImpl(IMEModel model, IMEView view) {
//    this(model, view, new InputStreamReader(System.in));
//  }
//
//  public IMEControllerImpl(IMEModel model, IMEView view, Readable readable) {
//    this.model = Objects.requireNonNull(model);
//    this.view = Objects.requireNonNull(view);
//    this.sc = new Scanner(readable);
//  }
//
//  @Override
//  public void runIME() throws IllegalStateException, IOException {
//    this.view.printMenu();
//    while (true) {
//      this.view.renderMsg("\nPlease enter a command:\n");
//
//      String cmdEntry = getNextIfExists();
//      // Quit program (exit while loop)
//      if (cmdEntry.equals("quit")) {
//        break;
//      } else if (cmdEntry.equals("menu")) {
//        this.view.printMenu();
//      } else if (this.twoArgCmds.containsKey(cmdEntry)) {
//        this.handle2ArgCmds(cmdEntry);
//      } else if (this.threeArgCmds.containsKey(cmdEntry)) {
//        this.handle3ArgCmds(cmdEntry);
//      } else if (this.conversionCmds.containsKey(cmdEntry)) {
//        this.handleConversionCmds(cmdEntry);
//      } else { // Invalid command
//        this.view.renderMsg("Command not recognized. Please enter again.");
//      }
//    }
//  }
//
//  private void handle2ArgCmds(String cmdEntry) throws IOException {
//    String arg1 = getNextIfExists(); // File path
//    String arg2 = getNextIfExists(); // Image name
//    if (!arg1.contains(".")) {
//      this.view.renderMsg("File extension must be provided.");
//      return;
//    }
//
//    String[] splitByPeriod = arg1.split("\\.");
//    String imgFormat = splitByPeriod[splitByPeriod.length - 1];
//
//    switch (cmdEntry) {
//      case "load":
//        try {
//          Image newImg = imgFac.createImage(imgFormat, arg2, arg1);
//          this.model.addImage(newImg);
//        } catch (Exception e) {
//          String message = String.format("Img format %1$s is not supported.\n", imgFormat);
//          this.view.renderMsg(message);
//          return;
//        }
//        break;
//      case "save":
//        Image img = this.model.getImageFromModel(arg2);
//        if (img == null) { // If name is not in hashmap
//          this.view.renderMsg(imgNotFoundMsg);
//          return;
//        }
//        img.saveImageToFile(arg1);
//        break;
//      default: // Debug clause
//        throw new NoSuchElementException("ERROR: Command was not found in hashmap.");
//    }
//  }
//
//  private void handle3ArgCmds(String cmdEntry) throws IOException {
//    String arg1 = getNextIfExists(); // Integer value
//    String arg2 = getNextIfExists(); // Name of base image
//    String arg3 = getNextIfExists(); // New image name
//
//    Image img = this.model.getImageFromModel(arg2);
//    if (img == null) { // If name is not in hashmap
//      this.view.renderMsg(imgNotFoundMsg);
//      return;
//    }
//
//    Image newImg;
//    switch (cmdEntry) {
//      case "brightness":
//        newImg = img.changeBrightness(Integer.parseInt(arg1), arg3);
//        break;
//      default: // Debug clause
//        throw new NoSuchElementException("ERROR: Command was not found in hashmap.");
//    }
//    this.model.addImage(newImg);
//  }
//
//  private void handleConversionCmds(String cmdEntry) throws IOException {
//    String arg1 = getNextIfExists(); // Name of base image
//    String arg2 = getNextIfExists(); // New image name
//
//    Image img = this.model.getImageFromModel(arg1);
//    if (img == null) { // If name is not in hashmap
//      this.view.renderMsg(imgNotFoundMsg);
//      return;
//    }
//
//    ICommands conversionFuncObj = this.conversionCmds.get(cmdEntry).createConverter(arg2);
//    Image newImg = img.convertToViz(conversionFuncObj);
//    this.model.addImage(newImg);
//  }
//
//
//  private String getNextIfExists() {
//    if (!this.sc.hasNext()) { // Shouldn't happen for a System.in-based readable
//      throw new NoSuchElementException("There is nothing to read.");
//    }
//    return this.sc.next();
//  }
//}