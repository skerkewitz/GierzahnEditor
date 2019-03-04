package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackdropMapLayerPaintComponent extends AbstractBaseMapLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(BackdropMapLayerPaintComponent.class);

  private BufferedImage backdropOriginal;
  private BufferedImage backdrop;

  public BackdropMapLayerPaintComponent(BufferedImage backdrop) {
    super();
    this.backdropOriginal = backdrop;
    this.backdrop = backdrop.getSubimage(0, 16, backdrop.getWidth(), backdrop.getHeight() -16);
    this.alpha = 0.2f;
  }

  @Override
  public void paintComponent(Graphics g, BaseMapLayer layer) {
    final Graphics2D g2d = (Graphics2D)g;
    willPaintComponent(g2d, layer);
    g.drawImage(backdrop, Map.MAX_LEFT, Map.MAX_UP, Map.PANEL_WIDTH, Map.PANEL_HEIGHT, null);

    didPaintComponent(g2d, layer);
  }
}
