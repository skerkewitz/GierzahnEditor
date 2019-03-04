package de.gierzahn.swing.action;

import de.gierzahn.editor.map.Map;
import de.gierzahn.editor.map.io.DefaultMapReader;
import de.gierzahn.swing.JEditorPanel;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Creates a new map.
 */
public class FileNewMapAction extends AbstractEditorAction {

  private final JEditorPanel jEditorPanel;

  public FileNewMapAction(JEditorPanel jEditorPanel) {
    super(jEditorPanel.editor, "New Map", "Create a new map");
    this.jEditorPanel = jEditorPanel;
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control N"));
  }

  protected void doPerformAction() {

    editor.reset();
    jEditorPanel.reset();

    /* Try to load _init.map */
    var writer = new DefaultMapReader();
    try {
      Map map = writer.read(new FileInputStream(new File("_init.map")));
      map.setName(null);
      editor.setMap(map);
      //mainFrame.updateTitle();
    } catch (IOException e1) {
      e1.printStackTrace();
    }
  }
}
