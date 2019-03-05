package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.AirflowDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditAirflowDownMapAction extends AbstractEditorAction {

  public EditAirflowDownMapAction(Editor editor) {
    super(editor, "Airflow Down", "Set airflow to down");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, 0));
  }

  protected void doPerformAction() {
    editor.setAirflow(AirflowDirection.DOWN);
  }
}
