package sophosuniversity.UniSophos.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sophosuniversity.UniSophos.dbservices.universidadservicedb;
import sophosuniversity.UniSophos.roles.Universidad;

@RestController
@RequestMapping("/")
public class universidadController {
	
	@PostMapping
	public ResponseEntity<String> c(@RequestBody Universidad universidad) {
			String respuesta=universidadservicedb.crearUniversidad(universidad);
			System.out.print("Data: "+universidad);
			return ResponseEntity.ok(respuesta);
		
	}
}


