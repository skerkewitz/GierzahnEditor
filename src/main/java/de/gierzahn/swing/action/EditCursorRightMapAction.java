package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import java.awt.event.KeyEvent;

import static javax.swing.KeyStroke.getKeyStroke;

/**
 * Mirrors the map around the Y axis.
 */
public class EditCursorRightMapAction extends AbstractEditorAction {

  public EditCursorRightMapAction(Editor editor) {
    super(editor, "Cursor Right", "Moves the cursor right");
    putValue(ACCELERATOR_KEY, getKeyStroke(KeyEvent.VK_RIGHT, 0));
  }

  protected void doPerformAction() {
    editor.moveCursorRight();
  }
}
