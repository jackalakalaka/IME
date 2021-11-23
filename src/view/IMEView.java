package view;

/**
 * User-side perspective of IME. Should at least support sending a system message.
 */
public interface IMEView {
  /**
   * Renders a message to the view's appendable.
   *
   * @param str The message to be appended.
   */
  void renderMsg(String str);
}
