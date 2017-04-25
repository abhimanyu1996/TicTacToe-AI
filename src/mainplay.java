import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class mainplay extends JFrame{
	
	mainplay(){
		super("TicTacToe AI");
		setLayout(new FlowLayout());
		
		Integer levels[] = {1,2,3,4,5,6,7,8,9};
		JComboBox<Integer> c = new JComboBox<>(levels);
		
		add(new JLabel("Level: "));
		add(c);
		
		
		gameboard gb = new gameboard();
		
		Dimension gbdim = new Dimension(302,302);
		gb.setPreferredSize(gbdim);
		gb.setMinimumSize(gbdim);
		gb.setMaximumSize(gbdim);
		
		add(gb);
		
		Button b = new Button("Reset");
		
		b.addActionListener(
				new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						gb.reset((int) c.getSelectedItem());
					}
					
				}
				
				
				);
		add(b);
		
		
	}
	
	public static void main(String arg[]){
		mainplay gf = new mainplay();
		gf.setSize(320,400);
		gf.setVisible(true);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setResizable(false);
	}
}
