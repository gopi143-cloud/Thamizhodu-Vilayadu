import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
	public static void main(String[] args) {
		new Main();
	}
	public Main() {
		JFrame window = new JFrame();;
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(60,60);
		JTextAreaPlus textarea = new JTextAreaPlus("Go");
		String path = "F:\\Pro\\Input.png";
		textarea.setImage(path);
		textarea.setPreferredSize(new Dimension(60, 60));
		Font font = new Font("Arial", Font.PLAIN, 50); // Font name, style (PLAIN, BOLD, ITALIC), size
		textarea.setBounds(0, 0, 0, 0);
            	textarea.setFont(font);
		window.add(textarea);
		window.setVisible(true);
	}
}
class CellClass extends JTextField{
	Image image,pre;
	int x,y;
	public JTextAreaPlus(){
		super();
	}
	public JTextAreaPlus(String text){
		super(text);
	}
	public void setImage(String ImagePath,int x,int y){
		ImageIcon icon1 = new ImageIcon(ImagePath);
		this.pre= icon1.getImage();
		setOpaque(false);
		this.image = pre; 
		repaint();
		this.x=x;
		this.y=y;
	}
	public void paint(Graphics g){
		g.drawImage(image,x,y,60,60,null);
		super.paint(g);
	}
}