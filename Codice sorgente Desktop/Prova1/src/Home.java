import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home(Controller ctrl) {
		Controller controller = ctrl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 743);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(532, 48, 37, 582);
		contentPane.add(separator);
		
		JLabel label = new JLabel("");
		
		
		JButton btnConsigliaFilm = new JButton("Consiglia film");
		btnConsigliaFilm.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				label.setIcon(new ImageIcon(Home.class.getResource("/Images/button_pressed.png")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				label.setIcon(new ImageIcon(Home.class.getResource("/Images/button.png")));
			}
		});
		
		btnConsigliaFilm.setForeground(UIManager.getColor("Button.disabledShadow"));
		btnConsigliaFilm.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		btnConsigliaFilm.setFocusPainted(false);
		btnConsigliaFilm.setContentAreaFilled(false);
		btnConsigliaFilm.setBackground(new Color(245, 245, 245));
		btnConsigliaFilm.setIcon(null);
		btnConsigliaFilm.setBorder(new RoundedBorder(30));
		btnConsigliaFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.cambiaFrame(new CercaFilm(controller));
				setVisible(false);
			}
		});
		btnConsigliaFilm.setBounds(643, 69, 400, 190);
		contentPane.add(btnConsigliaFilm);
		label.setIcon(new ImageIcon(Home.class.getResource("/Images/button.png")));
		label.setBounds(643, 58, 427, 213);
		contentPane.add(label);
		
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Home.class.getResource("/Images/button.png")));
		label_1.setBounds(643, 287, 427, 213);
		
		JButton btnInviaEmailPromozionale = new JButton("Invia e-mail promozionale");
		btnInviaEmailPromozionale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(Home.this,"Invierai un messaggio promozionale a tutti gli utenti continuare?","Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				CaricamentoDialog cd = new CaricamentoDialog();
				if(response==JOptionPane.YES_OPTION){
					cd.setLocationRelativeTo(null);
					cd.setVisible(true);
					 Thread t = new Thread(new Runnable(){
					        public void run(){
					        	if(controller.SendEmailPromozionale()){
									cd.setVisible(false);
									
									JOptionPane.showMessageDialog(null, "Email inviata con successo!");
								
									
								}else{
									cd.setVisible(false);
									JOptionPane.showMessageDialog(null, "Si è verificato un errore");
								}
					
					
					        	
					        } });
					  t.start();
					
				}
				
				
				
			}
		});
		btnInviaEmailPromozionale.setForeground(UIManager.getColor("Button.disabledShadow"));
		btnInviaEmailPromozionale.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		btnInviaEmailPromozionale.setFocusPainted(false);
		btnInviaEmailPromozionale.setContentAreaFilled(false);
		btnInviaEmailPromozionale.setBorder(new RoundedBorder(30));
		btnInviaEmailPromozionale.setBackground(new Color(245, 245, 245));
		btnInviaEmailPromozionale.setBounds(643, 298, 400, 190);
		btnInviaEmailPromozionale.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				label_1.setIcon(new ImageIcon(Home.class.getResource("/Images/button_pressed.png")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				label_1.setIcon(new ImageIcon(Home.class.getResource("/Images/button.png")));
			}
		});
		contentPane.add(btnInviaEmailPromozionale);
		
		
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("");
		JButton btnCreaProfiloModeratore = new JButton("Crea profilo moderatore\r\n");
		btnCreaProfiloModeratore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				controller.cambiaFrame(new CreaModeratore(controller));
			}
		});
		btnCreaProfiloModeratore.setForeground(UIManager.getColor("Button.disabledShadow"));
		btnCreaProfiloModeratore.setFont(new Font("Trebuchet MS", Font.BOLD, 26));
		btnCreaProfiloModeratore.setFocusPainted(false);
		btnCreaProfiloModeratore.setContentAreaFilled(false);
		btnCreaProfiloModeratore.setBorder(new RoundedBorder(32));
		btnCreaProfiloModeratore.setBackground(new Color(245, 245, 245));
		btnCreaProfiloModeratore.setBounds(643, 521, 400, 81);
		btnCreaProfiloModeratore.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				label_2.setIcon(new ImageIcon(Home.class.getResource("/Images/button_2_pressed.png")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				label_2.setIcon(new ImageIcon(Home.class.getResource("/Images/button_2.png")));
			}
		});
		contentPane.add(btnCreaProfiloModeratore);
		
		
		label_2.setIcon(new ImageIcon(Home.class.getResource("/Images/button_2.png")));
		label_2.setBounds(643, 516, 427, 91);
		contentPane.add(label_2);
		
		JLabel lblMen = new JLabel("Men\u00F9 iniziale");
		lblMen.setForeground(new Color(255, 140, 0));
		lblMen.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		lblMen.setBounds(15, 69, 435, 84);
		contentPane.add(lblMen);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Home.class.getResource("/Images/movie.png")));
		label_4.setBounds(133, 169, 256, 256);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon(Home.class.getResource("/Images/text_4.png")));
		label_5.setBounds(77, 422, 380, 220);
		contentPane.add(label_5);
		
		JLabel lblLogOut = new JLabel("<HTML><U>LogOut</U></HTML>");
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblLogOut.setForeground(Color.CYAN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblLogOut.setForeground(Color.BLUE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				controller.cambiaFrame(new Login(controller));
			}
		});
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogOut.setForeground(Color.BLUE);
		lblLogOut.setBounds(1075, 29, 82, 32);
		lblLogOut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(lblLogOut);
	
	}
}
