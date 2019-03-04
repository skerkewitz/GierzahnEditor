package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;

/**
 * Mirrors the map around the Y axis.
 */
public class EditAutosetAirflowMapAction extends AbstractEditorAction {

  public EditAutosetAirflowMapAction(Editor editor) {
    super(editor, "Autoset Airflow", "Automatically fill the map with airflow");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("control shift A"));
  }

  protected void doPerformAction() {
    editor.autofillAirflow();
  }
}
