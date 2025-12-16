import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CreaModeratore extends JFrame {

	private JPanel contentPane;
	private RoundJPasswordField textConferma_Password;
	private JLabel label_asterisco_confermaPassword;
	private JLabel label_asterisco_Nome;
	private JTextField textNome;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreaModeratore frame = new CreaModeratore();
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
	public CreaModeratore(Controller ctrl) {
		Controller controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 743);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextField textNome;
		textNome = new RoundJTextField(500);
		textNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.setLayout(null);
		textNome.setFont(new Font("Arial", Font.BOLD, 22));
		textNome.setText("Nome");
		textNome.setForeground(new Color(255, 140, 0));
		textNome.setBorder(BorderFactory.createCompoundBorder(
		        textNome.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textNome.setToolTipText("Nome");
		textNome.setSize(456, 43);
		
	
		textNome.setLocation(634, 147);
		contentPane.add(textNome);
		textNome.setColumns(10);
		
		RoundJPasswordField textConferma_Password;
		textConferma_Password = new RoundJPasswordField(500);
		textConferma_Password.setToolTipText("Conferma Password");
		textConferma_Password.setText("Password");
		textConferma_Password.setBorder(BorderFactory.createCompoundBorder(
		        textConferma_Password.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textConferma_Password.setForeground(new Color(255, 140, 0));
		textConferma_Password.setFont(new Font("Arial", Font.BOLD, 22));
		textConferma_Password.setColumns(10);
		textConferma_Password.setBounds(634, 472, 456, 43);
		contentPane.add(textConferma_Password);
		
		RoundJPasswordField textPassword = new RoundJPasswordField(500);
		textPassword.setToolTipText("Password");
		textPassword.setText("Password");
		textPassword.setBorder(BorderFactory.createCompoundBorder(
		        textPassword.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textPassword.setForeground(new Color(255, 140, 0));
		textPassword.setFont(new Font("Arial", Font.BOLD, 22));
		textPassword.setColumns(10);
		textPassword.setBounds(634, 359, 456, 43);
		contentPane.add(textPassword);
		

		JLabel label_asterisco_username = new JLabel("*");
		label_asterisco_username.setForeground(new Color(255, 0, 0));
		label_asterisco_username.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_username.setBounds(1102, 259, 57, 33);
		label_asterisco_username.setVisible(false);
		contentPane.add(label_asterisco_username);
		
		JLabel label_asterisco_Nome;
		label_asterisco_Nome = new JLabel("*");
		label_asterisco_Nome.setForeground(Color.RED);
		label_asterisco_Nome.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_Nome.setBounds(1105, 147, 50, 61);
		label_asterisco_Nome.setVisible(false);
		contentPane.add(label_asterisco_Nome);
		
		JLabel label_asterisco_password = new JLabel("*");
		label_asterisco_password.setForeground(Color.RED);
		label_asterisco_password.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_password.setBounds(1109, 359, 50, 61);
		label_asterisco_password.setVisible(false);
		contentPane.add(label_asterisco_password);
		
		JTextField textUsername = new RoundJTextField(500);
		textUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		contentPane.setLayout(null);
		textUsername.setFont(new Font("Arial", Font.BOLD, 22));
		textUsername.setText("Username");
		textUsername.setForeground(new Color(255, 140, 0));
		textUsername.setBorder(BorderFactory.createCompoundBorder(
		        textUsername.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		textUsername.setToolTipText("Username");
		textUsername.setSize(456, 43);
		
	
		textUsername.setLocation(634, 244);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel label_asterisco_confermapassword;
		label_asterisco_confermaPassword = new JLabel("*");
		label_asterisco_confermaPassword.setForeground(Color.RED);
		label_asterisco_confermaPassword.setFont(new Font("Arial", Font.PLAIN, 40));
		label_asterisco_confermaPassword.setBounds(1105, 473, 50, 61);
		label_asterisco_confermaPassword.setVisible(false);
		contentPane.add(label_asterisco_confermaPassword);
		
		JButton btnCreaModeratore = new JButton("");
		btnCreaModeratore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnCreaModeratore.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/crea_button_pressed.png")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCreaModeratore.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/crea_button.png")));
				
			}
		});
		btnCreaModeratore.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/crea_button.png")));
		btnCreaModeratore.setContentAreaFilled(false);
		btnCreaModeratore.setBorder(null);
		btnCreaModeratore.setBorderPainted(false);
		btnCreaModeratore.setFocusPainted(false);
		btnCreaModeratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
if(ctrl.checkTextEmpty(textUsername.getText(),label_asterisco_username) == false ){
					
				}
				if(ctrl.checkTextEmpty(textPassword.getText(),label_asterisco_password) == false){
					
				}
if(ctrl.checkTextEmpty(textNome.getText(),label_asterisco_Nome) == false ){
					
				}
				if(ctrl.checkTextEmpty(textConferma_Password.getText(),label_asterisco_confermaPassword) == false){
					
				}
				
				else 
					if (ctrl.checkTextEmpty(textUsername.getText(),label_asterisco_username) == true && ctrl.checkTextEmpty(textPassword.getText(),label_asterisco_password) == true && ctrl.checkTextEmpty(textNome.getText(),label_asterisco_Nome) == true && ctrl.checkTextEmpty(textConferma_Password.getText(),label_asterisco_confermaPassword) == true){
					     if(textPassword.getText().matches(textConferma_Password.getText())){
					    	 
					    	 try {
									
									if(controller.verificaModeratore(textUsername.getText()) == true ){
										controller.creaModeratore(textUsername.getText(), textPassword.getText());
										JOptionPane.showMessageDialog(null, "Moderatore creato con successo!");
										controller.cambiaFrame(new Home(controller));
									
										
										setVisible(false);
									}else{
										JOptionPane.showMessageDialog(null, "Username già in uso");
									}
									
									
								} catch (Exception err) {
									// TODO Auto-generated catch block
									err.printStackTrace();
								}
					    	 
					    	 
					     }else{
					    	 JOptionPane.showMessageDialog(null, "Password e conferma Password non corrispondono");
					     }
						
						
						
					
					
					}else{
						
					}
				
				
				
				
				
				
			}
		});
		btnCreaModeratore.setBounds(992, 591, 135, 48);
		contentPane.add(btnCreaModeratore);
		
		JButton back = new JButton("\r\n");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controller.cambiaFrame(new Home(controller));
			}
		});
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button_pressed.jpg")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button.jpg")));
			}
		});
		back.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/undo_button.jpg")));
		back.setFocusPainted(false);
		back.setBorder(null);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(897, 587, 67, 52);
		contentPane.add(back);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(532, 48, 37, 582);
		contentPane.add(separator);
		
		JLabel lblCreaModeratore = new JLabel("Crea Moderatore");
		lblCreaModeratore.setForeground(new Color(255, 140, 0));
		lblCreaModeratore.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		lblCreaModeratore.setBounds(15, 87, 502, 84);
		contentPane.add(lblCreaModeratore);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/crea_moderatore.png")));
		label.setBounds(144, 175, 218, 256);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(CreaModeratore.class.getResource("/Images/text_5.png")));
		label_1.setBounds(70, 429, 380, 61);
		contentPane.add(label_1);
	}
}
