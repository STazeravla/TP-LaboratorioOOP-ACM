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
		
		// 📌 Únicamente las 3 columnas solicitadas: Nombre, Superficie y Tipo
		modeloTabla = new DefaultTableModel(new Object[] { 
			"Nombre", "Superficie", "Tipo" 
		}, 0);
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
					String nombreBarrio = (String) table.getValueAt(filaSeleccionada, 0);
					String tipoBarrio = (String) table.getValueAt(filaSeleccionada, 2);
					
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
				}
			}
		});
		btnModificar.setBounds(500, 63, 115, 23);
		add(btnModificar);

		// Botón Ver Detalles (para consultar la info específica sin ensuciar la tabla principal)
		JButton btnVerDetalles = new JButton("Ver detalles");
		btnVerDetalles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada != -1) {
					String nombreBarrio = (String) table.getValueAt(filaSeleccionada, 0);
					String tipoBarrio = (String) table.getValueAt(filaSeleccionada, 2);
					
					StringBuilder detalle = new StringBuilder();
					detalle.append("Barrio: ").append(nombreBarrio).append("\n");
					detalle.append("Tipo: ").append(tipoBarrio).append("\n\n");
					
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
					String nombreBarrio = (String) table.getValueAt(filaSeleccionada, 0);
					String tipoBarrio = (String) table.getValueAt(filaSeleccionada, 2);
					
					if ("Trazado Formal".equals(tipoBarrio)) {
						new BTrazadoFormalDAO().eliminar(nombreBarrio);
					} else if ("Complejo Habitacional".equals(tipoBarrio)) {
						new BComplejosHabitacionalesDAO().eliminar(nombreBarrio);
					} else if ("Urbanización Planificada".equals(tipoBarrio)) {
						new BUrbanizacionPlanificadaDAO().eliminar(nombreBarrio);
					}
					
					cargarTabla();
				}
			}
		});
		btnEliminar.setBounds(500, 131, 115, 23);
		add(btnEliminar);
	}

	private void cargarTabla() {
		modeloTabla.setRowCount(0);
		
		BTrazadoFormalDAO daoFormal = new BTrazadoFormalDAO();
		BComplejosHabitacionalesDAO daoComplejos = new BComplejosHabitacionalesDAO();
		BUrbanizacionPlanificadaDAO daoPlanificada = new BUrbanizacionPlanificadaDAO();
		
		// 1. Trazado Formal
		ArrayList<Barrio> listaFormales = daoFormal.traerTodos();
		if (listaFormales != null) {
			for (Barrio b : listaFormales) {
				modeloTabla.addRow(new Object[] { 
					b.getNombreBarrio(), 
					b.getSuperficie(), 
					"Trazado Formal"
				});
			}
		}
		
		// 2. Complejos Habitacionales
		ArrayList<Barrio> listaComplejos = daoComplejos.traerTodos();
		if (listaComplejos != null) {
			for (Barrio b : listaComplejos) {
				modeloTabla.addRow(new Object[] { 
					b.getNombreBarrio(), 
					b.getSuperficie(), 
					"Complejo Habitacional"
				});
			}
		}
		
		// 3. Urbanización Planificada
		ArrayList<Barrio> listaPlanificadas = daoPlanificada.traerTodos();
		if (listaPlanificadas != null) {
			for (Barrio b : listaPlanificadas) {
				modeloTabla.addRow(new Object[] { 
					b.getNombreBarrio(), 
					b.getSuperficie(), 
					"Urbanización Planificada"
				});
			}
		}
	}
}