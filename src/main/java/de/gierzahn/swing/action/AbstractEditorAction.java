package de.gierzahn.swing.action;

import de.gierzahn.editor.Editor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Provides a common abstract class for actions that modify the layer
 * configuration. It makes sure the undo/redo information is properly
 * maintained.
 * <p>
 * todo: These actions will need to listen to changing of the current selected
 * todo: layer index as well as changes to the opened map. Action should always
 * todo: be disabled when no map is opened. More specific checks should be
 * todo: included in subclasses.
 *
 */
public abstract class AbstractEditorAction extends AbstractAction {

  protected final Editor editor;

  protected AbstractEditorAction(Editor editor, String name, String description) {
    super(name);
    putValue(SHORT_DESCRIPTION, description);
    putValue(ACTION_COMMAND_KEY, name);
    this.editor = editor;
  }

  protected AbstractEditorAction(Editor editor, String name, String description, Icon icon) {
    this(editor, name, description);
    putValue(SMALL_ICON, icon);
  }

  /**
   * Wraps {@link #doPerformAction} in order to capture the layer vector
   * before and after the action is performed.
   */
  public final void actionPerformed(ActionEvent e) {
//    // Capture the layers before the operation is executed.
//    Map map = editor.getCurrentMap();
//    var layersBefore = new ArrayList<>(map.getLayers());
//
    doPerformAction();
//
//    // Capture the layers after the operation is executed and create the
//    // layer state edit instance.
//    var layersAfter = new ArrayList<>(map.getLayers());
//    MapLayerStateEdit mapLayerStateEdit = new MapLayerStateEdit(map, layersBefore, layersAfter, e.getActionCommand());
//    editor.getUndoSupport().postEdit(mapLayerStateEdit);
  }

  /**
   * Actually performs the action that modifies the layer configuration.
   */
  protected abstract void doPerformAction();
}
