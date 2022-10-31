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
	private String telefono;
	private boolean activo;
	private int nit;
	private String facultad;
	private  ArrayList<String> cursos_dados = new ArrayList<String>();
	private  ArrayList<String> cursos_aprobados = new ArrayList<String>();
	private  ArrayList<String> cursos_matriculados = new ArrayList<String>();
	public Estudiante(int id,String nombre, String apellido,int numero_de_creditos, int semestre_cursado, String email, String direccion, String telefono,boolean activo,int nit, String facultad) {
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
		this.setFacultad(facultad);
		
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

public String getTelefono() {
	return this.telefono;
}

public boolean getActivo() {
	return this.activo;
}





	

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public int getNit() {
		return this.nit;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public ArrayList<String> getCursos_dados() {
		return cursos_dados;
	}

	public void setCursos_dados(String curso) {
		this.cursos_dados.add(curso);
	}

	public ArrayList<String> getCursos_aprobados() {
		return cursos_aprobados;
	}

	public void setCursos_aprobados(String curso) {
		this.cursos_aprobados.add(curso);
	}



	public ArrayList<String> getCursos_matriculados() {
		return cursos_matriculados;
	}



	public void setCursos_matriculados(String curso) {
		this.cursos_matriculados.add(curso);
	}
	
}
