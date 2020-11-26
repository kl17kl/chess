/* 
 * The ChessGUI class sets up the GUI, allowing the user to play chess with another player or the computer.
 */
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ChessGUI extends JFrame {

    JPanel menuBar, chessBoardPanel, getMovesPanel, deadPanel;
    JButton[][] chessBoardSquares = new JButton[8][8];
    String COLS = "ABCDEFGH";
    JLabel message = new JLabel(
            "White player makes the first move.");


    public ChessGUI() {
        super("Chess Game");
        setup();
    }

    /** This method sets up the GUI and event listeners. */
    public void setup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //initiate panels
        menuBar = new JPanel(new BorderLayout(3, 3));
        getMovesPanel = new JPanel();
        chessBoardPanel = new JPanel(new GridLayout(0, 9));
        chessBoardPanel.setBorder(new LineBorder(Color.BLACK));
        deadPanel = new JPanel();

        //set the panel dimensions
        getMovesPanel.setPreferredSize(new Dimension(150,550));
        chessBoardPanel.setPreferredSize(new Dimension(550,550));
        deadPanel.setPreferredSize(new Dimension(150,550));

        //delete later
        getMovesPanel.setBackground(new java.awt.Color(244, 181, 93));
        deadPanel.setBackground(new java.awt.Color(88, 190, 214));

        //set the panel positions
        add(menuBar,BorderLayout.NORTH);
        add(getMovesPanel,BorderLayout.WEST);
        add(chessBoardPanel,BorderLayout.CENTER);
        add(deadPanel,BorderLayout.EAST);

        //set up the menu bar
        final JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        menuBar.add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New Game"));
        tools.addSeparator();
        tools.add(new JButton("Resign"));
        tools.addSeparator();
        tools.add(message);

        // draw the chess board
        final Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                final JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                final ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                chessBoardSquares[jj][ii] = b;
            }
        }

        // fill the chess board
        chessBoardPanel.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            chessBoardPanel.add(new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                if (jj == 0) {
                    chessBoardPanel.add(new JLabel("" + (ii + 1), SwingConstants.CENTER));
                }
                chessBoardPanel.add(chessBoardSquares[jj][ii]);
            }
        }

        /*
         * JButton resize_btn = new JButton("Resize Grid  "); JButton start_btn = new
         * JButton("START          "); JButton stop_btn = new
         * JButton("STOP            ");
         */

        /*
         * panel_btn.add(resize_btn); resize_btn.addActionListener( // to resize the
         * grid (actionEvent)-> { } ); panel_btn.add(start_btn);
         * start_btn.addActionListener( // to start the solves (actionEvent)-> { } );
         * panel_btn.add(stop_btn); stop_btn.addActionListener( // to stop the solves
         * (actionEvent)-> { } );
         */

        setResizable(false);
        pack();
        setVisible(true);
    }

    /*
    *//**
        * This method takes in x and y values in the form of 'x,y' as input to draw an
        * x by y grid. The min grid size is 2x2 and the max grid size is 10x16.
        */
    /*
     * private void drawGrid() { while (true) { String grid=""; grid=grid.trim();
     * grid=(String)JOptionPane.showInputDialog( null, "Input: X,Y",
     * "Set the size of your grid!", JOptionPane.PLAIN_MESSAGE, null, null, grid
     * //Default value ); if (grid==null) break; if (grid.length()==0) {
     * System.out.println("Please input a value (i.e. X,Y)"); } else { try {
     * String[] newGrid = grid.split(","); // set row and col equal to x and y
     * values row = Integer.parseInt(newGrid[0].trim()); col =
     * Integer.parseInt(newGrid[1].trim()); if (row < 2 || row > 10 || col < 2 ||
     * col > 16) { System.out.
     * println("Please input a value greater than 2,2 and less than 10,16"); } else
     * { redrawGrid(); // draw the dotted grid break; } } catch (Exception e) {
     * System.out.println("Please use the format: x,y"); } } } }
     * 
     *//** This method redraws the dotted grid on the screen. */
    /*
     * public void redrawGrid() { int x1 = 50, x2 = 50, y1 = 50, y2 = 50; for (int i
     * = 0; i < row; i++) { for (int j = 0; j < col; j++) { Graphics g =
     * panel.getGraphics(); g.drawLine(x1, y1, x2, y2); x1 += 50; x2 += 50; } x1 =
     * 50; x2 = 50; y1 += 50; y2 += 50; } }
     * 
     *//** This method clears the painted section of the screen. */

    /*
     * public void clearGrid() { Graphics g = panel.getGraphics();
     * g.clearRect(40,40,col*50,row*50); }
     * 
     *//**
        * This method draws the path on the screen based on the current sequence.
        * sequence the current sequence to be drawn. score the score that corresponds
        * to the current sequence.
        *//*
           * public void drawLines(char[] sequence, int score) { int x1=50; int x2=50; int
           * y1=50; int y2=50; int x=0; int y=0; int[][] matrix = new int[row][col];
           * matrix[0][0] = 1;
           * 
           * for (int i=0; i<score-1; i++) { // draw up until the score-1 char c =
           * sequence[i]; Graphics g = panel.getGraphics(); g.setColor(Color.pink);
           * 
           * switch (c) { // draws in a direction based on the sequence character case
           * 'N': if (x-1>=0 && matrix[x-1][y]!=1) { g.drawLine(x1, y1, x2, y2 - 50);
           * matrix[x-1][y] = 1; y1 -= 50; y2 -= 50; x-=1; } else {i=score-1;} break; case
           * 'E': if (y+1<col && matrix[x][y+1]!=1) { g.drawLine(x1, y1, x2 + 50, y2);
           * matrix[x][y + 1] = 1; x1 += 50; x2 += 50; y += 1; } else {i=score-1;} break;
           * case 'S': if (x+1<row && matrix[x+1][y]!=1) { g.drawLine(x1, y1, x2, y2+50);
           * matrix[x+1][y] = 1; y1+=50; y2+=50; x+=1; } else { i=score-1; } break; case
           * 'W': if (y-1>=0 && matrix[x][y-1]!=1) { g.drawLine(x1, y1, x2-50, y2);
           * matrix[x][y-1] = 1; x1-=50; x2-=50; y-=1; } else { i=score-1; } break;
           * default: break; } } }
           */
    public static void main(final String[] args) {
        new ChessGUI();
    }
}