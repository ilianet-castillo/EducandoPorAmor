package api.evaluacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"evaluacion"})
public class RestControladorEvaluacion {

    @Autowired
    private ServicioEvaluacion servicioEvaluacion;

    @PostMapping
    public EntidadEvaluacion adicionarEvaluacion(@RequestBody EntidadEvaluacion evaluacion) {
        return servicioEvaluacion.guardar(evaluacion);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadEvaluacion> mostrarEvaluacion(@PathVariable int id) {
        return servicioEvaluacion.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadEvaluacion> editarEvaluacion(@PathVariable int id, @RequestBody EntidadEvaluacion evaluacion) {
        return servicioEvaluacion.actualizar(id, evaluacion)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadEvaluacion> eliminarEvaluacion(@PathVariable int id) {
        return servicioEvaluacion.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public List<EntidadEvaluacion> mostrarEvaluacion() {
        return servicioEvaluacion.listarTodos();
    }

    @GetMapping(path = "estudiante/{id}")
    public List<EntidadEvaluacion> mostrarEvaluacionPorEstudianteId(@PathVariable int id) {
        return servicioEvaluacion.listarTodosPorEstudiante(id);
    }
}
