package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ModelImpl implements IModel{
  private final HashMap<String,ImageModel> images;

  public ModelImpl(HashMap<String,ImageModel> images) {
    this.images = Objects.requireNonNull(images);
  }


  @Override
  public ImageModel getImageFromModel(String imageName) {
    return images.get(imageName);
  }

  @Override
  public void addImage(String name,ImageModel imageModel) {
    this.images.put(name, imageModel);
  }
}
