
package api.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"estudiante"})
public class RestControladorEstudiante {

    @Autowired
    private ServicioEstudiante servicioEstudiante;

    @PostMapping
    public EntidadEstudiante adicionarEstudiante(@RequestBody EntidadEstudiante estudiante) {
        return servicioEstudiante.guardar(estudiante);
    }

    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadEstudiante> mostrarEstudiante(@PathVariable int id) {
        return servicioEstudiante.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadEstudiante> editarEstudiante(@PathVariable int id, @RequestBody EntidadEstudiante estudiante) {
        return servicioEstudiante.actualizar(id, estudiante)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadEstudiante> eliminarEstudiante(@PathVariable int id) {
        return servicioEstudiante.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public List<EntidadEstudiante> mostrarEstudiante() {
        return servicioEstudiante.listarTodos();
    }
}
