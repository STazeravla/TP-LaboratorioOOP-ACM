package TP_LaboratorioOOP_ACM;

import javax.swing.JFrame;

import TP_Fatal_Final.JFrame;
import TP_Fatal_Final.String;
import TP_Fatal_Final.vista.ComunaConsulta;

public class App {

		public static void main(String[] args) {
			JFrame marco = new JFrame();
			marco.setVisible(true);
			marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			marco.setBounds(0, 0, 600, 800);
			
			marco.setContentPane(new ComunaConsulta());
			marco.validate();

		}
}

