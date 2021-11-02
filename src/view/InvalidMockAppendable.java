package view;

import java.io.IOException;

/**
 * This is a custom-made mock for an appendable passed in during construction of a view.
 * Its only use, at the moment, is that it throws an exception when its append method is called.
 */
public class InvalidMockAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("I solely was created for testing. Isn't that a pitiful existence?");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("I solely was created for testing. Isn't that a pitiful existence?");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("I solely was created for testing. Isn't that a pitiful existence?");
  }
}

