import javax.swing.*;
import java.awt.*;

public class WoodBackgroundFrame extends JFrame {
    private JLabel loadingLabel;

    public WoodBackgroundFrame() {
        // Set the title of the JFrame
        setTitle("Wood Background JFrame");

        // Get the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Set the size of the JFrame to match the screen resolution
        setSize(screenSize.width, screenSize.height);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
         setUndecorated(true);
         setLocationRelativeTo(null);
        // Create a JPanel with wood color background using gradient effect
        JPanel panel = new JPanel() {
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
        };

        // Set the layout of the panel to null for custom positioning of components
        panel.setLayout(null);

        // Add a small image to the panel
        ImageIcon logo = new ImageIcon("F:/Pro/Game_Logo.png");
	ImageIcon Logo = new ImageIcon(logo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(Logo);
        imageLabel.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 150, 300, 300);
        panel.add(imageLabel);

        // Add a JLabel for loading dots
        loadingLabel = new JLabel("");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setBounds(screenSize.width / 2 - 5, screenSize.height / 2 + 150, 200, 30);
        panel.add(loadingLabel);

        // Add the panel to the JFrame
        getContentPane().add(panel);

        // Make the JFrame visible
        setVisible(true);

        // Start the wavy loading effect with three dots for 10 seconds
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    loadingLabel.setText(".");
                    Thread.sleep(1000);
                    loadingLabel.setText("..");
                    Thread.sleep(1000);
                    loadingLabel.setText("...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        // Create an instance of the WoodBackgroundFrame class
        SwingUtilities.invokeLater(() -> new WoodBackgroundFrame());
    }
}
