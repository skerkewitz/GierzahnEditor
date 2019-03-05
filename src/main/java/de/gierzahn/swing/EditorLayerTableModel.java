package de.gierzahn.swing;

import de.gierzahn.swing.layer.EditorLayerPaintComponent;

import javax.swing.table.AbstractTableModel;

/** The swing table model to edit the layer. */
public class EditorLayerTableModel extends AbstractTableModel {
  private JEditorPanel editor;
  private static final String[] columnNames = {"Locked", "Visible", "Name" };

  public EditorLayerTableModel(JEditorPanel editor) {
    this.editor = editor;
  }

  public String getColumnName(int col) {
    return columnNames[col];
  }

  public int getRowCount() {
    return editor.layerList.size();
  }

  public int getColumnCount() {
    return columnNames.length;
  }

  public Class getColumnClass(int col) {
    switch (col) {
      case 0: return Boolean.class;
      case 1: return Boolean.class;
      case 2: return String.class;
    }
    return null;
  }

  public Object getValueAt(int row, int col) {
    EditorLayerPaintComponent layer = editor.layerList.get(getRowCount() - row - 1);

    if (col == 0) {
      return layer.getLocked();
    } else if (col == 1) {
      return layer.isVisible();
    } else if (col == 2) {
      return layer.getName();
    }

    throw new IllegalArgumentException("Unknown col " + col);
  }

  public boolean isCellEditable(int row, int col) {
    EditorLayerPaintComponent layer = editor.layerList.get(getRowCount() - row - 1);

    /* You can not rename the layer. */
    if (col == 2) {
      return false;
    }
    return !(col == 0 && !layer.isVisible());
  }

  public void setValueAt(Object value, int row, int col) {
    EditorLayerPaintComponent layer = editor.layerList.get(getRowCount() - row - 1);

    if (col == 0) {
      layer.setLocked((Boolean)value);
    } else if (col == 1) {
      layer.setVisible((Boolean)value);
    }

    fireTableCellUpdated(row, col);
  }

  @Override
  public void fireTableCellUpdated(int row, int column) {
    super.fireTableCellUpdated(row, column);
    this.editor.repaint();
  }
}
