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

public class BComplejosHabitacionalesDAO {
	private final static String URL = "jdbc:mysql://127.0.0.1:3306/bd_fatal_final";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(BComplejosHabitacionales B) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `barrio` (`nombreBarrio`,`superficie`,`Consorsio_Activo`,`Cantidad_Monoblocks`) "
					+ "VALUES (?,?,?,?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setString(1, B.getNombreBarrio());
			stmt.setInt(2,B.getSuperficie());
			stmt.setBoolean(3, B.isTieneAdministracionActiva());
			stmt.setInt(4,B.getCantidadDeMonoblocks());

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public boolean eliminar(int numeroBarrio) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM barrio WHERE numeroBarrio = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, numeroBarrio);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public boolean actualizar(BComplejosHabitacionales B, int numeroActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE barrio SET `numeroActual` = ?, `nombreBarrio` = ?, `Superficie` = ?, `Consorsio_Activo` = ?, `Cantidad_Monoblocks` =?"
					+ "WHERE `numeroBarrio` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, B.getNumeroBarrio());
			stmt.setString(2, B.getNombreBarrio());
			stmt.setInt(3, B.getSuperficie());
			stmt.setBoolean(4, B.isTieneAdministracionActiva());
			stmt.setInt(5, B.getCantidadDeMonoblocks());
			stmt.setInt(6, numeroActual);

			filasAfectadas = stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return filasAfectadas == 0 ? false : true;
	}

	public ArrayList<Barrio> traerTodos() {
		ArrayList<Barrio> Barrios = new ArrayList<Barrio>();

		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT numeroBarrio, nombreBarrio, Superficie,Consorsio_Activo, Cantidad_Monoblocks FROM barrio";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				BComplejosHabitacionales unBarrio = new BComplejosHabitacionales();
				unBarrio.setNumeroBarrio(rs.getInt("numeroBarrio"));
				unBarrio.setNombreBarrio(rs.getString("nombreBarrio"));
				unBarrio.isTieneAdministracionActiva();
				unBarrio.setCantidadDeMonoblocks(rs.getInt("Cantidad_Monoblocks"));
				unBarrio.setSuperficie(rs.getInt("superficie"));
				

				Barrios.add(unBarrio);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return Barrios;

	}

	public Barrio buscarPorNumeroBarrio(int numeroBarrio) {
		BComplejosHabitacionales Barrio = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT numeroBarrio, nombreBarrio, Superficie,Consorsio_Activo, Cantidad_Monoblocks FROM Barrios = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, numeroBarrio);
			ResultSet rs = stmt.executeQuery();

			
			if (rs.next()) {

				Barrio = new BComplejosHabitacionales ();
				Barrio.setNumeroBarrio(rs.getInt("numeroBarrio"));
				Barrio.setNombreBarrio(rs.getString("nombreBarrio"));
				Barrio.setSuperficie(rs.getInt("Superficie"));
				Barrio.isTieneAdministracionActiva();
				Barrio.setCantidadDeMonoblocks(rs.getInt("Cantidad_Monoblocks"));
				

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return Barrio;
	}

}