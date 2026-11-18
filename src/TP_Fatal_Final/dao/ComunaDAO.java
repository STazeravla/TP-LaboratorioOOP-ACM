package TP_Fatal_Final.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import TP_Fatal_Final.modelo.Comuna;
import TP_Fatal_Final.modelo.Barrio;

public class ComunaDAO {

		private final static String URL = "jdbc:mysql://localhost:3306/bd_fatal_final";
		private final static String USR = "root";
		private final static String PSS = "admin";

		List<Barrio> obtenerTodos() {
			
			return obtenerTodos();
		}
		
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
				String sql = "UPDATE comuna SET `Numero_Comuna` = ?, `Nombre_Sede` = ?, `Numero_Comuna` = ?, `Cantidad_Barrios` = ?"
						+ "WHERE `Numero_Comuna` = ?;";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, comunaActualizada.getNumeroComuna());
				stmt.setString(2, comunaActualizada.getNombreDeSede());
				stmt.setInt(3, comunaActualizada.getCantidadBarrios());
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
				String sql = "SELECT Numero_Comuna, Nombre_Sede, Cantidad_Barrios FROM comuna";
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {

					Comuna unaComuna= new Comuna();
					unaComuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
					unaComuna.setNombreDeSede(rs.getString("Nombre_Sede"));
					unaComuna.setCantidadBarrios(rs.getInt(obtenerTodos().size()));
					
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
	            String sql = "SELECT Nombre_Sede FROM comuna"; // Ajustá 'nombre' y la tabla a tu esquema real
	            Statement stmt = c.createStatement();
	            ResultSet rs = stmt.executeQuery(sql);

	            while (rs.next()) {
	                comunas.add(rs.getString("nombre")); // Asegurate que coincida con la columna de tu BD
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            if (c != null) {
	                try { c.close(); } catch (SQLException e) { e.printStackTrace(); }
	            }
	        }
	        return comunas;
	    }
		
		
		
		public Comuna buscarPorNumeroDeComuna(int numeroComuna) {
			Comuna comuna= null;
			Connection c = null;
			try {
				c = DriverManager.getConnection(URL, USR, PSS);
				String sql = "SELECT Numero_Comuna, Nombre_Sede, Cantidad_Barrios FROM comuna WHERE Numero_Comuna = ?";
				PreparedStatement stmt = c.prepareStatement(sql);
				stmt.setInt(1, numeroComuna);
				ResultSet rs = stmt.executeQuery();

				// Si lo encuentra, dado que el número de legajo es único, solo tendremos una
				// coincidencia. Por eso utilizo el if en vez del while
				if (rs.next()) {

					comuna = new Comuna();
					comuna.setNumeroComuna(rs.getInt("Numero_Comuna"));
					comuna.setNombreDeSede(rs.getString("Nombre_Sede"));
					//comuna.setCantidadBarrios(rs.getInt(obtenerTodos().size()));
										
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