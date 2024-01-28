import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
class QuizReader{
    private static final String FILE_PATH = "questions1.dat";
    @SuppressWarnings("unchecked")
    public static List<Question> readQuestions() {
        List<Question> questions = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                questions = (List<Question>) obj;
            } else {
                System.err.println("Unexpected object type found in the file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return questions;
    }
}

class CurvedBorderedPanel extends JPanel {
    private Color borderColor = new Color(0x0099dc);
    private int borderThickness = 5;
    public CurvedBorderedPanel(int width, int height, int x, int y) {
        setBounds(x, y, width, height);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(borderColor);
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.draw(new RoundRectangle2D.Double(borderThickness / 2.0, borderThickness / 2.0, getWidth() - borderThickness, getHeight() - borderThickness, 20, 20));
        g2d.dispose();
    }
}
class QuestionPanel extends CurvedBorderedPanel {
    private  Vector<Integer> index = new Vector<>();
    private JLabel questionLabel,noteLabel;
    private JButton correctButton, skipButton;
    public JRadioButton[] roundRadioButtons;
    public ButtonGroup buttonGroup;
    private int checkCount = 0;
    private List<Question> quizQuestions;
    private Set<Integer> displayedQuestionIndices;
    public QuestionPanel(int width, int height, int x, int y) {
        super(width, height, x, y);
        quizQuestions = QuizReader.readQuestions();
        displayedQuestionIndices = new HashSet<>();
        initializeComponents(width, height, x, y);

    }
    public void initializeComponents(int w, int h, int xx, int yy) {
        setLayout(null);
        setOpaque(false);
        quizQuestions = QuizReader.readQuestions();
        JPanel SubPanel = new JPanel(null);
        SubPanel.setBounds(50, 50, w-50, h-50);
        SubPanel.setOpaque(false);
        Font QFont = loadFont("Tamil012.ttf", Font.PLAIN, 40);
        // Create question label
        // Question Label
        questionLabel = new JLabel("");
        questionLabel.setFont(QFont);
        questionLabel.setBounds(30,30,w-50,50);
        questionLabel.setForeground(new Color(0x0099dc));
        SubPanel.add(questionLabel);

        // Create a panel for options
        JPanel choicesPanel = new JPanel(null);
        choicesPanel.setBounds(30, 100, w-50, 300);
        choicesPanel.setOpaque(false);
        int numberOfButtons = 4;
        roundRadioButtons = new JRadioButton[numberOfButtons];
        roundRadioButtons[0] = new JRadioButton("");
        roundRadioButtons[1] = new JRadioButton("");
        roundRadioButtons[2] = new JRadioButton("");
        roundRadioButtons[3] = new JRadioButton("");
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < numberOfButtons; i++) {
            roundRadioButtons[i].setFont(QFont);
            roundRadioButtons[i].setOpaque(false);
            roundRadioButtons[i].setBounds(10,i*60,700,50);
            roundRadioButtons[i].setForeground(new Color(0x0099dc));
            roundRadioButtons[i].setIcon(new RoundRadioButtonIcon());
            roundRadioButtons[i].setSelectedIcon(new RoundRadioButtonSelectedIcon());
            choicesPanel.add(roundRadioButtons[i]);
            buttonGroup.add(roundRadioButtons[i]);
            roundRadioButtons[i].setFocusPainted(false);
        }
        displayNextQuestion();
        SubPanel.add(choicesPanel);
        noteLabel = new JLabel("");
        Font NFont = loadFont("Tamil012.ttf", Font.PLAIN, 20);
        noteLabel.setFont(NFont);
        noteLabel.setForeground(Color.RED);
        noteLabel.setBounds(50, 340, w-50,30);
        SubPanel.add(noteLabel);
        // Create a panel for buttons
        JPanel buttonsPanel = new JPanel(null);
        buttonsPanel.setBounds(30, 380, w-50, 70);

