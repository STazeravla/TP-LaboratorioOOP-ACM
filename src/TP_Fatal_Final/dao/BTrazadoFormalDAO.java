package TP_Fatal_Final.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import TP_Fatal_Final.modelo.BTrazadoFormal;
import TP_Fatal_Final.modelo.Barrio;

public class BTrazadoFormalDAO {

	private final static String URL = "jdbc:mysql://127.0.0.1:3306/bd_fatal_final";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(BTrazadoFormal B, int numeroComuna) {
		Connection c = null; 
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `barrio` (`nombreBarrio`,`superficie`,`Cantidad_EdificiosPh`,`Porcentaje_Asfalto`, `fk_comuna`) "
					+ "VALUES (?,?,?,?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2, B.getSuperficie()); 
			stmt.setInt(3, B.getCantidadDeEdificiosHorizontales()); 
			stmt.setInt(4, B.getPorAsfaltoEnCalles());
			stmt.setInt(5, numeroComuna);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return filasAfectadas > 0;
	}

	public boolean eliminar(String nombreBarrio) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM barrio WHERE nombreBarrio = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return filasAfectadas > 0;
	}

	public boolean actualizar(BTrazadoFormal B, String nombreActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE barrio SET `nombreBarrio` = ?, `superficie` = ?, `Cantidad_EdificiosPh` = ?, `Porcentaje_Asfalto` = ? "
					+ "WHERE `nombreBarrio` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2, B.getSuperficie());
			stmt.setInt(3, B.getCantidadDeEdificiosHorizontales());
			stmt.setInt(4, B.getPorAsfaltoEnCalles());
			stmt.setString(5, nombreActual);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return filasAfectadas > 0;
	}

	public ArrayList<Barrio> traerTodos() {
		ArrayList<Barrio> Barrios = new ArrayList<Barrio>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT nombreBarrio, superficie, Cantidad_EdificiosPh, Porcentaje_Asfalto FROM barrio WHERE Cantidad_EdificiosPh IS NOT NULL";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BTrazadoFormal unBarrio = new BTrazadoFormal();
				
				unBarrio.setNombreBarrio(rs.getString("nombreBarrio")); 
				unBarrio.setSuperficie(rs.getInt("superficie"));
				unBarrio.setCantidadDeEdificiosHorizontales(rs.getInt("Cantidad_EdificiosPh"));
				unBarrio.setPorAsfaltoEnCalles(rs.getInt("Porcentaje_Asfalto"));

				Barrios.add(unBarrio);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return Barrios;
	}

	public Barrio buscarPorNombre(String nombreBarrio) {
		BTrazadoFormal Barrio = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT nombreBarrio, superficie, Cantidad_EdificiosPh, Porcentaje_Asfalto FROM barrio WHERE nombreBarrio = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Barrio = new BTrazadoFormal();
				Barrio.setNombreBarrio(rs.getString("nombreBarrio"));
				Barrio.setSuperficie(rs.getInt("superficie"));
				Barrio.setCantidadDeEdificiosHorizontales(rs.getInt("Cantidad_EdificiosPh"));
				Barrio.setPorAsfaltoEnCalles(rs.getInt("Porcentaje_Asfalto"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
			}
		}
		return Barrio;
	}
}