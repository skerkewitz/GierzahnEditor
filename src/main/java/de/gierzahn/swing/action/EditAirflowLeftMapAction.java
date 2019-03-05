package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.AirflowDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditAirflowLeftMapAction extends AbstractEditorAction {

  public EditAirflowLeftMapAction(Editor editor) {
    super(editor, "Airflow Left", "Set airflow to left");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, 0));
  }

  protected void doPerformAction() {
    editor.setAirflow(AirflowDirection.LEFT);
  }
}
