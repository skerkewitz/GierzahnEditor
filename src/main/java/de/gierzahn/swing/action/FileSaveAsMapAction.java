package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.io.DefaultMapWriter;
import de.gierzahn.swing.JEditorPanel;
import de.gierzahn.swing.JMainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class FileSaveAsMapAction extends AbstractEditorAction {

  private static final Logger logger = LogManager.getLogger(JEditorPanel.class);

  private final JMainFrame mainFrame;

  public FileSaveAsMapAction(JMainFrame mainFrame, Editor editor) {
    super(editor, "Save as ...", "Saves the map under a new file name");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("shift control S"));
    this.mainFrame = mainFrame;
  }

  protected void doPerformAction() {
    var fileDialo = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
    int result = fileDialo.showSaveDialog(mainFrame);
    if (result == JFileChooser.APPROVE_OPTION) {
      final File selectedFile = fileDialo.getSelectedFile();
      logger.info("Will save map as file: " + selectedFile.getAbsolutePath());

      var writer = new DefaultMapWriter();
      try {
        writer.write(editor.getMap(), new FileOutputStream(selectedFile));
        editor.getMap().setName(selectedFile.getName());
        mainFrame.updateTitle();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }
}
