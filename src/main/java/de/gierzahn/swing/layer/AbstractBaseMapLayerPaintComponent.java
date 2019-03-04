package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;

import java.awt.*;

public abstract class AbstractBaseMapLayerPaintComponent extends AbstractLayerPaintComponent {

  protected float alpha = 1.0f;

  @Override
  public void paintComponent(Graphics g, BaseMapLayer layer) {

    final Graphics2D g2d = (Graphics2D)g;
    willPaintComponent(g2d, layer);

    for (int x = 0; x < Map.NUM_TILES_HORIZONTAL; x += 1) {
      for (int y = 0; y < Map.NUM_TILES_VERTICAL; y += 1) {
        paintComponentAt(g, x, y, layer);
      }
    }

    didPaintComponent(g2d, layer);
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
}
