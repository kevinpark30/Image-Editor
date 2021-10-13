package view;

import controller.Features;
import image.Image;
import image.Pixel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalBorders.ScrollPaneBorder;

/**
 * An implementation of a multi-layer image processing GUI application. This implementation has
 * menus on the top that provide all functionality in the Features interface. This implementation
 * shows the top most visible layer on the left and multiple ways to manipulate the program on the
 * right hand side.
 */
public class SimpleImageProcessingGUIView extends JFrame implements ImageProcessingGUIView {

  private JMenuItem createLayer;
  private JMenuItem deleteLayer;
  private JMenuItem current;
  private JMenuItem invisible;
  private JMenuItem visible;
  private JMenuItem blur;
  private JMenuItem sharpen;
  private JMenuItem sepia;
  private JMenuItem greyscale;
  private JMenuItem downscale;
  private JMenuItem mosaic;
  private JMenuItem load;
  private JMenuItem loadProject;
  private JMenuItem save;
  private JMenuItem saveProject;
  private JMenuItem loadCheckerboard;
  private JMenuItem loadScript;

  private JPanel imagePanel;
  private JLabel imageLabel;


  private JButton createLayerButton;
  private JButton deleteLayerButton;
  private JButton invisibleButton;
  private JButton visibleButton;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton downscaleButton;
  private JButton mosaicButton;

  private JTextField downscaleWidthText;
  private JTextField downscaleHeightText;
  private JTextField mosaicSeedsText;

  private JList<String> listOfLayers;
  private DefaultListModel<String> dataForListOfLayers;


