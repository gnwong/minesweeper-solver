/*
 *  Copyright (C)  2014 George Wong
 *  GNU General Public License
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameBoard extends JPanel {

  private int width, height;


  //private CEll<Cell> cells;
  private Cell[][] cells;

  public GameBoard () {
    super();

    width = 20;
    height = 20;

    // Initialize the board
    initialize();

    this.setBorder(BorderFactory.createLineBorder(Color.black));
    this.setLayout(new GridLayout(width,height));

    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        this.add(cells[i][j]);
      }
    }

  }


  // 
  public int cleanBoard () {
    int uncovered = 0;

    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        if (cells[i][j].visible) {
          if (cells[i][j].isEmpty()) {
            for (Cell c : cells[i][j].getNeighbors()) {
              if (c.visible) continue;
              c.click(1);
              uncovered += 1;
    } } } } }

    return uncovered;
  }

  // 
  private void initialize () {
    Random rand = new Random(); 
    cells = new Cell[width][height];
    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        if (rand.nextInt(10) == 0) cells[i][j] = new Cell(-1, this);
        else cells[i][j] = new Cell(0, this);
      }
    }
    for (int i=0; i<width; i++) {
      for (int j=0; j<height; j++) {
        if (i>0 && j>0) cells[i][j].addNeighbor(cells[i-1][j-1]);
        if (i>0) cells[i][j].addNeighbor(cells[i-1][j]);
        if (i>0 && j<height-1) cells[i][j].addNeighbor(cells[i-1][j+1]);
        if (j>0) cells[i][j].addNeighbor(cells[i][j-1]);
        if (j<height-1) cells[i][j].addNeighbor(cells[i][j+1]);
        if (i<width-1 && j>0) cells[i][j].addNeighbor(cells[i+1][j-1]);
        if (i<width-1) cells[i][j].addNeighbor(cells[i+1][j]);
        if (i<width-1 && j<height-1) cells[i][j].addNeighbor(cells[i+1][j+1]);
        cells[i][j].setInfo();
      }
    }    
  }
}

class Cell extends JPanel {

  private boolean valid;
  public boolean visible;
  private JLabel label;
  private int info;
  private ArrayList<Cell> neighbors;

  private GameBoard gb;

  public Cell (int t, GameBoard g) {
    super();

    valid = true;
    visible = false;
    label = new JLabel("");
    info = t;
    gb = g;
    
    this.setBorder(BorderFactory.createRaisedBevelBorder());
    this.add(label);

    neighbors = new ArrayList<Cell>();

    // Set up listeners
    this.addMouseListener(new MouseListener() {
      @Override
      public void mousePressed (MouseEvent e) {

      }
      
      @Override
      public void mouseReleased (MouseEvent e) {
        click(e.getButton());
        while (gb.cleanBoard() != 0) { }
      }

      @Override
      public void mouseExited(MouseEvent e) {}
      public void mouseEntered(MouseEvent e) {}
      public void mouseClicked(MouseEvent e) {}
    });

  }

  public ArrayList<Cell> getNeighbors () {
    return this.neighbors;
  }

  public void addNeighbor (Cell c) {
    neighbors.add(c);
  }

  public boolean isEmpty () {
    if (info == 0) return true;
    return false;
  }

  public boolean isMine () {
    if (info<0) return true;
    return false;
  }

  public void setInfo () {
    if (info == -1) return;
    for (Cell c : neighbors) {
      if (c.isMine()) info += 1;
    }
  }



  public void click (int t) {
    if (t==1) {
      if (valid) {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        if (info != 0) label.setText(Integer.toString(info));
        visible = true;
      }
    } else if (t==3) {
      if (valid) {
        valid = false;
        label.setText("F");
      } else {
        valid = true;
        label.setText("");
      }
    }
  }
}




