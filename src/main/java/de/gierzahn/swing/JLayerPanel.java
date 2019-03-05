package de.gierzahn.swing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

public class JLayerPanel  extends JPanel {

  private static final Logger logger = LogManager.getLogger(JLayerPanel.class);

  private final JEditorPanel jEditorPanel;
  private final JTable jLayerTable;
  private EditorLayerTableModel editorLayerTableModel;
  private JSlider opacitySlider;

  public JLayerPanel(JEditorPanel jEditorPanel) {
    this.jEditorPanel = jEditorPanel;

    editorLayerTableModel = new EditorLayerTableModel(jEditorPanel);
    jLayerTable = createLayerPanel();

    final BorderLayout borderLayout = new BorderLayout();
    borderLayout.setVgap(5);
    this.setLayout(borderLayout);
    setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    setPreferredSize(new Dimension(250, 120));
    add(createSliderPanel(), BorderLayout.PAGE_START);
    add(new JScrollPane(jLayerTable), BorderLayout.CENTER);

    ListSelectionModel selectionModel = jLayerTable.getSelectionModel();
    selectionModel.addListSelectionListener(e -> updateSlider());
  }


  private void updateSlider() {
    int selectedRow = jLayerTable.getSelectedRow();
    if (selectedRow < 0) {
      return;
    }
    int selectedLayerIndex = jLayerTable.getRowCount() - selectedRow - 1;
    int value = (int) (jEditorPanel.layerList.get(selectedLayerIndex).getOpacity() * 100);
    this.opacitySlider.setValue(value);

    jEditorPanel.layerSelectionDidChange(selectedLayerIndex);
  }

  private JPanel createSliderPanel() {
    // Opacity slider
    opacitySlider = new JSlider(20, 100, 100);
    opacitySlider.setFocusable(false);
    opacitySlider.addChangeListener(e -> {
      int selectedRow = jLayerTable.getSelectedRow();
      if (selectedRow < 0) {
        return;
      }
      int selectedLayerIndex = jLayerTable.getRowCount() - selectedRow - 1;
      jEditorPanel.layerList.get(selectedLayerIndex).setOpacity((float)(opacitySlider.getValue()) / 100.0f);
      jEditorPanel.repaint();
    });
    JLabel opacityLabel = new JLabel("Opacity: ");
    opacityLabel.setLabelFor(opacitySlider);

    JPanel jSliderPanel = new JPanel();
    jSliderPanel.setLayout(new BoxLayout(jSliderPanel, BoxLayout.X_AXIS));
    jSliderPanel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
    jSliderPanel.add(opacityLabel);
    jSliderPanel.add(opacitySlider);
    jSliderPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, jSliderPanel.getPreferredSize().height));
    return jSliderPanel;
  }

  private JTable createLayerPanel() {
    var jLayerTable = new JTable(editorLayerTableModel);
    jLayerTable.setFocusable(false);
    jLayerTable.getColumnModel().getColumn(0).setPreferredWidth(32);
    jLayerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    return jLayerTable;
  }
}
