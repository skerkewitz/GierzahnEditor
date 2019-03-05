package de.gierzahn.swing;

import de.gierzahn.editor.Editor;
import de.gierzahn.swing.action.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

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
    add(new JLayerPanel(jEditorPanel), BorderLayout.EAST);

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
    menuBar.add(createEnemyMenu());

    return menuBar;
  }

  private Component createEditMenu() {
    JMenu jEditMenu = new JMenu("Edit");
    jEditMenu.add(new JMenuItem(new EditMirrorYMapAction(editor)));
    jEditMenu.addSeparator();
    jEditMenu.add(new JMenuItem(new EditAirflowUpMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditAirflowLeftMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditAirflowDownMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditAirflowRightMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditAutosetAirflowMapAction(editor)));
    jEditMenu.addSeparator();
    jEditMenu.add(new JMenuItem(new EditCursorUpMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditCursorLeftMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditCursorDownMapAction(editor)));
    jEditMenu.add(new JMenuItem(new EditCursorRightMapAction(editor)));
    return jEditMenu;
  }

  private Component createEnemyMenu() {
    JMenu jEnemyMenu = new JMenu("Enemy");
    jEnemyMenu.add(new JMenuItem(new EnemyPlaceEnemyAddMapAction(editor)));
    jEnemyMenu.add(new JMenuItem(new EnemyFlipEnemyMapAction(editor)));
    jEnemyMenu.add(new JMenuItem(new EnemyRemoveEnemyMapAction(editor)));
    return jEnemyMenu;
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
