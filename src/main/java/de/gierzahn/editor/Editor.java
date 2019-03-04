package de.gierzahn.editor;

import de.gierzahn.editor.map.AirflowDirection;
import de.gierzahn.editor.map.EnemyBaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.File;

public class Editor {

  private static final Logger logger = LogManager.getLogger(Editor.class);

  public interface EditorMapChangedListener {
    void mapDidChanged();
  }

  public int xPos = 0;
  public int yPos = 0;

  private Map map = new Map();

  private EditorMapChangedListener editorMapChangedListener;

  public void setEditorMapChangedListener(EditorMapChangedListener l) {
    this.editorMapChangedListener = l;
  }

  public void fire() {
    logger.debug("Fire at xPos: " + xPos + " yPos: " + yPos);

    if (map.staticMapLayer.getAt(xPos, yPos) == 0) {
      map.staticMapLayer.setAt(xPos, yPos, 1);
    } else {
      map.staticMapLayer.setAt(xPos, yPos, 0);
    }

    fireMapDidChangeEvent();
  }

  public void goLeft() {
    if (xPos > Map.MAX_LEFT) {
      xPos--;
      fireMapDidChangeEvent();
    }
  }

  public void goRight() {
    if (xPos < Map.MAX_RIGHT) {
      xPos++;
      fireMapDidChangeEvent();
    }

  }

  public void goUp() {
    if (yPos > Map.MAX_UP) {
      yPos--;
      fireMapDidChangeEvent();
    }
  }

  public void goDown() {
    if (yPos < Map.MAX_DOWN) {
      yPos++;
      fireMapDidChangeEvent();
    }
  }

  public void airflow(AirflowDirection direction) {

    /* Remove if the old code is the new code (toogle delete). */
    int current = map.airflowMapLayer.getAt(xPos, yPos);
    if (current != 0 && current == direction.getCode()) {
      map.airflowMapLayer.setAt(xPos, yPos, 0);
    } else {
      map.airflowMapLayer.setAt(xPos, yPos, direction.getCode());
    }

    fireMapDidChangeEvent();
  }

  public void cloneAirflowFromCursorAt(Point p) {
    int stamp = map.airflowMapLayer.getAt(xPos, yPos);
    map.airflowMapLayer.setAt(p.x, p.y, stamp);
  }

  public void paintAt(Point p) {
    map.staticMapLayer.setAt(p.x, p.y, 1);
    fireMapDidChangeEvent();
  }

  public void eraseAt(Point p, Map.Layer layer) {

    switch (layer) {
      case Static:
        map.staticMapLayer.setAt(p.x, p.y, 0);
        break;
      case Airflow:
        map.airflowMapLayer.setAt(p.x, p.y, 0);
        break;
    }

    fireMapDidChangeEvent();
  }

  public void reset() {
    map.staticMapLayer.fill(0);
    map.airflowMapLayer.fill(AirflowDirection.UP.getCode());
    fireMapDidChangeEvent();
  }

  protected void fireMapDidChangeEvent() {
    if (editorMapChangedListener != null) {
      editorMapChangedListener.mapDidChanged();
    }
  }

  public void mirrorX() {
    map.staticMapLayer.mirrorX();
//    map.airflowMapLayer.mirrorX();

    int width = map.airflowMapLayer.getWidth();
    final var xHalf = width / 2;
    map.airflowMapLayer.forEachField((x, y, currentValue) -> {
      if (x < xHalf) {
        map.airflowMapLayer.setAt(width - x - 1, y, AirflowDirection.fromCode(currentValue).getMirrorY().getCode());
      }
    });


    fireMapDidChangeEvent();
  }

  public Map getMap() {
    return map;
  }

  public void setMap(Map map) {
    this.map = map;
    fireMapDidChangeEvent();
  }

  public void placeCursorAt(Point p) {
    xPos = p.x;
    yPos = p.y;
    clampCursorBounds();
  }

  protected void clampCursorBounds() {
    xPos = Math.max(Map.MAX_LEFT, xPos);
    xPos = Math.min(Map.MAX_RIGHT, xPos);

    yPos = Math.max(Map.MAX_UP, yPos);
    yPos = Math.min(Map.MAX_DOWN, yPos);
  }

  public void autofillAirflow() {
    /* Reset the airflow map. */
    map.airflowMapLayer.fill(0);
    map.staticMapLayer.forEachField((x, y, v) -> map.airflowMapLayer.setAt(x, y,v == 0 ? AirflowDirection.UP.getCode() : 0));
    fireMapDidChangeEvent();
  }

  public String getMapName() {
    return map.getName() == null ? "untitled.map" : getMap().getName();
  }

  public void placeEnemy() {
    getMap().enemyMapLayer.addEnemyAt(new Point(xPos, yPos));
    fireMapDidChangeEvent();

  }

  public void removeEnemy() {
    getMap().enemyMapLayer.setAt(xPos, yPos, 0);
    fireMapDidChangeEvent();
  }

  public void flipEnemy() {
    int enemyId = getMap().enemyMapLayer.getAt(xPos, yPos);
    EnemyBaseMapLayer.Enemy enemy = getMap().enemyMapLayer.getEnemyById(enemyId);
    enemy.isLookingLeft = !enemy.isLookingLeft;
    fireMapDidChangeEvent();
  }
}
