import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LudoMain extends JFrame {
    private BufferedImage backgroundImage;
    private GameMoves gm;

    public LudoMain() {
        setTitle("Ludo Game");
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gm = new GameMoves();
        gm.setBounds(0, 0, 1000, 600);
        gm.setFocusable(true);
        gm.addKeyListener(gm);
        gm.addMouseListener(gm);

        add(gm);
	JButton quitButton = new JButton("Quit");
        quitButton.setBackground(Color.BLUE);
        quitButton.setForeground(Color.WHITE);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle quit button click, e.g., close the application
               // System.exit(0);
		//SwingUtilities.getWindowAncestor(customDialog).dispose();
                
                    dispose();

            }
        });
	quitButton.setBounds(1100,100,100,50);
        add(quitButton);
        try {
            backgroundImage = ImageIO.read(new File("Ludo_Background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel backgroundLabel = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image scaledImage = backgroundImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                g.drawImage(scaledImage, 0, 0, this);
            }
        };

        add(backgroundLabel);

        pack();
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LudoMain ludoMain = new LudoMain();
            ludoMain.setVisible(true);
        });
    }
}
