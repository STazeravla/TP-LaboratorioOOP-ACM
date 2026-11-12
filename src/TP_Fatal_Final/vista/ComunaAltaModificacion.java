package TP_Fatal_Final.vista;

import javax.swing.JPanel;

//public class Comuna extends JPanel {

//	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
//	public Comuna() {

//	}

//}



import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import TP_Fatal_Final.modelo.Comuna;
import TP_Fatal_Final.dao.ComunaDAO;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.event.ActionEvent;

public class ComunaAltaModificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreDeSede;
	private JTextField txtNumeroComuna;
	private JTextField txtJuntaComunal;
	private JButton btnGuardarModificar;

	/**
	 * Create the panel.
	 * 
	 * @param estudianteAModificar
	 */
	public ComunaAltaModificacion(Comuna comunaAModificar) {
		setLayout(null);

		txtNombreDeSede = new JTextField();
		txtNombreDeSede.setBounds(147, 59, 86, 20);
		add(txtNombreDeSede);
		txtNombreDeSede.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre de comuna");
		lblNewLabel.setBounds(12, 59, 135, 14);
		add(lblNewLabel);


		JLabel lblNewLabel_1 = new JLabel("Número de sede");
		lblNewLabel_1.setBounds(12, 87, 135, 14);
		add(lblNewLabel_1);

		txtNumeroComuna = new JTextField();
		txtNumeroComuna.setBounds(147, 84, 86, 20);
		add(txtNumeroComuna);
		txtNumeroComuna.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Junta Comunal");
		lblNewLabel_2.setBounds(12, 113, 95, 14);
		add(lblNewLabel_2);

		txtJuntaComunal = new JTextField();
		txtJuntaComunal.setBounds(147, 109, 86, 20);
		add(txtJuntaComunal);
		txtJuntaComunal.setColumns(10);
		
		if (comunaAModificar != null) {
			// Completo los campos de textos.
			txtNumeroComuna.setText("" + comunaAModificar.getNumeroComuna());
			txtNombreDeSede.setText(comunaAModificar.getNombreDeSede());
			txtJuntaComunal.setText("" + comunaAModificar.getJuntaComunal());
		}

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new ComunaConsulta());
				marco.validate();
			}
		});
		btnVolver.setBounds(179, 212, 89, 23);
		add(btnVolver);
		
		btnGuardarModificar = new JButton();
		btnGuardarModificar.setText(comunaAModificar == null ? "Guardar" : "Modificar");
		btnGuardarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comuna comuna = new Comuna();
				comuna.setNombreDeSede(txtNombreDeSede.getText());
				comuna.setNumeroComuna(Integer.valueOf(txtNumeroComuna.getText()));
				comuna.setJuntaComunal(txtJuntaComunal.getText());
				
				ComunaDAO cDao = new ComunaDAO();
				if (comunaAModificar == null) {
					// Es un alta.
					cDao.guardar(comuna);
				} else {
					// Es una modificación.
					cDao.actualizar(comuna, comunaAModificar.getNumeroComuna());
				}
				// Vuelvo al panel de consulta
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new ComunaConsulta());
				marco.validate();
				
			}
		});
		btnGuardarModificar.setBounds(86, 212, 89, 23);
		add(btnGuardarModificar);
		
		JLabel lblNewLabel_3 = new JLabel("Agregar Comuna");
		lblNewLabel_3.setBounds(160, 25, 119, 17);
		add(lblNewLabel_3);

	}
}
