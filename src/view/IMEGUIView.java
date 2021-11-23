package view;

import model.image.Image;

/**
 * Interface for a GUI-based view.
 */
public interface IMEGUIView extends IMEView {

  /**
   * Adds a listener to this view.
   * @param viewListener view listener
   */
  void addListener(ViewListener viewListener);

  /**
   * Sets the view to be visible/interactive or not.
   * @param show whether the view should be visible
   */
  void showView(boolean show);

  /**
   * Updates information registered by the view.
   * @param selectedImage current img being manipulated
   */
  void refresh(Image selectedImage);
}
