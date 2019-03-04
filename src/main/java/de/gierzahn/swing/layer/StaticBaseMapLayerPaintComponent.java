package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StaticBaseMapLayerPaintComponent extends AbstractBaseMapLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(StaticBaseMapLayerPaintComponent.class);

  private BufferedImage image;

  public StaticBaseMapLayerPaintComponent() {
    try {
      image = ImageIO.read(this.getClass().getResourceAsStream("tile_32x32.png"));
    } catch (IOException e) {
      logger.error(e,e);
    }
  }

  @Override
  public void paintComponentAt(Graphics g, int x, int y, BaseMapLayer layer) {
    if (layer.getAt(x, y) == 1) {
      g.drawImage(image, x * Map.TILE_WIDTH, y * Map.TILE_HEIGHT, null);
    }
  }
}
