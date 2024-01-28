import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class SnakeAndLadderPanel extends JPanel {
    private final int panelNumber;
    public SnakeAndLadderPanel(int panelNumber) {
        this.panelNumber = panelNumber;
        setPreferredSize(new Dimension(70, 70));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(panelNumber));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
    public int getPanelNumber() {
        return panelNumber;
    }
}
class Play extends JPanel{
    private int currentPosition;
    public boolean movAble = false;
    private ImageIcon coinImage;
    public Play(ImageIcon coinImage,int currentPosition) {
        this.currentPosition = currentPosition;
        this.coinImage = coinImage;
        setOpaque(false);
        setVisible(false);
    }
    public int getCurrentPosition() {
        return currentPosition;
    }
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
    public void Check(int diceValue){
        if(diceValue==1 && currentPosition==0){
            movAble = true;
            setVisible(true);
        }
    }
    public ImageIcon GetImage() {
        if (coinImage != null) {
            return coinImage;
        } else {
            return null;
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (coinImage != null) {
            g.drawImage(coinImage.getImage(), 0, 0, 70, 70, this);
        }
    }
}

class MPanel extends JPanel {
    public SnakeAndLadderPanel panel[][];
    public MPanel() {
        setLayout(new GridLayout(10, 10));
        int panelNumber = 100;
        panel= new SnakeAndLadderPanel[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                panel[i][j] = new SnakeAndLadderPanel(panelNumber);
                panel[i][j].setSize(70,70);
                add(panel[i][j]);
                if(i%2==0){
                    --panelNumber;
                }else{
                    panelNumber=panelNumber+1;
                }
            }
            if(i%2==0){
                panelNumber = panelNumber - 9;
            }else{
                panelNumber-=11;
            }
        }
        setSize(700,700);
    }
    public SnakeAndLadderPanel[][] getPanels() {
        return panel;
    }
    public SnakeAndLadderPanel getPanelFromNumber(int targetPanelNumber) {
        for (SnakeAndLadderPanel[] row : panel) {
            for (SnakeAndLadderPanel p : row) {
                if (p.getPanelNumber() == targetPanelNumber) {
                    return p;
                }
            }
        }
        return null; // Panel not found for the given number
    }
}

class PanelMover {
    private Timer timer;
    private boolean moveFinished = false;
    public PanelMover(JPanel panel, ImageIcon icon, int targetX, int targetY, ActionListener completionListener) {
        Rectangle bounds = panel.getBounds();
        final int[] coordinates = {bounds.x, bounds.y}; // Using an array to store mutable values

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coordinates[0] < targetX) {
                    coordinates[0]++;
                } else if (coordinates[0] > targetX) {
                    coordinates[0]--;
                }

                if (coordinates[1] < targetY) {
                    coordinates[1]++;
                } else if (coordinates[1] > targetY) {
                    coordinates[1]--;
                }

                panel.setBounds(coordinates[0], coordinates[1], bounds.width, bounds.height);
                panel.repaint();

                if (coordinates[0] == targetX && coordinates[1] == targetY) {
                    timer.stop();
                    moveFinished = true;
                    if (completionListener != null) {
                        completionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
                    }
                }
            }
        });
        timer.start();
    }
    public boolean isMoveFinished() {
        return moveFinished;
    }
    public PanelMover(JPanel panel, ImageIcon icon, int targetX, int targetY) {
        Rectangle bounds = panel.getBounds();
        final int[] coordinates = {bounds.x, bounds.y}; // Using an array to store mutable values

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (coordinates[0] < targetX) {
                    coordinates[0]++;
                } else if (coordinates[0] > targetX) {
                    coordinates[0]--;
                }

                if (coordinates[1] < targetY) {
                    coordinates[1]++;
                } else if (coordinates[1] > targetY) {
                    coordinates[1]--;
                }

                panel.setBounds(coordinates[0], coordinates[1], bounds.width, bounds.height);
                panel.repaint();

                if (coordinates[0] == targetX && coordinates[1] == targetY) {
                    timer.stop();
                }
            }
        });
        timer.start();
    }
}
public class SnakeLadder {
    private JLabel diceLabel,diceValue,playersTurn,board,backArrow,Coin,chance;
    private JLabel[] Name,Pos,PlayerLabel;
    private Timer timer;
    private ImageIcon[] diceImages,PlayerImage;
    private int currentIndex = 0;
    private int numPlayers=5,i=0;
    Play[] players;
    MPanel SPanel;
    public static class MainPanel extends JPanel{
        public MainPanel(){}
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(139, 69, 19), getWidth(), getHeight(), new Color(205, 133, 63));
            g2d.setPaint(gradientPaint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }
    private int checkForSpecialMove(int currentPosition) {
        // Check for ladders
        if (currentPosition == 4) {
            return 56;
        } else if (currentPosition == 12) {
            return 50;
        } else if (currentPosition == 14) {
            return 55;
        }else if (currentPosition == 22) {
            return 58;
        }else if (currentPosition == 41) {
            return 79;
        }else if (currentPosition == 54) {
            return 88;
        }
        else if (currentPosition == 96) {
            return 42;
        } else if (currentPosition == 94) {
            return 71;
        } else if (currentPosition == 75) {
            return 32;
        }else if (currentPosition == 48) {
            return 16;
        }else if (currentPosition == 37) {
            return 3;
        }else if (currentPosition == 28) {
            return 10;
        }

        // No special move
        return -1;
    }

