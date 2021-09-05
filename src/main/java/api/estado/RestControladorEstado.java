package api.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"estado"})
public class RestControladorEstado {

    @Autowired
    private ServicioEstado servicioEstado;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param estado
     * @return {@link EntidadEstado}
     */
    @PostMapping
    public EntidadEstado adicionarEstado(@RequestBody EntidadEstado estado) {
        return servicioEstado.guardar(estado);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadEstado}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadEstado> mostrarEstado(@PathVariable int id) {
        return servicioEstado.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param estado
     * @return {@link EntidadEstado}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadEstado> editarEstado(@PathVariable int id, @RequestBody EntidadEstado estado) {
        return servicioEstado.actualizar(id, estado)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadEstado}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadEstado> eliminarEstado(@PathVariable int id) {
        return servicioEstado.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadEstado> mostrarEstado() {
        return servicioEstado.listarTodos();
    }
}




