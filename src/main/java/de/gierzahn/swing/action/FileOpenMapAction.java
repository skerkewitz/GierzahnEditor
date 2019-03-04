package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.Map;
import de.gierzahn.editor.map.io.DefaultMapReader;
import de.gierzahn.swing.JEditorPanel;
import de.gierzahn.swing.JMainFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class FileOpenMapAction extends AbstractEditorAction {

  private static final Logger logger = LogManager.getLogger(JEditorPanel.class);

  private final JMainFrame mainFrame;

  public FileOpenMapAction(JMainFrame mainFrame, Editor editor) {
    super(editor, "Open Map", "Open an existing map file");
    this.mainFrame = mainFrame;
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control O"));
  }

  protected void doPerformAction() {
    var fileDialo = new JFileChooser(Paths.get(".").toAbsolutePath().normalize().toString());
    int result = fileDialo.showOpenDialog(mainFrame);
    if (result == JFileChooser.APPROVE_OPTION) {
      final File selectedFile = fileDialo.getSelectedFile();
      logger.info("Will open map file: " + selectedFile.getAbsolutePath());

      try {
        final var writer = new DefaultMapReader();
        final Map map = writer.read(new FileInputStream(selectedFile));
        map.setName(selectedFile.getName());
        editor.setMap(map);
        mainFrame.updateTitle();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }
}
