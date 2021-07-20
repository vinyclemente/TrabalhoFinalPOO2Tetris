import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TetrisPanel extends JFrame {

    Tetris game;

    public TetrisPanel() {
        super("Tetris");
        
        game = new Tetris();
        game.init();
        add(game);

        // BARRA DE MENUS
        JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic('O');
        
        JMenuItem instructionItem = new JMenuItem("Instructions");
        optionsMenu.add(instructionItem);

        JMenuItem highScoreItem = new JMenuItem("High Score");
        optionsMenu.add(highScoreItem);

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        bar.add(optionsMenu);

        instructionItem.addActionListener(new ActionListener() { // classe interna
            // anonima
            // exibe dialog quando usuario seleciona About...
            public void actionPerformed(ActionEvent event) {
                JOptionPane.showMessageDialog(TetrisPanel.this,
                        "Para girar as peças use: \u2191\nPara mover as peças use: \u2190  \u2192\nPara descer a peça use: SPACEBAR\n\nPressione S para começar", "Instructions",
                        JOptionPane.PLAIN_MESSAGE);
            } // fim do metodo actionPerformed
        } // fim da classe interna anonima
        ); // fim da chamada ao addActionListener

        highScoreItem.addActionListener(new ActionListener() { // classe interna
            // anonima
            // exibe dialog quando usuario seleciona About...
            public void actionPerformed(ActionEvent event) {
                Ranking rank = new Ranking();
                String highest = rank.getHighest();
                String all = rank.getAll();
                String message = String.format("HIGHEST SCORE: \n%s\n\nALL SCORES:\n%s", highest, all);
                JOptionPane.showMessageDialog(TetrisPanel.this, message, "High Score", JOptionPane.PLAIN_MESSAGE);
            } // fim do metodo actionPerformed
        } // fim da classe interna anonima
        ); // fim da chamada ao addActionListener

        optionsMenu.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                game.pause();
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        

        
        this.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    game.rotate(-1);
                    break;
                case KeyEvent.VK_DOWN:
                    game.rotate(+1);
                    break;
                case KeyEvent.VK_LEFT:
                    game.move(-1);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.move(+1);
                    break;
                case KeyEvent.VK_SPACE:
                    game.dropDown();
                    game.addScore(1);
                    break;
                case KeyEvent.VK_S:
                    game.unpause();
                }
            }

            public void keyReleased(KeyEvent e) {
            }
        });

        // Make the falling piece drop every half-ssecond
        new Thread() {
            @Override
            public void run() {
                while (!game.getGameOver()) {
                    try {
                        Thread.sleep(500);
                        game.dropDown();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(312, 648);
        setLocation(400,100);
        setVisible(true);
    }
}
