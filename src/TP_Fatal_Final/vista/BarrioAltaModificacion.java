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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BarrioAltaModificacion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombreDeSede;
	private JTextField txtNumeroComuna;
	private JTextField txtTipoBarrio;
	private JButton btnGuardarModificar;

	/**
	 * Create the panel.
	 * 
	 * @param estudianteAModificar
	 */
	public BarrioAltaModificacion(Comuna comunaAModificar) {
		setLayout(null);

		txtNombreDeSede = new JTextField();
		txtNombreDeSede.setBounds(147, 59, 121, 20);
		add(txtNombreDeSede);
		txtNombreDeSede.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nombre del barrio");
		lblNewLabel.setBounds(12, 59, 135, 14);
		add(lblNewLabel);


		JLabel lblNewLabel_1 = new JLabel("Superficie");
		lblNewLabel_1.setBounds(12, 87, 135, 14);
		add(lblNewLabel_1);

		txtNumeroComuna = new JTextField();
		txtNumeroComuna.setBounds(147, 84, 121, 20);
		add(txtNumeroComuna);
		txtNumeroComuna.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Tipo de barrio");
		lblNewLabel_2.setBounds(12, 113, 95, 14);
		add(lblNewLabel_2);
		
		if (comunaAModificar != null) {
			// Completo los campos de textos.
			txtNumeroComuna.setText("" + comunaAModificar.getNumeroComuna());
			txtNombreDeSede.setText(comunaAModificar.getNombreDeSede());
			txtTipoBarrio.setText("" + comunaAModificar.getTipoDeBarrio());
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
				comuna.setNumeroBarrios(Integer.valueOf(txtNumeroBarrios.getText()));
				comuna.setTipoDeBarrio(txtTipoBarrio.getText());
				
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
		
		JLabel lblNewLabel_4 = new JLabel("Agregar Barrio");
		lblNewLabel_4.setBounds(160, 25, 119, 17);
		add(lblNewLabel_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Trazado Formal", "Complejo Habitacional", "Urbanizacion Planificada"}));
		comboBox.setBounds(147, 109, 121, 22);
		add(comboBox);

	}
}
