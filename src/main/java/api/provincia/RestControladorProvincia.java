package api.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"provincia"})
public class RestControladorProvincia {

    @Autowired
    private ServicioProvincia servicioProvincia;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param provincia
     * @return {@link EntidadProvincia}
     */
    @PostMapping
    public EntidadProvincia adicionarProvincia(@RequestBody EntidadProvincia provincia) {
        return servicioProvincia.guardar(provincia);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadProvincia}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadProvincia> mostrarProvincia(@PathVariable int id) {
        return servicioProvincia.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param provincia
     * @return {@link EntidadProvincia}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadProvincia> editarProvincia(@PathVariable int id, @RequestBody EntidadProvincia provincia) {
        return servicioProvincia.actualizar(id, provincia)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadProvincia}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadProvincia> eliminarProvincia(@PathVariable int id) {
        return servicioProvincia.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadProvincia> mostrarProvincia() {
        return servicioProvincia.listarTodos();
    }
}
