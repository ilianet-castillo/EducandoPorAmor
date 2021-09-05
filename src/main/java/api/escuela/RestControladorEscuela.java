package api.escuela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"escuela"})
public class RestControladorEscuela {

    @Autowired
    private ServicioEscuela servicioEscuela;

    /**
     * @param escuela
     * @return {@Link EntidadEscuela}
     */
    @PostMapping
    public EntidadEscuela adicionarEscuela(@RequestBody EntidadEscuela escuela) {
        return servicioEscuela.guardar(escuela);
    }

    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadEscuela> mostrarEscuela(@PathVariable int id) {
        return servicioEscuela.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadEscuela> editarEscuela(@PathVariable int id, @RequestBody EntidadEscuela escuela) {
        return servicioEscuela.actualizar(id, escuela)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadEscuela> eliminarEscuela(@PathVariable int id) {
        return servicioEscuela.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadEscuela> mostrarListadosEscuelas() {
        return servicioEscuela.listarTodos();
    }
}
