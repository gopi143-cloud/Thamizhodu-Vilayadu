import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.Collections;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Random;
import java.text.BreakIterator;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
class CurvedPanel extends JPanel {
    private Color borderColor = new Color(0x0099dc);
    private int borderThickness = 5;
    public CurvedPanel(int width, int height, int x, int y) {
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
class RoundButton extends JButton {

    public RoundButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Paint the button background with rounded corners
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 10, 10);

        super.paintComponent(g);
        g2.dispose();
    }
}
class CustomScrollBarUI extends BasicScrollBarUI {
    @Override
    protected void configureScrollBarColors() {
        thumbColor = new Color(0, 120, 215); // Customize thumb color
        trackColor = Color.WHITE; // Customize track color
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(thumbColor);
        int arc = 10; // Adjust the arc value for a softer edge
        g2.fill(new RoundRectangle2D.Float(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, arc, arc));

        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // Uncomment the following line if you want to remove the track background
        // super.paintTrack(g, c, trackBounds);
    }
}
class BackC {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            showCustomMessageDialog(null);
        });
    }
    public static void showCustomMessageDialog(JFrame f) {
        JDialog customDialog = new JDialog();
        customDialog.setUndecorated(true); // Remove window decorations
        customDialog.setSize(500, 281);
        customDialog.setResizable(false);
        customDialog.setModal(true);

        Font TamilFont = loadCustomFont("Question_Font.ttf");

        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set curved border
                int borderThickness = 5;
                int arc = 20;
                g2d.setColor(new Color(0xff0e57)); // Set border color
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness, getHeight() - borderThickness, arc, arc);
                g2d.dispose();
            }
        };

        customPanel.setBackground(new Color(0x83E169)); // Set background color to black
        JLabel closeLabel = new JLabel("X", SwingConstants.CENTER);
        closeLabel.setBounds(460, 10, 30, 30);
        closeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        closeLabel.setForeground(new Color(0xfffb02));
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customDialog.dispose();
            }
        });

        JLabel label = new JLabel("வெளியேரா வேண்டுமா?");
        label.setBounds(60, 70, 500, 40);
        label.setFont(TamilFont.deriveFont(Font.BOLD, 34));
        label.setForeground(new Color(0x0099dc)); // Set text color to white
        customPanel.add(label);

        JButton yesButton = new JButton("ஆம்");
        yesButton.setFont(TamilFont.deriveFont(Font.PLAIN, 30));
        yesButton.setBounds(70, 200, 100, 40);
        yesButton.setForeground(Color.BLACK);
        yesButton.setFocusPainted(false);
        yesButton.setBorderPainted(false);
        yesButton.setContentAreaFilled(false);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the message pane and the parent frame
                SwingUtilities.getWindowAncestor(customDialog).dispose();
                if (f != null) {
                    f.dispose();
                }
            }
        });

        JButton noButton = new JButton("இல்லை");
        noButton.setFont(TamilFont.deriveFont(Font.PLAIN, 30));
        noButton.setBounds(300, 200, 130, 40);
        noButton.setBorderPainted(false);
        noButton.setContentAreaFilled(false);
        noButton.setFocusPainted(false);
        noButton.setForeground(Color.RED);
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close only the message pane
                customDialog.dispose();
            }
        });
        customPanel.add(closeLabel);
        customPanel.add(yesButton);
        customPanel.add(noButton);
        customPanel.setLayout(null);
        customDialog.getContentPane().setBackground(new Color(0, 0, 0, 0));
        customDialog.setBackground(new Color(0, 0, 0, 0));
        customDialog.add(customPanel);
        customDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        customDialog.setVisible(true);
    }

    private static Font loadCustomFont(String fontFileName) {
        try {
            File fontFile = new File(fontFileName);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle font loading exception
        }
        return null;
    }
}
class QuestionManager {
    QuestionOne randomQuestion;
    private List<QuestionOne> questions;

    public QuestionManager() {
        this.questions = deserializeQuestions("questions.dat");
    }

    public String[][] getTwoDArray() {
        randomQuestion = getRandomQuestion();
        return (randomQuestion != null) ? randomQuestion.getArray2D() : null;
    }

    public String[] getOneDArray() {
        return (randomQuestion != null) ? randomQuestion.getArray1D() : null;
    }

