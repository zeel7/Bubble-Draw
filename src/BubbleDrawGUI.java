import javax.swing.JFrame;
import java.awt.*;

public class BubbleDrawGUI extends JFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("BubbleDraw GUI App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.setSize(new Dimension(650,450));
		frame.setVisible(true);
	}
}
