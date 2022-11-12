import java.awt.event.*;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Dimension;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class BubblePanel extends JPanel {
	
	ArrayList <Bubble> bubbleList;
	int initialSize = 30;
	Timer timer;
	int latency = 50;   //25 for optimum performance
	JSlider slider;

	public BubblePanel() {
		timer = new Timer(latency, new BubbleListener() );
		bubbleList = new ArrayList<>();
		setBackground(Color.BLACK);
		
		JPanel panel = new JPanel();
		add(panel);
		
		JButton btnNewButton = new JButton("Pause");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton)e.getSource();
				if (btn.getText().equals("Pause")) {
				timer.stop();
				btn.setText(" Start ");
				}
				else {
				timer.start();
				btn.setText("Pause");
				}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});
		panel.add(btnClear);
		
		JButton btnNewButton_1 = new JButton("Vision");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 bubbleList = new Bubble().testBubbles();	
				 repaint();
			}
		});
		panel.add(btnNewButton_1);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int speed = slider.getValue() + 1;
				int delay = 1000 / speed;
				timer.setDelay(delay);
			}
		});
		slider.setPaintTicks(true);
		slider.setValue(20);
		slider.setMinorTickSpacing(5);
		slider.setMajorTickSpacing(20);
		slider.setMaximum(120);
		panel.add(slider);
		
		addMouseListener( new BubbleListener() );
		addMouseMotionListener( new BubbleListener() );
		addMouseWheelListener( new BubbleListener() );
		timer.start();
		}
	
	public void paintComponent(Graphics canvas) {
		super.paintComponent(canvas);
		for(Bubble b : bubbleList) {
			b.draw(canvas);
			}
		}
	
	private class BubbleListener extends MouseAdapter implements ActionListener{
		public void mousePressed(MouseEvent e) {
			bubbleList.add(new Bubble(e.getX(), e.getY(), initialSize));
			repaint();
			}
		public void mouseDragged(MouseEvent e) {
			bubbleList.add(new Bubble(e.getX(), e.getY(), initialSize));
			repaint();
			}
		public void mouseWheelMoved(MouseWheelEvent e) {
			initialSize += e.getUnitsToScroll();
			}
		public void actionPerformed(ActionEvent e) {
			for (Bubble b : bubbleList)
				b.update(getWidth(), getHeight());
			repaint();
		}
	}
}
