import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Point;
import javax.swing.Icon;

public class ScegliUtenti extends JFrame {

	private JPanel contentPane;
	private JTextField textCerca;
    private ArrayList<Utente> lista_utenti_scelti;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScegliUtenti frame = new ScegliUtenti();
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
	public ScegliUtenti(Controller ctrl) {
		Controller controller = ctrl;
		lista_utenti_scelti = new ArrayList<Utente>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1213, 743);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Icon imgIcon = new ImageIcon(CaricamentoDialog.class.getResource("/Images/Rolling-1s-200px.gif"));
		JLabel label_1 = new JLabel(imgIcon);
		label_1.setBounds(328, 187, 427, 200);
		label_1.setVisible(false);
		contentPane.add(label_1);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setOpaque(false);
		scrollPane1.setBorder(null);
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane1.setBounds(890, 152, 270, 413);
		contentPane.add(scrollPane1);
		
		JPanel base_scelti = new RoundedPanel(30,new Color(255, 215, 0));
		base_scelti.setForeground(new Color(255, 215, 0));
		base_scelti.setBorder(null);
		base_scelti.setAutoscrolls(true);
		scrollPane1.setViewportView(base_scelti);
		base_scelti.setLayout(new MigLayout("wrap 0", "20[fill,:220:220]100","20[grow,fill,:50:55]"));
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(583, 152, 270, 413);
		contentPane.add(scrollPane);
		
