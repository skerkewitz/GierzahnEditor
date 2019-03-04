package de.gierzahn.swing.action;

import de.gierzahn.swing.JEditorPanel;
import de.gierzahn.swing.JMainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileOpenBackdropAction extends AbstractEditorAction {

  private static final Logger logger = LogManager.getLogger(JEditorPanel.class);

  private final JMainFrame mainFrame;
  private final JEditorPanel jEditorPanel;

  public FileOpenBackdropAction(JMainFrame mainFrame, JEditorPanel jEditorPanel) {
    super(jEditorPanel.editor, "Open Backdrop Image...", "Open an existing image and add it as backdrop");
    this.mainFrame = mainFrame;
    this.jEditorPanel = jEditorPanel;
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
  }

  protected void doPerformAction() {
    var fileDialo = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
    int result = fileDialo.showOpenDialog(mainFrame);
    if (result == JFileChooser.APPROVE_OPTION) {
      final File selectedFile = fileDialo.getSelectedFile();
      logger.info("Will open backdrop image file: " + selectedFile.getAbsolutePath());

      try {
        var backdrop = ImageIO.read(selectedFile);

        jEditorPanel.setBackdropImage(backdrop);
      } catch (IOException e) {
        logger.error(e,e);
      }
    }
  }
}
