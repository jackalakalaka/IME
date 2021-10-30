package model;

import java.util.HashMap;
import java.util.Objects;

public class ModelImpl implements IModel{
  private final HashMap<String, iImage> images;

  public ModelImpl(HashMap<String, iImage> images) {
    this.images = Objects.requireNonNull(images);
  }

  @Override
  public iImage getImageFromModel(String imageName) {
    return images.get(imageName);
  }

  @Override
  public void addImage(String name, iImage iImage) {
    this.images.put(name, iImage);
  }
}
