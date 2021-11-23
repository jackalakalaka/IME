package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import factory.ImageFactory;
import model.IMEModel;
import model.IMEModelImpl;
import model.image.Image;
import view.IMEGUIView;
import view.IMEViewGUI;
import view.ViewListener;

/**
 * Controller for a GUI-based IME program. Uses command handlers and serves as a view listener for
 * its passed view.
 */
public class IMEControllerGUI implements IMEController, ViewListener {
  private final List<String> images = new ArrayList<>();

  private final IMEModel model;
  private final IMEGUIView view;
  private Image selectedImg;
  private String selectedImgNm = "";

  /**
   * Default constructor.
   */
  public IMEControllerGUI() {
    this.model = new IMEModelImpl();
    this.view = new IMEViewGUI();
    this.view.addListener(this);
  }

  /**
   * Customized constructor.
   */
  public IMEControllerGUI(IMEModel model, IMEGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addListener(this);
  }

  @Override
  public void runIME() {
    this.view.showView(true);
  }

  @Override
  public void loadFileEvent(String filepath, String name) {
    try {
      this.model.addImage(name, new ImageFactory().createImage(filepath));
      this.images.add(name);
      this.selectImageEvent(name);
      this.view.renderMsg("Load successful!");
      this.view.refresh(this.selectedImg);
    } catch (IllegalArgumentException iAE) {
      this.view.renderMsg(iAE.getMessage());
    }
  }

  @Override
  public void saveFileEvent(String filepath) {
    this.selectedImg.saveImageToFile(filepath);
  }

  @Override
  public void selectImageEvent(String name) {
    this.switchImage(name);
    this.view.refresh(this.selectedImg);
  }

  /**
   * Switch image to that with the given name.
   * @param imageName new image name
   */
  private void switchImage(String imageName) {
    this.selectedImg = this.model.getImageFromModel(imageName);
    this.selectedImgNm = imageName;
  }

  @Override
  public List<String> getImageNames() {
    return this.images;
  }

  @Override
  public void commandEvent(String actionCommand) {
    // Modify under original name until this loaded image's name is overwritten.
    this.model.applyCommand(actionCommand, this.selectedImgNm, this.selectedImgNm);

    this.selectedImg = this.model.getImageFromModel(this.selectedImgNm);
    this.view.refresh(this.selectedImg);
  }
}
