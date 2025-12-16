import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SchedaFilm extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SchedaFilm frame = new SchedaFilm();
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
	public SchedaFilm(Controller ctrl) {
		Controller controller=ctrl;
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
		
		JLabel lblPoster = new JLabel("");
		lblPoster.setBounds(574, 90, 196, 245);
		contentPane.add(lblPoster);
		
		JLabel lblTitolo = new JLabel("Titolo");
		lblTitolo.setForeground(new Color(255, 140, 0));
		lblTitolo.setFont(new Font("Arial", Font.BOLD, 27));
		lblTitolo.setBounds(785, 86, 406, 84);
		contentPane.add(lblTitolo);
		
		JLabel lblCodice = new JLabel("Codice");
		lblCodice.setForeground(new Color(255, 140, 0));
		lblCodice.setFont(new Font("Arial", Font.BOLD, 15));
		lblCodice.setBounds(584, 48, 92, 59);
		contentPane.add(lblCodice);
		
		JLabel lblData = new JLabel("0-0-2000");
		lblData.setForeground(new Color(255, 140, 0));
		lblData.setFont(new Font("Arial", Font.BOLD, 20));
		lblData.setBounds(785, 287, 247, 59);
		contentPane.add(lblData);
		
		JTextArea lblDescrizione = new JTextArea();
		lblDescrizione.setEditable(false);
		lblDescrizione.setForeground(new Color(255, 165, 0));
		lblDescrizione.setFont(new Font("Arial", Font.BOLD, 18));
		lblDescrizione.setLineWrap(true);
		lblDescrizione.setText("Descrizione del film\r\n");
		lblDescrizione.setBackground(new Color(245, 245, 245));
		lblDescrizione.setBounds(584, 369, 560, 227);
		contentPane.add(lblDescrizione);
		
		controller.setLabelSchedaFilm(lblTitolo, lblData, lblPoster, lblCodice, lblDescrizione);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SchedaFilm.class.getResource("/Images/film_icon.jpg")));
		label.setBounds(147, 187, 233, 237);
		contentPane.add(label);
		
		JLabel lblSchedaFilm = new JLabel("Scheda Film");
		lblSchedaFilm.setForeground(new Color(255, 140, 0));
		lblSchedaFilm.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		lblSchedaFilm.setBounds(15, 62, 435, 84);
		contentPane.add(lblSchedaFilm);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(SchedaFilm.class.getResource("/Images/text_2.jpg")));
		lblNewLabel.setBounds(78, 405, 423, 225);
		contentPane.add(lblNewLabel);
		
		JButton btnConsiglia = new JButton("");
		btnConsiglia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				     setVisible(false);
				     controller.cambiaFrame(new ScegliUtenti(controller));
					
					
					
					
					
					
					
					
					
				}
				
				
				
			
		});
		btnConsiglia.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				btnConsiglia.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/consiglia_button_pressed.jpg")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnConsiglia.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/consiglia_button.jpg")));
			}
		});
		btnConsiglia.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/consiglia_button.jpg")));
		btnConsiglia.setOpaque(true);
		btnConsiglia.setContentAreaFilled(false);
		btnConsiglia.setFocusPainted(false);
		btnConsiglia.setBorder(null);
		btnConsiglia.setBounds(1014, 612, 146, 44);
		contentPane.add(btnConsiglia);
		
		JButton back = new JButton("\r\n");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controller.cambiaFrame(new CercaFilm(controller));
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
		back.setBounds(938, 608, 67, 52);
		contentPane.add(back);
		
	}
}
