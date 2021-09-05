package api.nivelensennanza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"nivelensennanza"})
public class RestControladorNivelEnsennanza {

    @Autowired
    private ServicioNivelEnsennanza servicioNivelEnsennanza;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param nivelEnsennanza
     * @return {@link EntidadNivelEnsennanza}
     */
    @PostMapping
    public EntidadNivelEnsennanza adicionarNivelEnsennanza(@RequestBody EntidadNivelEnsennanza nivelEnsennanza) {
        return servicioNivelEnsennanza.guardar(nivelEnsennanza);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadNivelEnsennanza}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadNivelEnsennanza> mostrarNivelEnsennanza(@PathVariable int id) {
        return servicioNivelEnsennanza.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param nivelEnsennanza
     * @return {@link EntidadNivelEnsennanza}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadNivelEnsennanza> editarNivelEnsennanza(@PathVariable int id, @RequestBody EntidadNivelEnsennanza nivelEnsennanza) {
        return servicioNivelEnsennanza.actualizar(id, nivelEnsennanza)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadNivelEnsennanza}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadNivelEnsennanza> eliminarNivelEnsennanza(@PathVariable int id) {
        return servicioNivelEnsennanza.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadNivelEnsennanza> mostrarListadoNivelEnsennanza() {
        return servicioNivelEnsennanza.listarTodos();
    }
}
