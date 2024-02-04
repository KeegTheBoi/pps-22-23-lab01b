package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import e2.Cells.Cell;
import e2.Cells.Cell.Status;

import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Coord> buttons = new HashMap<>();
    private final Logics logics;
    private static final int BOMB_SIZE = 4;
    
    public GUI(int size, int diffuculty) {
        this.logics = new LogicsImpl(size, BOMB_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Coord pos = buttons.get(bt);
            logics.hit(pos);
            boolean aMineWasFound = logics.isOver(); // call the logic here to tell it that cell at 'pos' has been seleced
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
                System.exit(0);
            } else {
                drawBoard();            	
            }
            boolean isThereVictory = logics.hasWon(); // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final var pos = buttons.get(bt);
                    // call the logic here to put/remove a flag
                    logics.flag(pos);
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.setForeground(Color.WHITE);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Coord(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            entry.getKey().setForeground(Color.YELLOW);
            var cell = logics.getResult(entry.getValue());
            cell.changeVisibility();
            entry.getKey().setText(this.getText(cell));
    	}
    }

    private String getText(Cell cell) {
        switch (cell.getType()) {
            case MINE:
                return "💣";
            case GROUND:
                return String.valueOf(cell.getCount().get());
            case FLAG:
                return "⚑";
            default:
                return "";
        }
    }

    private void drawBoard() {
        buttons.forEach((k, v) -> {
            String text = "";
            var cell = logics.getResult(v);
            if(cell.getStatus() == Status.VISIBLE) {
                k.setEnabled(false);
            } else if (Cells.isFlag(cell)) {
                k.setBackground(Color.RED);
            } 
            k.setText(getText(cell));
            
        });
    }
    
}