        skipButton = new JButton("இக்கேள்வியை தவிர்க்க");
        correctButton = new JButton("விடை சரி பார்க்க");
        Font BFont = loadFont("Tamil012.ttf", Font.PLAIN, 40);
        skipButton.setFont(BFont);
        correctButton.setFont(BFont);
        correctButton.setBorder(new LineBorder(new Color(0x0099dc), 2));
        skipButton.setBounds(10, 5, 400, 60);
        correctButton.setBounds(450, 5, 300, 60);
        correctButton.setFocusPainted(false);
        correctButton.setContentAreaFilled(false);
        correctButton.setForeground(new Color(0xfffb02));
        skipButton.setFocusPainted(false);
        skipButton.setContentAreaFilled(false);
        skipButton.setForeground(new Color(0xff0e57));
        skipButton.setBorder(new LineBorder(new Color(0x0099dc), 2));
        buttonsPanel.add(skipButton);
        buttonsPanel.add(correctButton);
        buttonsPanel.setOpaque(false);
        SubPanel.add(buttonsPanel);
        skipButton.setEnabled(false);
        add(SubPanel);
        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayNextQuestion();
		checkCount = 0;
            }
        });

        correctButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                if(checkCount == 5)
                    skipButton.setEnabled(true);
            }
        });
    }
    private void displayNextQuestion() {
        if (displayedQuestionIndices.size() == quizQuestions.size()) {
            System.out.println("No more questions!");
            return;
        }
        if (displayedQuestionIndices.size() == 0) {
            Collections.shuffle(quizQuestions);
        }
        int randomIndex;
        do {
            randomIndex = (int) (Math.random() * quizQuestions.size());
        } while (displayedQuestionIndices.contains(randomIndex));
        Question currentQuestion = quizQuestions.get(randomIndex);
        questionLabel.setText(currentQuestion.getQuestion());
        String[] choices = currentQuestion.getChoices();
        for (int i = 0; i < choices.length && i < roundRadioButtons.length; i++) {
            roundRadioButtons[i].setText(choices[i]);
        }
        displayedQuestionIndices.add(randomIndex);
        index.add(randomIndex);
    }
    private int getCurrentQuestionIndex() {
        return index.lastElement();
    }
    private int getSelectedAnswerIndex() {
        for (int i = 0; i < roundRadioButtons.length; i++) {
            if (roundRadioButtons[i].isSelected()) {
                return i+1;
            }
        }
        return -1;

    }

    private boolean isAnswerCorrect(int selectedAnswerIndex) {
        int currentQuestionIndex = getCurrentQuestionIndex();

        if (currentQuestionIndex != -1) {
            Question currentQuestion = quizQuestions.get(currentQuestionIndex);
            int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
            return selectedAnswerIndex == correctAnswerIndex;
        }

        return false;
    }
    private void checkAnswer() {
        if (correctButton.getText().equals("அடுத்த கேள்வி")) {
            QuizFrame.updateCoinCount(50);
            QuizFrame.updateStarCount(5);
            displayNextQuestion();
            correctButton.setText("விடை சரி பார்க்க");
            noteLabel.setText("");
            noteLabel.setForeground(Color.RED);
            buttonGroup.clearSelection();
            return;
        } else {
            int selectAnswerIndex = getSelectedAnswerIndex();

            if (selectAnswerIndex != -1) {
                if (isAnswerCorrect(selectAnswerIndex)) {
                    noteLabel.setText("சரியான விடை, தொடர அடுத்த கேள்வியை தொடவும்");
                    noteLabel.setForeground(Color.GREEN);
                    correctButton.setText("அடுத்த கேள்வி");
                    if (checkCount == 5) {
                        checkCount = 0;
                        skipButton.setEnabled(false);
                    }
                    checkCount++;
                } else {
                    noteLabel.setText("சரியான விடையைத் தேர்ந்தெடுக்கவும்");
                    noteLabel.setForeground(Color.RED);
                    checkCount = 0;
                }
            } else {
                noteLabel.setText("சரியான விடையைத் தேர்ந்தெடுக்கவும்");
                noteLabel.setForeground(Color.RED);
            }
        }
    }
    private static Font loadFont(String filePath, int style, int size) {
        try {
            File fontFile = new File(filePath);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", style, size);
        }
    }
}
class RoundRadioButtonIcon implements Icon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(new Color(0xfffb02));
        g.fillOval(x, y, getIconWidth(), getIconHeight());
        g.setColor(Color.BLACK);
        g.drawOval(x, y, getIconWidth(), getIconHeight());
    }

    @Override
    public int getIconWidth() {
        return 20; 
    }

    @Override
    public int getIconHeight() {
        return 20; 
    }
}
class RoundRadioButtonSelectedIcon extends RoundRadioButtonIcon {
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        super.paintIcon(c, g, x, y);
        g.setColor(new Color(0xff0e57));
        g.fillOval(x, y, getIconWidth(), getIconHeight());
    }
}

