package sophosuniversity.UniSophos.dbservices;
import sophosuniversity.UniSophos.conexion.Conexion;
import sophosuniversity.UniSophos.roles.Curso;
import sophosuniversity.UniSophos.roles.Profesor;
import 	sophosuniversity.UniSophos.roles.Universidad;
import 	sophosuniversity.UniSophos.roles.Curso_Profesor_Estudiante;

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
			con.desconectar();
			return resultado;
		}catch(Exception e){
			return resultado;
		}
	
	}
	

	public static Universidad obtenerUniversidad(int nit) {
		String query="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();

		ResultSet r= null;
		PreparedStatement p=null;
		try {
			query="SELECT * from Universidad WHERE Universidad.nit=?";
			p=connection.prepareStatement(query);
			p.setInt(1,nit);
			
			r=p.executeQuery();
			if(r.next()) {
				String nombre=r.getString("nombre");
				String ciudad=r.getString("ciudad");
				String pais=r.getString("pais");
				Universidad u=new Universidad(nombre,nit,ciudad,pais);
				return u;
			}
			return null;
		}catch(Exception e){
			return null;
		}
	}
	public static Boolean findUniversidad(int nit) {
		String query="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();

		ResultSet r= null;
		PreparedStatement p=null;
		try {
			query="SELECT * from Universidad WHERE Universidad.nit=?";
			p=connection.prepareStatement(query);
			p.setInt(1,nit);
			
			r=p.executeQuery();
			if(r.next()) {
				return true;
			}
			return false;
		}catch(Exception e){
			return false;
		}
	}
	
	public static ArrayList<Estudiante> getTodosLosEstudiantes(int nit) {
		
		String query="";
		String query2="";
		String query3="";
		String query4="";
		ResultSet resultado=null;
		ResultSet r1=null;
		ResultSet r2=null;
		ResultSet r3=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		PreparedStatement p3=null;
		PreparedStatement p4=null;
		
		ArrayList<Estudiante> estudiantes=new ArrayList<Estudiante>();
		
		try {
			query="SELECT * FROM Estudiante where nit=? AND activo=TRUE";
			query2="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.aprobado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			query3="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.cursado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			query4="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.matriculado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id_estudiante");
				String nombre=resultado.getString("nombre");
				String apellido=resultado.getString("apellido");
				int numero_de_creditos=resultado.getInt("numero_de_creditos");
				int semestre_cursado= resultado.getInt("semestre_cursado");
				String email=resultado.getString("email");
				String direccion=resultado.getString("direccion");
				String telefono=resultado.getString("telefono");
				boolean activo=resultado.getBoolean("activo");
				String facultad=resultado.getString("facultad");
			
				Estudiante est=new Estudiante(id,nombre,apellido,numero_de_creditos,semestre_cursado,email,direccion,telefono,activo,nit,facultad);
				 p2=connection.prepareStatement(query2);
				 p2.setInt(1, est.getId());
				 r1=p2.executeQuery();
			   while(r1.next()) {
				 System.out.println("Nombre materia: "+p2);
				 est.setCursos_aprobados(r1.getString("nombre_materia"));
			 }
				 p3=connection.prepareStatement(query3);
				 p3.setInt(1, est.getId());
				 r2=p3.executeQuery();
			   while(r2.next()) {
				 System.out.println("Nombre materia: "+p3);
				 est.setCursos_dados(r2.getString("nombre_materia"));
			 }
			    p4=connection.prepareStatement(query4);
			    p4.setInt(1, est.getId());
			    r3=p4.executeQuery();
			   while(r3.next()) {
					 System.out.println("Nombre materia: "+p3);
					 est.setCursos_matriculados(r3.getString("nombre_materia"));
				 }
				estudiantes.add(est);
			}
			con.desconectar();
			return estudiantes;
		}catch(Exception e){
			System.out.print("Exception"+e);
			return null;
		}
	
	}
	public static ArrayList<Profesor> getTodosLosProfesores(int nit) {
		
		String query="";
		String query2="";
		ResultSet resultado=null;
		ResultSet r1=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		ArrayList<Profesor> profesores=new ArrayList<Profesor>();
		System.out.print("id: ");
		try {
			query="SELECT * FROM `Profesor` WHERE nit=? AND activo=TRUE";
			query2="SELECT DISTINCT c.nombre_materia FROM Curso_Profesor_Estudiante as cu, Profesor as p, Curso as c WHERE p.id_profesor=cu.id_profesor AND p.id_profesor=c.id_profesor AND p.id_profesor=? AND p.activo=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id_profesor");
				System.out.print(id);
				String nombre=resultado.getString("nombre");
				String apellido=resultado.getString("apellido");
				int años_docencia=resultado.getInt("años_docencia");
				String maximo_titulo_academico= resultado.getString("maximo_titulo_academico");
				String email=resultado.getString("email");
				String direccion=resultado.getString("direccion");
				String telefono=resultado.getString("telefono");
				boolean activo=resultado.getBoolean("activo");
				System.out.print("id: "+id);
				System.out.print("nombre: "+nombre);
				System.out.print("apellido: "+apellido);
				System.out.print("años_docencia: "+años_docencia);
				System.out.print("maximo_titulo_academico: "+maximo_titulo_academico);
				System.out.print("email: "+email);
				System.out.print("direccion: "+direccion);
				System.out.print(telefono);
				Profesor pro=new Profesor(id,nombre,apellido,años_docencia,maximo_titulo_academico,email,direccion,telefono,activo,nit);
				 
				 p2=connection.prepareStatement(query2);
				 p2.setInt(1, pro.getId());
				 p2.setBoolean(2, true);
				 p2.execute();
				 r1=p2.executeQuery();
			   while(r1.next()) {
				 System.out.println("Nombre materia: "+p2);
				 pro.setCursos_que_dicta(r1.getString("nombre_materia"));
			 }
				
				profesores.add(pro);
				
			}
			con.desconectar();
			return profesores;
		}catch(Exception e){
			System.out.print("Exception: "+e);
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
			query="SELECT * FROM Curso where nit=?";
			
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id_curso");
				String nombre_materia=resultado.getString("nombre_materia");
				int curso_prerrequisito=resultado.getInt("id_curso_prerrequisito");
				int num_creditos= resultado.getInt("num_creditos");
				int cupos=resultado.getInt("cupos");
				int id_profesor=resultado.getInt("id_profesor");
				boolean activo=resultado.getBoolean("activo");
				int matriculados=resultado.getInt("matriculados");
				int no_curso=resultado.getInt("no_curso");
				Curso cur=new Curso(id,nombre_materia,curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo,no_curso);
				cur.setMatriculados(matriculados);
				cursos.add(cur);
			}
			con.desconectar();
			return cursos;
		}catch(Exception e){
			System.out.print("Exception "+e);
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
			query="INSERT into `Estudiante` (`id_estudiante`,`nombre`,`apellido`,`numero_de_creditos`,`semestre_cursado`,`email`,`direccion`,`telefono`,`activo`,`nit`,`facultad`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			p=connection.prepareStatement(query);
			
			p.setInt(1, estudiante.getId());
			p.setString(2, estudiante.getNombre());
			p.setString(3, estudiante.getApellido());
			p.setInt(4, estudiante.getNumero_de_creditos());
			p.setInt(5, estudiante.getSemestre_cursado());
			p.setString(6, estudiante.getEmail());
			p.setString(7, estudiante.getDireccion());
			p.setString(8, estudiante.getTelefono());
			p.setBoolean(9, estudiante.getActivo());
			p.setInt(10, estudiante.getNit());
			p.setString(11, estudiante.getFacultad());
			p.execute();
			resultado="Registro de estudiante hecho";
			
			return resultado;
		}catch(Exception e){
			System.out.println("Exception"+e);
			System.out.println("Ingrese campos correctos");
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
			query="INSERT into `Profesor` (`id_profesor`,`nombre`,`apellido`,`años_docencia`,`maximo_titulo_academico`,`email`,`direccion`,`telefono`,`activo`,`nit`) VALUES (?,?,?,?,?,?,?,?,?,?)";
			p=connection.prepareStatement(query);
			
			p.setInt(1, profesor.getId());
			p.setString(2, profesor.getNombre());
			p.setString(3, profesor.getApellido());
			p.setInt(4, profesor.getAños_docencia());
			p.setString(5, profesor.getMaximo_titulo_academico());
			p.setString(6, profesor.getEmail());
			p.setString(7, profesor.getDireccion());
			p.setString(8, profesor.getTelefono());
			p.setBoolean(9, profesor.getActivo());
			p.setInt(10,profesor.getNit());
			p.execute();
			resultado="Registro de Profesor hecho";
			con.desconectar();
			return resultado;
		}catch(Exception e){
			System.out.print("error: "+e);
			return resultado;
		}
	
	}
	public static String addCurso(Curso curso) {
		String query="";
		String query2="";
		String resultado="Resgistro no hecho";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;

	
			try {
			
					query="INSERT into `Curso` (`id_curso`,`nombre_materia`,`id_curso_prerrequisito`,`num_creditos`,`cupos`,`id_profesor`,`nit`,`activo`,`matriculados`,`no_curso`) VALUES (?,?,?,?,?,?,?,?,?,?)";
					
					p=connection.prepareStatement(query);
					
					p.setInt(1, curso.getId());
					System.out.print("ID: "+curso.getId());
					p.setString(2, curso.getNombre_Materia());
					p.setInt(3, (curso.getIdPrerequisito()));
					p.setInt(4, curso.getNum_creditos());
					p.setInt(5, curso.getCupos());
					p.setInt(6, curso.getIdProfesor());
					p.setInt(7,curso.getNit());
					p.setBoolean(8,curso.getActivo());
					p.setInt(9,0);
					p.setInt(10,curso.getNo_curso());
					p.execute();
					resultado="Registro de Curso hecho";
					
					
					
					//Introducir a la tabla
					query="INSERT into `Curso_Profesor_Estudiante` (`id_curso`,`id_profesor`,`id_estudiante`,`nit`,`aprobado`,`cursado`,`matriculado`) VALUES (?,?,?,?,?,?,?)";
					p=connection.prepareStatement(query);
					p.setInt(1, curso.getId());
					p.setInt(2, curso.getIdProfesor());
					p.setInt(3, -1);
					p.setInt(4, curso.getNit());
					p.setBoolean(5, false);
					p.setBoolean(6, false);
					p.setBoolean(7, false);
					p.execute();
					System.out.println("Esto es "+p);
					System.out.print("NIT: "+curso.getNit()+" ID CURSO: "+curso.getNo_curso()+" Matrícula exitosa");
				
					
					con.desconectar();
				con.desconectar();
				return resultado;
			}catch(Exception e){
				System.out.print("Exception: "+e);
				return resultado;
			}
		
	
	}
	
	public static String desactivarProfesor(int nit, int id_profesor) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE  `Profesor` SET activo=? where nit=? AND id_profesor=?";
			p=connection.prepareStatement(query);
			
			p.setBoolean(1,false);
			p.setInt(2, nit);
			p.setInt(3, id_profesor);
			
			p.execute();
			resultado="Registro de Profesor "+id_profesor+" desactivado";
			con.desconectar();
			return resultado;
		}catch(SQLException e){
			return "Error desactivando Profesor "+id_profesor;
		}
	}
	
	public static String desactivarCurso(int nit, int id_Curso) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE  `Curso` SET activo=false where nit=? AND no_curso=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, id_Curso);
			
			p.execute();
			resultado="Registro de Curso "+id_Curso+" desactivado";
			con.desconectar();
			return resultado;
		}catch(SQLException e){
			return "Error desactivando Curso "+id_Curso;
		}
	}
	public static String desactivarEstudiante(int nit, int id_Estudiante) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE  `Estudiante` SET activo=false where nit=? AND id_estudiante=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, id_Estudiante);
			
			p.execute();
			resultado="Registro de Estudiante "+id_Estudiante+" desactivado";
			con.desconectar();
			return resultado;
		}catch(SQLException e){
			return "Error desactivando Estudiante "+id_Estudiante;
		}
	}
	
	public static Profesor encontrarProfesor(int nit, int id_profesor) {

		String query="";
		String query2;
		String resultado="";
		Conexion con =new Conexion();
		ResultSet r=null;
		ResultSet r1=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		System.out.print("1");
		try {
			query="Select * FROM `Profesor` where nit=? AND id_profesor=? AND activo = TRUE";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, id_profesor);
			r=p.executeQuery();
			System.out.println(r.next());
			int id=r.getInt("id_profesor");
			 String nombre=r.getString("nombre");
			 String apellido=r.getString("apellido");
			 int años_docencia=r.getInt("años_docencia");
			 String maximo_titulo_academico=r.getString("maximo_titulo_academico");
			 String email=r.getString("email");
			 String direccion=r.getString("direccion");
			 String telefono=r.getString("telefono");
			 Boolean activo=r.getBoolean("activo");
			 
			 p.execute();
			 Profesor prof=new Profesor(id,nombre,apellido,años_docencia,maximo_titulo_academico,email,direccion,telefono,activo,nit);
			
			 query2="SELECT DISTINCT c.nombre_materia FROM Curso_Profesor_Estudiante as cu, Profesor as p, Curso as c WHERE p.id_profesor=cu.id_profesor AND p.id_profesor=c.id_profesor AND p.id_profesor=? AND p.activo=?";
			 p2=connection.prepareStatement(query2);
			 p2.setInt(1, id_profesor);
			 p2.setBoolean(2, true);
			 p2.execute();
			 r1=p2.executeQuery();
		   while(r1.next()) {
			 System.out.println("Nombre materia: "+p2);
			 prof.setCursos_que_dicta(r1.getString("nombre_materia"));
		 }
		 
			 con.desconectar();
			return prof;
		}catch(SQLException e){
			System.out.print("Error encontrando Profesor "+id_profesor+"\nException: "+e);
			return null;
		}
	}
	
	public static Estudiante encontrarEstudiante(int nit, int id_Estudiante) {

		String query="";
		String query2="";
		String query3="";
		String query4="";
		String resultado="";
		Conexion con =new Conexion();
		ResultSet r=null;
		ResultSet r1=null;
		ResultSet r2=null;
		ResultSet r3=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		PreparedStatement p3=null;
		PreparedStatement p4=null;
		
		try {
			query="Select * FROM `Estudiante` where nit=? AND id_estudiante=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, id_Estudiante);
			r=p.executeQuery();
				
			System.out.print(r.next());
			 int id=r.getInt("id_estudiante");
			 String nombre=r.getString("nombre");
			 String apellido=r.getString("apellido");
			 int numero_de_creditos=r.getInt("numero_de_creditos");
			 int semestre_cursado=r.getInt("semestre_cursado");
			 String email=r.getString("email");
			 String direccion=r.getString("direccion");
			 String telefono=r.getString("telefono");
			 Boolean activo=r.getBoolean("activo");
			 String facultad=r.getString("facultad");
			 Estudiante est=new Estudiante(id,nombre,apellido,numero_de_creditos,semestre_cursado,email,direccion,telefono,activo,nit,facultad);
			 query2="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.aprobado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p2=connection.prepareStatement(query2);
			 p2.setInt(1, id_Estudiante);
			 p2.execute();
			 r1=p2.executeQuery();
			 while(r1.next()) {
			 System.out.println("Nombre materia: "+p2);
			 est.setCursos_aprobados(r1.getString("nombre_materia"));
		 }
		 
			 query3="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.cursado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p3=connection.prepareStatement(query3);
			 p3.setInt(1, id_Estudiante);
			 p3.execute();
			 r2=p3.executeQuery();
			 while(r2.next()) {
			 System.out.println("Nombre materia: "+p3);
			 est.setCursos_dados(r2.getString("nombre_materia"));
		 }	
			 query4="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.matriculado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p4=connection.prepareStatement(query4);
			 p4.setInt(1, id_Estudiante);
			 p4.execute();
			 r3=p4.executeQuery();
			 while(r3.next()) {
			 System.out.println("Nombre materia: "+p4);
			 est.setCursos_matriculados(r3.getString("nombre_materia"));
		 }
			 con.desconectar();
			return est;
		}catch(SQLException e){
			System.out.print("Error encontrando Estudiante "+id_Estudiante);
			return null;
		}
	}
	public static Curso encontrarCurso(int nit, int no_Curso) {

		String query="";
		String resultado="";
		Conexion con =new Conexion();
		ResultSet r=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="Select * FROM `Curso` where nit=? AND no_curso=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, no_Curso);
			r=p.executeQuery();
			System.out.print("R: "+r.next());
			 int id=r.getInt("id_curso");
			 String nombre_materia=r.getString("nombre_materia");
			 int id_curso_prerrequisito=r.getInt("id_curso_prerrequisito");
			 int num_creditos=r.getInt("num_creditos");
			 int cupos=r.getInt("cupos");
			 int id_profesor=r.getInt("id_profesor");
			 Boolean activo=r.getBoolean("activo");
			 int no_curso=r.getInt("no_curso");
			 p.execute();
			 Curso cur=new Curso(id,nombre_materia,id_curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo,no_curso);
			con.desconectar();
			return cur;
		}catch(SQLException e){
			System.out.print("Error encontrando Curso "+no_Curso);
			return null;
		}
	}	
	
	
	public static String activarProfesor(int nit, int id_profesor) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE  `Profesor` SET activo=? where nit=? AND id_profesor=?";
			p=connection.prepareStatement(query);
			
			p.setBoolean(1,true);
			p.setInt(2, nit);
			p.setInt(3, id_profesor);
			
			p.execute();
			resultado="Registro de Profesor "+id_profesor+" activado";
			con.desconectar();
			return resultado;
		}catch(SQLException e){
			return "Error activando Profesor "+id_profesor;
		}
	}
	public static String activarEstudiante(int nit, int id_estudiante) {

		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE  `Estudiante` SET activo=? where nit=? AND id_estudiante=?";
			p=connection.prepareStatement(query);
			
			p.setBoolean(1,true);
			p.setInt(2, nit);
			p.setInt(3, id_estudiante);
			
			p.execute();
			resultado="Registro de Estudiante "+id_estudiante+" activado";
			con.desconectar();
			return resultado;
		}catch(SQLException e){
			return "Error activando Estudiante "+id_estudiante;
		}
		
		
	}
	
	public static ArrayList<Curso> filtrar_cursos_por_cupos(int nit,int id_curso) {
		
		String query="";
		String query2="";
		ResultSet resultado=null;
		ResultSet resultado2=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Curso> cursos=new ArrayList<Curso>();
		
		try {
			query="SELECT * FROM Curso as C where nit=? AND C.activo=TRUE AND C.cupos>0 AND C.id_curso=?";
			
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setInt(2, id_curso);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id_curso");
				String nombre_materia=resultado.getString("nombre_materia");
				int curso_prerrequisito=resultado.getInt("id_curso_prerrequisito");
				int num_creditos= resultado.getInt("num_creditos");
				int cupos=resultado.getInt("cupos");
				int id_profesor=resultado.getInt("id_profesor");
				boolean activo=resultado.getBoolean("activo");
				int matriculados=resultado.getInt("matriculados");
				int no_curso=resultado.getInt("no_curso");
				Curso cur=new Curso(id,nombre_materia,curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo,no_curso);
				cur.setMatriculados(matriculados);
				cursos.add(cur);
			}
			con.desconectar();
			return cursos;
		}catch(Exception e){
			System.out.print("Exception :"+e);
			return null;
		}
	
	}

	
	public static Profesor buscar_profesor_por_nombre(int nit, String nombre_profesor,String apellido_profesor) {
		String [] nombres=nombre_profesor.split(" ");
		String [] apellidos=apellido_profesor.split(" ");
		String query="";
		String query2="";
		String resultado="";
		Conexion con =new Conexion();
		ResultSet r=null;
		ResultSet r1=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		System.out.print("1");
		try {
			query="Select * FROM `Profesor` where nit=? AND nombre=? AND apellido=? AND activo = TRUE";
			
			p=connection.prepareStatement(query);
			
			p.setInt(1, nit);
			
			p.setString(2, nombre_profesor);
			p.setString(3, apellido_profesor);
		
			r=p.executeQuery();
		
			System.out.print(r.next());
			int id=r.getInt("id_profesor");
		
			 String nombre=r.getString("nombre");
			 String apellido=r.getString("apellido");
			 int años_docencia=r.getInt("años_docencia");
			 String maximo_titulo_academico=r.getString("maximo_titulo_academico");
			 String email=r.getString("email");
			 String direccion=r.getString("direccion");
			 String telefono=r.getString("telefono");
			 Boolean activo=r.getBoolean("activo");
			 p.execute();
			Profesor prof=new Profesor(id,nombre,apellido,años_docencia,maximo_titulo_academico,email,direccion,telefono,activo,nit);
			
			 query2="SELECT DISTINCT c.nombre_materia FROM Curso_Profesor_Estudiante as cu, Profesor as p, Curso as c WHERE p.id_profesor=cu.id_profesor AND p.id_profesor=c.id_profesor AND p.id_profesor=? AND p.activo=?";
			 p2=connection.prepareStatement(query2);
			 p2.setInt(1, id);
			 p2.setBoolean(2, true);
			 p2.execute();
			 r1=p2.executeQuery();
		   while(r1.next()) {
			 System.out.println("Nombre materia: "+p2);
			 prof.setCursos_que_dicta(r1.getString("nombre_materia"));
		 }
			con.desconectar();
			
			return prof;
		}catch(SQLException e){
			System.out.print("Error encontrando Profesor "+nombre_profesor+"\nException "+e);
			return null;
		}
	}

	public static Estudiante buscar_estudiante_por_nombre(int nit, String nombre_est,String apellido_est) {
	
		String query="";
		String query2="";
		String resultado="";
		String query3="";
		String query4="";
		Conexion con =new Conexion();
		ResultSet r=null;
		ResultSet r1=null;
		ResultSet r2=null;
		ResultSet r3=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p2=null;
		PreparedStatement p3=null;
		PreparedStatement p4=null;
		System.out.print("1");
		try {
			query="Select * FROM `Estudiante` where nit=? AND nombre=? AND apellido=? AND activo = TRUE";
			System.out.print("2");
			p=connection.prepareStatement(query);
			System.out.print("3");
			p.setInt(1, nit);
			System.out.print("4");
			p.setString(2, nombre_est);
			p.setString(3, apellido_est);
			System.out.print("5");
			r=p.executeQuery();
			System.out.print("6\n");
			System.out.print(r.next());
			 int id=r.getInt("id_estudiante");
			 String nombre=r.getString("nombre");
			 String apellido=r.getString("apellido");
			 int numero_de_creditos=r.getInt("numero_de_creditos");
			 int semestre_cursado=r.getInt("semestre_cursado");
			 String email=r.getString("email");
			 String direccion=r.getString("direccion");
			 String telefono=r.getString("telefono");
			 Boolean activo=r.getBoolean("activo");
			 String facultad=r.getString("facultad");
			 Estudiante est=new Estudiante(id,nombre,apellido,numero_de_creditos,semestre_cursado,email,direccion,telefono,activo,nit,facultad);
			 query2="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.aprobado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p2=connection.prepareStatement(query2);
			 p2.setInt(1, id);
			 p2.execute();
			 r1=p2.executeQuery();
			 while(r1.next()) {
			 System.out.println("Nombre materia: "+p2);
			 est.setCursos_aprobados(r1.getString("nombre_materia"));
		 }
		 
			 query3="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.cursado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p3=connection.prepareStatement(query3);
			 p3.setInt(1, id);
			 p3.execute();
			 r2=p3.executeQuery();
			 while(r2.next()) {
			 System.out.println("Nombre materia: "+p3);
			 est.setCursos_dados(r2.getString("nombre_materia"));
		 }
			 query4="SELECT T.nombre_materia FROM (SELECT DISTINCT C.nombre_materia,CU.id_estudiante FROM Curso as C, Curso_Profesor_Estudiante as CU WHERE CU.id_curso = C.id_curso AND CU.matriculado=TRUE) as T, Estudiante E WHERE E.id_estudiante=T.id_estudiante AND E.id_estudiante=?";
			 p4=connection.prepareStatement(query4);
			 p4.setInt(1, id);
			 p4.execute();
			 r3=p4.executeQuery();
			 while(r3.next()) {
			 System.out.println("Nombre materia: "+p4);
			 est.setCursos_matriculados(r3.getString("nombre_materia"));
		 } 
			 con.desconectar();
			;
			 System.out.print("16");
			
			con.desconectar();
			System.out.print("18");
			return est;
		}catch(SQLException e){
			System.out.print("Error encontrando Estudiante "+nombre_est+"\nException "+e);
			return null;
		}
	}

	public static Curso buscar_curso_por_nombre(int nit, String nombre_Curso) {

		String query="";
		String resultado="";
		Conexion con =new Conexion();
		ResultSet r=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="Select * FROM `Curso` where nit=? AND nombre_materia=?";
			p=connection.prepareStatement(query);
			p.setInt(1, nit);
			p.setString(2, nombre_Curso);
			r=p.executeQuery();
			 System.out.println("R: "+r.next());
			 int id=r.getInt("id_curso");
			 String nombre_materia=r.getString("nombre_materia");
			 int id_curso_prerrequisito=r.getInt("id_curso_prerrequisito");
			 int num_creditos=r.getInt("num_creditos");
			 int cupos=r.getInt("cupos");
			 int id_profesor=r.getInt("id_profesor");
			 Boolean activo=r.getBoolean("activo");
			 int matriculados=r.getInt("matriculados");
			 int no_curso=r.getInt("no_curso");
			 System.out.println(no_curso);
			 Curso cur=new Curso(id,nombre_materia,id_curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo,no_curso);
			 cur.setMatriculados(matriculados);
			
			
			 con.desconectar();
			return cur;
		}catch(SQLException e){
			System.out.print("Error encontrando Curso "+nombre_Curso+" \nException"+e);
			return null;
		}
	
}

	public static boolean matricula(int nit,int no_curso, int id_profesor, int id_estudiante) {
		
		String query0="";
		String query="";
		String query1="";
		String query2="";
		String resultado="";
		int id_curso;
		int id_est=0;
		int num_creditos_curso;
		int num_creditos_est;
		int matriculados;
		int cupos;
		Conexion con =new Conexion();
		ResultSet r=null;
		ResultSet re=null;
		ResultSet re1=null;
		ResultSet re0=null;
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		PreparedStatement p1=null;
		PreparedStatement p0=null;
		try {
			
			//Valido que tanto el curso como el estudiante tengan cupos y creditos disponibles 
			//y que el curso no haya sido aprobado anteriormente.
			query0="SELECT * FROM `Curso_Profesor_Estudiante` WHERE id_curso=? AND id_profesor=? AND id_estudiante=? AND (aprobado=TRUE OR matriculado=TRUE)";
			query1="Select id_curso, num_creditos, cupos, matriculados, Estudiante.numero_de_creditos FROM Curso as c,Estudiante Where no_curso=? AND cupos>=0 AND c.activo=TRUE AND  Estudiante.id_estudiante=? AND Estudiante.numero_de_creditos>=num_creditos";
			
			p1=connection.prepareStatement(query1);
			p1.setInt(1, no_curso);
			p1.setInt(2, id_estudiante);
			

			System.out.println(p1);
			r=p1.executeQuery();
			while(r.next()){
				num_creditos_curso=r.getInt("num_creditos");
			 matriculados=r.getInt("matriculados");
			System.out.println("num_creditos: "+num_creditos_curso);
			 cupos=r.getInt("cupos");
			 id_curso=r.getInt("id_curso");
			if(cupos==0 || r.getInt("numero_de_creditos")< num_creditos_curso) {
				System.out.println("Sin Cupos en curso o sin creditos para estudiante");
				return false;
			}
			
			p0=connection.prepareStatement(query0);
			p0.setInt(1,id_curso);
			p0.setInt(2, id_profesor);
			p0.setInt(3, id_estudiante);
			re0=p0.executeQuery();
			
			if(re0.next()) {
				System.out.println("Esta materia no se puede matricular debido a que ya fue matriculada o ya está aprobada");
				return false;
			}
			
			System.out.println("Acá");
			//Actualizar información de cupos y matriculados en tabla Curso
			cupos=cupos-1;
			query2="UPDATE Curso SET `cupos`=?, `matriculados`=? Where no_curso=?";
			p=connection.prepareStatement(query2);
			p.setInt(1, cupos);
			p.setInt(2, matriculados+1);
			p.setInt(3, no_curso);
			p.execute();
			
			
			//Actualizar información de Numero de créditos en tabla Estudiante
			query="SELECT numero_de_creditos FROM `Estudiante` where id_estudiante=?";
			p1=connection.prepareStatement(query);
			p1.setInt(1, id_estudiante);
			query2="UPDATE Estudiante SET `numero_de_creditos`=? Where id_estudiante=?";			
			p=connection.prepareStatement(query2);
			System.out.println("PEEEE: "+p1);
			re=p1.executeQuery();
			System.out.println("REEEE:"+re);

			while(re.next()) {
				System.out.println("Actualizar información de Numero de creditos en tabla Estudiante");
				num_creditos_est=re.getInt("numero_de_creditos");
				System.out.println("Actualizar información de Numero de creditos en tabla Estudiante 2");
				p.setInt(1, num_creditos_est-num_creditos_curso);
				p.setInt(2, id_estudiante);
				p.execute();
				
				
			}		
			
			//Introducir a la tabla Curso_Profesor_Estudiante
			String que="SELECT id_curso,id_profesor,id_estudiante FROM `Curso_Profesor_Estudiante` where id_estudiante=? AND id_curso=? AND id_profesor=?";
			p=connection.prepareStatement(que);
			p.setInt(1, -1);
			p.setInt(2, id_curso);
			p.setInt(3, id_profesor);			
			
			re1=p.executeQuery();
			
			if(re1.next()){
				{id_est=re1.getInt("id_estudiante");
				System.out.println("While Re1: ");
			}
			
			System.out.println("Esto es idecurso: "+id_est);
			if(id_est==-1) {
				query="UPDATE `Curso_Profesor_Estudiante` SET `id_estudiante`=?, `matriculado`=TRUE  Where id_curso=? AND id_profesor=?";
				p=connection.prepareStatement(query);
				p.setInt(1, id_estudiante);
				p.setInt(2, id_curso);
				p.setInt(3, id_profesor);
				p.execute();				
			}
			}
			
			if(id_est==0) {
				query="INSERT into `Curso_Profesor_Estudiante` (`id_curso`,`id_profesor`,`id_estudiante`,`nit`,`aprobado`,`cursado`,`matriculado`) VALUES (?,?,?,?,?,?,TRUE)";
				System.out.println(query);
				p=connection.prepareStatement(query);
				p.setInt(1, id_curso);
				p.setInt(2, id_profesor);
				p.setInt(3, id_estudiante);
				p.setInt(4, nit);
				p.setBoolean(5, false);
				p.setBoolean(6, false);
				p.execute();}
				System.out.println("P: "+p);
				System.out.println("Re: "+re1);
			}
			
			
		
			
			con.desconectar();
			return true;
			
		} 
		catch(Exception e){
			System.out.println("Exception: "+e);
		return false;}
	}
	
	public static ArrayList<Curso> cursos_matriculados_de_estudiante(int nit, int id_estudiante) {
		
		String query="";
		String query2="";
		ResultSet resultado=null;
		ResultSet resultado2=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Curso> cursos=new ArrayList<Curso>();
		
		try {
			query="SELECT DISTINCT c.id_curso,c.nombre_materia,c.num_creditos, c.id_curso_prerrequisito FROM  Curso as c,  (SELECT * FROM Curso_Profesor_Estudiante WHERE matriculado=TRUE) as cu WHERE c.id_curso = cu.id_curso AND cu.id_estudiante=?";
			
			p=connection.prepareStatement(query);
			p.setInt(1, id_estudiante);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int id=resultado.getInt("id_curso");
				String nombre_materia=resultado.getString("nombre_materia");
				int curso_prerrequisito=resultado.getInt("id_curso_prerrequisito");
				int num_creditos= resultado.getInt("num_creditos");
				int cupos=0;
				int id_profesor=2;
				boolean activo=true;
				int matriculados=0;
				int no_curso=0;
				Curso cur=new Curso(id,nombre_materia,curso_prerrequisito,num_creditos,cupos,id_profesor,nit,activo,no_curso);
				cur.setMatriculados(matriculados);
				cursos.add(cur);
			}
			System.out.println("Busqueda existosa");
			con.desconectar();
			return cursos;
		}catch(Exception e){
			System.out.print("Exception "+e);
			return null;
		}
	}


	public static ArrayList<Curso> cursos_aprobados_de_estudiante(int nit, int id_estudiante){
		
		String query="";
		String query2="";
		ResultSet resultado=null;
		ResultSet resultado2=null;
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		ArrayList<Curso> cursos=new ArrayList<Curso>();
		
		try {
			query="SELECT DISTINCT c.`id_curso`,c.`nombre_materia`,c.`id_curso_prerrequisito`,c.`num_creditos` FROM (SELECT * FROM Curso_Profesor_Estudiante WHERE aprobado=TRUE) as cu, Curso as c WHERE c.id_curso = cu.id_curso AND cu.id_estudiante=? AND cu.aprobado=?";
			
			p=connection.prepareStatement(query);
			p.setInt(1, id_estudiante);
			p.setBoolean(2, true);
			resultado=p.executeQuery();
			while(resultado.next()) {
				int prerreq=resultado.getInt("id_curso_prerrequisito");
				int id=resultado.getInt("id_curso");
				String nombre_materia=resultado.getString("nombre_materia");
				int num_creditos=resultado.getInt("num_creditos");
				Curso cur=new Curso(id,nombre_materia,prerreq,num_creditos,0,0,nit,true,-1);
				cursos.add(cur);
			}
			System.out.println("Busqueda existosa");
			con.desconectar();
			return cursos;
		}catch(Exception e){
			System.out.print("Exception "+e);
			return null;
		}
	}

	public static String aprobar_curso_a_estudiante(int nit, int id_estudiante,int id_curso) {
		String query="";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE `Curso_Profesor_Estudiante` SET `aprobado`=?, `cursado`=? , `matriculado`=? where id_estudiante=? AND id_curso=? AND matriculado=TRUE";
			p=connection.prepareStatement(query);
			p.setBoolean(1, true);
			p.setBoolean(2, true);	
			p.setBoolean(3, false);	
			p.setInt(4, id_estudiante);
			p.setInt(5, id_curso);
		
			p.execute();
			resultado="aprobado";
			
			return resultado;
		}catch(Exception e){
			System.out.print("Exception"+e);
			return resultado;
		}
	}
	public static String cursar_curso_de_estudiante(int nit, int id_estudiante,int id_curso) {
		String query="Curso no añadido a historial";
		String resultado="";
		Conexion con =new Conexion();
		Connection connection=con.getConnection();
		PreparedStatement p=null;
		
		try {
			query="UPDATE `Curso_Profesor_Estudiante` SET cursado=TRUE, matriculado=FALSE where id_estudiante=? AND id_curso=? AND matriculado=TRUE";
			p=connection.prepareStatement(query);
			
			p.setInt(1, id_estudiante);
			p.setInt(2, id_curso);
		
			p.execute();
			resultado="Curso de estudiante añadido a historial";
			
			return resultado;
		}catch(Exception e){
			System.out.print("Exception"+e);
			return "Curso no añadido a historial";
		}
	}
}
	

