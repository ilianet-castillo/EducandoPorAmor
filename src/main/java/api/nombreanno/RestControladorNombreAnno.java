package api.nombreanno;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"nombreanno"})
public class RestControladorNombreAnno {

    @Autowired
    private ServicioNombreAnno servicioNombreAnno;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param nombreAnno
     * @return {@link EntidadNombreAnno}
     */
    @PostMapping
    public EntidadNombreAnno adicionarNombreAnno(@RequestBody EntidadNombreAnno nombreAnno) {
        return servicioNombreAnno.guardar(nombreAnno);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadNombreAnno}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadNombreAnno> mostrarNombreAnno(@PathVariable int id) {
        return servicioNombreAnno.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param nombreAnno
     * @return {@link EntidadNombreAnno}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadNombreAnno> editarNombreAnno(@PathVariable int id, @RequestBody EntidadNombreAnno nombreAnno) {
        return servicioNombreAnno.actualizar(id, nombreAnno)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadNombreAnno}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadNombreAnno> eliminarNombreAnno(@PathVariable int id) {
        return servicioNombreAnno.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadNombreAnno> mostrarNombreAnno() {
        return servicioNombreAnno.listarTodos();
    }
}
