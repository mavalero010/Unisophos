package sophosuniversity.UniSophos.dbservices;
import sophosuniversity.UniSophos.conexion.Conexion;
import sophosuniversity.UniSophos.roles.Curso;
import sophosuniversity.UniSophos.roles.Profesor;
import 	sophosuniversity.UniSophos.roles.Universidad;

import 	sophosuniversity.UniSophos.roles.Estudiante;
import java.sql.*;
import java.util.ArrayList;

public class universidadservicedb {
	public static String crearUniversidad(Universidad universidad) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="INSERT into `Universidad` (`nombre`,`nit`,`ciudad`,`pais`) VALUES (?,?,?,?)";
			p=connection.prepareStatement(query);
			
			p.setString(1, universidad.getNombre());
			p.setInt(2, universidad.getNit());
			p.setString(3, universidad.getCiudad());
			p.setString(4, universidad.getPais());
			p.execute();
			resultado="Registro de Universidad hecho";
			
			return resultado;
		}catch(Exception e){
			return resultado;
		}
	
	}
	
	public static ArrayList<Estudiante> getTodosLosEstudiantes(int nit) {
		
		String query="";
		ResultSet resultado=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Estudiante> estudiantes=new ArrayList<Estudiante>();
		
		try {
			query="SELECT * FROM estudiante where universidad_Nit=? AND activo=TRUE";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id");
				String nombre=resultado.getString("nombre");
				String apellido=resultado.getString("apellido");
				int numero_de_creditos=resultado.getInt("numero_de_creditos");
				int semestre_cursado= resultado.getInt("semestre_cursado");
				String email=resultado.getString("email");
				String direccion=resultado.getString("direccion");
				int telefono=resultado.getInt("telefono");
				boolean activo=resultado.getBoolean("activo");
				
				Estudiante est=new Estudiante(id,nombre,apellido,numero_de_creditos,semestre_cursado,email,direccion,telefono,activo,nit);
				estudiantes.add(est);
			}
			return estudiantes;
		}catch(Exception e){
			return null;
		}
	
	}
	public static ArrayList<Profesor> getTodosLosProfesores(int nit) {
		
		String query="";
		ResultSet resultado=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Profesor> profesores=new ArrayList<Profesor>();
		
		try {
			query="SELECT * FROM profesor where universidad_Nit=? AND activo=TRUE";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id");
				String nombre=resultado.getString("nombre");
				String apellido=resultado.getString("apellido");
				int años_docencia=resultado.getInt("años_docencia");
				String maximo_titulo_academico= resultado.getString("maximo_titulo_academico");
				String email=resultado.getString("email");
				String direccion=resultado.getString("direccion");
				int telefono=resultado.getInt("telefono");
				boolean activo=resultado.getBoolean("activo");
				
				Profesor pro=new Profesor(id,nombre,apellido,años_docencia,maximo_titulo_academico,email,direccion,telefono,activo,nit);
				profesores.add(pro);
			}
			return profesores;
		}catch(Exception e){
			return null;
		}
	
	}
	public static ArrayList<Curso> getTodosLosCursos(int nit) {
		
		String query="";
		String query2="";
		ResultSet resultado=null;
		ResultSet resultado2=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Curso> cursos=new ArrayList<Curso>();
		
		try {
			query="SELECT * FROM curso where universidad_Nit=? AND activo=TRUE";
			
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id");
				query2="SELECT * \n"
						+ "FROM estudiante \n"
						+ "WHERE \n"
						+ "    activo=True  \n"
						+ "    AND CC in (Select id_estudiante \n"
						+ "           FROM curso \n"
						+ "           WHERE universidad_Nit=? \n"
						+ "             AND id=? \n"
						+ "             AND activo=TRUE)";
				PreparedStatement p1=connection.prepareStatement(query2);
				p1.setInt(1, nit);
				p1.setInt(2, id);
				resultado2=p1.executeQuery();
				
				String nombre_materia=resultado.getString("nombre_materia");
				int curso_prerrequisito=resultado.getInt("id_curso_prerrequisito");
				int num_creditos= resultado.getInt("num_creditos");
				int cupos=resultado.getInt("cupos");
				int id_profesor=resultado.getInt("id_profesor");
				int telefono=resultado.getInt("telefono");
				boolean activo=resultado.getBoolean("activo");
				
				Curso cur=new Curso(id,nombre_materia,curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo);
				cursos.add(cur);
			}
			return cursos;
		}catch(Exception e){
			return null;
		}
	
	}
	
	public static String addEstudiante(Estudiante estudiante) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="INSERT into `Universidad` (`id`,`nombre`,`apellido`,`numero_de_creditos`,`semestre_cursado`,`email`,`direccion`,`telefono`,`activo`) VALUES (?,?,?,?,?,?,?,?,?)";
			p=connection.prepareStatement(query);
			
			p.setInt(1, estudiante.getId());
			p.setString(2, estudiante.getNombre());
			p.setString(3, estudiante.getApellido());
			p.setInt(4, estudiante.getNumero_de_creditos());
			p.setInt(5, estudiante.getSemestre_cursado());
			p.setString(6, estudiante.getEmail());
			p.setString(7, estudiante.getDireccion());
			p.setInt(8, estudiante.getTelefono());
			p.setBoolean(9, estudiante.getActivo());
			p.execute();
			resultado="Registro de estudiante hecho";
			
			return resultado;
		}catch(Exception e){
			return resultado;
		}
	
	}
	public static String addProfesor(Profesor profesor) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="INSERT into `Universidad` (`id`,`nombre`,`apellido`,`años_docencia`,`maximo_titulo_academico`,`email`,`direccion`,`telefono`,`activo`,`nit`) VALUES (?,?,?,?,?,?,?,?,?,?)";
			p=connection.prepareStatement(query);
			
			p.setInt(1, profesor.getId());
			p.setString(2, profesor.getNombre());
			p.setString(3, profesor.getApellido());
			p.setInt(4, profesor.getAños_docencia());
			p.setString(5, profesor.getMaximo_titulo_academico());
			p.setString(6, profesor.getEmail());
			p.setString(7, profesor.getDireccion());
			p.setInt(8, profesor.getTelefono());
			p.setBoolean(9, profesor.getActivo());
			p.setInt(10,profesor.getNit());
			p.execute();
			resultado="Registro de Profesor hecho";
			
			return resultado;
		}catch(Exception e){
			return resultado;
		}
	
	}
	public static String addCurso(Curso curso) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;

	
			try {
				int i ;
				int len=curso.getEstudiantes().size();
				if (len!=0) {
					for(i=0;i<=len;i++) {
						query="INSERT into `Universidad` (`id`,`nombre_materia`,`id_curso_prerrequisito`,`num_creditos`,`cupos`,`id_profesor`,`nit`,`activo`,`estudiantes`) VALUES (?,?,?,?,?,?,?,?,?)";
						p=connection.prepareStatement(query);
						
						p.setInt(1, curso.getId());
						p.setString(2, curso.getNombre_Materia());
						p.setInt(3, (curso.getIdPrerequisito()));
						p.setInt(4, curso.getNum_creditos());
						p.setInt(5, curso.getCupos());
						p.setInt(6, curso.getIdProfesor());
						p.setInt(7,curso.getNit());
						p.setBoolean(8,curso.getActivo());
						p.setInt(9,curso.getEstudiantes().get(i).getId());
						
						p.execute();
						resultado="Registro de Curso hecho";
					}
				}else {
					query="INSERT into `Universidad` (`id`,`nombre_materia`,`id_curso_prerrequisito`,`num_creditos`,`cupos`,`id_profesor`,`nit`,`activo`,`estudiantes`) VALUES (?,?,?,?,?,?,?,?,null)";
					p=connection.prepareStatement(query);
					
					p.setInt(1, curso.getId());
					p.setString(2, curso.getNombre_Materia());
					p.setInt(3, (curso.getIdPrerequisito()));
					p.setInt(4, curso.getNum_creditos());
					p.setInt(5, curso.getCupos());
					p.setInt(6, curso.getIdProfesor());
					p.setInt(7,curso.getNit());
					p.setBoolean(8,curso.getActivo());
					p.execute();
					resultado="Registro de Curso hecho";
				}
				
		
				return resultado;
			}catch(Exception e){
				return resultado;
			}
		
	
	}
	
	
	
}
