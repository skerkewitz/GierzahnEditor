package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.EnemyBaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EnemyMapLayerPaintComponent extends AbstractLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(EnemyMapLayerPaintComponent.class);

  private BufferedImage left;
  private BufferedImage right;

  public EnemyMapLayerPaintComponent() {
    super(Map.Layer.Enemies);
    try {
      left = ImageIO.read(this.getClass().getResourceAsStream("zenchan-left-32x32.png"));
      right = ImageIO.read(this.getClass().getResourceAsStream("zenchan-right-32x32.png"));
    } catch (IOException e) {
      logger.error(e,e);
    }
    alpha = 0.3f;

  }

  @Override
  public void paintComponentAt(Graphics g, int x, int y, BaseMapLayer layer) {
    int enemyId = layer.getAt(x, y);
    if (enemyId != 0) {
      EnemyBaseMapLayer enemyBaseMapLayer = (EnemyBaseMapLayer)layer;
      EnemyBaseMapLayer.Enemy enemyById = enemyBaseMapLayer.getEnemyById(enemyId);
      var image = enemyById.isLookingLeft ? left : right;
      g.drawImage(image, x * Map.TILE_WIDTH, y * Map.TILE_HEIGHT, Map.TILE_WIDTH * 2, Map.TILE_WIDTH * 2, null);
    }
  }

  @Override
  public String getName() {
    return "Enemies";
  }
}
