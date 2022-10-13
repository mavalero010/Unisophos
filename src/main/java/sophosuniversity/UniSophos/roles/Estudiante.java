package sophosuniversity.UniSophos.roles;
import java.util.ArrayList;
public class Estudiante {
	private int id;
	private String nombre;
	private String apellido;
	private int numero_de_creditos;
	private int semestre_cursado;
	private String email;
	private String direccion;
	private int telefono;
	private boolean activo;
	private int nit;
	private Curso[] cursosactuales;
	private Curso[] cursos_aprobados;
	
	public Estudiante(int id,String nombre, String apellido,int numero_de_creditos, int semestre_cursado, String email, String direccion, int telefono,boolean activo,int nit) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.numero_de_creditos=numero_de_creditos;
		this.semestre_cursado =semestre_cursado;
		this.email=email;			
		this.direccion=direccion;
		this.telefono=telefono;
		this.activo=activo;
		this.nit=nit;
		this.cursosactuales= getCursosactuales(id);
		this.cursos_aprobados=getCursos_aprobados(id);
		
	}

	private Curso[] getCursos_aprobados(int id2) {
		try {
			Curso [] cursos_dados = Curso.getCursos_aprobados(id);
			return cursos_dados;
		}
		catch(Exception e) {
			
		}
		return null;
	}
	
	public int getNumero_de_creditos() {
		return this.numero_de_creditos;
	}
	public int getId() {
		return this.id;
	}
	public String getNombre() {
		return this.nombre;
	}
	public String getApellido() {
		return this.apellido;
	}
	public int getSemestre_cursado() {
		return this.semestre_cursado;
	}
	public String getEmail() {
		return this.email;
	}
	
public String getDireccion() {
	return this.direccion;
}

public int getTelefono() {
	return this.telefono;
}

public boolean getActivo() {
	return this.activo;
}



private Curso[] getCursosactuales(int id) {
		try {
			Curso [] cursos_actuales=Curso.getCursosactuales(id);
			return cursos_actuales;
		}
		catch(Exception e) {
			
		}
		return null;
	}

	

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}


	
	public Estudiante getEstudiante(int id_) {
		try {
			//Object [] estudiante = estudianteDB.get_estudiante(id).toArray();
			Object [] estudiante =null;
			int id=(int) estudiante[0];
			String nombre= (String) estudiante[1] ;
			String apellido=(String) estudiante[2];
			int numero_de_creditos= (int) estudiante[3];
			int semestre_cursado =(int) estudiante[4];
			String email=(String) estudiante[5];
			String direccion=(String) estudiante[6];
			int telefono= (int) estudiante[7];
			boolean activo = (boolean) estudiante[8];
			int nit=(int) estudiante[9];
			return new Estudiante(id, nombre, apellido, numero_de_creditos,semestre_cursado,email,direccion,telefono,activo,nit);
		}
		catch(Exception e){
		return null;
		}
		


	}

	

	
	private void setCursos_dados(Curso[] cursos) {
		try {
			Curso.setCursos_aprobados(this.id,cursos);
			
		}catch (Exception e) {
			
		}
	}
	private void setCursosactuales(Curso[] cursos) {
		try {
			Curso.setCursosactuales(this.id,cursos);
			
		}catch (Exception e) {
		
		}
	}
	
	
	private String guardarEstudiante(){
		return null;
		//return estudianteDB.guardarEstudianteDB(this.id, this.nombre, this.apellido, this.numero_de_creditos,this.semestre_cursado,this.email,this.direccion,this.telefono,this.activo);
	}
	
}
