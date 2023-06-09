package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Venta {

	private int id;
	private String nombre;
	private int edad;
	private String especie;
	private String razon;
	private String usuario;
	private String contrasena;

	public Venta() {

	}

	public Venta(String nombre, int edad, String especie) {
		this.nombre = nombre;
		this.edad = edad;
		this.especie = especie;
	}

	public Venta(int id, String nombre, String especie, int edad, String razon) {
		this.nombre = nombre;
		this.edad = edad;
		this.especie = especie;
		this.id = id;
		this.razon = razon;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getId() {
		return id;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Override
	public String toString() {
		return "Id: " + id + "\nnombre: " + nombre + "\nedad: " + edad + "\nespecie: $" + especie;
	}

	public static ArrayList<Venta> listaVentas() {

		ArrayList<Venta> ventas = new ArrayList<Venta>();

		Connection con = MySQLConnection.connect();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT * FROM datospeces";
			rs = st.executeQuery(consulta);

			while (rs.next()) {
				ventas.add(new Venta(rs.getInt("id_mpeces"), rs.getString("nombre"), rs.getString("especie"),
						rs.getInt("edad"), rs.getString("razon")

				));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return ventas;
	}

	public static Venta verVenta(int id) {
		Venta venta = null;

		Connection con = MySQLConnection.connect();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT * FROM datospeces WHERE id_mpeces = " + id;
			rs = st.executeQuery(consulta);

			if (rs.next()) {
				venta = new Venta(rs.getInt("id_mpeces"), rs.getString("nombre"), rs.getString("especie"),
						rs.getInt("edad"), rs.getString("razon")

				);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return venta;
	}

	public static void insertar(Venta venta) {

		Connection con = MySQLConnection.connect();
		Statement st = null;

		try {
			st = (Statement) con.createStatement();
			String consulta = "INSERT INTO datospeces (nombre, edad, especie) " + " VALUES ('" + venta.getNombre()
					+ "','" + venta.getEdad() + "', '" + venta.getEspecie() + "');";

			int rs = st.executeUpdate(consulta);
			System.out.println("Insertados: " + rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void insertarVenta(Venta venta) {
		Connection con = MySQLConnection.connect();
		PreparedStatement st = null;

		try {
			String consulta = "INSERT INTO datospeces (nombre, especie, edad, razon) VALUES (?,?,?,?)";

			st = con.prepareStatement(consulta);
			st.setString(1, venta.getNombre());
			st.setString(2, venta.getEspecie());
			st.setInt(3, venta.getEdad());
			st.setString(4, venta.getRazon());
			st.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void actualizar(Venta venta) {
		PreparedStatement st = null;

		try (Connection con = MySQLConnection.connect()) {
			String consulta = "UPDATE datospeces SET nombre = ?, edad = ?, especie = ? WHERE id = ?";

			st = con.prepareStatement(consulta);

			st.setString(1, venta.getNombre());
			st.setInt(2, venta.getEdad());
			st.setString(3, venta.getEspecie());
			st.setInt(4, venta.getId());

			st.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void eliminar(int id) {

		Connection con = MySQLConnection.connect();
		Statement st = null;

		try {
			st = (Statement) con.createStatement();
			String consulta = "DELETE FROM datospeces WHERE id_mpeces = " + id;

			int eliminados = st.executeUpdate(consulta);
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static boolean verificarUsuario(String usuario, String contrasena) {

		boolean usuarioValido = false;
		Connection con = MySQLConnection.connect();
		Statement st = null;
		ResultSet rs = null;

		try {
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT COUNT(*) AS total FROM usuarios WHERE usuario = '" + usuario
					+ "' AND contrasena = '" + contrasena + "'";
			rs = st.executeQuery(consulta);

			if (rs.next()) {
				int total = rs.getInt("total");
				usuarioValido = total > 0;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				st.close();
				rs.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return usuarioValido;
	}
}