package sophosuniversity.UniSophos.roles;

public class Curso_Profesor_Estudiante {
	private int id_Curso;
	private int id_Profesor;
	private int id_Estudiante;
	private int nit;
	private Boolean aprobado;
	private Boolean cursado;
	public Curso_Profesor_Estudiante(int id_curso,int id_profesor, int id_estudiante,int nit) {
		setId_curso(id_curso);
		setId_profesor(id_profesor);
		setId_estudiante(id_estudiante);	
		setAprobado(false);
		setCursado(false);
	}

	public int getId_curso() {
		return id_Curso;
	}

	public void setId_curso(int id_curso) {
		this.id_Curso = id_curso;
	}

	public int getId_profesor() {
		return id_Profesor;
	}

	public void setId_profesor(int id_profesor) {
		this.id_Profesor = id_profesor;
	}

	public int getId_estudiante() {
		return id_Estudiante;
	}

	public void setId_estudiante(int id_estudiante) {
		this.id_Estudiante = id_estudiante;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public Boolean getAprobado() {
		return aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	public Boolean getCursado() {
		return cursado;
	}

	public void setCursado(Boolean cursado) {
		this.cursado = cursado;
	}


	
}
