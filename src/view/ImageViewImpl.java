package view;

import java.util.Objects;

import model.ImageModel;

/**
 * Implementation of an image viewer.
 */
public class ImageViewImpl implements ImageView {
  private ImageModel model;
  private Appendable appendable;

  /**
   * Single input constructor.
   *
   * @param model The model that is represented.
   */
  public ImageViewImpl(ImageModel model) {
    this(model,System.out);
  }

  /**
   * Two input constructor for the view.
   *
   * @param model The model that is represented.
   * @param appendable The appendable for the model or messages.
   */
  public ImageViewImpl(ImageModel model, Appendable appendable) {
    Objects.requireNonNull(model);
    Objects.requireNonNull(appendable);
    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public String createImage(ImageModel model) {
    return "null";
  }
}
