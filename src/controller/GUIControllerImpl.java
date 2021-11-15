package controller;

import java.util.Objects;

import model.IMEModel;
import view.IGUIView;
import view.ViewListener;

public class GUIControllerImpl implements IGUIController, ViewListener {
  private final IMEModel model;
  private final IGUIView view;

  public GUIControllerImpl(IMEModel model, IGUIView view) {
    this.model = Objects.requireNonNull(model);
    this.view = Objects.requireNonNull(view);
    this.view.addListener(this);
  }

  @Override
  public void go() {
    this.view.showView(true);
  }

  @Override
  public void saveDataEvent(String data) {

  }

  @Override
  public void retrieveData() {

  }
}
