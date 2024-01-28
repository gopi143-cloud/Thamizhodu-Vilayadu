import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
class CurvedBorderPanel extends JPanel {
    private Color borderColor = new Color(0x520004);
    private int borderThickness = 5;

    public CurvedBorderPanel() {
        setOpaque(false);
    }

  
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        int width = getWidth();
        int height = getHeight();

        // Paint the background color
        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, width, height);

        // Draw the rounded border on top of the background
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.draw(new RoundRectangle2D.Double(
                borderThickness / 2.0, borderThickness / 2.0,
                width - borderThickness, height - borderThickness,
                20, 20));

        g2d.dispose();
    }
}

public class GamePage extends JFrame {
	public CurvedBorderPanel panel[];
    public static ImageIcon[] loadImages(String[] filePaths) {
        List<ImageIcon> imageIcons = new ArrayList<>();

        for (String filePath : filePaths) {
            ImageIcon imageIcon = new ImageIcon(filePath);
            // Resize the image to 70x70 pixels
            Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(image);
            imageIcons.add(imageIcon);
        }

        return imageIcons.toArray(new ImageIcon[0]);
    }

    private JPanel containerPanel;

    public GamePage() {
	JFrame frame = new JFrame();
        Font LFont = loadFontFromFile("Question_Font.ttf", Font.PLAIN, 30);

        frame.setTitle("Curved Bordered Panels");
        frame.setUndecorated(true); // Set window decoration to undecorated

        // Maximize the window
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background of the JFrame to a wood color gradient
        frame.setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // Define the wood color gradient
                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(150, 75, 0), getWidth(), getHeight(), new Color(210, 105, 30));

                // Set the paint to the wood color gradient
                g2d.setPaint(gradient);

                // Fill the entire panel with the wood color gradient
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        containerPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // One row, two columns
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        String[] filePaths = {
                "1.png", "2.png", "3.png", "4.png", "5.png", "6.png"
        };
        String[] title = {"குறுக்கு எழுத்து", "வார்த்தை வேட்டை", "வினாடி வினா", "சுடோகு", "பகடையாட்டம்", "பரமபதம்"};
        ImageIcon[] imageIcons = loadImages(filePaths);
	CurvedBorderPanel panel[] = new CurvedBorderPanel[6];
        for (int i = 0; i < 6; i++) {
            panel[i] = new CurvedBorderPanel();
            panel[i].setLayout(new GridLayout(1, 2)); // One row, two columns

            // Use a JLabel to display the image on the left
            JLabel imageLabel = new JLabel(imageIcons[i]);
            panel[i].add(imageLabel);

            // Add the label with text on the right
            JLabel labelComponent = new JLabel(title[i]);
            labelComponent.setForeground(new Color(0x520004));
            labelComponent.setFont(LFont);
            labelComponent.setHorizontalAlignment(SwingConstants.LEFT);
            labelComponent.setVerticalAlignment(SwingConstants.CENTER);
            panel[i].add(labelComponent);
	    panel[i].setBackground(new Color(205, 133, 63));
            containerPanel.add(panel[i]);
        }
	panel[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
            new CrossWordPuzzleFrame();
        });
            }
});
	panel[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                        RandomQuestionAnswer.readQuestionsAndAnswersFromFile();
        		SwingUtilities.invokeLater(() -> {
            		new OneWord();
        	});
	     }
        });
	panel[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
            new QuizFrame();
        });
            }
        });
	panel[4].addMouseListener(new MouseAdapter() {
            @Override
	   public void mouseClicked(MouseEvent e) {
            SwingUtilities.invokeLater(() -> {
            LudoMain ludoMain = new LudoMain();
            ludoMain.setVisible(true);
        });
            }
        });
	panel[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               SwingUtilities.invokeLater(() -> {
            Random random = new Random();
            int K = random.nextInt(10) + 25;
            int N = 9;
            SudokuGenerator sudokuGenerator = new SudokuGenerator(N, K);
            int[][] sudokuArray = sudokuGenerator.sudokuGenerator();
            new SudokuGame(sudokuArray);
        });
            }
        });
	panel[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        });
	frame.setLayout(null);
	containerPanel.setSize(1100, 560);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int centerX = ((screenSize.width/2) - (containerPanel.getWidth()/2));
	int centerY = ((screenSize.height - containerPanel.getHeight()) / 2);
	
	// Set the bounds of the containerPanel to be centered on the screen
	containerPanel.setBounds(centerX, centerY, containerPanel.getWidth(), containerPanel.getHeight());
	ImageIcon BackArrow = new ImageIcon("Exit_Arrow.png");
        BackArrow = new ImageIcon(BackArrow.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        JLabel backArrow = new JLabel(BackArrow);
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
        containerPanel.setOpaque(false);
        containerPanel.setVisible(true);
        frame.add(containerPanel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        frame.setVisible(true);
    }
	
    private static Font loadFontFromFile(String filePath, int style, int size) {
        try {
            File fontFile = new File(filePath);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", style, size);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GamePage());
    }
}