  /**
   * Creates the GUI with the given title.
   * @param caption the title for the GUI application
   */
  public SimpleImageProcessingGUIView(String caption) {
    // Create the name of the application
    super(caption);

    JMenuBar menuBar;
    JMenu file;
    JMenu image;
    JMenu layers;

    JPanel mainPanel;
    JPanel layerButtonPanel;
    JPanel selectionListPanel;

    JScrollPane imageScrollPane;
    JPanel imageOperationPanel;

    JLabel downscaleWidth;
    JLabel downscaleHeight;
    JLabel mosaicSeeds;

    setSize(1200, 700);
    setLocation(200, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Create a menubar
    menuBar = new JMenuBar();

    // First menu in the menu bar with name "File"
    file = new JMenu("File");
    menuBar.add(file);

    // Create menu items for this first menu, "File"
    load = new JMenuItem("Load Image");
    load.setActionCommand("Load");
    file.add(load);

    loadProject = new JMenuItem("Load Project");
    loadProject.setActionCommand("Load Project");
    file.add(loadProject);

    save = new JMenuItem("Save Image");
    save.setActionCommand("Save");
    file.add(save);

    saveProject = new JMenuItem("Save Project");
    saveProject.setActionCommand("Save Project");
    file.add(saveProject);

    loadCheckerboard = new JMenuItem("Load Checkerboard Image");
    loadCheckerboard.setActionCommand("Load Checkerboard");
    file.add(loadCheckerboard);

    loadScript = new JMenuItem("Load Script");
    loadScript.setActionCommand("Load Script");
    file.add(loadScript);

    // Second menu in the menu bar with name "Image"
    image = new JMenu("Image");
    menuBar.add(image);

    // Create menu items for this second menu, "Image"
    blur = new JMenuItem("Blur");
    blur.setActionCommand("Blur");
    image.add(blur);

    sharpen = new JMenuItem("Sharpen");
    sharpen.setActionCommand("Sharpen");
    image.add(sharpen);

    sepia = new JMenuItem("Sepia");
    sepia.setActionCommand("Sepia");
    image.add(sepia);

    greyscale = new JMenuItem("Greyscale");
    greyscale.setActionCommand("Greyscale");
    image.add(greyscale);

    downscale = new JMenuItem("Downscale");
    downscale.setActionCommand("Downscale");
    image.add(downscale);

    mosaic = new JMenuItem("Mosaic");
    mosaic.setActionCommand("Mosaic");
    image.add(mosaic);

    // Create menu items for this third menu, "Layers"
    layers = new JMenu("Layers");
    menuBar.add(layers);

    // Create menu items for this third menu, "Layers"
    createLayer = new JMenuItem("Create Layer");
    createLayer.setActionCommand("Create");
    layers.add(createLayer);

    deleteLayer = new JMenuItem("Delete Layer");
    deleteLayer.setActionCommand("Delete");
    layers.add(deleteLayer);

    current = new JMenuItem("Set Current Layer");
    current.setActionCommand("Current");
    layers.add(current);

    invisible = new JMenuItem("Invisible");
    invisible.setActionCommand("Invisible");
    layers.add(invisible);

    visible = new JMenuItem("Visible");
    visible.setActionCommand("Visible");
    layers.add(visible);


    setJMenuBar(menuBar);

    // create panels
    mainPanel = new JPanel();

    mainPanel.setLayout(new FlowLayout());
    add(mainPanel);

    // add layer button panel
    layerButtonPanel = new JPanel();
    layerButtonPanel.setLayout(new BoxLayout(layerButtonPanel, BoxLayout.Y_AXIS));
    mainPanel.add(layerButtonPanel);
    layerButtonPanel.setPreferredSize(new Dimension(300, 500));
    layerButtonPanel.setBorder(BorderFactory.createTitledBorder(
        new ScrollPaneBorder(), "Layers", TitledBorder.CENTER, TitledBorder.TOP));

    // add selection list
    selectionListPanel = new JPanel();

    layerButtonPanel.add(selectionListPanel);
    dataForListOfLayers = new DefaultListModel<>();
    listOfLayers = new JList<>(dataForListOfLayers);
    listOfLayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    JScrollPane selectionPane = new JScrollPane(listOfLayers);
    selectionPane.setPreferredSize(new Dimension(200, 400));
    selectionListPanel.add(selectionPane);

    layerButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    // add buttons to layer button panel (create and delete layer buttons)
    createLayerButton = new JButton("Create Layer");
    createLayerButton.setActionCommand("Create Layer Button");
    layerButtonPanel.add(createLayerButton);
    createLayerButton.setPreferredSize(new Dimension(120, 20));
    createLayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    layerButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    deleteLayerButton = new JButton("Delete Layer");
    deleteLayerButton.setActionCommand("Delete Layer Button");
    layerButtonPanel.add(deleteLayerButton);
    deleteLayerButton.setPreferredSize(new Dimension(120, 20));
    deleteLayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    layerButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    // add visible and invisible buttons
    invisibleButton = new JButton(" Set Invisible ");
    layerButtonPanel.add(invisibleButton);
    invisibleButton.setPreferredSize(new Dimension(120, 20));
    invisibleButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    layerButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    visibleButton = new JButton("   Set Visible   ");
    layerButtonPanel.add(visibleButton);
    visibleButton.setPreferredSize(new Dimension(120, 20));
    visibleButton.setAlignmentX(Component.CENTER_ALIGNMENT);


    //show an image with a scrollbar
    imagePanel = new JPanel();
    imagePanel.setLayout(new BoxLayout(imagePanel,BoxLayout.Y_AXIS));

    mainPanel.add(imagePanel);
    imageLabel = new JLabel();

    imagePanel.setBorder(BorderFactory.createTitledBorder(
        new ScrollPaneBorder(), "Image", TitledBorder.CENTER, TitledBorder.TOP));
    imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(700, 500));
    imagePanel.add(imageScrollPane);

    // add image operations panel
    imageOperationPanel = new JPanel();
    imageOperationPanel.setLayout(new BoxLayout(imageOperationPanel,BoxLayout.Y_AXIS));
    mainPanel.add(imageOperationPanel);
    imageOperationPanel.setBorder(BorderFactory.createTitledBorder(
        new ScrollPaneBorder(), "Actions", TitledBorder.CENTER, TitledBorder.TOP));

    imageOperationPanel.setPreferredSize(new Dimension(120, 500));

    // image operation buttons (blur, sharpen, sepia, greyscale, downscale, mosaic)
    blurButton = new JButton("      Blur      ");
    blurButton.setPreferredSize(new Dimension(120, 30));
    blurButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(blurButton);

    imageOperationPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    sharpenButton = new JButton("  Sharpen   ");
    sharpenButton.setPreferredSize(new Dimension(120, 30));
    sharpenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(sharpenButton);

    imageOperationPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    sepiaButton = new JButton("     Sepia     ");
    sepiaButton.setPreferredSize(new Dimension(120, 30));
    sepiaButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(sepiaButton);

    imageOperationPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    greyscaleButton = new JButton(" Greyscale ");
    greyscaleButton.setPreferredSize(new Dimension(120, 30));
    greyscaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(greyscaleButton);

    imageOperationPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    downscaleButton = new JButton("Downscale");
    downscaleButton.setPreferredSize(new Dimension(120, 30));
    downscaleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(downscaleButton);

    JPanel downscalePanel = new JPanel();
    imageOperationPanel.add(downscalePanel);
    downscalePanel.setLayout(new GridLayout(2, 2));
    downscalePanel.setMaximumSize(new Dimension(100,50));

    downscaleWidth = new JLabel("Width:");
    downscalePanel.add(downscaleWidth);
    downscaleWidthText = new JTextField();
    downscalePanel.add(downscaleWidthText);
    downscaleHeight = new JLabel("Height:");
    downscalePanel.add(downscaleHeight);
    downscaleHeightText = new JTextField();
    downscalePanel.add(downscaleHeightText);

    imageOperationPanel.add(Box.createRigidArea(new Dimension(0, 20)));

    mosaicButton = new JButton("    Mosaic    ");
    mosaicButton.setPreferredSize(new Dimension(120, 20));
    mosaicButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    imageOperationPanel.add(mosaicButton);

    JPanel mosaicPanel = new JPanel();
    imageOperationPanel.add(mosaicPanel);
    mosaicPanel.setLayout(new GridLayout(1, 1));
    mosaicPanel.setMaximumSize(new Dimension(100,25));

    mosaicSeeds = new JLabel("Seeds:");
    mosaicPanel.add(mosaicSeeds);
    mosaicSeedsText = new JTextField();
    mosaicPanel.add(mosaicSeedsText);

    setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    createLayer.addActionListener(
        evt -> features.createLayer(JOptionPane.showInputDialog("Name of layer:")));
    deleteLayer.addActionListener(
        evt -> features.deleteLayer(JOptionPane.showInputDialog("Name of layer:")));
    current.addActionListener(
        evt -> features.setCurrentLayer(JOptionPane.showInputDialog("Name of layer:")));
    invisible.addActionListener(
        evt -> features.setCurrentLayerToInvisible(JOptionPane.showInputDialog("Name of layer:")));
    visible.addActionListener(
        evt -> features.setCurrentLayerToVisible(JOptionPane.showInputDialog("Name of layer:")));
    blur.addActionListener(evt -> features.applyBlurToCurrentLayer());
    sharpen.addActionListener(evt -> features.applySharpenToCurrentLayer());
    sepia.addActionListener(evt -> features.applySepiaToCurrentLayer());
    greyscale.addActionListener(evt -> features.applyGreyscaleToCurrentLayer());
    downscale.addActionListener(
        evt -> features.applyDownscaleToAllLayers(JOptionPane.showInputDialog("Width:"),
            JOptionPane.showInputDialog("Height:")));
    mosaic.addActionListener(
        evt -> features.applyMosaicToCurrentLayer(JOptionPane.showInputDialog("Seeds:")));
    load.addActionListener(evt -> features.loadImage(this.openFile()));
    loadProject.addActionListener(evt ->  {
      features.loadProject(this.openProject());

    });
    save.addActionListener(evt -> features.save(this.saveFile()));
    saveProject.addActionListener(evt -> features.saveProject(this.saveProject()));
    loadCheckerboard.addActionListener(
        evt -> features.loadCheckboardImage(JOptionPane.showInputDialog("Side Length:")));
    loadScript.addActionListener(evt -> features.loadScript(this.openScript()));
    listOfLayers
        .addListSelectionListener(evt -> {
          String name = listOfLayers.getSelectedValue();
          if (name == null) {
            // do nothing
          }
          else {
            if (name.length() >= 13 &&
                name.substring(name.length() - 12,name.length()).equals(" (Invisible)")) {
              name = name.substring(0,name.length() - 12);
              features.setCurrentLayer(name);
            }
            else {
              features.setCurrentLayer(name);
            }
          }
        });

    createLayerButton.addActionListener(
        evt -> features.createLayer(JOptionPane.showInputDialog("Name of layer:")));
    deleteLayerButton.addActionListener(
        evt -> features.deleteLayer(listOfLayers.getSelectedValue()));

    invisibleButton.addActionListener(evt -> {
      String name = listOfLayers.getSelectedValue();
      if (name != null && !name.contains("(Invisible)")) {
        features.setCurrentLayerToInvisible(name);
        dataForListOfLayers.setElementAt(name + " (Invisible)",listOfLayers.getSelectedIndex());
      }
    });

    visibleButton.addActionListener(evt -> {
      String name = listOfLayers.getSelectedValue();
      if (name != null) {
        if (name.length() >= 13 &&
            name.substring(name.length() - 12,name.length()).equals(" (Invisible)")) {
          name = name.substring(0,name.length() - 12);
          features.setCurrentLayerToVisible(name);
        }
        dataForListOfLayers.setElementAt(name,listOfLayers.getSelectedIndex());
      }

    });
    blurButton.addActionListener(evt -> features.applyBlurToCurrentLayer());
    sharpenButton.addActionListener(evt -> features.applySharpenToCurrentLayer());
    sepiaButton.addActionListener(evt -> features.applySepiaToCurrentLayer());
    greyscaleButton.addActionListener(evt -> features.applyGreyscaleToCurrentLayer());

    downscaleButton
        .addActionListener(evt ->  {
          features.applyDownscaleToAllLayers(downscaleWidthText.getText(),
              downscaleHeightText.getText());
          downscaleWidthText.setText("");
          downscaleHeightText.setText("");
        });
    mosaicButton
        .addActionListener(evt ->  {
          features.applyMosaicToCurrentLayer(mosaicSeedsText.getText());
          mosaicSeedsText.setText("");
        });
  }


