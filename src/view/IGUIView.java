package view;

import model.Image;

public interface IGUIView {

  void addListener(ViewListener viewListener);
  void requestViewFocus();
  void showView(boolean show);
  void refresh(Image selectedImage);
}
