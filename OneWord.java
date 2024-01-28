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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.BreakIterator;
class CurvePanel extends JPanel {
    private int borderThickness = 5;
    public CurvePanel(int width, int height, int x, int y) {
        setBounds(x, y, width, height);
        setOpaque(false);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.decode("#cec7ce"));
        g2d.setStroke(new BasicStroke(borderThickness));
        g2d.draw(new RoundRectangle2D.Double(borderThickness / 2.0, borderThickness / 2.0, getWidth() - borderThickness, getHeight() - borderThickness, 20, 20));
        g2d.dispose();
    }
}
class BackO {
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
                int borderThickness = 5;
                int arc = 20;
                g2d.setColor(Color.decode("#cec7ce")); // Set border color
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness,
                        getHeight() - borderThickness, arc, arc);
                g2d.dispose();
            }
        };
        customPanel.setBackground(new Color(0x353541)); // Set background color to black
	JLabel closeLabel = new JLabel("X", SwingConstants.CENTER);
        closeLabel.setBounds(460, 10, 30, 30);
        closeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        closeLabel.setForeground(Color.WHITE);
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customDialog.dispose();
            }
        });

        JLabel label = new JLabel("வெளியேரா வேண்டுமா?");
        label.setBounds(60, 70, 500, 40);
        label.setFont(TamilFont.deriveFont(Font.BOLD, 34));
        label.setForeground(Color.WHITE); // Set text color to white
        customPanel.add(label);

        JButton yesButton = new JButton("ஆம்");
        yesButton.setFont(TamilFont.deriveFont(Font.PLAIN, 30));
        yesButton.setBounds(70, 200, 100, 40);
        yesButton.setForeground(Color.GREEN);
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

    public static Font loadCustomFont(String fontFileName) {
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
class AnsCheck {
    public static void showCustomMessageDialog() {
        JDialog customDialog = new JDialog();
        customDialog.setUndecorated(true); // Remove window decorations
        customDialog.setSize(400, 250);
        customDialog.setResizable(false);
        customDialog.setModal(true);

        Font TamilFont = BackO.loadCustomFont("Question_Font.ttf");

        JPanel customPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int borderThickness = 5;
                int arc = 20;
                g2d.setColor(Color.decode("#cec7ce")); // Set border color
                g2d.setStroke(new BasicStroke(borderThickness));
                g2d.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness,
                        getHeight() - borderThickness, arc, arc);
                g2d.dispose();
            }
        };
        customPanel.setBackground(new Color(0x353541)); // Set background color to black
	JLabel closeLabel = new JLabel("X", SwingConstants.CENTER);
        closeLabel.setBounds(360, 10, 30, 30);
        closeLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        closeLabel.setForeground(Color.RED);
        closeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customDialog.dispose();
            }
        });
	JLabel label = new JLabel("தவறான விடை");
        label.setBounds(100, 100, 500, 40);
        label.setFont(TamilFont.deriveFont(Font.BOLD, 34));
        label.setForeground(Color.RED); // Set text color to white
        customPanel.add(label);
	customPanel.add(closeLabel);
        customPanel.setLayout(null);
        customDialog.getContentPane().setBackground(new Color(0, 0, 0, 0));
        customDialog.setBackground(new Color(0, 0, 0, 0));
        customDialog.add(customPanel);
        customDialog.setLocationRelativeTo(null); // Center the dialog on the screen
        customDialog.setVisible(true);
    }
}
public class OneWord extends JFrame {
    public static String[] tamilLetterstotal = {
            "அ", "ஆ", "இ", "ஈ", "உ", "ஊ", "எ", "ஏ", "ஐ", "ஒ", "ஓ", "ஔ",
            "க", "கா", "கி", "கீ", "கு", "கூ", "கெ", "கே", "கை", "கொ", "கோ", "கௌ", "ஙெ", "ஙே", "ஙை", "ஙொ", "ஙோ", "ஙௌ",
            "ச", "சா", "சி", "சீ", "சு", "சூ", "செ", "சே", "சை", "சொ", "சோ", "சௌ", "ஞெ", "ஞே", "ஞை", "ஞொ", "ஞோ", "ஞௌ",
            "ட", "டா", "டி", "டீ", "டு", "டூ", "டெ", "டே", "டை", "டொ", "டோ", "டௌ",
            "ண", "ணா", "ணி", "ணீ", "ணு", "ணூ", "ணெ", "ணே", "ணை", "ணொ", "ணோ", "ணௌ",
            "த", "தா", "தி", "தீ", "து", "தூ", "தெ", "தே", "தை", "தொ", "தோ", "தௌ",
            "ந", "நா", "நி", "நீ", "நு", "நூ", "நெ", "நே", "நை", "நொ", "நோ", "நௌ",
            "ப", "பா", "பி", "பீ", "பு", "பூ", "பெ", "பே", "பை", "பொ", "போ", "பௌ",
            "ம", "மா", "மி", "மீ", "மு", "மூ", "மெ", "மே", "மை", "மொ", "மோ", "மௌ",
            "ய", "யா", "யி", "யீ", "யு", "யூ", "ரு", "ரெ", "ரே", "ரை", "ரொ", "ரோ", "ரௌ",
            "ல", "லா", "லி", "லீ", "லு", "லூ", "லெ", "லே", "லை", "லொ", "லோ", "லௌ",
            "வ", "வா", "வி", "வீ", "வு", "வூ", "வெ", "வே", "வை", "வொ", "வோ", "வௌ",
            "ழ", "ழா", "ழி", "ழீ", "ழு", "ழூ", "ழெ", "ழே", "ழை", "ழொ", "ழோ", "ழௌ",
            "ள", "ளா", "ளி", "ளீ", "ளு", "ளூ", "ளெ", "ளே", "ளை", "ளொ", "ளோ", "ளௌ",
            "ற", "றா", "றி", "றீ", "று", "றூ", "றெ", "றே", "றை", "றொ", "றோ", "றௌ",
            "ன", "னா", "னி", "னீ", "னு", "னூ", "னெ", "னே", "னை", "னொ", "னோ", "னௌ"};
    public static String initialAns ="";
    public static String correctAns = "";
    public JLabel backArrow,coin,star;
    public static JLabel coinCount,starCount,qLabel,aLabel,Retry,Next;
    public double width,height;
    public JPanel panel;
    public int count;
    public OneWord() {
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
                        BackO.showCustomMessageDialog(frame);
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
        JLabel TitleText = new JLabel("வார்த்தைத் தேடல்");
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
        CurvePanel backO = new CurvePanel((int) (width*0.1), (int) (height*0.09),(int) (width*0),(int) (height*0));
        frame.add(backO);
        backO.add(backArrow);
        CurvePanel titleO = new CurvePanel((int) (width*0.5), (int) (height*0.09),(int) (width*0.1),(int) (height*0));
        frame.add(titleO);
        titleO.add(TitleText);
        CurvePanel starsO = new CurvePanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.6),(int) (height*0));
        starsO.add(star);
        starsO.add(starCount);
        frame.add(starsO);
        CurvePanel coinsO = new CurvePanel((int) (width*0.15), (int) (height*0.09),(int) (width*0.75),(int) (height*0));
        coinsO.add(coin);
        coinsO.add(coinCount);
        frame.add(coinsO);
        CurvePanel settingsO = new CurvePanel((int) (width*0.1), (int) (height*0.09),(int) (width*0.9),(int) (height*0));
        settingsO.add(Setting);
        frame.add(settingsO);
        CurvePanel questionO = new CurvePanel((int) (width), (int) ((height*0.91)/2),(int) (width*0),(int) (height*0.09));
	questionO.setLayout(new GridLayout(3, 1));
	qLabel = new JLabel("");
	aLabel = new JLabel("");
	Font QFont = loadFontFromFile("Tamil.ttf", Font.PLAIN, 40);
	Font AFont = loadFontFromFile("new0.ttf", Font.PLAIN, 60);
	qLabel.setFont(QFont);
	aLabel.setFont(AFont);
        qLabel.setHorizontalAlignment(SwingConstants.CENTER);
	aLabel.setHorizontalAlignment(SwingConstants.CENTER);
	qLabel.setForeground(Color.WHITE);
	aLabel.setForeground(Color.WHITE);
	JPanel BPanel = new JPanel(new GridLayout(1,2));
	BPanel.setOpaque(false);
	questionO.add(qLabel);
	questionO.add(aLabel);
	ImageIcon retry = new ImageIcon("Retry.png");
        retry = new ImageIcon(retry.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        Retry = new JLabel(retry);
	Retry.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aLabel.setText(initialAns);
            }
        });
	ImageIcon next = new ImageIcon("Next.png");
        next = new ImageIcon(next.getImage().getScaledInstance(128, 72, Image.SCALE_SMOOTH));
        Next = new JLabel(next);
	Next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                String check = aLabel.getText();
		if(check.equals(correctAns)){
			updateQuestion();
			updateStarCount(50);
			updateCoinCount(100);
		}else{
		   SwingUtilities.invokeLater(() -> {
                        AnsCheck.showCustomMessageDialog();
			aLabel.setText(initialAns);
                    });
		}
            }
        });
	updateQuestion();
	BPanel.add(Retry);
	BPanel.add(Next);
	questionO.add(BPanel);
        frame.add(questionO);
	CurvePanel buttonO = new CurvePanel((int) (width), (int) ((height*0.91)/2),(int) (width*0),(int) (((height*0.91)/2)+height*0.09));
        frame.add(buttonO);
	buttonO.setLayout(null);
	//panel = createButtonPanel(9);
        panel.setOpaque(false);
	int x = (buttonO.getWidth() - panel.getPreferredSize().width) / 2;
        int y = (buttonO.getHeight() - panel.getPreferredSize().height) / 2;
        panel.setBounds(x, y, panel.getPreferredSize().width, panel.getPreferredSize().height);
	buttonO.add(panel);
        frame.getContentPane().setBackground(new Color(0x353541));
        frame.setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    public void updateQuestion(){
    	String[] randomQA = RandomQuestionAnswer.getRandomQuestionAndAnswer();
	qLabel.setText(randomQA[0]);
	String Ans = randomQA[1];
	correctAns = Ans;
	int c = getCount(Ans);
	initialAns ="";
	for(int i=0;i<c;i++)
	initialAns +="-";
	aLabel.setText(initialAns);
	String[] result = splitFillShuffleAns(Ans);
	panel = createButtonPanel(c,result);
    }
    private JPanel createButtonPanel(int number,String[] res) {
        JPanel panel = new JPanel();
        int rows, cols, buttonSize;
        if (number >= 1 && number <= 9) {
            rows = 2;cols = 5;
            buttonSize = 120;
            number = 10;
        } else if (number >= 10 && number <= 16) {
            rows = 2;cols = 8;
            buttonSize = 120;
            number = 16;
        } else {
            throw new IllegalArgumentException("Number should be between 1-8 or 9-16");
        }

       
        panel.setLayout(new GridLayout(rows, cols, 5, 5)); // Adjust the gap between buttons if needed
	Font BFont = loadFontFromFile("bevan-2.ttf", Font.PLAIN, 50);
        // Create buttons and add them to the panel
        for (int i = 0; i < number; i++) {
	    RoundedButton button = new RoundedButton(res[i]);
            button.setPreferredSize(new Dimension(buttonSize, buttonSize));
            button.setBackground(Color.decode("#cec7ce"));
            button.setForeground(Color.BLACK);
	    button.setFont(BFont);
            button.addActionListener(new ButtonClickListener());
            button.addMouseListener(new ButtonMouseListener(button));
            panel.add(button);
        }

        return panel;
    }
     private static class RoundedButton extends JButton {

        public RoundedButton(String text) {
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
    private static class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
	    String currentText = aLabel.getText();
            if (currentText.contains("-")) {
            	int index = currentText.indexOf("-");
            	String updatedText = currentText.substring(0, index) + source.getText() + currentText.substring(index + 1);
            	aLabel.setText(updatedText);
        	}
        }
	
    }
    public static int getCount(String sample) {
        int characterCount = -1;
        BreakIterator iterator = BreakIterator.getCharacterInstance();
        iterator.setText(sample);
        int boundary = iterator.first();
        while (boundary != BreakIterator.DONE) {
            characterCount++;
            boundary = iterator.next();
        }
	return characterCount;
    }private static String[] splitFillShuffleAns(String inputText) {
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
        
        int number = tamilLetterList.size();
        int x;
        
        if (number >= 1 && number <= 9) {
            x = 10 - number;
        } else if (number >= 10 && number <= 16) {
            x = 16 - number;
        } else {
            x = 0; // Handle other cases
        }

        Random random = new Random();

        for (int i = 0; i < x; i++) {
            int randomCodePoint = random.nextInt(120) + 2946;
            String tamil = tamilLetterstotal[random.nextInt(tamilLetterstotal.length)];
            tamilLetterList.add(tamil);
        }

        String[] array = new String[tamilLetterList.size()];
        array = tamilLetterList.toArray(array);

        int n = array.length;

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        return array;
    }
     private static class ButtonMouseListener extends MouseAdapter {
        private final RoundedButton button;

        public ButtonMouseListener(RoundedButton button) {
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
    public static void main(String[] args) {
	RandomQuestionAnswer.readQuestionsAndAnswersFromFile();
        SwingUtilities.invokeLater(() -> {
            new OneWord();
        });
    }
}