# Image Manipulation & Enhancement (IME)
## Changes in pt2
- In order to support a script entry to the app, we added a corresponding function class `CommandScript` that converts a given filepath to a readable for the controller.
  - Additional tests for this were written in the controller test class `IMEControllerImplTest`.
- To integrate the new commands (blur, sharpening, greyscale, and sepia), we further implemented the `ICommands` interface in abstract-class-extending function classes.
  - Tests for the new commands were appended to the function object testing class.
  - The new commands are by default initialized by the model implementation's constructor.
- Additional file formats were added through extension of the Image interface. Instead of inserting an abstract class into the mix, the format similarities and support from teh ImageIO library allowed the PNG and BMP classes to extend the JPG one with minimal add-ons.
  - Conversion between these was tested via script in the controller test class.
  - The workings of each variant of image class was abstractable, and so an abstract image test class `ImageTest` was created to be extended by the file format image implementations' corresponding testing classes. 
  - ImageFactory was implemented and its construction cases populated for each file format to support.
    - It is also utilized in the ImageTest abstract class and its children.
## Changes in pt3
- The main method was altered to accept no arguments for a GUI version of the pap, 1 to specify that one wants to use the text version, or 2 arguments that specify a script with which to run the program.
  - If any disallowed arguments (as documented in the USEME) or more than 2 arguments are input, the program will not run.
- In order to both apply commands that require a storage and images model, as well as listen for input events to the GUI, another implementation of a controller was created. It accounts for the much-differing version of the app that employs a GUI.
- A GUI-based view implementation, extending swing's JFrame, was designed. This view adds an object implementing the ViewListener interface which, in this case, was implemented by the GUI-based controller.
  - The ViewListener interface functions by listening to the IME-specific view for command inputs. It serves to separate the view's user interaction from the data model of the program.
- A single histogram class for each color, or intensity, component and their respective value frequencies of a given (selected) image was added. It extends JPanel, which allows it to be painted as a component by a JFrame.

## Code Overview

### Source Code

- **Class ImageManipulationEnhancement**
    - The main class for initializing and running the controller.
- Package _controller_:
    - **Interface IMEController**
        - Interface for controllers in IME.
        - **Class IMEControllerGUI**
        - This is the controller class for IME's GUI version.
        - **Class IMEControllerText**
          - This is the controller class for IME's text version.
- Package _model_:
    - Package _funcobjs_:
        - **Interface ICommands**
            - An interface for command function objects. Can take in a subclass-decided amount of
              arguments.
            - **Abstract Class ACommandDimension**
                - An abstract class for function objects that work with dimensions.
                - **Class ConvertByHorizontal**
                    - A function object for getting the pixel on the other side of the image
                      horizontally.
                - **Class ConvertByVertical**
                    - A function object for getting the pixel on the other side of the image
                      vertically.
            - **Abstract Class ACommandImageOp**
              - An abstract class for function objects that require the whole image to work.
              - **Class CommandBlur**
                - This function object creates a blurred version of the pixel.
              - **Class CommandSharpen**
                - Function object for sharpening an image pixel by pixel.
              - **Class CommandSepia**
                - Function object that returns the sepia version of a given pixel.
            - **Abstract Class ACommandsAbsolute**
                - An abstract class for function objects that get a single value from pixels.
                - **Class CommandsRed**
                    - A function object for getting the red value of a pixel.
                - **Class CommandsBlue**
                    - A function object for getting the blue value of a pixel.
                - **Class CommandsGreen**
                    - A function object for getting the green value of a pixel.
                - **Class CommandsIntensity**
                    - A function object for getting the intensity of a pixel.
                - **Class CommandsValue**
                    - A function object for getting the value of a pixel.
                - **Class CommandGreyscale**
                  - A function object that returns the greyscale version of a pixel.
            - **Abstract Class ACommandsMultiple**
                - An abstract class for function objects that get multiple values from pixels.
                - **Class CommandsLuma**
                    - A function object for getting the luma of a pixel.
    - Package _image_:
      - **Interface Image**
        - Interface for representing images.
        - **Abstract AbstractImage**
          - Code reuse for image implementations.
          - **Class ImagePPM**
              - Representation of an image from a PPM file.
          - **Class ImageJPG**
              - Representation of an image from a JPG file.
          - **Class ImagePNG**
              - Representation of an image from a PNG file.
          - **Class ImageBMP**
              - Representation of an image from a BMP file.
        - **Interface IPixel**
          - An interface for pixels of an image.
          - **Class Pixel**
              - This class represents an immutable pixel.
          - **Class MockPixel**
              - Simulates a pixel for the more simplistic testing of Image and IMEModel classes.
                Simplifies pixel construction and assertion.
      - **Interface IMEModel**
          - Interface for models used by IME.
          - **Class IMEModelImpl**
              - A representation of an IME model.