    public SnakeLadder(int numPlayers) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        this.numPlayers = numPlayers;
        // Set the frame to full screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        MainPanel bg = new MainPanel();

        //CPanel
        players = new Play[numPlayers];
        JPanel CPanel = new JPanel(null);
        CPanel.setBounds((int) Math.round(screenWidth*0.1),(int) Math.round(screenHeight*0.05),700,700);
        for (int i = 0; i < numPlayers; i++) {
            ImageIcon playerImage = new ImageIcon("Player_" + (i + 1) + ".png");
            players[i] = new Play(playerImage,0);
            players[i].setBounds(0,630, 70, 70);
            players[i].setVisible(false);
            CPanel.add(players[i]);
        }
        CPanel.setOpaque(false);
        frame.add(CPanel);

        //Dice
        diceImages = new ImageIcon[6];
        for (int i = 0; i < 6; i++) {
            diceImages[i] = new ImageIcon("Dice" + (i + 1) + ".png");
            diceImages[i] = new ImageIcon(diceImages[i].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        }

        //Player
        PlayerImage = new ImageIcon[numPlayers];
        Pos = new JLabel[numPlayers];
        Name = new JLabel[numPlayers];
        PlayerLabel = new JLabel[numPlayers];int x=0;
        for (int j = 0; j < numPlayers; j++) {
            Pos[j] = new JLabel("");
            Name[j] = new JLabel("");
            PlayerImage[j] = new ImageIcon("Player_" + (j + 1) + ".png");
            PlayerImage[j] = new ImageIcon(PlayerImage[j].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            PlayerLabel[j] = new JLabel(PlayerImage[j]);
            Name[j].setText("Player "+(j+1));
            Name[j].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            Pos[j].setFont(new Font("Times New Roman", Font.PLAIN, 20));
            Pos[j].setHorizontalAlignment(JLabel.CENTER);
            Name[j].setHorizontalAlignment(JLabel.CENTER);
            Pos[j].setForeground(Color.WHITE);
            Name[j].setForeground(Color.WHITE);
            if(j%2==0){
                PlayerLabel[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+300,100,100);
                Name[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+390,100,30);
                Pos[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+420,100,30);
                frame.add(PlayerLabel[j]);
                frame.add(Name[j]);
                frame.add(Pos[j]);
            }
            else{
                PlayerLabel[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+450,100,100);
                Name[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+540,100,30);
                Pos[j].setBounds((int) Math.round(screenWidth*0.7)+x,(int) Math.round(screenHeight*0.1)+570,100,30);
                frame.add(PlayerLabel[j]);
                frame.add(Name[j]);
                frame.add(Pos[j]);
                x+=120;
            }

        }

        SPanel = new MPanel();
        //positionPanel = SPanel.getPanels();
        diceLabel = new JLabel(diceImages[0]);
        diceLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startRollingAnimation(i);
                if(i==numPlayers-1)
                    i=-1;
                i++;
            }
        });
        diceValue=new JLabel("");
        chance=new JLabel("Now Player 1 Turns");
        diceValue.setFont(new Font("Times New Roman", Font.BOLD, 100));
        chance.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        chance.setBounds((int) Math.round(screenWidth*0.7),(int) Math.round(screenHeight*0.1)+100,300,100);
        //chance.setForeground(Color.RED);
        diceValue.setForeground(Color.WHITE);
        diceLabel.setBounds((int) Math.round(screenWidth*0.7),(int) Math.round(screenHeight*0.1),100,100);
        diceValue.setBounds((int) Math.round(screenWidth*0.7)+110,(int) Math.round(screenHeight*0.1),100,100);

        //Back Arrow
        ImageIcon BackArrow = new ImageIcon("Exit_Arrow.png");
        BackArrow = new ImageIcon(BackArrow.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        backArrow = new JLabel(BackArrow);
        backArrow.setBounds(20,20,60,60);
        backArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    SwingUtilities.invokeLater(() -> {
                        CustomMessageDialogExample.showCustomMessageDialog(frame);
                    });
                }
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                CustomMessageDialogExample.showCustomMessageDialog(frame);
            }
        });
        frame.add(backArrow);

        ImageIcon Board = new ImageIcon("Snake_Ladder_Board.jpg");
        Board = new ImageIcon(Board.getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH));
        board = new JLabel(Board);
        board.setSize(700,700);
        board.setBounds((int) Math.round(screenWidth*0.1),(int) Math.round(screenHeight*0.05),700,700);
        frame.add(board);

        SPanel.setBounds((int) Math.round(screenWidth*0.1),(int) Math.round(screenHeight*0.05),700,700);
        SPanel.setSize(700,700);
        frame.add(SPanel);
        frame.add(diceLabel);
        frame.add(diceValue);
        frame.add(chance);
        frame.add(bg);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }private void startRollingAnimation(int i) {
        Random random = new Random();
        int stopIndex = random.nextInt(diceImages.length) + 1;
        int animationSpeed = 100; // Milliseconds between each frame change
        currentIndex = 0;

        Timer rollingTimer = new Timer(animationSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceLabel.setIcon(diceImages[currentIndex]);
                currentIndex++;
                if (currentIndex >= diceImages.length) {
                    currentIndex = 0;
                }
            }
        });
        Timer stopTimer = new Timer(animationSpeed * (stopIndex + 1), new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                rollingTimer.stop();
                diceValue.setText("" + stopIndex);
                int position = players[i].getCurrentPosition();
                int check=position+stopIndex;
                players[i].Check(stopIndex);
                int k=i+2;
                if(k==numPlayers+1)
                    k=1;
                chance.setText("Now player "+k+" Turns");
                if(check<=100 && players[i].movAble){
                    movePlayerIncrementally(position, stopIndex,i);
                }
            }
        });

        rollingTimer.start();
        stopTimer.setRepeats(false);
        stopTimer.start();

    }
    private void call(int j,int c){
        int special=checkForSpecialMove(c+1);
        if(special!=-1){
            SnakeAndLadderPanel target = SPanel.getPanelFromNumber(special);
            Rectangle bounds = target.getBounds();
            new PanelMover(players[j], players[j].GetImage(), bounds.x, bounds.y);
            players[j].setCurrentPosition(special);
        }
        Pos[j].setText("Position:" + players[j].getCurrentPosition());
    }
    private void movePlayerIncrementally(int currentPosition, int remainingMoves,int i) {
        if (remainingMoves > 0) {
            int newPosition = currentPosition + 1;
            players[i].setCurrentPosition(newPosition);
            if (newPosition == 100) {
                declareWinner(i);
                return; // Stop the movement for this player
            }
            SnakeAndLadderPanel target = SPanel.getPanelFromNumber(newPosition);
            Rectangle bounds = target.getBounds();

            new PanelMover(players[i], players[1].GetImage(), bounds.x, bounds.y, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    movePlayerIncrementally(newPosition, remainingMoves - 1,i);
                    if (remainingMoves == 1) {
                        call(i,currentPosition);
                    }
                }
            });
        }
    }
    private void declareWinner(int winningPlayerIndex) {
        int winningPlayerNumber = winningPlayerIndex + 1;
        JOptionPane.showMessageDialog(
                null, "Player " + winningPlayerNumber + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

        int[] ranks = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            ranks[i] = i + 1;
        }

        // Sort players based on their current positions
        for (int i = 0; i < numPlayers - 1; i++) {
            for (int j = i + 1; j < numPlayers; j++) {
                if (players[i].getCurrentPosition() < players[j].getCurrentPosition()) {
                    // Swap ranks if the current position is greater
                    int temp = ranks[i];
                    ranks[i] = ranks[j];
                    ranks[j] = temp;

                    // Swap players if the current position is greater
                    Play tempPlayer = players[i];
                    players[i] = players[j];
                    players[j] = tempPlayer;
                }
            }
        }

        StringBuilder rankMessage = new StringBuilder("Ranks:\n");
        for (int i = 0; i < numPlayers; i++) {
            rankMessage.append("Rank ").append(i + 1).append(" - ")
                    .append("Player ").append(ranks[i]).append(" (Position ")
                    .append(players[i].getCurrentPosition()).append(")\n");
        }

        JOptionPane.showMessageDialog(null, rankMessage.toString(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Prompt user to select the number of players
            String[] options = {"2", "3", "4", "5"};
            String selectedOption = (String) JOptionPane.showInputDialog(
                    null,
                    "Select the number of players:",
                    "Number of Players",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selectedOption != null) {
                int numPlayers = Integer.parseInt(selectedOption);
                new SnakeLadder(numPlayers);
            }
        });
    }
}