package api.valorevaluacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"valorevaluacion"})
public class RestControladorValorEvaluacion {

    @Autowired
    private ServicioValorEvaluacion servicioValorEvaluacion;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param valorEvaluacion
     * @return {@link EntidadValorEvaluacion}
     */
    @PostMapping
    public EntidadValorEvaluacion adicionarProvincia(@RequestBody EntidadValorEvaluacion valorEvaluacion) {
        return servicioValorEvaluacion.guardar(valorEvaluacion);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadValorEvaluacion}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadValorEvaluacion> mostrarValorEvaluacion(@PathVariable int id) {
        return servicioValorEvaluacion.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param valorEvaluacion
     * @return {@link EntidadValorEvaluacion}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadValorEvaluacion> editarNombreAnno(@PathVariable int id, @RequestBody EntidadValorEvaluacion valorEvaluacion) {
        return servicioValorEvaluacion.actualizar(id, valorEvaluacion)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadValorEvaluacion}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadValorEvaluacion> eliminarValorEvaluacion(@PathVariable int id) {
        return servicioValorEvaluacion.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadValorEvaluacion> mostrarValorEvaluacion() {
        return servicioValorEvaluacion.listarTodos();
    }
}

