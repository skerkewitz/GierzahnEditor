package de.gierzahn.swing;

import de.gierzahn.editor.map.AirflowDirection;
import de.gierzahn.editor.Editor;
import de.gierzahn.swing.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JMainFrame extends JFrame {

  private static final Logger logger = LogManager.getLogger(JMainFrame.class);

  private JEditorPanel jEditorPanel;

  /* The actual editor engine. */
  public final Editor editor = new Editor();

  public JMainFrame() {
    updateTitle();
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(new BorderLayout());
    jEditorPanel = new JEditorPanel(editor);
    add(jEditorPanel, BorderLayout.CENTER);
    createMenuBar();

    addKeyListener(new KeyListener() {

      @Override
      public void keyTyped(KeyEvent e) {
      }

      @Override
      public void keyReleased(KeyEvent e) {
      }

      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case KeyEvent.VK_SPACE:
            jEditorPanel.editor.fire();
            break;
          case KeyEvent.VK_E:
            jEditorPanel.editor.placeEnemy();
            break;
          case KeyEvent.VK_Q:
            jEditorPanel.editor.removeEnemy();
            break;
          case KeyEvent.VK_F:
            jEditorPanel.editor.flipEnemy();
            break;
          case KeyEvent.VK_LEFT:
            jEditorPanel.editor.goLeft();
            break;
          case KeyEvent.VK_RIGHT:
            jEditorPanel.editor.goRight();
            break;

          case KeyEvent.VK_UP:
            jEditorPanel.editor.goUp();
            break;

          case KeyEvent.VK_DOWN:
            jEditorPanel.editor.goDown();
            break;

          case KeyEvent.VK_W:
            jEditorPanel.editor.airflow(AirflowDirection.UP);
            break;

          case KeyEvent.VK_S:
            jEditorPanel.editor.airflow(AirflowDirection.DOWN);
            break;

          case KeyEvent.VK_A:
            jEditorPanel.editor.airflow(AirflowDirection.LEFT);
            break;

          case KeyEvent.VK_D:
            jEditorPanel.editor.airflow(AirflowDirection.RIGHT);
            break;

          default:
        }

        jEditorPanel.repaint();
      }
    });

    setJMenuBar(createMenuBar());

    setVisible(true);
    pack();
  }

  public void updateTitle() {
    setTitle("Gierzahn Editor [" + editor.getMapName() +"]");
  }

  private JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    menuBar.add(createFileMenu());
    menuBar.add(createEditMenu());

    return menuBar;
  }

  private Component createEditMenu() {
    JMenu jEditMenu = new JMenu("Edit");
    jEditMenu.add(new JMenuItem(new EditMirrorYMapAction(editor)));
    jEditMenu.addSeparator();
    jEditMenu.add(new JMenuItem(new EditAutosetAirflowMapAction(editor)));
    return jEditMenu;
  }

  private JMenu createFileMenu() {
    JMenu jFileMenu = new JMenu("File");

    JMenuItem jExitMenuItem = new JMenuItem("Exit");
    jExitMenuItem.addActionListener(e -> dispose());

    jFileMenu.add(new JMenuItem(new FileNewMapAction(jEditorPanel)));
    jFileMenu.add(new JMenuItem(new FileOpenMapAction(this, editor)));
    jFileMenu.add(new JMenuItem(new FileSaveAsMapAction(this, editor)));
    jFileMenu.addSeparator();
    jFileMenu.add(new JMenuItem(new FileOpenBackdropAction(this, jEditorPanel)));
    jFileMenu.addSeparator();
    jFileMenu.add(jExitMenuItem);
    return jFileMenu;
  }
}
