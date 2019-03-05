package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import java.awt.event.KeyEvent;

import static javax.swing.KeyStroke.getKeyStroke;

/**
 * Mirrors the map around the Y axis.
 */
public class EditCursorLeftMapAction extends AbstractEditorAction {

  public EditCursorLeftMapAction(Editor editor) {
    super(editor, "Cursor Left", "Moves the cursor left");
    putValue(ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_LEFT, 0));
  }

  protected void doPerformAction() {
    editor.moveCursorLeft();
  }
}
