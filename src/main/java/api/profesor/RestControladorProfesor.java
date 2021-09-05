package api.profesor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"profesor"})
public class RestControladorProfesor {

    @Autowired
    private ServicioProfesor servicioProfesor;

    @PostMapping
    public EntidadProfesor adicionarProfesor(@RequestBody EntidadProfesor profesor) {
        return servicioProfesor.guardar(profesor);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadProfesor> mostrarProfesor(@PathVariable int id) {
        return servicioProfesor.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadProfesor> editarProfesor(@PathVariable int id, @RequestBody EntidadProfesor profesor) {
        return servicioProfesor.actualizar(id, profesor)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadProfesor> eliminarProfesor(@PathVariable int id) {
        return servicioProfesor.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadProfesor> mostrarProfesor() {
        return servicioProfesor.listarTodos();
    }
}
