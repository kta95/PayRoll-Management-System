package forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Splash {

	private JFrame frame;
    JLabel message=new JLabel();
    JProgressBar progressBar=new JProgressBar();

	;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash window = new Splash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Splash() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame=new JFrame();
        frame.getContentPane().setLayout(null);//setting layout to null
        frame.setUndecorated(true);//Turning off Title bar
        frame.setSize(600,400);//Setting size
        frame.setLocationRelativeTo(null);//Setting location to the center of screen
        frame.getContentPane().setBackground(Color.BLACK);//setting background color
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(10, 11, 580, 378);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(Splash.class.getResource("/icons/logo.png")));
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setBounds(0, 0, 580, 195);
        panel.add(lblLogo);
        
        JLabel lblNewLabel = new JLabel("Payroll Management System");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 206, 580, 76);
        panel.add(lblNewLabel);
        frame.setVisible(true);//setting visibility
        progressBar.setBounds(98,314,400,30);//Setting Location and size
        progressBar.setBorderPainted(true);//Setting border painted property
        progressBar.setStringPainted(true);//Setting String painted property
        progressBar.setBackground(Color.WHITE);//setting background color
        progressBar.setForeground(Color.BLACK);//setting foreground color
        progressBar.setValue(0);//setting progress bar current value
        panel.add(progressBar);//adding progress bar to frame
        message.setBounds(250,320,200,40);//Setting the size and location of the label
        message.setForeground(Color.black);//Setting foreground Color
        message.setFont(new Font("arial",Font.BOLD,15));//Setting font properties
        panel.add(message);//adding label to the frame
        int i=0;//Creating an integer variable and intializing it to 0

        while( i<=100)
        {
            try{
                Thread.sleep(50);//Pausing execution for 50 milliseconds
                progressBar.setValue(i);//Setting value of Progress Bar
                message.setText("LOADING "+Integer.toString(i)+"%");//Setting text of the message JLabel
                i++;
                if(i==100) {
                    frame.dispose();
	                Main main = new Main();
                    main.mainframe.setVisible(true);	                	
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
	}
	
}