  /**
   * Gets the path of the script file that is chosen in the JFileChooser.
   * @return the path of the script file
   */
  private String openScript() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Text Files", "txt");
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(SimpleImageProcessingGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getPath();
    } else if (retvalue == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return "";

  }

  /**
   * Gets the path of the directory of the project chosen in the JFileChooser.
   * @return the path of the project
   */
  private String saveProject() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int retvalue = fchooser.showSaveDialog(SimpleImageProcessingGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getPath();
    } else if (retvalue == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return "";

  }

  /**
   * Gets the path of the file saved in the JFileChooser.
   * @return the path of the saved file
   */
  private String saveFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int retvalue = fchooser.showSaveDialog(SimpleImageProcessingGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getPath();
    } else if (retvalue == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return "";
  }

  /**
   * Gets the path of the directory of the project that is opened in the JFileChooser.
   * @return the path of the project
   */
  private String openProject() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    int retvalue = fchooser.showOpenDialog(SimpleImageProcessingGUIView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getPath();
    } else if (retvalue == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return "";

  }

  /**
   * Gets the path of the file that is opened in the JFileChooser.
   * @return the path of the opened file
   */
  private String openFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "JPG, PNG, PPM Files", "jpg", "png", "ppm");

    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(SimpleImageProcessingGUIView.this);

    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getPath();
    } else if (retvalue == JFileChooser.CANCEL_OPTION) {
      return null;
    }

    return "";

  }

  @Override
  public void showTopMostImage(Image im, String name) {
    if (im == null) {
      imageLabel.setIcon(null);
      imagePanel.setBorder(BorderFactory.createTitledBorder(
          new ScrollPaneBorder(), "Image", TitledBorder.CENTER, TitledBorder.TOP));
    } else {
      int width = im.getWidth();
      int height = im.getHeight();
      List<List<Pixel>> pixels = im.getPixels();

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Pixel p = pixels.get(i).get(j);
          Color c = new Color(p.getRed(), p.getGreen(), p.getBlue());
          image.setRGB(j, i, c.getRGB());
        }
      }

      imagePanel.setBorder(BorderFactory.createTitledBorder(
          new ScrollPaneBorder(), name, TitledBorder.CENTER, TitledBorder.TOP));
      imageLabel.setIcon(new ImageIcon(image));

      setVisible(true);
    }
  }

  @Override
  public void addToLayerSelectionList(String name) {
    if (name == null) {
      return;
    }
    dataForListOfLayers.addElement(name);

  }

  @Override
  public void removeFromLayerSelectionList(String name) {
    if (name == null) {
      return;
    }
    dataForListOfLayers.removeElement(name);
  }

  @Override
  public void notifyErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Dialog",
        JOptionPane.ERROR_MESSAGE);
  }


}
