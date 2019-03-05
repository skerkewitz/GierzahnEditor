package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EnemyRemoveEnemyMapAction extends AbstractEditorAction {

  public EnemyRemoveEnemyMapAction(Editor editor) {
    super(editor, "Remove Enemy", "Remove the enemy at cursor position");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0));
  }

  protected void doPerformAction() {
    editor.removeEnemy();
  }
}
