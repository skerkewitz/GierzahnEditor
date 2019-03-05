package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.AirflowDirection;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Mirrors the map around the Y axis.
 */
public class EnemyPlaceEnemyAddMapAction extends AbstractEditorAction {

  public EnemyPlaceEnemyAddMapAction(Editor editor) {
    super(editor, "Place Enemy", "Place enemy at cursor position");
    putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, 0));
  }

  protected void doPerformAction() {
    editor.placeEnemy();
  }
}
