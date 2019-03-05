package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BackdropMapLayerPaintComponent extends AbstractLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(BackdropMapLayerPaintComponent.class);

  private BufferedImage backdropOriginal;
  private BufferedImage backdrop;

  public BackdropMapLayerPaintComponent(BufferedImage backdrop) {
    super(null);
    this.backdropOriginal = backdrop;
    this.backdrop = backdrop.getSubimage(0, 16, backdrop.getWidth(), backdrop.getHeight() -16);
    this.alpha = 0.2f;
  }

  @Override
  public void paintComponent(Graphics2D g, BaseMapLayer layer) {
    willPaintComponent(g, layer);
    g.drawImage(backdrop, Map.MAX_LEFT, Map.MAX_UP, Map.PANEL_WIDTH, Map.PANEL_HEIGHT, null);

    didPaintComponent(g, layer);
  }

  @Override
  public String getName() {
    return "Backdrop";
  }
}
