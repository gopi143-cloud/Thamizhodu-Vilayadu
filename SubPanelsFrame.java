import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SubPanelsFrame extends JFrame {
    public SubPanelsFrame() {
        // Set the title of the JFrame
        setTitle("Curved Bordered Panels");

        // Set the size of the JFrame
        setSize(400, 300);

        // Specify that the JFrame should close when the close button is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a container panel with GridLayout to hold 3x2 components
        JPanel containerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        containerPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add some space around the panels

        // Create curved bordered panels with cloudy white background and add them to the container panel
        for (int i = 1; i <= 6; i++) {
            JPanel panel = createCurvedBorderedPanel("Panel " + i);
            containerPanel.add(panel);
        }

        // Add the container panel to the JFrame
        getContentPane().add(containerPanel);

        // Center the JFrame on the screen
        setLocationRelativeTo(null);

        // Make the JFrame visible
        setVisible(true);
    }

    private JPanel createCurvedBorderedPanel(String label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(255, 255, 255, 150)); // Transparent cloudy white background
        panel.setOpaque(false);

        // Create a compound border with a curved black border inside and an empty border outside
        Border innerCurve = new LineBorder(Color.BLACK, 2, true); // Curved black border
        Border outerEmpty = new EmptyBorder(10, 10, 10, 10); // Empty border with spaces
        Border compoundBorder = new CompoundBorder(outerEmpty, innerCurve);

        // Set the compound border to the panel
        panel.setBorder(compoundBorder);

        // Add a label to the panel
        JLabel labelComponent = new JLabel(label);
        labelComponent.setHorizontalAlignment(SwingConstants.CENTER);
        labelComponent.setForeground(Color.BLACK); // Set label text color to black
        panel.add(labelComponent, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        // Create an instance of the CurvedBorderedPanels class
        SwingUtilities.invokeLater(() -> new SubPanelsFrame());
    }
}