		JPanel base = new RoundedPanel(30,new Color(255, 165, 0));
		base.setForeground(new Color(255, 140, 0));
		base.setBorder(null);
		base.setAutoscrolls(true);
		scrollPane.setViewportView(base);
		base.setLayout(new MigLayout("wrap 0", "20[fill,:200:200]100","20[grow,fill,:50:250]"));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 140, 0));
		separator.setBackground(new Color(255, 140, 0));
		separator.setBounds(532, 48, 37, 582);
		contentPane.add(separator);
		
		textCerca = new RoundJTextField(500);
		textCerca.setFont(new Font("Tahoma", Font.BOLD, 20));
		textCerca.setText("Cerca");
		textCerca.setForeground(new Color(255, 165, 0));
		textCerca.setBounds(612, 56, 495, 44);
		textCerca.setBorder(BorderFactory.createCompoundBorder(
		        textCerca.getBorder(), 
		        BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		contentPane.add(textCerca);
		textCerca.setColumns(10);
		
		JLabel lblSearch = new JLabel("");
		lblSearch.setIcon(new ImageIcon(CercaFilm.class.getResource("/Images/magnifying-glass.png")));
		lblSearch.setBounds(1122, 56, 54, 52);
		contentPane.add(lblSearch);
		
		JButton btnAggiungiBottone = new JButton("");
		
		
		btnAggiungiBottone.setIcon(null);
		btnAggiungiBottone.setOpaque(false);
		btnAggiungiBottone.setBorder(null);
		btnAggiungiBottone.setBackground(new Color(245, 245, 245));
		btnAggiungiBottone.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAggiungiBottone.addActionListener(new ActionListener() {
			//int num_click=0;
			//ArrayList elenco_bottoni=new ArrayList();
			int x=1161;
			int y=62;
			int o=82;
			int v=49;
			public void actionPerformed(ActionEvent arg0) {
				base.removeAll();
				//elenco_bottoni.clear();
				System.out.println(o+" "+v);
				Utente[] array_utente = ctrl.cercaUtenti(textCerca.getText().replaceAll("\\s+", "%20"));
				if(array_utente == null){
					textCerca.setText("Ricerca senza risultati");
				}else{
				for(int i=0 ; i< array_utente.length ; i++){
					//elenco_bottoni.add(new JButton());
					JButton bottone= new JButton("");//elenco_bottoni.get(i);
					bottone.setName(array_utente[i].getUsername());
					Utente user = array_utente[i];
					String nome = array_utente[i].getNome();
					String cognome = array_utente[i].getCognome();
					bottone.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent arg0) {
						System.out.println("Hai selezionato:"+nome+"  "+cognome);
						if(controller.cercaNellaLista(lista_utenti_scelti, user.getUsername())){
							System.out.println("Tale utente già esiste");
						}else{
						lista_utenti_scelti.add(user);
						JButton bottone_scelto= new JButton("");
						bottone_scelto.setName(user.getUsername());
						bottone_scelto.setToolTipText(user.getNome()+"  "+user.getCognome());
						bottone_scelto.setVerticalAlignment(SwingConstants.BOTTOM);
						bottone_scelto.setBounds(o, v, 196, 245);
						bottone_scelto.setFont(new Font("Tahoma", Font.BOLD, 20));
						bottone_scelto.setText(user.getUsername());
						bottone_scelto.setBackground(new Color(255, 165, 0));
				       
						bottone_scelto.setVerticalAlignment(SwingConstants.CENTER);
						bottone_scelto.setIcon(new ImageIcon(CercaFilm.class.getResource("/Images/cancel.jpg")));
						bottone_scelto.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
						bottone_scelto.setHorizontalAlignment(SwingConstants.RIGHT);
						bottone_scelto.setHorizontalTextPosition(SwingConstants.LEFT);
						bottone_scelto.setIconTextGap(40);
						base_scelti.add(bottone_scelto);
						bottone_scelto.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent arg0) {
								lista_utenti_scelti.remove(user);
								base_scelti.remove(bottone_scelto);
								base_scelti.updateUI();
								
								
							}
							});
						
						
						
						
						scrollPane1.setViewportView(base_scelti);
						}
						/*controller.setFilmSelezionato(controller.cercaFilmDalId(bottone.getName()).getCodice(), controller.cercaFilmDalId(bottone.getName()).getAnno(),controller.cercaFilmDalId(bottone.getName()).getTitolo(), controller.cercaFilmDalId(bottone.getName()).getPoster(),controller.cercaFilmDalId(bottone.getName()).getDescrizione()  );
						controller.setCampiSchedaFilm();*/
						//setVisible(false);
			         }
					  });
					
					
					
					bottone.setToolTipText(array_utente[i].getNome()+"  "+array_utente[i].getCognome());
					bottone.setFont(new Font("Tahoma", Font.BOLD, 20));
					bottone.setVerticalAlignment(SwingConstants.BOTTOM);
					bottone.setSize(225, 53);
					bottone.setFocusable(false);
					bottone.setFocusPainted(false);
					bottone.setBounds(o, v, 196, 245);
					bottone.setBackground(new Color(255, 215, 0));
					
					//ImageFileFiltrer iff = new ImageFileFiltrer();
					//if(array_film[i].getPoster().equals("https://image.tmdb.org/t/p/w500null")){
						bottone.setLabel(array_utente[i].getUsername());
						bottone.setVerticalAlignment(SwingConstants.CENTER);
						base.add(bottone);
						
								
					//}else{
					//bottone.setIcon(new ImageIcon(iff.getScaledImage(new ImageIcon(ctrl.getImageByUrl(array_film[i].getPoster())).getImage(),200, 250)));
					//base.add(bottone);
					//}
				}
				
				
					
					
					
					
					
				}
				
				
				//num_click=num_click+1;
				
				
				
				scrollPane.setViewportView(base);		
			}
		});
		btnAggiungiBottone.setBounds(1122, 48, 38, 52);
		contentPane.add(btnAggiungiBottone);
		
		JButton btnConsiglia = new JButton("");
		btnConsiglia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(lista_utenti_scelti.isEmpty()){
					JOptionPane.showMessageDialog(null, "La lista è vuota");
				}else{
					int response = JOptionPane.showConfirmDialog(ScegliUtenti.this,"Vuoi davvero consigliare il film a questi/o utenti/o?","Conferma",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					CaricamentoDialog cd = new CaricamentoDialog();
					if(response==JOptionPane.YES_OPTION){
						cd.setLocationRelativeTo(null);
						cd.setVisible(true);
						System.out.println(controller.film_selezionato.getTitolo());
						 Thread t = new Thread(new Runnable(){
						        public void run(){
						        	if(controller.SendEmailFilm(controller.film_selezionato.getTitolo(),lista_utenti_scelti)){
										cd.setVisible(false);
										
										JOptionPane.showMessageDialog(null, "Email inviata con successo!");
										setVisible(false);
										controller.cambiaFrame(new Home(controller));
									}else{
										JOptionPane.showMessageDialog(null, "Si è verificato un errore");
									}
						        	
						        }
						    });
						  t.start();
						
						
						
					}else{
						
						
						
						
					}
					
					
					
					
					
					
					
					
					
				}
				
				
				
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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/scegli_utente.jpg")));
		label.setBounds(147, 187, 233, 237);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/text_3.jpg")));
		lblNewLabel.setBounds(75, 417, 380, 93);
		contentPane.add(lblNewLabel);
		
		JLabel lblScegliUtenti = new JLabel("Scegli Utenti");
		lblScegliUtenti.setForeground(new Color(255, 140, 0));
		lblScegliUtenti.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
		lblScegliUtenti.setBounds(15, 76, 435, 84);
		contentPane.add(lblScegliUtenti);
		
		JLabel lblUtentiCercati = new JLabel("Utenti cercati");
		lblUtentiCercati.setForeground(new Color(255, 140, 0));
		lblUtentiCercati.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblUtentiCercati.setBounds(594, 107, 146, 52);
		contentPane.add(lblUtentiCercati);
		
		JLabel lblUtentiScelti = new JLabel("Utenti scelti");
		lblUtentiScelti.setForeground(new Color(255, 140, 0));
		lblUtentiScelti.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblUtentiScelti.setBounds(1014, 107, 146, 52);
		contentPane.add(lblUtentiScelti);
		
		JButton buttonBack = new JButton("");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				controller.cambiaFrame(new CercaFilm(controller));
			}
		});
		buttonBack.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/undo_button.jpg")));
		buttonBack.setBounds(945, 610, 54, 52);
		buttonBack.setOpaque(true);
		buttonBack.setContentAreaFilled(false);
		buttonBack.setFocusPainted(false);
		buttonBack.setBorder(null);
		buttonBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				buttonBack.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/undo_button_pressed.jpg")));
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				buttonBack.setIcon(new ImageIcon(ScegliUtenti.class.getResource("/Images/undo_button.jpg")));
			}
		});
		contentPane.add(buttonBack);
		
		
		
		
	}
}
