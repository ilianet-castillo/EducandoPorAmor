package api.asignatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"asignatura"})
public class RestControladorAsignatura {

    @Autowired
    private ServicioAsignatura servicioAsignatura;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param asignatura
     * @return {@link EntidadAsignatura}
     */
    @PostMapping
    public EntidadAsignatura adicionarAsignatura(@RequestBody EntidadAsignatura asignatura) {
        return servicioAsignatura.guardar(asignatura);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadAsignatura}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadAsignatura> mostrarAsignatura(@PathVariable int id) {
        return servicioAsignatura.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param asignatura
     * @return {@link EntidadAsignatura}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadAsignatura> editarAsignatura(@PathVariable int id, @RequestBody EntidadAsignatura asignatura) {
        return servicioAsignatura.actualizar(id, asignatura)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadAsignatura}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadAsignatura> eliminarAsignatura(@PathVariable int id) {
        return servicioAsignatura.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadAsignatura> mostrarAsignatura() {
        return servicioAsignatura.listarTodos();
    }
}



