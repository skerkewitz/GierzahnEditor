package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.AirflowDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditAirflowUpMapAction extends AbstractEditorAction {

  public EditAirflowUpMapAction(Editor editor) {
    super(editor, "Airflow Up", "Set airflow to up");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_W, 0));
  }

  protected void doPerformAction() {
    editor.setAirflow(AirflowDirection.UP);
  }
}
