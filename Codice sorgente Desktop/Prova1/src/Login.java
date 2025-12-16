import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login(Controller ctrl) {
		setResizable(false);
		Controller controller=ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(SystemColor.menu);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCinemates = new JLabel("CineMates20");
		lblCinemates.setBounds(90, 417, 363, 84);
		lblCinemates.setForeground(new Color(255, 140, 0));
		lblCinemates.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		contentPane.add(lblCinemates);
		
		JLabel label = new JLabel("Pagina riservata ai moderatori\r\n");
		label.setForeground(new Color(255, 140, 0));
		label.setFont(new Font("Corbel Light", Font.BOLD, 19));
		label.setBounds(140, 493, 267, 24);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\r\n");
		label_1.setIcon(new ImageIcon(Login.class.getResource("/Images/tools.png")));
		label_1.setBounds(142, 119, 260, 280);
		contentPane.add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(550, 48, 37, 582);
		contentPane.add(separator);
		
		JTextField textUsername = new RoundJTextField(500);
		textUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		textUsername.setFont(new Font("Arial", Font.BOLD, 22));
		textUsername.setText("Username");
		textUsername.setForeground(new Color(255, 140, 0));
		textUsername.setBorder(BorderFactory.createCompoundBorder(
		        textUsername.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textUsername.setToolTipText("Username");
		textUsername.setSize(456, 43);
		
	
		textUsername.setLocation(634, 197);
		//textUsername.setBounds(721, 172, 146, 26);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		RoundJPasswordField textPassword = new RoundJPasswordField(500);
		textPassword.setToolTipText("Password");
		textPassword.setText("Password");
		textPassword.setBorder(BorderFactory.createCompoundBorder(
		        textPassword.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textPassword.setForeground(new Color(255, 140, 0));
		textPassword.setFont(new Font("Arial", Font.BOLD, 22));
		textPassword.setColumns(10);
		textPassword.setBounds(634, 312, 456, 43);
		contentPane.add(textPassword);
		
	
		JLabel label_asterisco_username = new JLabel("*");
		label_asterisco_username.setForeground(new Color(255, 0, 0));
		label_asterisco_username.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_username.setBounds(1102, 212, 57, 33);
		label_asterisco_username.setVisible(false);
		contentPane.add(label_asterisco_username);
		
		JLabel label_asterisco_password = new JLabel("*");
		label_asterisco_password.setForeground(Color.RED);
		label_asterisco_password.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_password.setBounds(1105, 325, 57, 33);
		label_asterisco_password.setVisible(false);
		contentPane.add(label_asterisco_password);
		
		
		JButton buttonAccedi = new JButton("");
		buttonAccedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ctrl.checkTextEmpty(textUsername.getText(),label_asterisco_username) == false ){
					
				}
				if(ctrl.checkTextEmpty(textPassword.getText(),label_asterisco_password) == false){
					
				}
				else 
					if (ctrl.checkTextEmpty(textUsername.getText(),label_asterisco_username) == true && ctrl.checkTextEmpty(textPassword.getText(),label_asterisco_password) == true){
				
				
				try {
					
					if(controller.cercaModeratore(textUsername.getText(),textPassword.getText())){
						controller.cambiaFrame(new Home(controller));
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "Username o password errate");
					}
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		});
		buttonAccedi.setIcon(new ImageIcon(Login.class.getResource("/Images/login.png")));
		buttonAccedi.setOpaque(false);
		buttonAccedi.setForeground(new Color(255, 215, 0));
		buttonAccedi.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		buttonAccedi.setFocusPainted(false);
		buttonAccedi.setContentAreaFilled(false);
		buttonAccedi.setBorder(null);
		buttonAccedi.setBackground(new Color(255, 140, 0));
		buttonAccedi.setBounds(1040, 387, 50, 41);
		contentPane.add(buttonAccedi);
		
		
		
	}
}
