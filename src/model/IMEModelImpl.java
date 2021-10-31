package model;

import java.util.HashMap;
import java.util.Objects;

public class IMEModelImpl implements IMEModel {
  private final HashMap<String, Image> images;

  public IMEModelImpl() {
    this.images = new HashMap<>();
  }

  public IMEModelImpl(HashMap<String, Image> images) {
    this.images = Objects.requireNonNull(images);
  }

  @Override
  public Image getImageFromModel(String imageName) {
    return images.get(imageName);
  }

  @Override
  public void addImage(Image image) {
    this.images.put(image.getName(), image);
  }
}
