package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;

import java.awt.*;

public interface EditorLayerPaintComponent {

  float getOpacity();
  void setOpacity(float v);

  String getName();

  void paintComponentForLayer(Graphics g, BaseMapLayer layer);

  boolean getLocked();

  boolean isVisible();

  void setLocked(Boolean value);

  void setVisible(Boolean value);

  Map.Layer getType();
}
