package de.gierzahn.swing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class JConfirmingFileDialog extends JFileChooser {



//  private static final String UNKNOWN_TYPE_MESSAGE = Resources.getString("dialog.saveas.unknown-type.message");
//  private static final String CONFIRM_MISMATCH = Resources.getString("dialog.saveas.confirm.mismatch");
//  private static final String CONFIRM_MISMATCH_TITLE = Resources.getString("dialog.saveas.confirm.mismatch.title");
//  private static final String FILE_EXISTS_MESSAGE = Resources.getString("general.file.exists.message");
//  private static final String FILE_EXISTS_TITLE = Resources.getString("general.file.exists.title");

  public JConfirmingFileDialog(String currentDirectoryPath) {
    super(currentDirectoryPath);
  }

  public JConfirmingFileDialog() {
    this(null);
  }

  public int showSaveDialog(Component component) throws HeadlessException {
    setDialogTitle("Save as");
    return super.showSaveDialog(component);
  }

  public void approveSelection() {
    // When it's an open dialog, we don't need the extension or overwrite
    // checks. Probably you should just be using JFileChooser.
    if (getDialogType() == OPEN_DIALOG) {
      super.approveSelection();
      return;
    }

    File file = new File(getSelectedFile().getAbsolutePath());

    // If the file does not have an extention, append the default
    // extension specified by the file filter.
    String filename = file.getName();
    int lastDot = filename.lastIndexOf('.');

    if ((lastDot == -1 || lastDot == filename.length() - 1)) {
      String extension = "map";
      String newFilePath = file.getAbsolutePath();

      // Add a dot if it wasn't at the end already
      if (lastDot != filename.length() - 1) {
        newFilePath += ".";
      }

      file = new File(newFilePath + extension);
      setSelectedFile(file);
    }

    // Confirm overwrite if the file happens to exist already
    if (file.exists()) {
      int answer = JOptionPane.showConfirmDialog(this, "FILE_EXISTS_MESSAGE", "FILE_EXISTS_TITLE", JOptionPane.YES_NO_OPTION);
      if (answer == JOptionPane.YES_OPTION) {
        super.approveSelection();
      }
    } else {
      super.approveSelection();
    }
  }
}
