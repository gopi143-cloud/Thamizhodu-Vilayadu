import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class CurvedBorderedPanels extends JFrame {
	public static ImageIcon[] loadImages(String[] filePaths) {
        List<ImageIcon> imageIcons = new ArrayList<>();

        for (String filePath : filePaths) {
            ImageIcon imageIcon = new ImageIcon(filePath);
            imageIcons.add(imageIcon);
        }

        return imageIcons.toArray(new ImageIcon[0]);
    }
    private JPanel containerPanel;
    
    public CurvedBorderedPanels() {
	Font LFont = loadFontFromFile("F:\\Pro\\Question_Font.ttf", Font.PLAIN, 30);
        // Set the title of the JFrame
        setTitle("Curved Bordered Panels");

        // Set the size of the JFrame
        setSize(1100, 560);

        // Specify that the JFrame should close when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a container panel with GridLayout to hold 3x2 components
        containerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add some space around the panels
	String[] filePaths = {
            "F:/Pro/1.png","F:/Pro/2.png","F:/Pro/3.png","F:/Pro/4.png","F:/Pro/5.png","F:/Pro/6.png"
        };
	String[] title={"குறுக்கு எழுத்து","வார்த்தை வேட்டை","வினாடி வினா","சுடோகு","லுடோ","பரமபதம்"};
	ImageIcon[] imageIcons = loadImages(filePaths);
        // Add curved bordered panels with cloudy white background to the container panel
        for (int i = 0; i < 6; i++) {
            JPanel panel = createCurvedBorderedPanel(title[i],imageIcons[i],LFont);
            containerPanel.add(panel);
        }

        // Set the visibility of the containerPanel to true
        containerPanel.setVisible(true);

        // Add the container panel to the JFrame
        getContentPane().add(containerPanel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Make the JFrame visible
        setVisible(true);
    }
    private static Font loadFontFromFile(String filePath, int style, int size) {
        try {
            File fontFile = new File(filePath);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Arial", style, size); // Default font if loading fails
        }
    }
    private JPanel createCurvedBorderedPanel(String label, ImageIcon imageIcon,Font LFont) {
	 JPanel mainPanel = new JPanel(null){
	protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(
                    0, 0, 520 - 1, 160 - 1, 40, 40); // Set arc width and height to 40
            g2d.setColor(Color.BLACK);
            g2d.draw(roundedRectangle);
        }
    };

    //setOpaque(false); // Make the panel transparent
    //setPreferredSize(new Dimension(150, 100)); // Set preferred size for the panels
 
	JLabel labelComponent = new JLabel(label);
    	/*labelComponent.setHorizontalAlignment(SwingConstants.CENTER); // Horizontally center the text
    	labelComponent.setVerticalAlignment(SwingConstants.CENTER); */ // Vertically center the text
    	labelComponent.setForeground(Color.BLACK); // Set label text color to black
    	labelComponent.setFont(LFont);
	add(labelComponent);
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
        

    

    return mainPanel;
}


    public static void main(String[] args) {
        // Create an instance of the CurvedBorderedPanels class
        SwingUtilities.invokeLater(() -> new CurvedBorderedPanels());
    }
}
