package TP_Fatal_Final.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import TP_Fatal_Final.modelo.Comuna;

public class ComunaDAO {

	private final static String URL = "jdbc:mysql://localhost:3306/bd_fatal_final";
	private final static String USR = "root";
	private final static String PSS = "admin";

	public boolean guardar(Comuna comuna) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "INSERT INTO `comuna` (`Numero_Comuna`, `Nombre_Sede`, `Cantidad_Barrios`) "
					+ "VALUES (?, ?, ?);";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, comuna.getNumeroComuna());
			stmt.setString(2, comuna.getNombreDeSede());
			stmt.setInt(3, comuna.getCantidadBarrios());

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
		return filasAfectadas > 0;
	}

	public boolean eliminar(int numeroComuna) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "DELETE FROM comuna WHERE Numero_Comuna = ?;";

			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, numeroComuna);

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
		return filasAfectadas > 0;
	}

	public boolean actualizar(Comuna comunaActualizada, int numeroComunaActual) {
		Connection c = null;
		int filasAfectadas = 0;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "UPDATE comuna SET `Numero_Comuna` = ?, `Nombre_Sede` = ?, `Cantidad_Barrios` = ? "
					+ "WHERE `Numero_Comuna` = ?;";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, comunaActualizada.getNumeroComuna());
			stmt.setString(2, comunaActualizada.getNombreDeSede());
			stmt.setInt(3, comunaActualizada.getCantidadBarrios());
			stmt.setInt(4, numeroComunaActual);

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
		return filasAfectadas > 0;
	}

	public ArrayList<Comuna> traerTodas() {
		ArrayList<Comuna> comunas = new ArrayList<Comuna>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT c.Numero_Comuna, c.Nombre_Sede, COUNT(b.fk_comuna) AS Total_Barrios "
					+ "FROM comuna c LEFT JOIN barrio b ON c.Nombre_Sede = b.fk_comuna "
					+ "GROUP BY c.Numero_Comuna, c.Nombre_Sede";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Comuna unaComuna = new Comuna();
				unaComuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
				unaComuna.setNombreDeSede(rs.getString("Nombre_Sede"));
				unaComuna.setCantidadBarrios(rs.getInt("Total_Barrios"));

				comunas.add(unaComuna);
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
		return comunas;
	}

	public ArrayList<String> traerNombresComunas() {
		ArrayList<String> comunas = new ArrayList<>();
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT Nombre_Sede FROM comuna";
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				comunas.add(rs.getString("Nombre_Sede"));
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
		return comunas;
	}

	public Comuna buscarPorNumeroDeComuna(int numeroComuna) {
		Comuna comuna = null;
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USR, PSS);
			String sql = "SELECT Numero_Comuna, Nombre_Sede, Cantidad_Barrios FROM comuna WHERE Numero_Comuna = ?";
			PreparedStatement stmt = c.prepareStatement(sql);
			stmt.setInt(1, numeroComuna);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				comuna = new Comuna();
				comuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
				comuna.setNombreDeSede(rs.getString("Nombre_Sede"));
				comuna.setCantidadBarrios(rs.getInt("Cantidad_Barrios"));
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

		return comuna;
	}
}