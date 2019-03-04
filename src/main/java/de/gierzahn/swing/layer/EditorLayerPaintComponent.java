package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;

import java.awt.*;

public interface EditorLayerPaintComponent {

  void paintComponent(Graphics g, BaseMapLayer layer);

}
