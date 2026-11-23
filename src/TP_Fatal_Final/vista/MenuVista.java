package TP_Fatal_Final.vista;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuVista extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtMenu;

	public MenuVista() {
		setLayout(null);
		
		txtMenu = new JTextField();
		txtMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMenu.setText("         Sistema Censo CABA");
		txtMenu.setBounds(88, 11, 255, 29);
		add(txtMenu);
		txtMenu.setColumns(10);
		
		JButton btnNewButton = new JButton("Comunas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MenuVista.this);
				frame.setContentPane(new ComunaConsulta());
				frame.revalidate();
		        frame.repaint();
			}
		});
		btnNewButton.setBounds(170, 89, 108, 34);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Barrios");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(MenuVista.this);
				frame.setContentPane(new BarrioConsulta());
				frame.revalidate();
		        frame.repaint();
			}
		});
		btnNewButton_1.setBounds(170, 155, 108, 34);
		add(btnNewButton_1);
		
		

	}
}
