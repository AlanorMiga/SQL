package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Venta{

	private int id;
	private String nombre;
	private int edad;
	private String especie;
	private String razon;
	
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
		this.razon= razon;
	}

	public String getnombre() {
		return nombre;
	}

	public void setnombre(String nombre) {
		this.nombre = nombre;
	}

	public int getedad() {
		return edad;
	}

	public void setedad(int edad) {
		this.edad = edad;
	}

	public String getespecie() {
		return especie;
	}

	public void setespecie(String especie) {
		this.especie = especie;
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Id: " + id  + "\nnombre: " + nombre + "\nedad: " + edad + "\nespecie: $" + especie;
	}
	
	public static ArrayList<Venta> listaVentas(){
		
		ArrayList<Venta> ventas = new ArrayList<Venta>();
		
		Connection con = MySQLConnection.connect();
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = (Statement) con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String consulta = "SELECT * FROM datospeces";
			rs = st.executeQuery(consulta);
			
			while(rs.next()) {
				ventas.add(new Venta(
					rs.getInt("id"),
					rs.getString("nombre"),
					rs.getString("especie"),
					rs.getInt("edad"),
					rs.getString("razon")

				));
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				con.close();
				st.close();
				rs.close();
			}catch(SQLException ex) {
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
			
			if(rs.next()) {
				venta= new Venta(
					rs.getInt("id_mpeces"),
					rs.getString("nombre"),
					rs.getString("especie"),
					rs.getInt("edad"),
					rs.getString("razon")

				);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				con.close();
				st.close();
				rs.close();
			}catch(SQLException ex) {
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
			String consulta = "INSERT INTO datospeces (nombre, edad, especie) "
				+ " VALUES ('" + venta.getnombre() + "','" + venta.getedad() + "', '" + venta.getespecie() + "');";
			
			int rs = st.executeUpdate(consulta);
			System.out.println("Insertados: " + rs);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				con.close();
				st.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	public static void insertarVenta(Venta venta) {
		Connection con = MySQLConnection.connect();
		PreparedStatement st = null;
		
		try {
			String consulta = "INSERT INTO datospeces (nombre, edad, especie) VALUES (?,?,?)";
			
			st = con.prepareStatement(consulta);
			st.setString(1, venta.getnombre());
			st.setInt(2,  venta.getedad());
			st.setString(3,  venta.getespecie());
			st.execute();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				con.close();
				st.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void actualizar(Venta venta) {
		PreparedStatement st = null;
		
		try (Connection con = MySQLConnection.connect()) {
			String consulta = "UPDATE datospeces SET nombre = ?, edad = ?, especie = ? WHERE id = ?";
			
			st = con.prepareStatement(consulta);
			
			st.setString(1, venta.getnombre());
			st.setInt(2, venta.getedad());
			st.setString(3, venta.getespecie());
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
			String consulta = "DELETE FROM datospeces WHERE id = " + id;
			
			int eliminados = st.executeUpdate(consulta);
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}