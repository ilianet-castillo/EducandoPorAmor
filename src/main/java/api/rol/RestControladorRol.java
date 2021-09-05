package api.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"rol"})
public class RestControladorRol {

    @Autowired
    private ServicioRol servicioRol;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param rol
     * @return {@link EntidadRol}
     */
    @PostMapping
    public EntidadRol adicionarRol(@RequestBody EntidadRol rol) {
        return servicioRol.guardar(rol);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadRol}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadRol> mostrarRol(@PathVariable int id) {
        return servicioRol.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param id
     * @param rol
     * @return {@link EntidadRol}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadRol> editarRol(@PathVariable int id, @RequestBody EntidadRol rol) {
        return servicioRol.actualizar(id, rol)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadRol}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadRol> eliminarRol(@PathVariable int id) {
        return servicioRol.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadRol> mostrarListadoRol() {
        return servicioRol.listarTodos();
    }
}
