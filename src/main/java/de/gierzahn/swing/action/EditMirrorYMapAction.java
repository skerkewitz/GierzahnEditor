package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;

/**
 * Mirrors the map around the Y axis.
 */
public class EditMirrorYMapAction extends AbstractEditorAction {

  public EditMirrorYMapAction(Editor editor) {
    super(editor, "Mirror Y", "Mirrors the map around the Y axis");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F5"));
  }

  protected void doPerformAction() {
    editor.mirrorX();
  }
}
