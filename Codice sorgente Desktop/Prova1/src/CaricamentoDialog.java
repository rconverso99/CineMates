import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;

public class CaricamentoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			CaricamentoDialog dialog = new CaricamentoDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CaricamentoDialog() {
		setBounds(100, 100, 732, 388);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			Icon imgIcon = new ImageIcon(CaricamentoDialog.class.getResource("/Images/Rolling-1s-200px.gif"));
			JLabel label = new JLabel(imgIcon);
			label.setBounds(136, 16, 427, 200);
			contentPanel.add(label);
		}
		
		JLabel label = new JLabel("Caricamento in corso...");
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBounds(248, 249, 218, 20);
		contentPanel.add(label);
	}

}
