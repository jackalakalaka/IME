package view;

public interface IGUIView {

  void addListener(ViewListener viewListener);
  void requestViewFocus();
  void showView(boolean show);
}
