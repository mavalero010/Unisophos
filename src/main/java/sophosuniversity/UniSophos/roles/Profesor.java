package sophosuniversity.UniSophos.roles;
import java.util.ArrayList;



public class Profesor {
		private int id;
		private String nombre;
		private String apellido;
		private int años_docencia;
		private String maximo_titulo_academico;
		private String email;
		private String direccion;
		private String telefono;
		private boolean activo;
		private int nit;
		private  ArrayList<String> cursos_que_dicta = new ArrayList<String>();
		
		
		public Profesor(int id,String nombre, String apellido,int años_docencia, String maximo_titulo_academico, String email, String direccion, String telefono,boolean activo,int nit) {
			this.id=id;
			this.nombre=nombre;
			this.apellido=apellido;
			this.años_docencia=años_docencia;
			this.maximo_titulo_academico =maximo_titulo_academico;
			this.email=email;			
			this.direccion=direccion;
			this.telefono=telefono;
			this.activo=activo;
			this.nit=nit;
		}
	
		
	
		public void setNit(int nit) {
			this.nit=nit;
		}
		
		public int getNit() {
			return this.nit;
		}
		
	
			


		
		public String getNombre() {
			return this.nombre;
		}
		public int getId() {
			return this.id;
		}
		public String getApellido() {
			return this.apellido;
		}
		public void setApellido(String ap) {
			this.apellido=ap;
		}
		public void setNombre(String n) {
			this.nombre = n;
		}
		public int getAños_docencia() {
			return this.años_docencia;
		}
		public void setAños_docencia(int a) {
			this.años_docencia = a;
		}
		
		public String getMaximo_titulo_academico() {
			return this.maximo_titulo_academico;
		}
		
		public void setMaximo_titulo_academico(String max) {
			this.maximo_titulo_academico=max;
		}
		
		public String getEmail() {
			return this.email;
		}
		
		public void setEmail(String e) {
			this.email=e;
		}
		
		public String getDireccion() {
			return this.direccion;
		}
		
		public void setDireccion(String d) {
			this.direccion=d;
		}
		
		public String getTelefono() {
			return this.telefono;
		}
		
		public void setTelefono(String t) {
			this.telefono=t;
		}
		
		public boolean getActivo() {
			return this.activo;
		}
		public void setActivo(boolean a) {
			this.activo=a;
		}
		public ArrayList<String>  getCursos_que_dicta() {
			return cursos_que_dicta;
		}
		public void setCursos_que_dicta(String cursos) {
			this.cursos_que_dicta.add(cursos);
		}
		
		
		
		


}


