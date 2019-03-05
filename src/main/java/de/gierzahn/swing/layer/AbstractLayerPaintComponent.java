package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;

import java.awt.*;

public abstract class AbstractLayerPaintComponent implements EditorLayerPaintComponent {

  protected float alpha = 1.0f;

  private boolean locked = false;
  private boolean visible = true;
  private Map.Layer layerType;

  public AbstractLayerPaintComponent(Map.Layer layerType) {
    this.layerType = layerType;
  }

  @Override
  public float getOpacity() {
    return alpha;
  }

  @Override
  public void setOpacity(float v) {
    alpha = v;
  }

  @Override
  public boolean getLocked() {
    return locked;
  }

  @Override
  public boolean isVisible() {
    return visible;
  }

  @Override
  public void setLocked(Boolean value) {
    this.locked = value;
  }

  @Override
  public void setVisible(Boolean value) {
    this.visible = value;
  }

  @Override
  public void paintComponentForLayer(Graphics g, BaseMapLayer layer) {

    if (isVisible()) {
      paintComponent((Graphics2D)g, layer);
    }
  }

  public void paintComponent(Graphics2D g, BaseMapLayer layer) {

    willPaintComponent(g, layer);

    for (int x = 0; x < Map.NUM_TILES_HORIZONTAL; x += 1) {
      for (int y = 0; y < Map.NUM_TILES_VERTICAL; y += 1) {
        paintComponentAt(g, x, y, layer);
      }
    }

    didPaintComponent(g, layer);
  }

  public void willPaintComponent(Graphics2D g2d, BaseMapLayer layer) {
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
  }

  public void paintComponentAt(Graphics g, int x, int y, BaseMapLayer layer) {
    /* Does nothing. */
  }

  protected void didPaintComponent(Graphics2D g2d, BaseMapLayer layer) {
    g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
  }

  @Override
  public Map.Layer getType() {
    return layerType;
  }
}
