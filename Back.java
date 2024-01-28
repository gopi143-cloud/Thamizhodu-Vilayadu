import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Back {

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
                g2d.drawRoundRect(borderThickness / 2, borderThickness / 2, getWidth() - borderThickness,
                        getHeight() - borderThickness, arc, arc);

                g2d.dispose();
            }
        };

        customPanel.setBackground(Color.BLACK); // Set background color to black
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