- Package _factory_:
    - **Class IMEModelFactory**
        - Creates a new IMEModel implementation object.
- Package _view_:
    - **Interface IMEView**
        - General view interface.
        - **Interface IMEGUIView**
          - GUI-based view interface.
          - **Class IMEViewGUI**
            - GUI-based view implementation.
        - **Interface IMETextView
          - Text-based view interface.
          - **Class IMEViewText**
            - Text-based view implementation.
    - **Interface ViewListener**
        - Functionality for listening to a GUI-based view implementation.
    - **Class Histogram**
        - Image-specific JPanel object that can be multiplied to show component composition of an image.

### Test Code

- Package _controller_:
  - **Class IMEControllerGUILogging**
      - Test decorator class for a IMEControllerGUI object.
  - **Class IMEControllerGUITest**
      - Test class for a GUI-based controller implementation.
  - **Class IMEControllerTextTest**
    - Test class for a text-based controller implementation.
- Package _model_:
    - **Abstract Class ImageTest**
      - **Class ImagePPMTest**
        - Tests ImagePPM class's utility methods. Also checks that null cannot be input to methods,
          including the constructor.
      - **Class ImageJPGTest**
        - Tests ImageJPG class's utility methods. Also checks that null cannot be input to methods,
          including the constructor.
      - **Class ImagePNGTest**
          - Tests ImagePNG class's utility methods. Also checks that null cannot be input to methods,
            including the constructor.
      - **Class ImageBMPTest**
          - Tests ImageBMP class's utility methods. Also checks that null cannot be input to methods,
            including the constructor.
    - **Class FunctionObjectTests**
        - Test class for commands' function objects.
    - **Class IMEModelTest**
      - Tests IMEModelImpl class using standard assertions as well as multiple method calls in a
      single test method. Uses mock pixels for instantiating an image.
    - **Class PixelTest**
      - Employs a variety of looping and hashmap-factory methods to automate similar calls and
      simplify assertions, respectively. Tests the Pixel class.
    - **Class MockPixel**
      - Simulates a pixel for the more simplistic testing of Image and IMEModel classes. Simplifies
      pixel construction and assertion.
- Package _view_:
  - **Class HistogramTest**
    - Tests histogram construction inputs.
  - **Class IMEViewGUITest**
    - Tests the GUI-based view.
  - **Class IMEViewTextTest**
    - Tests the text-based view, which takes in or has a default list of commands that give the menu. The
    view also allows system messaging whose string appends are tested.
  - **Class InvalidMockAppendable**
    - This is a custom-made mock for an appendable passed in during construction of a view. Its
    only use, at the moment, is that it throws an exception when its append method is called.

## Image License

Title: "Goat Mammal Cute Animal Domestic Farm Funny"

Author: [Max Pixel](https://www.maxpixel.net/)

Source: https://www.maxpixel.net/Goat-Mammal-Cute-Animal-Domestic-Farm-Funny-3471349

License: CC BY 2.0
