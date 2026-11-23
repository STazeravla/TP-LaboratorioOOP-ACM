package TP_Fatal_Final;

import javax.swing.JFrame;

import TP_Fatal_Final.vista.BarrioConsulta;
import TP_Fatal_Final.vista.ComunaConsulta;
import TP_Fatal_Final.vista.MenuVista;

public class App {

	public static void main(String[] args) {
		JFrame marco = new JFrame();
		marco.setVisible(true);
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		marco.setBounds(0, 0, 600, 800);
		
		
		marco.setContentPane(new MenuVista());
		marco.validate();

	}

}
