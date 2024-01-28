import javax.swing.*;
import java.awt.*;

public class Opening extends JFrame {
    private JLabel loadingLabel;

    public Opening() {
        setTitle("Wood Background JFrame");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(150, 75, 0), getWidth(), getHeight(), new Color(210, 105, 30));

                g2d.setPaint(gradient);

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(null);

        ImageIcon logo = new ImageIcon("Game_Logo.png");
        ImageIcon Logo = new ImageIcon(logo.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        JLabel imageLabel = new JLabel(Logo);
        imageLabel.setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 150, 300, 300);
        panel.add(imageLabel);

        loadingLabel = new JLabel("");
        loadingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setBounds(screenSize.width / 2 - 5, screenSize.height / 2 + 150, 200, 30);
        panel.add(loadingLabel);

        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation

        setVisible(true);

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    loadingLabel.setText(".");
                    Thread.sleep(500);
                    loadingLabel.setText("..");
                    Thread.sleep(500);
                    loadingLabel.setText("...");
                    Thread.sleep(500);
                }
		SwingUtilities.invokeLater(new Runnable() {
            		@Override
            		public void run() {
                	new MainFrame().setVisible(true);
            		}
       		 });
                dispose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Opening());
    }
}
