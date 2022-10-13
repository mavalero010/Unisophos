package sophosuniversity.UniSophos.roles;


public class Universidad {
	
	private String nombre;
	private int nit;
	private String ciudad;
	private String pais;
	
	public Universidad(String nombre, int nit, String ciudad, String pais) {
		this.setNombre(nombre);
		this.setNit(nit);
		this.setCiudad(ciudad);
		this.setPais(pais);
	}

	private Profesor [] getProfesores() {
		return null;
	}
	
	private Estudiante[] getEstudiantes() {
		return null;
	}
	
	private Curso[] getCursos() {
		return null;
	}

	private void AñadirProfesores() {
		
	}

	private void AñadirEstudiantes() {
		
	}
	
	private void AñadirCursos() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	}