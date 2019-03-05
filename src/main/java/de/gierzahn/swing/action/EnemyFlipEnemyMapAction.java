package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EnemyFlipEnemyMapAction extends AbstractEditorAction {

  public EnemyFlipEnemyMapAction(Editor editor) {
    super(editor, "Flip Enemy", "Flip the enemy around the Y axis");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, 0));
  }

  protected void doPerformAction() {
    editor.flipEnemy();
  }
}
