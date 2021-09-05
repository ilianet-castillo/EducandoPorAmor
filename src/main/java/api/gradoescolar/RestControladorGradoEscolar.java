package api.gradoescolar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"gradoescolar"})
public class RestControladorGradoEscolar {

    @Autowired
    private ServicioGradoEscolar servicioGradoEscolar;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param gradoEscolar
     * @return {@link EntidadGradoEscolar}
     */
    @PostMapping
    public EntidadGradoEscolar adicionarGradoEscolar(@RequestBody EntidadGradoEscolar gradoEscolar) {
        return servicioGradoEscolar.guardar(gradoEscolar);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadGradoEscolar}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadGradoEscolar> mostrarGradoEscolar(@PathVariable int id) {
        return servicioGradoEscolar.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param id
     * @param gradoEscolar
     * @return {@link EntidadGradoEscolar}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadGradoEscolar> editarGradoEscolar(@PathVariable int id, @RequestBody EntidadGradoEscolar gradoEscolar) {
        return servicioGradoEscolar.actualizar(id, gradoEscolar)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadGradoEscolar}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadGradoEscolar> eliminarGradoEscolar(@PathVariable int id) {
        return servicioGradoEscolar.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadGradoEscolar> mostrarListadoGradoEscolar() {
        return servicioGradoEscolar.listarTodos();
    }

}
