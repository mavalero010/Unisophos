package sophosuniversity.UniSophos.conexion;

import java.sql.*;

public class Conexion {
	private String nombreBd="UnisophosDB";
	private String usuario="Rector";
	private String password="Rector";
	private String url="jdbc:mysql://localhost:8889/"+nombreBd;

	Connection conn=null;
	//constructor de la clase
	public Conexion(){
		try {
			//obtener el driver
			Class.forName("com.mysql.jdbc.Driver");
			//obtener la conexion
			conn=DriverManager.getConnection(url,usuario,password);
			if (conn!=null) {
				System.out.println("Conexion Exitosa  a la BD: "+nombreBd);
			}else{
				System.out.println("******************NO SE PUDO CONECTAR "+nombreBd);
			}
		}
		catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundException : "+e.getMessage());
		} catch (SQLException e) {
			System.out.println("ocurre una SQLException: "+e.getMessage());
			System.out.println("Verifique que Mysql esté encendido...");
		}
	}
	public Connection getConnection(){
		return conn;
	}
	public void desconectar(){
		conn=null;
	}
}