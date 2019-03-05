package de.gierzahn.swing;

import de.gierzahn.editor.Editor;
import de.gierzahn.editor.map.BaseMapLayer;
import de.gierzahn.editor.map.Map;
import de.gierzahn.swing.layer.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static de.gierzahn.editor.map.Map.TILE_HEIGHT;
import static de.gierzahn.editor.map.Map.TILE_WIDTH;

public class JEditorPanel extends JPanel {
  private static final Logger logger = LogManager.getLogger(JEditorPanel.class);

  public final Editor editor;

  private EditorLayerPaintComponent airflowLayer = new AirflowBaseMapLayerPaintComponent(0.3f);
  private EditorLayerPaintComponent staticLayer = new StaticBaseMapLayerPaintComponent();
  private EditorLayerPaintComponent gridLayer = new GridBaseMapLayerPaintComponent();
  private EditorLayerPaintComponent enemyLayer = new EnemyMapLayerPaintComponent();
  private BackdropMapLayerPaintComponent backdropLayer;

  public final List<EditorLayerPaintComponent> layerList;

  private EditorLayerPaintComponent selectedLayer;

  public JEditorPanel(Editor editor) {

    layerList = new ArrayList<>(Arrays.asList(gridLayer, staticLayer, airflowLayer, enemyLayer));
    this.setFocusable(true);
    this.editor = editor;
    setPreferredSize(new Dimension(Map.PANEL_WIDTH, Map.PANEL_HEIGHT));

    this.editor.setEditorMapChangedListener(this::repaint);

    MouseAdapter mouseAdapter = new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        logger.debug("Mouse click at " + e.getPoint() + " " + e.getButton());

        if (SwingUtilities.isLeftMouseButton(e)) {
          editor.placeCursorAt(convertToTileSpace(e.getPoint()));
          if (selectedLayer == staticLayer) {
            editor.paintAt(convertToTileSpace(e.getPoint()));
          }
        } else if (SwingUtilities.isRightMouseButton(e)) {
          if (selectedLayer == staticLayer || selectedLayer == airflowLayer) {
            editor.eraseAt(convertToTileSpace(e.getPoint()), selectedLayer.getType());
          }
        }
      }

      @Override
      public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        logger.debug("Mouse dragged at " + e.getPoint() + " " + e.getButton());
        logger.debug("Pos " + e.getPoint() + " " + e.getButton());
        if (SwingUtilities.isLeftMouseButton(e)) {
          if (selectedLayer == staticLayer) {
            editor.paintAt(convertToTileSpace(e.getPoint()));
          } else if (selectedLayer == airflowLayer) {
            editor.cloneAirflowFromCursorAt(convertToTileSpace(e.getPoint()));
          }
        } else if (SwingUtilities.isRightMouseButton(e)) {
          if (selectedLayer == staticLayer || selectedLayer == airflowLayer) {
            editor.eraseAt(convertToTileSpace(e.getPoint()), selectedLayer.getType());
          }
        }
      }
    };
    addMouseListener(mouseAdapter);
    addMouseMotionListener(mouseAdapter);
  }

  public void setBackdropImage(BufferedImage backdrop) {
    backdropLayer = new BackdropMapLayerPaintComponent(backdrop);
    layerList.add(backdropLayer);
    repaint();
  }

  public Point convertToTileSpace(Point p) {
    return new Point(p.x / TILE_WIDTH, p.y / TILE_HEIGHT);
  }


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (backdropLayer != null) {
      backdropLayer.paintComponentForLayer(g, null);
    }

    staticLayer.paintComponentForLayer(g, editor.getMap().staticMapLayer);
    airflowLayer.paintComponentForLayer(g, editor.getMap().airflowMapLayer);
    enemyLayer.paintComponentForLayer(g, editor.getMap().enemyMapLayer);
    gridLayer.paintComponentForLayer(g, null);

    // Current cursor
    g.setColor(Color.BLUE);
    g.drawRect(editor.xPos * TILE_WIDTH, editor.yPos * TILE_HEIGHT, TILE_WIDTH - 1, TILE_HEIGHT - 1);
  }

  public void reset() {
    backdropLayer = null;
  }

  public void layerSelectionDidChange(int selectedLayerIndex) {
    selectedLayer = layerList.get(selectedLayerIndex);
  }
}
