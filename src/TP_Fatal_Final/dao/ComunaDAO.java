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
				String sql = "INSERT INTO `comuna` (`Numero_Comuna`, `Nombre_Sede`, `Junta_Responsable`) "
						+ "VALUES (?, ?, ?);";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, comuna.getNumeroComuna());
				stmt.setString(2, comuna.getNombreDeSede());
				stmt.setString(3, comuna.getJuntaComunal());

				filasAfectadas = stmt.executeUpdate();

			} catch (SQLIntegrityConstraintViolationException e) {
				// Esta excepción significa que un usuario con ese legajo ya existe.
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
			return filasAfectadas == 0 ? false : true;
		}

		public boolean actualizar(Comuna comunaActualizada, int numeroComunaActual) {
			Connection c = null;
			int filasAfectadas = 0;
			try {
				c = DriverManager.getConnection(URL, USR, PSS);
				String sql = "UPDATE comuna SET `Numero_Comuna` = ?, `Nombre_Sede` = ?, `Junta_Responsable` = ?"
						+ "WHERE `Numero_Comuna` = ?;";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, comunaActualizada.getNumeroComuna());
				stmt.setString(2, comunaActualizada.getNombreDeSede());
				stmt.setString(3, comunaActualizada.getJuntaComunal());
				stmt.setInt(4, numeroComunaActual);

				filasAfectadas = stmt.executeUpdate();

			} catch (SQLIntegrityConstraintViolationException e) {
				// Esta excepción significa que un usuario con ese legajo ya existe.
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

		public ArrayList<Comuna> traerTodas() {
			ArrayList<Comuna> comunas = new ArrayList<Comuna>();

			Connection c = null;
			try {
				c = DriverManager.getConnection(URL, USR, PSS);
				String sql = "SELECT Numero_Comuna, Nombre_Sede, Junta_Responsable FROM comuna";
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {

					Comuna unaComuna= new Comuna();
					unaComuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
					unaComuna.setNombreDeSede(rs.getString("Nombre_Sede"));
					unaComuna.setJuntaComunal(rs.getString("Junta_Responsable"));
					
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

		public Comuna buscarPorNumeroDeComuna(int numeroComuna) {
			Comuna comuna= null;
			Connection c = null;
			try {
				c = DriverManager.getConnection(URL, USR, PSS);
				String sql = "SELECT Numero_Comuna, Nombre_Sede, Junta_Responsable FROM comuna WHERE Numero_Comuna = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, numeroComuna);
				ResultSet rs = stmt.executeQuery();

				// Si lo encuentra, dado que el número de legajo es único, solo tendremos una
				// coincidencia. Por eso utilizo el if en vez del while
				if (rs.next()) {

					comuna = new Comuna();
					comuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
					comuna.setNombreDeSede(rs.getString("Nombre_Sede"));
					comuna.setJuntaComunal(rs.getString("Junta_Responsable"));
										
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
