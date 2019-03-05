package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditCursorUpMapAction extends AbstractEditorAction {

  public EditCursorUpMapAction(Editor editor) {
    super(editor, "Cursor Up", "Moves the cursor up");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0));
  }

  protected void doPerformAction() {
    editor.moveCursorUp();
  }
}
