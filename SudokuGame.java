import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Random;
class SudokuGenerator {

    private int[][] mat;
    private int N; // number of columns/rows.
    private int SRN; // square root of N
    private int K; // No. Of missing digits
    public SudokuGenerator(int N, int K) {
        this.N = N;
        this.K = K;
        double SRNd = Math.sqrt(N);
        SRN = (int) SRNd;
        mat = new int[N][N];
    }
    public int[][] sudokuGenerator() {
        fillDiagonal();
        fillRemaining(0, SRN);
        removeKDigits();
        return mat;
    }
    void fillDiagonal() {
        for (int i = 0; i < N; i = i + SRN)
            fillBox(i, i);
    }
    boolean unUsedInBox(int rowStart, int colStart, int num) {
        for (int i = 0; i < SRN; i++)
            for (int j = 0; j < SRN; j++)
                if (mat[rowStart + i][colStart + j] == num)
                    return false;
        return true;
    }
    void fillBox(int row, int col) {
        int num;
        for (int i = 0; i < SRN; i++) {
            for (int j = 0; j < SRN; j++) {
                do {
                    num = randomGenerator(N);
                } while (!unUsedInBox(row, col, num));
                mat[row + i][col + j] = num;
            }
        }
    }
    int randomGenerator(int num) {
        return (int) Math.floor((Math.random() * num + 1));
    }
    boolean CheckIfSafe(int i, int j, int num) {
        return (unUsedInRow(i, num) && unUsedInCol(j, num) && unUsedInBox(i - i % SRN, j - j % SRN, num));
    }
    boolean unUsedInRow(int i, int num) {
        for (int j = 0; j < N; j++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    boolean unUsedInCol(int j, int num) {
        for (int i = 0; i < N; i++)
            if (mat[i][j] == num)
                return false;
        return true;
    }
    boolean fillRemaining(int i, int j) {
        if (j >= N && i < N - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= N && j >= N)
            return true;
        if (i < SRN) {
            if (j < SRN)
                j = SRN;
        } else if (i < N - SRN) {
            if (j == (int) (i / SRN) * SRN)
                j = j + SRN;
        } else {
            if (j == N - SRN) {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }
        for (int num = 1; num <= N; num++) {
            if (CheckIfSafe(i, j, num)) {
                mat[i][j] = num;
                if (fillRemaining(i, j + 1))
                    return true;
                mat[i][j] = 0;
            }
        }
        return false;
    }
    public void removeKDigits() {
        int count = K;
        while (count != 0) {
            int cellId = randomGenerator(N * N) - 1;
            int i = (cellId / N);
            int j = cellId % 9;
            if (j != 0)
                j = j - 1;
            if (mat[i][j] != 0) {
                count--;
                mat[i][j] = 0;
            }
        }
    }
}
class SudokuSolver {
    static int N = 9;
    public static int[][] solveSudoku(int[][] unsolvedGrid) {
        int[][] solvedGrid = new int[N][N];
        // Copy the values from the unsolvedGrid to the solvedGrid
        for (int i = 0; i < N; i++) {
            System.arraycopy(unsolvedGrid[i], 0, solvedGrid[i], 0, N);
        }

        if (solveSudokuUtil(solvedGrid, 0, 0)) {
            return solvedGrid;
        } else {
            System.out.println("No Solution exists");
            return null;
        }
    }
    static boolean solveSudokuUtil(int[][] grid, int row, int col) {
        if (row == N - 1 && col == N) {
            return true;
        }
        if (col == N) {
            row++;
            col = 0;
        }
        if (grid[row][col] != 0) {
            return solveSudokuUtil(grid, row, col + 1);
        }
        for (int num = 1; num <= N; num++) {
            if (isSafe(grid, row, col, num)) {
                grid[row][col] = num;

                if (solveSudokuUtil(grid, row, col + 1)) {
                    return true;
                }

                grid[row][col] = 0; // Backtrack
            }
        }

        return false;
    }
    static boolean isSafe(int[][] grid, int row, int col, int num) {
        for (int x = 0; x < N; x++) {
            if (grid[row][x] == num || grid[x][col] == num) {
                return false;
            }
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
class SudokuDocumentListener implements DocumentListener {
    private int row;
    private int col;
    public SudokuDocumentListener(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(SudokuGame.check(row, col))
            SudokuGame.cells[row][col].setBorder(new LineBorder(Color.GREEN, 2));
        else
            SudokuGame.cells[row][col].setBorder(new LineBorder(Color.RED, 2));
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        if(SudokuGame.check(row, col))
            SudokuGame.cells[row][col].setBorder(new LineBorder(Color.GREEN, 2));
        else
            SudokuGame.cells[row][col].setBorder(new LineBorder(Color.RED, 2));
    }
    public void removeUpdate(DocumentEvent e) {
        SudokuGame.cells[row][col].setBorder(new LineBorder(Color.RED, 2));
    }
}
public class SudokuGame extends JFrame {
    public static JTextField[][] cells;
    private JTextField focusedTextField;
    private static int[][] solvedGrid;
    private int[][] sudokuArray;
    public SudokuGame(int[][] sudokuArrays) {
        sudokuArray = sudokuArrays;
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solvedGrid = SudokuSolver.solveSudoku(sudokuArray);
        ImageIcon BackArrow = new ImageIcon("Back_SUDOKU.png");
        BackArrow = new ImageIcon(BackArrow.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        JLabel backArrow = new JLabel(BackArrow);
        backArrow.setBounds(20, 20, 60, 60);
        backArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    SwingUtilities.invokeLater(() -> {
                        BackSudo.showCustomMessageDialog(frame);
                    });
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                BackSudo.showCustomMessageDialog(frame);
            }
        });
        frame.add(backArrow);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3, 5, 5));
        buttonPanel.setSize(300, 300);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(950, 50, 300, 300);
        frame.add(buttonPanel);
        JButton solveButton = new JButton("Solve");
        JButton resetButton = new JButton("Reset");
        JButton gameButton = new JButton("New Game");
        solveButton.setBounds(1050, 450, 100, 50);
        resetButton.setBounds(1050, 550, 100, 50);
        gameButton.setBounds(1050, 650, 100, 50);
        frame.add(solveButton);
        frame.add(resetButton);
        frame.add(gameButton);
        JPanel panel = new JPanel(new GridLayout(3, 3, 2, 2));
        cells = new JTextField[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                if (sudokuArray[i][j] != 0) {
                    String str = String.valueOf(sudokuArray[i][j]);
                    cells[i][j] = new JTextField(str);
                    cells[i][j].transferFocus();
                    cells[i][j].setForeground(new Color(0xA52A2A));
                    cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                    cells[i][j].setBorder(new LineBorder(Color.BLUE, 2));
                    cells[i][j].setEditable(false);
                    cells[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                } else {
                    cells[i][j] = new JTextField();
                    cells[i][j].setHorizontalAlignment(JTextField.CENTER);
                    cells[i][j].setBorder(new LineBorder(Color.BLACK, 2));
                    cells[i][j].setEditable(false);
                    cells[i][j].setFont(new Font("Arial", Font.PLAIN, 50));
                    cells[i][j].addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            cells[finalI][finalJ].setBorder(new LineBorder(Color.BLUE, 2));
                            setFocusedTextField(cells[finalI][finalJ]);
                        }
                        @Override
                        public void focusLost(FocusEvent e) {
                            cells[finalI][finalJ].setBorder(new LineBorder(Color.BLACK, 2));
                        }
                    });
                    cells[i][j].getDocument().addDocumentListener(new SudokuDocumentListener(i, j));
                }
            }
        }
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(sudokuArray);
            }
        });
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetBoard(solvedGrid);
            }
        });
        gameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame(frame);
            }
        });
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                JPanel bigGridPanel = new JPanel(new GridLayout(3, 3, 2, 2));
                bigGridPanel.setBorder(new LineBorder(Color.BLUE, 2));
                for (int x = i; x < i + 3; x++) {
                    for (int y = j; y < j + 3; y++) {
                        bigGridPanel.add(cells[x][y]);
                    }
                }

                panel.add(bigGridPanel);
            }
        }
        for (int num = 1; num <= 9; num++) {
            JButton numberButton = createButton(String.valueOf(num));
            numberButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField focusedTextField = getFocusedTextField();
                    if (focusedTextField != null) {
                        focusedTextField.setText(((JButton) e.getSource()).getText());
                    }
                }
            });
            buttonPanel.add(numberButton);
        }
        panel.setSize(700, 700);
        panel.setBounds(90, 50, 700, 700);
        frame.add(panel);

        JPanel background = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, getHeight());
                float[] dist = {0.0f, 1.0f};
                Color[] colors = {new Color(0xa1c4fd), new Color(0xc2e9fb)};
                g2d.setPaint(new LinearGradientPaint(start, end, dist, colors));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        background.setBounds(0, 0, getWidth(), getHeight());
        frame.add(background);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(60, 60));
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        button.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if (model.isPressed()) {
                button.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));
            } else {
                button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2, true));
            }
        });

        return button;
    }
    public void resetBoard(int[][] sudokuArray){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int finalI = i;
                int finalJ = j;
                if (sudokuArray[i][j] != 0) {
                    String str = String.valueOf(sudokuArray[i][j]);
                    cells[i][j].setText(str);
                } else {
                    cells[i][j].setText("");

                }
            }
        }
    }
    private void newGame(JFrame f) {
        f.dispose();
        Random random = new Random();
        int K = random.nextInt(10) + 25;
        int N = 9;
        SudokuGenerator sudokuGenerator = new SudokuGenerator(N, K);
        int[][] sudokuArray = sudokuGenerator.sudokuGenerator();
        new SudokuGame(sudokuArray);
    }
    private JTextField getFocusedTextField() {
        return focusedTextField;
    }
    public static boolean check(int i,int j){
        String get = cells[i][j].getText();
        int getValue = Integer.parseInt(get);
        int crtValue = solvedGrid[i][j];
        return crtValue == getValue;
    }

    private void setFocusedTextField(JTextField textField) {
        focusedTextField = textField;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Random random = new Random();
            int K = random.nextInt(10) + 25;
            int N = 9;
            SudokuGenerator sudokuGenerator = new SudokuGenerator(N, K);
            int[][] sudokuArray = sudokuGenerator.sudokuGenerator();
            new SudokuGame(sudokuArray);
        });
    }
}
