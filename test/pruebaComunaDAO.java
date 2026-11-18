import java.util.ArrayList;
import java.util.Arrays;

import TP_Fatal_Final.dao.ComunaDAO;
import TP_Fatal_Final.modelo.Comuna;

public class pruebaComunaDAO {

	public static void main(String[] args) {
		
		Comuna yo = new Comuna();
		
		yo.setNombreDeSede("SedeBacacay");
		yo.setNumeroComuna(10);
		yo.setJuntaComunal("Allbo");
		
		
		
		ComunaDAO cDAO = new ComunaDAO();
		
		//cDAO.guardar(yo);
		
		//cDAO.actualizar(yo, 7);
		
		//cDAO.eliminar(7);
		
		//cDAO.buscarPorNumeroDeComuna(0);
		
		//ArrayList<Comuna> todasLasComunas = cDAO.traerTodas();
		//for (Comuna comuna : todasLasComunas) {
		//	System.out.println(comuna);
		//	Para mostrar solo el nombre de sede de las comunas:
		//  System.out.println(comuna.getNombreDeSede());
		}
	}
