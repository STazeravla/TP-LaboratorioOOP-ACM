package TP_Fatal_Final;
import java.util.ArrayList;
import java.util.Arrays;

import TP_Fatal_Final.dao.BComplejosHabitacionalesDAO;
import TP_Fatal_Final.dao.BTrazadoFormalDAO;
import TP_Fatal_Final.modelo.BComplejosHabitacionales;
import TP_Fatal_Final.modelo.BTrazadoFormal;
import TP_Fatal_Final.modelo.Barrio;
import TP_Fatal_Final.modelo.Persona;

public class pruebasDAO {

	public static void main(String[] args) {
		// String nombreBarrio, int superficie, int cantidadDeEdificiosHorizontales, int porAsfaltoEnCalles int numeroBarrio
		//BTrazadoFormal yo = new BTrazadoFormal();
		
		//yo.setNombreBarrio("SedeBacacay");
		//yo.setNumeroBarrio(10);
		//yo.setCantidadDeEdificiosHorizontales(15);
		//yo.setPorAsfaltoEnCalles(30);
		//yo.setSuperficie(100);
		
		
		//BTrazadoFormalDAO btfDAO = new BTrazadoFormalDAO();
		
		//btfDAO.guardar(yo);
		
		//btfDAO.actualizar(yo, 7);
		
		//btfDAO.eliminar(7);
		
		//btfDAO.buscarPorNumeroDeComuna(0);
		
		//ArrayList<Comuna> todasLasComunas = cDAO.traerTodas();
		//for (Comuna comuna : todasLasComunas) {
		//	System.out.println(comuna);
		//	Para mostrar solo el nombre de sede de las comunas:
		//  System.out.println(comuna.getNombreDeSede());
		
		BComplejosHabitacionales yo = new BComplejosHabitacionales();
		
		yo.setNombreBarrio("Bou");
		yo.setNumeroBarrio(19);
		yo.setCantidadDeMonoblocks(30);
		yo.setSuperficie(1500);
		yo.setTieneAdministracionActiva(true);
				
		BComplejosHabitacionalesDAO bchDAO = new BComplejosHabitacionalesDAO();
		
		bchDAO.guardar(yo);
		
	
	
	
	
	
		}
	}
