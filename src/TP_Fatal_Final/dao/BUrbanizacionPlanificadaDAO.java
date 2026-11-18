package TP_Fatal_Final.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import TP_Fatal_Final.modelo.Barrio;
import TP_Fatal_Final.modelo.BUrbanizacionPlanificada;

public class BUrbanizacionPlanificadaDAO {

    private final static String URL = "jdbc:mysql://127.0.0.1:3306/bd_fatal_final";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(BUrbanizacionPlanificada B) {
		Connection c = null; 
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS); 
			String sql = "INSERT INTO `barrio` (`nombreBarrio`,`superficie`,`Conexion_Agua`,`Ley_Integracion`) "
					+ "VALUES (?,?,?,?)";
			
				PreparedStatement stmt = c.prepareStatement(sql); 
				stmt.setString(1, B.getNombreBarrio());
				stmt.setInt(2, B.getSuperficie());
				stmt.setBoolean(3, B.isTieneAguaPotable());
				stmt.setInt(4, B.getNumeroDeLey());
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

	public boolean actualizar(BUrbanizacionPlanificada B, String nombreActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE barrio SET `nombreBarrio` = ?, `superficie` = ?, `Conexion_Agua` = ?, `Ley_Integracion` = ? "
					+ "WHERE `nombreBarrio` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2, B.getSuperficie());
			stmt.setBoolean(3, B.isTieneAguaPotable());
			stmt.setInt(4, B.getNumeroDeLey());
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
			String sql = "SELECT nombreBarrio, superficie, Conexion_Agua, Ley_Integracion FROM barrio WHERE Ley_Integracion IS NOT NULL";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BUrbanizacionPlanificada unBarrio = new BUrbanizacionPlanificada();
				
					unBarrio.setNombreBarrio(rs.getString("nombreBarrio"));
					unBarrio.setSuperficie(rs.getInt("superficie"));
					unBarrio.setTieneAguaPotable(rs.getBoolean("Conexion_Agua"));
					unBarrio.setNumeroDeLey(rs.getInt("Ley_Integracion"));

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
		BUrbanizacionPlanificada Barrio = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT nombreBarrio, superficie, Conexion_Agua, Ley_Integracion FROM barrio WHERE nombreBarrio = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Barrio = new BUrbanizacionPlanificada();
				Barrio.setNombreBarrio(rs.getString("nombreBarrio"));
				Barrio.setSuperficie(rs.getInt("superficie"));
				Barrio.setTieneAguaPotable(rs.getBoolean("Conexion_Agua"));
				Barrio.setNumeroDeLey(rs.getInt("Ley_Integracion"));
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