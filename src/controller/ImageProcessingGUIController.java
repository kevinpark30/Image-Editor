package controller;

import java.io.StringReader;
import model.ColorTransformation;
import model.EnhancedMultiLayerImageProcessingModel;
import model.Filter;
import model.Layer;
import view.ImageProcessingGUIView;

/**
 * Represents the controller for an image processing application GUI that supports features
 * found in the Features interface.
 */
public class ImageProcessingGUIController implements Features {
  EnhancedMultiLayerImageProcessingModel model;
  ImageProcessingGUIView view;

  /**
   * Creates a controller with the given model.
   * @param model the model
   * @throws IllegalArgumentException if the model is null
   */
  public ImageProcessingGUIController(EnhancedMultiLayerImageProcessingModel model) throws
      IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("The model or view given was null.");
    }
    this.model = model;
  }

  /**
   * Sets the view that is used for this controller to the view given.
   * @param view the view to be used
   */
  public void setView(ImageProcessingGUIView view) {
    this.view = view;
    //provide view with all the callbacks
    this.view.addFeatures(this);
  }

  @Override
  public void createLayer(String name) {
    try {
      model.createLayer(name);
      view.addToLayerSelectionList(name);

      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.showTopMostImage(null,"");
    }
  }

  @Override
  public void deleteLayer(String name) {
    try {
      model.deleteLayer(name);
      view.removeFromLayerSelectionList(name);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());

    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.showTopMostImage(null,"");
    }
  }

  @Override
  public void setCurrentLayer(String name) {
    try {
      model.setCurrentLayer(name);
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void setCurrentLayerToInvisible(String name) {
    try {
      model.setInvisible(name);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.showTopMostImage(null,"");
    }
  }

  @Override
  public void setCurrentLayerToVisible(String name) {
    try {
      model.setVisible(name);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.showTopMostImage(null,"");
    }
  }

  @Override
  public void applyBlurToCurrentLayer() {
    try {
      model.imageFilter(Filter.BLUR);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void applySharpenToCurrentLayer() {
    try {
      model.imageFilter(Filter.SHARPEN);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void applySepiaToCurrentLayer() {
    try {
      model.colorTransformation(ColorTransformation.SEPIA);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void applyGreyscaleToCurrentLayer() {
    try {
      model.colorTransformation(ColorTransformation.GREYSCALE);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void applyDownscaleToAllLayers(String width, String height) {
    try {
      Integer.parseInt(width);
      Integer.parseInt(height);
    } catch (NumberFormatException e) {
      view.notifyErrorMessage("Width or Height must be a number.");
      return;
    }
    try {
      model.downscaling(Integer.parseInt(width),Integer.parseInt(height));
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }
  }

  @Override
  public void applyMosaicToCurrentLayer(String seeds) {
    try {
      Integer.parseInt(seeds);
    } catch (NumberFormatException e) {
      view.notifyErrorMessage("Seeds must be a number.");
      return;
    }
    try {
      model.mosaicing(Integer.parseInt(seeds));
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }

  @Override
  public void loadImage(String filepath) {
    try {
      new Load(filepath).run(model);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }


  }

  @Override
  public void loadProject(String filepath) {
    try {
      for (Layer l : model.getLayers()) {
        view.removeFromLayerSelectionList(l.getName());
      }

      new LoadProject(filepath).run(model);


      for (Layer l : model.getLayers()) {
        String name = l.getName();
        if (l.getVisibility()) {
          view.addToLayerSelectionList(name);
        }
        else {
          view.addToLayerSelectionList(name + " (Invisible)");
        }
      }
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }

  @Override
  public void save(String filepath) {
    try {
      new Save(filepath).run(model);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }

  @Override
  public void saveProject(String filepath) {
    try {
      new SaveProject(filepath).run(model);
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }

  @Override
  public void loadCheckboardImage(String length) {
    try {
      Integer.parseInt(length);
    } catch (NumberFormatException e) {
      view.notifyErrorMessage("Side Length must be a number.");
      return;
    }

    try {
      new LoadCheckerboard(length).run(model);
      view.showTopMostImage(model.exportImage(), model.topMostVisibleLayerName());
    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }

  @Override
  public void loadScript(String filepath) {
    if (filepath == null) {
      view.notifyErrorMessage("Script must be given.");
      return;
    }
    try {
      new SimpleMultiLayerImageProcessingController(model,
          new StringReader("S \n" + filepath),new StringBuilder()).run();

    } catch (IllegalArgumentException e) {
      view.notifyErrorMessage(e.getMessage());
    } catch (IllegalStateException e) {
      view.notifyErrorMessage(e.getMessage());
    }

  }
}
