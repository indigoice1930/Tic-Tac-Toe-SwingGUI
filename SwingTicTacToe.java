package Lab9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTicTacToe extends JFrame implements ActionListener {
    public static void main(String[] args) {
        SwingTicTacToe ttt = new SwingTicTacToe();
        ttt.setVisible(true);
        ///System.out.println(-123%10);
    }

    public static final int WIDTH = 500;
    public static final int HEIGHT = 300;

    private String[] curPlayer;
    private int playerIndex = 0;
    private static JLabel northLabel;
    private boolean gameOver;

    private int count = 0;
    private final static int BOARD_SIZE = 3;
    private String[][] board = new String[BOARD_SIZE][BOARD_SIZE]; //Keep track of the change of board made by two players
    private JButton[] JButtons = new JButton[BOARD_SIZE*BOARD_SIZE];

    public SwingTicTacToe(){
        super();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "0";
            }
        }

        curPlayer = new String[]{"X", "O"};

        setSize(WIDTH, HEIGHT);
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.gray);

        setLayout(new BorderLayout());

        northLabel = new JLabel("Welcome to Tic Tac Toe!");
        northLabel.setForeground(Color.pink);
        northLabel.setFont(new Font("Apple Casual", Font.PLAIN, 25));
        add(northLabel, BorderLayout.NORTH);

        JPanel game_board = new JPanel();
        add(game_board, BorderLayout.CENTER);
        game_board.setLayout(new GridLayout(BOARD_SIZE,BOARD_SIZE));

        for(int i = 0; i < JButtons.length; i++){
            JButtons[i] = new JButton();
            JButton curButton = JButtons[i];
            //curButton.setSize(100,100);
            curButton.setActionCommand(String.valueOf(i));
            curButton.addActionListener(this);
            curButton.setFont(new Font("Apple Casual", Font.PLAIN, 30));
            curButton.setBackground(Color.gray);
            curButton.setOpaque(true);
            game_board.add(JButtons[i]);

        }

        JPanel bottomButtons = new JPanel();
        add(bottomButtons, BorderLayout.SOUTH);
        bottomButtons.setLayout(new FlowLayout());

        JButton endButton = new JButton("End");
        endButton.setForeground(Color.gray);
        endButton.setFont(new Font("Apple Casual", Font.PLAIN, 25));
        endButton.addActionListener(new EndingListener());
        bottomButtons.add(endButton);

        JButton restartButton = new JButton("Restart");
        restartButton.setForeground(Color.gray);
        restartButton.setFont(new Font("Apple Casual", Font.PLAIN, 25));
        RestartListener a = new RestartListener();
        a.setTicTacToe(this);
        restartButton.addActionListener(a);
        bottomButtons.add(restartButton);
    }

    public boolean win(int x, int y) {//0-based coordinates
        if (x + y != 1 && x + y != 3) { //when the coordinates are at position 1,3,5,7,9
            //decreasing diagonal
            if ((board[0][0].equals("X") || board[0][0].equals("O")) && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
                return true;
            }
            //increasing diagonal
            if ((board[1][1].equals("X") || board[1][1].equals("O")) && board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])){
                return true;
            }
        }
        //horizontal
        if ((board[x][0].equals("X") || board[x][0].equals("O")) && board[x][0].equals(board[x][1]) && board[x][1].equals(board[x][2])) {
            return true;
        }
        //vertical
        if ((board[0][y].equals("X") || board[0][y].equals("O")) && board[0][y].equals(board[1][y]) && board[1][y].equals(board[2][y])) {
            return true;
        }
        return false;
    }

    public void placeToken(String s){
        if(gameOver) return;

        int num = Integer.valueOf(s);
        JButtons[num].setBackground(Color.PINK);
        JButtons[num].setContentAreaFilled(true);

        //check if the clicked button was filled already
        int x = num / BOARD_SIZE;
        int y = num % BOARD_SIZE;
        if (board[x][y].equals("X") || board[x][y].equals("O")) {
            northLabel.setText("Please click on empty buttons.");
            return;
        }

        board[x][y] = curPlayer[playerIndex];
        count++;
        JButtons[num].setText(curPlayer[playerIndex]);

        //check if the current player wins
        if(win(x,y)){
            northLabel.setText(curPlayer[playerIndex] + " wins! Game Over.");
            gameOver = true;
        }

        //if not, check if all 9 places have been filled
        else if(count == BOARD_SIZE*BOARD_SIZE){
            northLabel.setText("It's a tie! Game Over.");
            gameOver = true;
        }

        //if not, update the board array's values
        else {
            //set the text to current player name
            northLabel.setText("Player " + curPlayer[playerIndex] + "'s token is successfully placed.");
            //set the next player name
            if(playerIndex == 0) playerIndex = 1;
            else playerIndex = 0;
        }
    }
    public void reset() {
        northLabel.setText("Welcome to Tic Tac Toe!");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "0";
            }
        }
        for(int i = 0; i < JButtons.length; i++){
            JButtons[i].setText("");
            JButtons[i].setBackground(Color.gray);
        }
        count = 0;
        gameOver = false;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String buttonString = actionEvent.getActionCommand();
        placeToken(buttonString);
    }
}
