package controller;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

import factory.ImageFactory;
import model.IMEModel;
import model.Image;
import view.IGUIView;
import view.ViewListener;

public class GUIControllerImpl implements IGUIController, ViewListener {
  private final IMEModel model;
  private final IGUIView view;
  private Image selectedImage;
  private List<String> images;

  public GUIControllerImpl(IMEModel model, IGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addListener(this);
    this.selectedImage = null;
    this.images = new Vector<>();
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
  public void switchImage(String imageName) {
    this.selectedImage = this.model.getImageFromModel(imageName);
  }

  @Override
  public void loadFileEvent(String name) {
    this.model.addImage(name,new ImageFactory().createImage(name));
    this.switchImage(name);
    this.images.add(name);
    this.view.refresh(this.selectedImage);
  }

  @Override
  public void saveFileEvent(String name) {
    this.selectedImage.saveImageToFile(name);
  }

  @Override
  public void selectImageEvent(String name) {
    this.selectedImage = this.model.getImageFromModel(name);
    this.view.refresh(this.selectedImage);
  }

  @Override
  public List<String> getImageNames() {
    return this.images;
  }
}
