package TP_Fatal_Final.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import TP_Fatal_Final.dao.ComunaDAO;
import TP_Fatal_Final.modelo.Comuna;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class ComunaConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;

	/**
	 * Create the panel.
	 * 
	 * @param marco
	 */
	public ComunaConsulta() {

		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 32, 309, 197);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		modeloTabla = new DefaultTableModel(new Object[] { "Numero de comuna", "Nombre de sede", "Junta Comunal" }, 0);
		table.setModel(modeloTabla);

		cargarTabla();

		JButton btnNuevo = new JButton("Agregar");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				// Le paso null en el caso que sea una alta. Caso contrario el objeto que quiero modificar.
				marco.setContentPane(new ComunaAltaModificacion(null));
				marco.validate();

			}
		});
		btnNuevo.setBounds(400, 29, 103, 23);
		add(btnNuevo);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ComunaDAO cDao = new ComunaDAO();
				int numeroComuna = (int) table.getValueAt(table.getSelectedRow(), 0);
				cDao.eliminar(numeroComuna);
				cargarTabla();
			}
		});
		btnEliminar.setBounds(400, 63, 103, 23);
		add(btnEliminar);

		JButton btnModificcar = new JButton("Modificar");
		btnModificcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComunaDAO cDao = new ComunaDAO();
				int numeroComuna = (int) table.getValueAt(table.getSelectedRow(), 0);
				// Opto por buscar el estudiante en la base de datos (puede haber otras
				// soluciones más óptimas).
				Comuna comunaAModificar = cDao.buscarPorNumeroDeComuna(numeroComuna);
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new ComunaAltaModificacion(comunaAModificar));
				marco.validate();

			}
		});
		btnModificcar.setBounds(400, 97, 103, 23);
		add(btnModificcar);

	}

	private void cargarTabla() {
		// Vacio la tabla
		modeloTabla.setRowCount(0);
		ComunaDAO cDAO = new ComunaDAO();
		ArrayList<Comuna> comunas= cDAO.traerTodas();
		for (Comuna c : comunas) {
			modeloTabla.addRow(new Object[] { c.getNumeroComuna(), c.getNombreDeSede(), c.getJuntaComunal()});
		}
	}

}
