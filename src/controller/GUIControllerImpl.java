package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import factory.ImageFactory;
import model.IMEModel;
import model.Image;
import view.IGUIView;
import view.ViewListener;

public class GUIControllerImpl implements IGUIController, ViewListener {
  private final IMEModel model;
  private final IGUIView view;
  private Image selectedImage;
  private String selectedImageName = "";
  private final List<String> images;

  public GUIControllerImpl(IMEModel model, IGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addListener(this);
    this.selectedImage = null;
    this.images = new ArrayList<>();
  }

  @Override
  public void go() {
    this.view.showView(true);
  }

  @Override
  public void performCommand() {
    this.view.refresh(selectedImage);
  }

  @Override
  public void loadFileEvent(String name) {
    this.model.addImage(name,new ImageFactory().createImage(name));
    this.selectImageEvent(name);
    this.images.add(name);
    this.view.refresh(this.selectedImage);
  }

  @Override
  public void saveFileEvent(String name) {
    this.selectedImage.saveImageToFile(name);
  }

  @Override
  public void selectImageEvent(String name) {
    this.switchImage(name);
    this.view.refresh(this.selectedImage);
  }

  private void switchImage(String imageName) {
    this.selectedImage = this.model.getImageFromModel(imageName);
    this.selectedImageName = imageName;
  }

  @Override
  public List<String> getImageNames() {
    return this.images;
  }

  @Override
  public void commandEvent(String actionCommand) {
    this.model.applyCommand(actionCommand,selectedImageName,selectedImageName);
    this.selectedImage = this.model.getImageFromModel(selectedImageName);
    this.view.refresh(selectedImage);
  }
}
