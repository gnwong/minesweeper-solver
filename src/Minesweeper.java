/*
 *  `Minesweeper.java'
 *    ...
 *
 *  Copyright (C)  2014  George Wong
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see http://www.gnu.org/licenses/.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class Minesweeper {

  private static JFrame frame;
  private static JPanel statusBar;

  // I've been silly and included both the 
  // board and the game methods in here.
  private static GameBoard game;

  public JPanel StatusBar () {
    JPanel statusBar = new JPanel();
    statusBar.add(new JLabel("... status bar ..."));
    return statusBar;
  }

  public JMenuBar MainMenu () {
    // Declaring in one place for ease-of-read
    JMenuBar menuBar;
    JMenuItem menuItem;
    JMenu menu;
    
    menuBar = new JMenuBar();
  
    // File menu
    menu = new JMenu("File");
    menu.setMnemonic('F');
    JMenuItem mFileNew = new JMenuItem("New");
    mFileNew.setMnemonic('n');
    mFileNew.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
    } } );
    menu.add(mFileNew);
    JMenuItem mFileOpen = new JMenuItem("Open");
    mFileOpen.setMnemonic('o');
    mFileOpen.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //openFile();
    } } );
    menu.add(mFileOpen);
    JMenuItem mFileSave = new JMenuItem("Save");
    mFileSave.setMnemonic('s');
    mFileSave.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          /*if (saveFile()) {
            //infoBox.setStatus("  File saved!");
          } else {
            //infoBox.setStatus("  Unable to save file...");
          }*/
    } } );
    menu.add(mFileSave);
    menu.addSeparator();
    JMenuItem mFileExit = new JMenuItem("Exit");
    mFileExit.setMnemonic('x');
    mFileExit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //frame.dispose();
          //System.exit(0);
    } } );
    menu.add(mFileExit);
    menuBar.add(menu);
    
    // Help menu
    menu = new JMenu("Help");
    menu.setMnemonic('H');
    JMenuItem mHelpAbout = new JMenuItem("About");
    mHelpAbout.setMnemonic('A');
    mHelpAbout.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          //AboutBox aboutBox = new AboutBox();
    } } );
    menu.add(mHelpAbout);
    menuBar.add(menu);
  
    return menuBar;
  }


  

  private static void initGUI () {

    Minesweeper me = new Minesweeper();

    frame = new JFrame();
    frame.setTitle("Minesweeper");

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setMinimumSize(new Dimension(500,500));

    frame.setJMenuBar(me.MainMenu());

    statusBar = me.StatusBar();

    frame.setLayout(new BorderLayout());

    frame.add(statusBar, BorderLayout.PAGE_END);

    game = new GameBoard();

    frame.add(game, BorderLayout.CENTER);

    frame.setVisible(true);


  }


  public static void main (String[] args) {
    initGUI();
  }

}


