package TP_Fatal_Final.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import TP_Fatal_Final.dao.BComplejosHabitacionalesDAO;
import TP_Fatal_Final.dao.BTrazadoFormalDAO;
import TP_Fatal_Final.dao.BUrbanizacionPlanificadaDAO;
import TP_Fatal_Final.modelo.BComplejosHabitacionales;
import TP_Fatal_Final.modelo.BTrazadoFormal;
import TP_Fatal_Final.modelo.BUrbanizacionPlanificada;
import TP_Fatal_Final.modelo.Barrio;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class BarrioConsulta extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel modeloTabla;

	public BarrioConsulta() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 32, 450, 197);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		modeloTabla = new DefaultTableModel(new Object[] {
			"Nombre", "Superficie", "Tipo", "Comuna"
		}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(modeloTabla);

		cargarTabla();

		JButton btnNuevo = new JButton("Agregar");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
				marco.setContentPane(new BarrioAltaModificacion(null));
				marco.validate();
			}
		});
		btnNuevo.setBounds(500, 29, 115, 23);
		add(btnNuevo);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 0));
					String tipoBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 2));

					Barrio barrioAModificar = null;

					if ("Trazado Formal".equals(tipoBarrio)) {
						barrioAModificar = new BTrazadoFormalDAO().buscarPorNombre(nombreBarrio);
					} else if ("Complejo Habitacional".equals(tipoBarrio)) {
						barrioAModificar = new BComplejosHabitacionalesDAO().buscarPorNombre(nombreBarrio);
					} else if ("Urbanización Planificada".equals(tipoBarrio)) {
						barrioAModificar = new BUrbanizacionPlanificadaDAO().buscarPorNombre(nombreBarrio);
					}

					JFrame marco = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());
					marco.setContentPane(new BarrioAltaModificacion(barrioAModificar));
					marco.validate();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un barrio de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnModificar.setBounds(500, 63, 115, 23);
		add(btnModificar);

		JButton btnVerDetalles = new JButton("Ver detalles");
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 0));
					String tipoBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 2));
					Object valorComuna = table.getValueAt(filaSeleccionada, 3);
					String comunaBarrio = valorComuna != null ? valorComuna.toString() : "";

					StringBuilder detalle = new StringBuilder();
					detalle.append("Barrio: ").append(nombreBarrio).append("\n");
					detalle.append("Tipo: ").append(tipoBarrio).append("\n");
					detalle.append("Comuna: ").append(comunaBarrio).append("\n\n");

					if ("Trazado Formal".equals(tipoBarrio)) {
						BTrazadoFormal b = (BTrazadoFormal) new BTrazadoFormalDAO().buscarPorNombre(nombreBarrio);
						if (b != null) {
							detalle.append("Cantidad de Edificios PH: ").append(b.getCantidadDeEdificiosHorizontales()).append("\n");
							detalle.append("Porcentaje de Asfalto: ").append(b.getPorAsfaltoEnCalles()).append("%\n");
						}
					} else if ("Complejo Habitacional".equals(tipoBarrio)) {
						BComplejosHabitacionales b = (BComplejosHabitacionales) new BComplejosHabitacionalesDAO().buscarPorNombre(nombreBarrio);
						if (b != null) {
							detalle.append("Cantidad de Monoblocks: ").append(b.getCantidadDeMonoblocks()).append("\n");
							detalle.append("Consorcio Activo: ").append(b.isTieneAdministracionActiva() ? "Sí" : "No").append("\n");
						}
					} else if ("Urbanización Planificada".equals(tipoBarrio)) {
						BUrbanizacionPlanificada b = (BUrbanizacionPlanificada) new BUrbanizacionPlanificadaDAO().buscarPorNombre(nombreBarrio);
						if (b != null) {
							detalle.append("Número de Ley: ").append(b.getNumeroDeLey()).append("\n");
							detalle.append("Conexión Agua Potable: ").append(b.isTieneAguaPotable() ? "Sí" : "No").append("\n");
						}
					}

					JOptionPane.showMessageDialog(null, detalle.toString(), "Detalles del Barrio", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un barrio de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnVerDetalles.setBounds(500, 97, 115, 23);
		add(btnVerDetalles);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 0));
					String tipoBarrio = String.valueOf(table.getValueAt(filaSeleccionada, 2));

					if ("Trazado Formal".equals(tipoBarrio)) {
						new BTrazadoFormalDAO().eliminar(nombreBarrio);
					} else if ("Complejo Habitacional".equals(tipoBarrio)) {
						new BComplejosHabitacionalesDAO().eliminar(nombreBarrio);
					} else if ("Urbanización Planificada".equals(tipoBarrio)) {
						new BUrbanizacionPlanificadaDAO().eliminar(nombreBarrio);
					}

					cargarTabla();
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione un barrio de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnEliminar.setBounds(500, 131, 115, 23);
		add(btnEliminar);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(BarrioConsulta.this);
				frame.setContentPane(new MenuVista());
				frame.revalidate();
		        frame.repaint();
			}
		});
		btnNewButton.setBounds(500, 165, 115, 23);
		add(btnNewButton);
	}

	private void agregarFilasDesdeLista(ArrayList<Barrio> listaBarrios, String tipoBarrio) {
		if (listaBarrios != null) {
			for (Barrio b : listaBarrios) {
				modeloTabla.addRow(new Object[] {
					b.getNombreBarrio(),
					b.getSuperficie(),
					tipoBarrio,
					b.getComuna()
				});
			}
		}
	}

	private void cargarTabla() {
		modeloTabla.setRowCount(0);

		BTrazadoFormalDAO daoFormal = new BTrazadoFormalDAO();
		BComplejosHabitacionalesDAO daoComplejos = new BComplejosHabitacionalesDAO();
		BUrbanizacionPlanificadaDAO daoPlanificada = new BUrbanizacionPlanificadaDAO();

		agregarFilasDesdeLista(daoFormal.traerTodos(), "Trazado Formal");
		agregarFilasDesdeLista(daoComplejos.traerTodos(), "Complejo Habitacional");
		agregarFilasDesdeLista(daoPlanificada.traerTodos(), "Urbanización Planificada");
	}
}