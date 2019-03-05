package de.gierzahn.swing.layer;

import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class GridBaseMapLayerPaintComponent extends AbstractLayerPaintComponent {

  private static final Logger logger = LogManager.getLogger(GridBaseMapLayerPaintComponent.class);

  public GridBaseMapLayerPaintComponent() {
    super(null);
  }

  @Override
  public void paintComponent(Graphics2D g, BaseMapLayer layer) {

    var minX = 0;
    var minY = 0;
    var maxX = Map.PANEL_WIDTH;
    var maxY = Map.PANEL_HEIGHT;

    g.setColor(Color.DARK_GRAY);

    for (var x = 0; x < Map.PANEL_WIDTH; x += Map.TILE_WIDTH) {
      g.drawLine(x, minY, x, maxY);
    }

    for (var y = 0; y < Map.PANEL_HEIGHT; y += Map.TILE_HEIGHT) {
      g.drawLine(minX, y, maxX, y);
    }

    /* Draw middle line. */
    g.setColor(Color.RED);

    int middelX = Map.NUM_TILES_HORIZONTAL / 2 * Map.TILE_WIDTH;
    g.drawLine(middelX, minY, middelX, maxY);

    int middelY = Map.NUM_TILES_VERTICAL / 2 * Map.TILE_HEIGHT;
    g.drawLine(minX, middelY, maxX, middelY);

  }

  @Override
  public String getName() {
    return "Grid";
  }
}
