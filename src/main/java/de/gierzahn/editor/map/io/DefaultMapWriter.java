package de.gierzahn.editor.map.io;

import de.gierzahn.editor.map.EnemyBaseMapLayer;
import de.gierzahn.editor.map.Map;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class DefaultMapWriter implements MapWriter {

  @Override
  public void write(Map map, OutputStream outputStream) throws IOException {
    IOUtils.write(map.staticMapLayer.toByteArray(), outputStream);
    IOUtils.write(map.airflowMapLayer.toByteArray(), outputStream);

    /* Write the enemy layer. */
    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ArrayList<EnemyBaseMapLayer.Enemy> enemies = map.enemyMapLayer.toEnemyList();
    DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    dataOutputStream.writeInt(enemies.size());
    for (EnemyBaseMapLayer.Enemy enemy : enemies) {
      dataOutputStream.writeInt(enemy.x);
      dataOutputStream.writeInt(enemy.y);
      dataOutputStream.writeInt(enemy.type);
      dataOutputStream.writeInt(enemy.isLookingLeft ? 1 : 0);
    }

    IOUtils.write(byteArrayOutputStream.toByteArray(), outputStream);
  }
}
