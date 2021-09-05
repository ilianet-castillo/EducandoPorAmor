package api.observacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"observacion"})
public class RestControladorObservacion {

    @Autowired
    private ServicioObservacion servicioObservacion;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param observacion
     * @return {@link EntidadObservacion}
     */
    @PostMapping
    public EntidadObservacion adicionarObservacion(@RequestBody EntidadObservacion observacion) {
        return servicioObservacion.guardar(observacion);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadObservacion}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadObservacion> mostrarObservacion(@PathVariable int id) {
        return servicioObservacion.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param id
     * @param observacion
     * @return {@link EntidadObservacion}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadObservacion> editarObservacion(@PathVariable int id, @RequestBody EntidadObservacion observacion) {
        return servicioObservacion.actualizar(id, observacion)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadObservacion}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadObservacion> eliminarObservacion(@PathVariable int id) {
        return servicioObservacion.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadObservacion> mostrarListadoObservacion() {
        return servicioObservacion.listarTodos();
    }
}