public class QuizFrame extends JFrame {
    public JLabel backArrow,coin,star;
    public static JLabel coinCount,starCount;
    public double width,height;
    public QuizFrame() {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        ImageIcon BackArrow = new ImageIcon("BackQ.png");
        BackArrow = new ImageIcon(BackArrow.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        backArrow = new JLabel(BackArrow);
        backArrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    SwingUtilities.invokeLater(() -> {
                        Back.showCustomMessageDialog(frame);
                    });
                }
            }
        });
        ImageIcon Coin = new ImageIcon("Coins.png");
        Coin = new ImageIcon(Coin.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        coin = new JLabel(Coin);
        ImageIcon Star = new ImageIcon("Star.png");
        Star = new ImageIcon(Star.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        star = new JLabel(Star);
        Font TitleFont = loadFontFromFile("new0.ttf", Font.PLAIN, 60);
        starCount = new JLabel("0");
        starCount.setFont(TitleFont);
        starCount.setForeground(new Color(0xfffb02));
        coinCount = new JLabel("0");
        coinCount.setForeground(new Color(0xfffb02));
        coinCount.setFont(TitleFont);
        JLabel titleText = new JLabel("வினாடி");
        titleText.setForeground(new Color(0xfffb02));
        titleText.setFont(TitleFont);
        JLabel TitleText = new JLabel(" வினா!");
        TitleText.setForeground(new Color(0xff0e57));
        TitleText.setFont(TitleFont);
        JLabel Setting = new JLabel("கருவி");
        Setting.setForeground(new Color(0xff0e57));
        Setting.setFont(TitleFont);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CurvedBorderedPanel back = new CurvedBorderedPanel((int) (width*0.1), (int) (height*0.09),(int) (width*0),(int) (height*0));
        frame.add(back);
        back.add(backArrow);
        CurvedBorderedPanel title = new CurvedBorderedPanel((int) (width*0.5), (int) (height*0.09),(int) (width*0.1),(int) (height*0));
        frame.add(title);
        title.add(titleText);
        title.add(TitleText);
        CurvedBorderedPanel stars = new CurvedBorderedPanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.6),(int) (height*0));
        stars.add(star);
        stars.add(starCount);
        frame.add(stars);
        CurvedBorderedPanel coins = new CurvedBorderedPanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.75),(int) (height*0));
        coins.add(coin);
        coins.add(coinCount);
        frame.add(coins);
        CurvedBorderedPanel settings = new CurvedBorderedPanel((int) (width*0.1), (int) (height*0.09),(int) (width*0.9),(int) (height*0));
        settings.add(Setting);
        frame.add(settings);
        CurvedBorderedPanel content = new QuestionPanel((int) (width), (int) (height*0.91),(int) (width*0),(int) (height*0.09));
        frame.add(content);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public static void updateCoinCount(int CoinCount) {
        String labelText = coinCount.getText();
        int newCoinCount = Integer.parseInt(labelText) + CoinCount;
        coinCount.setText(Integer.toString(newCoinCount));
    }

    public static void updateStarCount(int StarCount) {
        String labelText = starCount.getText();
        int newStarCount = Integer.parseInt(labelText) + StarCount;
        starCount.setText(Integer.toString(newStarCount));
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
        SwingUtilities.invokeLater(() -> {
            new QuizFrame();
        });
    }
}