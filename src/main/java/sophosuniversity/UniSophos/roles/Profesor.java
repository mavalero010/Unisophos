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
		private int telefono;
		private boolean activo;
		private int nit;
		private Curso [] cursos_que_dicta;
		
		public Profesor(int id,String nombre, String apellido,int años_docencia, String maximo_titulo_academico, String email, String direccion, int telefono,boolean activo,int nit) {
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
			this.cursos_que_dicta = getCursosquedicta(id);
			
		}
		private Curso [] getCursosquedicta(int id) {
			try{
				return Curso.getCursosquedicta(id);
			}catch(Exception e){
						
			}
			return null;
}
		
		private void setAñadir_cursos_quedicta(Curso [] cursos) {
			try {
				 Curso.setAñadir_cursos_quedicta(this.id,cursos);
			}catch(Exception e) {
				
			}
		}
		public void setNit(int nit) {
			this.nit=nit;
		}
		
		public int getNit() {
			return this.nit;
		}
		
		public Profesor getprofesor() {
			try {
				//Object [] profesor = profesorDB.get_profesor(id).toArray();
				Object[] profesor=null;
				
				int id=(int) profesor[0];
				String nombre= (String) profesor[1] ;
				String apellido=(String) profesor[2];
				int años_docencia= (int) profesor[3];
				String maximo_titulo_academico =(String) profesor[4];
				String email=(String) profesor[5];
				String direccion=(String) profesor[6];
				int telefono= (int) profesor[7];
				boolean activo = (boolean) profesor[8];
				int nit=(int) profesor[9];
				return new Profesor(id, nombre, apellido, años_docencia, maximo_titulo_academico,email,direccion,telefono,activo,nit);
			}
			catch(Exception e){
			 return null;
			}
			


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
		
		public int getTelefono() {
			return this.telefono;
		}
		
		public void setTelefono(int t) {
			this.telefono=t;
		}
		
		public boolean getActivo() {
			return this.activo;
		}
		public void setActivo(boolean a) {
			this.activo=a;
		}
		
		
		
		


}


