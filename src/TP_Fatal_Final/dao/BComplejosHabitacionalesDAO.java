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
import TP_Fatal_Final.modelo.BComplejosHabitacionales;
import TP_Fatal_Final.modelo.BTrazadoFormal;

public class BComplejosHabitacionalesDAO {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/bd_fatal_final";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(BComplejosHabitacionales B) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `barrio` (`nombreBarrio`,`superficie`,`Consorcio_Activo`,`Cantidad_Monoblocks`) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2, B.getSuperficie());
			stmt.setBoolean(3, B.isTieneAdministracionActiva());
			stmt.setInt(4, B.getCantidadDeMonoblocks());

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

	public boolean actualizar(BComplejosHabitacionales B, String nombreActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE barrio SET `nombreBarrio` = ?, `superficie` = ?, `Consorcio_Activo` = ?, `Cantidad_Monoblocks` = ? "
					+ "WHERE `nombreBarrio` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2, B.getSuperficie());
			stmt.setBoolean(3, B.isTieneAdministracionActiva());
			stmt.setInt(4, B.getCantidadDeMonoblocks());
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
			String sql = "SELECT nombreBarrio, superficie, Consorcio_Activo, Cantidad_Monoblocks FROM barrio WHERE Cantidad_Monoblocks IS NOT NULL";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				BComplejosHabitacionales unBarrio = new BComplejosHabitacionales();
				
				
				unBarrio.setNombreBarrio(rs.getString("nombreBarrio"));
				unBarrio.setSuperficie(rs.getInt("superficie"));
				unBarrio.setTieneAdministracionActiva(rs.getBoolean("Consorcio_Activo"));
				unBarrio.setCantidadDeMonoblocks(rs.getInt("Cantidad_Monoblocks"));

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
		BComplejosHabitacionales Barrio = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT nombreBarrio, superficie, Consorcio_Activo, Cantidad_Monoblocks FROM barrio WHERE nombreBarrio = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, nombreBarrio);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Barrio = new BComplejosHabitacionales();
				Barrio.setNombreBarrio(rs.getString("nombreBarrio"));
				Barrio.setSuperficie(rs.getInt("superficie"));
				Barrio.setTieneAdministracionActiva(rs.getBoolean("Consorcio_Activo"));
				Barrio.setCantidadDeMonoblocks(rs.getInt("Cantidad_Monoblocks"));
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