package api.respuestaobservacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"respuestaobservacion"})
public class RestControladorRespuestaObservacion {

    @Autowired
    private ServicioRespuestaObservacion servicioRespuestaObservacion;

    @PostMapping
    public EntidadRespuestaObservacion adicionarRespuestaObservacion(@RequestBody EntidadRespuestaObservacion respuestaObservacion) {
        return servicioRespuestaObservacion.guardar(respuestaObservacion);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadRespuestaObservacion> mostrarRespuestaObservacion(@PathVariable int id) {
        return servicioRespuestaObservacion.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadRespuestaObservacion> editarRespuestaObservacion(@PathVariable int id, @RequestBody EntidadRespuestaObservacion respuestaObservacion) {
        return servicioRespuestaObservacion.actualizar(id, respuestaObservacion)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadRespuestaObservacion> eliminarRespuestaobservacion(@PathVariable int id) {
        return servicioRespuestaObservacion.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadRespuestaObservacion> mostrarRespuestaObservacion() {
        return servicioRespuestaObservacion.listarTodos();
    }

    @GetMapping(path = "observacion/{id}")
    public List<EntidadRespuestaObservacion> mostrarRespuestaObservacionPorObservacion(@PathVariable int id) {
        return servicioRespuestaObservacion.listarTodosPorObservacion(id);
    }
}
