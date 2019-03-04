package de.gierzahn;

import de.gierzahn.swing.JMainFrame;

import javax.swing.*;

public class GierzahnEditor {

  public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    new JMainFrame();
  }

}
