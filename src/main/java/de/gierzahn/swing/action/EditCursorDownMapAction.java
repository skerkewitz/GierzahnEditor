package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EditCursorDownMapAction extends AbstractEditorAction {

  public EditCursorDownMapAction(Editor editor) {
    super(editor, "Cursor Down", "Moves the cursor down");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0));
  }

  protected void doPerformAction() {
    editor.moveCursorDown();
  }
}
