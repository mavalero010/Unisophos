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
	private int matriculados;
	private int no_curso;
	
	public Curso(int id, String nombre_materia,int id_curso_prerrequisito, int num_creditos,int cupos,int id_profesor,int nit,boolean activo,int no_curso){
		this.id=id;
		this.nombre_materia=nombre_materia;
		this.id_curso_prerrequisito=id_curso_prerrequisito;
		this.num_creditos=num_creditos;
		this.cupos=cupos;
		this.id_profesor=id_profesor;
		this.nit=nit;
		this.setActivo(activo);
		this.setNo_curso(no_curso);
		
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int i) {
		 this.id=i;
	}
	
	public int getNit() {
		return this.nit;
	}
	public void setNit(int nit) {
		this.nit=nit;
	}
	

	public String getNombre_Materia() {
		return this.nombre_materia;
	}
	
	public void setNombre_Materia(String n) {
		this.nombre_materia=n;
	}
	public int getIdPrerequisito() {
		return this.id_curso_prerrequisito;
	}
	public void setIdPrerequisito(int ide) {
		 this.id_curso_prerrequisito=ide;
	}
	public int getNum_creditos() {
		return this.num_creditos;
	}
	public void setNum_creditos(int nu) {
		 this.num_creditos=nu;
	}
	public int getCupos() {
		return this.cupos;
	}

	
	public void setCupos(int c) {
		 this.cupos=c;
	}
	public int getIdProfesor() {
		return this.id_profesor;
	}
	public void setIdProfesor(int idp) {
		 this.id_profesor=idp;
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

	

	

	public int getMatriculados() {
		return matriculados;
	}

	public void setMatriculados(int matriculados) {
		this.matriculados = matriculados;
	}

	public int getNo_curso() {
		return no_curso;
	}

	public void setNo_curso(int no_curso) {
		this.no_curso = no_curso;
	}
	


	
	

}
