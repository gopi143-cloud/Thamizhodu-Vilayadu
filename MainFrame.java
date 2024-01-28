import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class MainFrame extends JFrame {
    private Image backgroundImage;

    public MainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        // Set the frame to be undecorated
        setUndecorated(true);

        // Set the size of the frame to be the same as the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        // Set the frame to maximize the screen
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon logo = new ImageIcon("Game_Logo.png");
        ImageIcon Logo = new ImageIcon(logo.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(Logo);

        backgroundImage = new ImageIcon("M_Background.png").getImage(); // Replace with the actual path to your image

        // Add a panel to the frame
        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Use BorderLayout for contentPane
        contentPane.setLayout(new BorderLayout());

        // Add imageLabel to the CENTER of contentPane
        contentPane.add(imageLabel, BorderLayout.CENTER);

        // Create a panel for other components (Play button and label)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon play = new ImageIcon("M_Play.png");
        ImageIcon Play = new ImageIcon(play.getImage().getScaledInstance(320, 180, Image.SCALE_SMOOTH));
        JLabel playLabel = new JLabel(Play);
	playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> new GamePage());
            }
        });
   
        buttonPanel.add(playLabel);
	buttonPanel.setOpaque(false);
        // Add buttonPanel to the SOUTH of contentPane
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        // Add contentPane to the frame
        setContentPane(contentPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
