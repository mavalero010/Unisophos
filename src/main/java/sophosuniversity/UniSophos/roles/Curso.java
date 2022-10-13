package sophosuniversity.UniSophos.roles;

import java.util.ArrayList;

public class Curso {
	private int id;
	private String nombre_materia;
	private int id_curso_prerrequisito;
	private int num_creditos;
	private int cupos;
	private int id_profesor;
	private int nit;
	private boolean activo;
	private ArrayList<Estudiante> estudiantes=new ArrayList<Estudiante>();
	
	public Curso(int id, String nombre_materia,int id_curso_prerrequisito, int num_creditos,int cupos,int id_profesor,int nit,boolean activo){
		this.nombre_materia=nombre_materia;
		this.id_curso_prerrequisito=id_curso_prerrequisito;
		this.num_creditos=num_creditos;
		this.cupos=cupos;
		this.id_profesor=id_profesor;
		this.nit=nit;
		this.setActivo(activo);
	}
	
	public int getId() {
		return this.id;
	}
	public void setNit(int nit) {
		this.nit=nit;
	}
	
	public int getNit() {
		return this.nit;
	}
	public String getNombre_Materia() {
		return this.nombre_materia;
	}
	public int getIdPrerequisito() {
		return this.id_curso_prerrequisito;
	}
	public int getNum_creditos() {
		return this.num_creditos;
	}
	public int getCupos() {
		return this.cupos;
	}
	public int getIdProfesor() {
		return this.id_profesor;
	}


	public static Curso[] getCursos_aprobados(int id_estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Curso[] getCursosactuales(int id_estudiante) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void setCursos_aprobados(int id, Curso[] cursos) {
		// TODO Auto-generated method stub
		
	}

	public static Curso[] getCursosquedicta(int id_profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void setAñadir_cursos_quedicta(int id, Curso[] cursos) {
		// TODO Auto-generated method stub
		
	}

	public static void setCursosactuales(int id2, Curso[] cursos) {
		// TODO Auto-generated method stub
		
	}

	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		// TODO Auto-generated method stub
		return null;
	}
	


	
	

}
