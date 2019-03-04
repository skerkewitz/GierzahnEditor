package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static de.gierzahn.editor.map.Map.TILE_HEIGHT;
import static de.gierzahn.editor.map.Map.TILE_WIDTH;

public class AirflowBaseMapLayerPaintComponent extends AbstractBaseMapLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(AirflowBaseMapLayerPaintComponent.class);

  private BufferedImage up, down, left, right;


  public AirflowBaseMapLayerPaintComponent(float alpha) {
    try {
      up = ImageIO.read(this.getClass().getResourceAsStream("up.png"));
      down = ImageIO.read(this.getClass().getResourceAsStream("down.png"));
      left = ImageIO.read(this.getClass().getResourceAsStream("left.png"));
      right = ImageIO.read(this.getClass().getResourceAsStream("right.png"));
    } catch (IOException e) {
      logger.error(e,e);
    }

    this.alpha = alpha;
  }


  @Override
  public void paintComponentAt(Graphics g, int x, int y, BaseMapLayer layer) {
    BufferedImage image = null;
    final int content = layer.getAt(x, y);

    switch (content) {
      case 1:
        image = up;
        break;

      case 2:
        image = down;
        break;

      case 3:
        image = left;
        break;

      case 4:
        image = right;
        break;

      default:
        break;
    }
    if (image != null) {
      g.drawImage(image, x * TILE_WIDTH, y * TILE_HEIGHT, null);
    }
  }
}