    @SuppressWarnings("unchecked")
    private List<QuestionOne> deserializeQuestions(String filename) {
        List<QuestionOne> deserializedQuestions = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            if (obj instanceof List) {
                deserializedQuestions = (List<QuestionOne>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return deserializedQuestions;
    }


    private QuestionOne getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }

        Collections.shuffle(questions);
        return questions.remove(0);
    }
}
public class CrossWordPuzzleFrame extends JFrame {
    private static JTextField focusedTextField;
    public static CellClass[][] Cell;
    public static JScrollPane scrollPane;
    public static String AnsCell1[][];
    public static StringBuilder ans = new StringBuilder("");
    public static String crtAns[];
    public static String Quiz1[];
    public JLabel backArrow,coin,star;
    public static JLabel Quiz[] = new JLabel[8];
    public static JPanel PuzzlePanel,CellPanel,QuestionPanel;
    public static JLabel coinCount,starCount;
    public double width,height;
    public CrossWordPuzzleFrame() {
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
                        BackC.showCustomMessageDialog(frame);
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
        JLabel titleText = new JLabel("குறுக்கு");
        titleText.setForeground(new Color(0xfffb02));
        titleText.setFont(TitleFont);
        JLabel TitleText = new JLabel(" எழுத்து");
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
        CurvedPanel back = new CurvedPanel((int) (width*0.1), (int) (height*0.09),(int) (width*0),(int) (height*0));
        frame.add(back);
        back.add(backArrow);
        CurvedPanel title = new CurvedPanel((int) (width*0.5), (int) (height*0.09),(int) (width*0.1),(int) (height*0));
        frame.add(title);
        title.add(titleText);
        title.add(TitleText);
        CurvedPanel stars = new CurvedPanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.6),(int) (height*0));
        stars.add(star);
        stars.add(starCount);
        frame.add(stars);
        CurvedPanel coins = new CurvedPanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.75),(int) (height*0));
        coins.add(coin);
        coins.add(coinCount);
        frame.add(coins);
        CurvedPanel settings = new CurvedPanel((int) (width*0.1), (int) (height*0.09),(int) (width*0.9),(int) (height*0));
        settings.add(Setting);
        frame.add(settings);
        CurvedPanel puzzle = new CurvedPanel((int) (width/2), (int) (height*0.8),(int) (width*0),(int) (height*0.09));
        frame.add(puzzle);
        puzzle.setLayout(new BorderLayout());
        PuzzlePanel = new JPanel(new GridBagLayout());
        CellPanel = null;
        Cell = createCell(10, 10);
        puzzle.add(PuzzlePanel,BorderLayout.CENTER);
        PuzzlePanel.setOpaque(false);
        CurvedPanel quest = new CurvedPanel((int) (width/2), (int) (height*0.8),(int) (width/2),(int) (height*0.09));
        frame.add(quest);
        quest.setLayout(new BorderLayout());
        Font LFont = loadFontFromFile("Question_Font.ttf", Font.PLAIN, 30);
        QuestionPanel = new JPanel();
        QuestionPanel.setLayout(null);
        Font QFont = loadFontFromFile("Question_Font.ttf", Font.PLAIN, 25);
        JLabel UDLabel = new JLabel();
        UDLabel.setText("மேலிருந்து கீழ்");
        UDLabel.setFont(LFont);
        UDLabel.setBounds(40, 40, 480, 30);
        UDLabel.setForeground(Color.RED);
        QuestionPanel.add(UDLabel);
        JLabel DULabel = new JLabel();
        DULabel.setText("கீழிருந்து மேல்");
        DULabel.setFont(LFont);
        DULabel.setBounds(40, 160, 480, 30);
        DULabel.setForeground(Color.RED);
        QuestionPanel.add(DULabel);
        JLabel LRLabel = new JLabel();
        LRLabel.setText("இடதுபுறத்தில் இருந்து வலதுபுறம்");
        LRLabel.setFont(LFont);
        LRLabel.setBounds(40, 280, 480, 30);
        LRLabel.setForeground(Color.RED);
        QuestionPanel.add(LRLabel);
        JLabel RLLabel = new JLabel();
        RLLabel.setText("வலதுபுறத்தில் இருந்து இடதுபுறம்");
        RLLabel.setFont(LFont);
        RLLabel.setBounds(40, 400, 480, 30);
        RLLabel.setForeground(Color.RED);
        QuestionPanel.add(RLLabel);
        QuestionPanel.setOpaque(false);
        quest.add(QuestionPanel,BorderLayout.CENTER);
        JPanel but = new JPanel();
        ImageIcon retry = new ImageIcon("Retry.png");
        retry = new ImageIcon(retry.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        JLabel Retry = new JLabel(retry);
        Retry.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                load();
            }
        });
        ImageIcon ba = new ImageIcon("Next.png");
        ba = new ImageIcon(ba.getImage().getScaledInstance(128, 72, Image.SCALE_SMOOTH));
        JLabel Back = new JLabel(ba);
	QuestionManager qm = new QuestionManager();
        Back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean status = true;
                for(int i = 0;i<10;i++){
                    for(int j=0;j<10;j++){
                        if(!Cell[i][j].getText().equals(AnsCell1[i][j])){
                            status = false;
                            break;
                        }
                    }
                }
                if(status){
                    updateQuest((int) (width), (int) (height*0.11),qm);
		    updateStarCount(50);
		    updateCoinCount(500);
                }
            }
        });
        but.add(Retry);
        but.add(Back);
        but.setOpaque(false);
        quest.add(but,BorderLayout.SOUTH);
        CurvedPanel button = new CurvedPanel((int) (width), (int) (height*0.11),(int) (width*0),(int) (height*0.89));
        //JScrollPane scrollPane = createCustomScrollPane(0, 0, (int) (width), (int) (height*0.11));
        button.setLayout(null);
        frame.add(button);
        button.setOpaque(false);
        updateQuest( (int) (width), (int) (height*0.11),new QuestionManager());
        button.add(scrollPane);
        scrollPane.setOpaque(false);
        frame.getContentPane().setBackground(new Color(0x83E169));
        frame.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    private static JScrollPane createCustomScrollPane(int x, int y, int width, int height) {
        JPanel buttonPanel = createButtonPanel();
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        // Set bounds for the JScrollPane
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setOpaque(false);
        // Customize the scrollbar look and feel
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setUI(new CustomScrollBarUI());
        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
        horizontalScrollBar.setUI(new CustomScrollBarUI());

        return scrollPane;
    }
    public static void updateQuest(int x, int z,QuestionManager questionManager){
        AnsCell1 = questionManager.getTwoDArray();
        Quiz1 = questionManager.getOneDArray();
        int[] dimension = DimensionMaker(AnsCell1);
        if (dimension == null || dimension.length != 2) {
            return;
        }
        CellPanel = new JPanel(new GridLayout(dimension[1], dimension[0], 0, 0));
        CellPanel.setPreferredSize(new Dimension(dimension[0] * 60, dimension[1] * 60));
        CellPanel.setLayout(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        PuzzlePanel.add(CellPanel,gbc);
        CellPanel.setOpaque(false);
        for (int i = 0; i < Cell.length; i++) {
            for (int j = 0; j < Cell[i].length; j++) {
                if (!AnsCell1[i][j].isEmpty()){
                    CellPanel.add(Cell[i][j]);
                    ans.append(AnsCell1[i][j]);
                }
            }
        }
        int y=78;
        for(int i=0;i<8;i++){
            Quiz[i] = new JLabel(Quiz1[i]);
            Quiz[i].setForeground(Color.BLACK);
            Quiz[i].setBounds(60, y, 550, 25);
            if(i%2==0)
                y+=38;
            else
                y+=85;
            QuestionPanel.add(Quiz[i]);
        }
        crtAns = ShuffleAns(ans.toString());
        scrollPane = createCustomScrollPane(0, 0,x,z);
    }
    private static void load(){
        for (int i = 0; i < 10; i++){
            for(int j = 0;j < 10; j++){
                Cell[i][j].setText("");
                Cell[i][j].setImage("Input.png");
            }
        }
    }
    private static String[] ShuffleAns(String inputText) {
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText(inputText);
        int start = iterator.first();
        List<String> tamilLetterList = new ArrayList<>();

        while (start != BreakIterator.DONE) {
            int end = iterator.next();
            if (end != BreakIterator.DONE) {
                String character = inputText.substring(start, end);
                tamilLetterList.add(character);
            }
            start = end;
        }
        String[] array = new String[tamilLetterList.size()];
        array = tamilLetterList.toArray(array);

        int n = array.length;
        Random random = new Random();
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return array;
    }
    private static JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        Font BFont = loadFontFromFile("bevan-2.ttf", Font.PLAIN, 50);
        for (int i = 0; i < crtAns.length; i++) {
            RoundButton button = new RoundButton(crtAns[i]);
            button.setPreferredSize(new Dimension(120, 60));
            button.setBackground(Color.decode("#cec7ce"));
            button.setForeground(Color.BLACK);
            button.setFont(BFont);
            button.addActionListener(new ButtonClickListener());
            button.addMouseListener(new ButtonMouseListener(button));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField focusedTextField = getFocusedTextField();
                    if (focusedTextField != null) {
                        focusedTextField.setText(((JButton) e.getSource()).getText());
                    }
                }
            });
            buttonPanel.add(button);
        }
        buttonPanel.setOpaque(false);
        buttonPanel.setPreferredSize(new Dimension(crtAns.length * (125),60));
        return buttonPanel;
    }
    public static void updateStarCount(int StarCount) {
        String labelText = starCount.getText();
        int newStarCount = Integer.parseInt(labelText) + StarCount;
        starCount.setText(Integer.toString(newStarCount));
    }
    public static void updateCoinCount(int CoinCount) {
        String labelText = coinCount.getText();
        int newCoinCount = Integer.parseInt(labelText) + CoinCount;
        coinCount.setText(Integer.toString(newCoinCount));
    }
    private static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String currentText = source.getText();
        }

    }
    private static class ButtonMouseListener extends MouseAdapter {
        private final RoundButton button;

        public ButtonMouseListener(RoundButton button) {
            this.button = button;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            button.setBackground(Color.BLACK);
            button.setForeground(Color.decode("#cec7ce"));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            button.setBackground(Color.decode("#cec7ce"));
            button.setForeground(Color.BLACK);
        }
    }
    private static CellClass[][] createCell(int rows, int columns) {
        CellClass[][] cell = new CellClass[rows][columns];
        int initialX = 0;
        int initialY = 0;
        int cellWidth = 60;
        int cellHeight = 60;
        Font font = new Font("Puzzle_Font.ttf", Font.BOLD, 30);
        for (int i = 0; i < rows; i++) {
            int y = initialY + (i * cellHeight);
            int x = initialX;

            for (int j = 0; j < columns; j++) {
                int finalI = i;
                int finalJ = j;
                cell[i][j] = new CellClass();
                cell[i][j].setImage("Input.png");
                cell[i][j].setForeground(Color.WHITE); // Set text color to white
                cell[i][j].setHorizontalAlignment(CellClass.CENTER); // Align text to the center
                cell[i][j].setBounds(x,y,60,60);
                cell[i][j].setBorder(null);
                x += cellWidth; // Update X for each cell in the row
                cell[i][j].setPreferredSize(new Dimension(60, 60));
                cell[i][j].setFont(font);
                cell[i][j].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        setFocusedTextField(cell[finalI][finalJ]);
                    }
                    @Override
                    public void focusLost(FocusEvent e) {
                    }
                });
                cell[i][j].getDocument().addDocumentListener(new PuzzleDocumentListener(i, j));
            }
        }
        return cell;
    }
    private static int[] DimensionMaker(String check[][]) {
        int w = -1, x = -1, y = -1, z = -1;
        for (int i = 0; i < check.length; i++) {
            for (int j = 0; j < check[i].length; j++) {
                if (!check[i][j].isEmpty()) {
                    if (w == -1) {
                        w = j;
                        x = j;
                    }
                    if (j > x) {
                        x = j;
                    }
                    if (y == -1) {
                        y = i;
                        z = i;
                    }
                    if (i > z) {
                        z = i;
                    }
                }
            }
        }
        int a = x - w + 1;
        int b = z - y + 1;
        int[] result = {a, b};
        return result;
    }
    private static void setFocusedTextField(JTextField textField) {
        focusedTextField = textField;
    }
    private static JTextField getFocusedTextField() {
        return focusedTextField;
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
            new CrossWordPuzzleFrame();
        });
    }
}
class PuzzleDocumentListener implements DocumentListener {
    private int row;
    private int col;
    public PuzzleDocumentListener(int row, int col) {
        this.row = row;
        this.col = col;
    }
    @Override
    public void insertUpdate(DocumentEvent e) {
        if(CrossWordPuzzleFrame.AnsCell1[row][col].equals(CrossWordPuzzleFrame.Cell[row][col].getText()))
            CrossWordPuzzleFrame.Cell[row][col].setImage("Correct.png");
        else
            CrossWordPuzzleFrame.Cell[row][col].setImage("Wrong.png");
    }
    @Override
    public void changedUpdate(DocumentEvent e) {
        if(CrossWordPuzzleFrame.AnsCell1[row][col].equals(CrossWordPuzzleFrame.Cell[row][col].getText()))
            CrossWordPuzzleFrame.Cell[row][col].setImage("Correct.png");
        else
            CrossWordPuzzleFrame.Cell[row][col].setImage("Wrong.png");
    }
    public void removeUpdate(DocumentEvent e) {

    }
}
class CellClass extends JTextField {
    Image image,pre;
    public CellClass() {
        super();
        setEditable(false);
    }

    public CellClass(String text) {
        super(text);
        setEditable(false);
    }
    public void setImage(String ImagePath){
        ImageIcon icon1 = new ImageIcon(ImagePath);
        this.pre= icon1.getImage();
        setOpaque(false);
        this.image = pre;
        repaint();
    }
    public void paint(Graphics g){
        g.drawImage(image,0,0,60,60,null);
        super.paint(g);
    }
}