package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.AirflowDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditAirflowRightMapAction extends AbstractEditorAction {

  public EditAirflowRightMapAction(Editor editor) {
    super(editor, "Airflow Right", "Set airflow to right");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, 0));
  }

  protected void doPerformAction() {
    editor.setAirflow(AirflowDirection.RIGHT);
  }
}
