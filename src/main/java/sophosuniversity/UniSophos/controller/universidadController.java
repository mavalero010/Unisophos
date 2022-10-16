package sophosuniversity.UniSophos.controller;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophosuniversity.UniSophos.dbservices.universidadservicedb;
import sophosuniversity.UniSophos.roles.Curso;
import sophosuniversity.UniSophos.roles.Estudiante;
import sophosuniversity.UniSophos.roles.Profesor;
import sophosuniversity.UniSophos.roles.Universidad;

@RestController
@RequestMapping("/")
public class universidadController {
	@RequestMapping("/crearUniversidad/")
	@PostMapping
	public ResponseEntity<String> crearUniversidad(@RequestBody Universidad universidad) {
			String respuesta=universidadservicedb.crearUniversidad(universidad);
			System.out.print("Data: "+universidad);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/estudiantes/{nit}/")
	@GetMapping
	public ResponseEntity<ArrayList<Estudiante>> getTodosLosEstudiantes(@RequestBody @PathVariable int nit) {
		ArrayList<Estudiante> respuesta=universidadservicedb.getTodosLosEstudiantes(nit);
			System.out.print("Data: Lista de Estudiantes "+respuesta);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/profesores/{nit}/")
	@GetMapping
	public ResponseEntity<ArrayList<Profesor>> getTodosLosProfesores(@RequestBody @PathVariable int nit) {
		ArrayList<Profesor> respuesta=universidadservicedb.getTodosLosProfesores(nit);
			System.out.print("Data: Lista de profesores");
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/cursos/{nit}/")
	@GetMapping
	public ResponseEntity<ArrayList<Curso>> getTodosLosCursos(@RequestBody @PathVariable int nit) {
		ArrayList<Curso> respuesta=universidadservicedb.getTodosLosCursos(nit);
			System.out.print("Data: Lista de Cursos");
			return ResponseEntity.ok(respuesta);
		
	}
	
	@RequestMapping("/añadir_estudiante/")
	@PostMapping
	public ResponseEntity<String> addEstudiante(@RequestBody Estudiante estudiante) {
		String respuesta=universidadservicedb.addEstudiante(estudiante);
			System.out.print("Data: "+estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping("/añadir_profesor/")
	@PostMapping
	public ResponseEntity<String> addProfesor(@RequestBody Profesor profesor) {
		String respuesta=universidadservicedb.addProfesor(profesor);
			System.out.print("Data: "+profesor.getApellido());
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping("/añadir_curso/")
	@PostMapping
	public ResponseEntity<String> addCurso(@RequestBody Curso curso) {
		System.out.print("ID: "+curso.getId());
		String respuesta=universidadservicedb.addCurso(curso);
			System.out.print("Data: "+curso);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/desactivar_profesor/{nit}/{id_profesor}/")
	@DeleteMapping
	public ResponseEntity<String> desactivarProfesor(@RequestBody @PathVariable int nit,  @PathVariable int id_profesor) {
		String respuesta=universidadservicedb.desactivarProfesor(nit,id_profesor);
			System.out.print("NIT: "+nit+" IDPROF: "+id_profesor);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/desactivar_curso/{nit}/{id_curso}/")
	@DeleteMapping
	public ResponseEntity<String> desactivarCurso(@RequestBody @PathVariable int nit,@PathVariable  int id_curso) {
		String respuesta=universidadservicedb.desactivarCurso(nit,id_curso);
			System.out.print("NIT: "+nit+" IDCUR: "+id_curso);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/desactivar_estudiante/{nit}/{id_estudiante}/")
	@DeleteMapping
	public ResponseEntity<String> desactivarEstudiante(@RequestBody @PathVariable int nit,@PathVariable int id_estudiante) {
		String respuesta=universidadservicedb.desactivarEstudiante(nit,id_estudiante);
			System.out.print("NIT: "+nit+" IDEST: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	
	@RequestMapping(value="/encontrar_profesor/{nit}/{id_profesor}/")
	@GetMapping
	public ResponseEntity<Profesor> encontrarProfesor(@RequestBody @PathVariable int nit,@PathVariable int id_profesor) {
		Profesor respuesta=universidadservicedb.encontrarProfesor(nit,id_profesor);
			System.out.print("NIT: "+nit+" IDPROF: "+id_profesor);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/encontrar_estudiante/{nit}/{id_estudiante}/")
	@GetMapping
	public ResponseEntity<Estudiante> encontrarEstudiante(@RequestBody @PathVariable int nit, @PathVariable int id_estudiante) {
		Estudiante respuesta=universidadservicedb.encontrarEstudiante(nit,id_estudiante);
			System.out.print("NIT: "+nit+" IDEST: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/encontrar_curso/{nit}/{no_Curso}/")
	@GetMapping
	public ResponseEntity<Curso> encontrarCurso(@RequestBody @PathVariable int nit,@PathVariable int no_Curso) {
		Curso respuesta=universidadservicedb.encontrarCurso(nit,no_Curso);
			System.out.print("NIT: "+nit+" IDEST: "+no_Curso);
			return ResponseEntity.ok(respuesta);
		
	}
	
	
	@RequestMapping(value="/activar_profesor/{nit}/{id_profesor}")
	@PostMapping
	public ResponseEntity<String> activarProfesor(@RequestBody @PathVariable int nit,  @PathVariable int id_profesor) {
		String respuesta=universidadservicedb.activarProfesor(nit,id_profesor);
			System.out.print("NIT: "+nit+" IDPROF: "+id_profesor);
			return ResponseEntity.ok(respuesta);
		
	}
	
	@RequestMapping(value="/activar_estudiante/{nit}/{id_estudiante}/")
	@PostMapping
	public ResponseEntity<String> activarEstudiante(@RequestBody @PathVariable int nit,  @PathVariable int id_estudiante) {
		String respuesta=universidadservicedb.activarEstudiante(nit,id_estudiante);
			System.out.print("NIT: "+nit+" IDPROF: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	
	@RequestMapping(value="/filtrar_cursos_por_cupos/{nit}/{id_curso}/")
	@GetMapping
	public ResponseEntity<ArrayList<Curso>> filtrar_cursos_por_cupos(@RequestBody @PathVariable int nit, @PathVariable int id_curso) {
		ArrayList<Curso> respuesta=universidadservicedb.filtrar_cursos_por_cupos(nit,id_curso);
			System.out.print("Data: Lista de Cursos");
			return ResponseEntity.ok(respuesta);
		
	}
	
	
	@RequestMapping(value="/encontrar_profesor_por_nombre/{nit}/{nombre_profesor}/{apellido_profesor}/")
	@GetMapping
	public ResponseEntity<Profesor> buscar_profesor_por_nombre(@RequestBody @PathVariable int nit,@PathVariable String nombre_profesor,@PathVariable String apellido_profesor) {
		Profesor respuesta=universidadservicedb.buscar_profesor_por_nombre(nit,nombre_profesor,apellido_profesor);
			System.out.print("NIT: "+nit+" IDPROF: "+apellido_profesor);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/encontrar_curso_por_nombre/{nit}/{nombre_cur}/")
	@GetMapping
	public ResponseEntity<Curso> buscar_curso_por_nombre(@RequestBody @PathVariable int nit,@PathVariable String nombre_cur) {
		Curso respuesta=universidadservicedb.buscar_curso_por_nombre(nit,nombre_cur);
			System.out.print("NIT: "+nit+" IDPROF: "+nombre_cur);
			return ResponseEntity.ok(respuesta);
		
	}

	@RequestMapping(value="/encontrar_estudiante_por_nombre/{nit}/{nombre_est}/{apellido_est}/")
	@GetMapping
	public ResponseEntity<Estudiante> buscar_curso_por_nombre(@RequestBody @PathVariable int nit,@PathVariable String nombre_est,@PathVariable String apellido_est) {
		Estudiante respuesta=universidadservicedb.buscar_estudiante_por_nombre(nit,nombre_est,apellido_est);
			System.out.print("NIT: "+nit+" Apellido EST: "+apellido_est);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/matricula/{nit}/{no_curso}/{id_profesor}/{id_estudiante}/")
	@PostMapping
	public ResponseEntity<String> matricula(@RequestBody @PathVariable int nit,@PathVariable int no_curso,@PathVariable int id_profesor,@PathVariable int id_estudiante) {
		boolean respuesta=universidadservicedb.matricula(nit,no_curso,id_profesor,id_estudiante);
			System.out.print("NIT: "+nit+" ID CURSO: "+no_curso);
			if(respuesta) {
				return ResponseEntity.ok("Matricula hecha");
			}
			return ResponseEntity.ok("Matricula no hecha");
		
	}
	
	@RequestMapping(value="/cursos_matriculados_estudiante/{nit}/{id_estudiante}/")
	@GetMapping
	public ResponseEntity<ArrayList<Curso>> cursos_matriculados_de_estudiante(@RequestBody @PathVariable int nit,@PathVariable int id_estudiante) {
		ArrayList<Curso> respuesta=universidadservicedb.cursos_matriculados_de_estudiante(nit,id_estudiante);
			System.out.print("NIT: "+nit+" ID CURSO: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/cursos_aprobados_estudiante/{nit}/{id_estudiante}/")
	@GetMapping
	public ResponseEntity<ArrayList<Curso>> cursos_aprobados_de_estudiante(@RequestBody @PathVariable int nit,@PathVariable int id_estudiante) {
		ArrayList<Curso> respuesta=universidadservicedb.cursos_aprobados_de_estudiante(nit,id_estudiante);
			System.out.print("NIT: "+nit+" ID CURSO: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	
	@RequestMapping(value="/aprobar_curso/{nit}/{id_estudiante}/{id_curso}/")
	@PostMapping
	public ResponseEntity<String> aprobar_curso_a_estudiante(@RequestBody @PathVariable int nit,@PathVariable int id_estudiante,@PathVariable int id_curso) {
		String respuesta=universidadservicedb.aprobar_curso_a_estudiante(nit,id_estudiante,id_curso);
			System.out.print("NIT: "+nit+" ID CURSO: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
	@RequestMapping(value="/cursar_curso/{nit}/{id_estudiante}/{id_curso}/")
	@PostMapping
	public ResponseEntity<String> cursar_curso_de_estudiante(@RequestBody @PathVariable int nit,@PathVariable int id_estudiante,@PathVariable int id_curso) {
		String respuesta=universidadservicedb.cursar_curso_de_estudiante(nit,id_estudiante,id_curso);
			System.out.print("NIT: "+nit+" ID CURSO: "+id_estudiante);
			return ResponseEntity.ok(respuesta);
		
	}
}


