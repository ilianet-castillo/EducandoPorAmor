package api.militancia;

import api.estado.EntidadEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"militancia"})
public class RestControladorMilitancia {

    @Autowired
    private ServicioMilitancia servicioMilitancia;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param militancia
     * @return {@link EntidadMilitancia}
     */
    @PostMapping
    public EntidadMilitancia adicionarMilitancia(@RequestBody EntidadMilitancia militancia) {
        return servicioMilitancia.guardar(militancia);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadMilitancia}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadMilitancia> mostrarMilitancia(@PathVariable int id) {
        return servicioMilitancia.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param militancia
     * @return {@link EntidadMilitancia}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadMilitancia> editarMilitancia(@PathVariable int id, @RequestBody EntidadMilitancia militancia) {
        return servicioMilitancia.actualizar(id, militancia)
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
    public ResponseEntity<EntidadMilitancia> eliminarMilitancia(@PathVariable int id) {
        return servicioMilitancia.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadMilitancia> mostrarMilitancia() {
        return servicioMilitancia.listarTodos();
    }
}

