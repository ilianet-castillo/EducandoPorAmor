package api.facultad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"facultad"})
public class RestControladorFacultad {

    @Autowired
    private ServicioFacultad servicioFacultad;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param facultad
     * @return {@link EntidadFacultad}
     */
    @PostMapping
    public EntidadFacultad adicionarFacultad(@RequestBody EntidadFacultad facultad) {
        return servicioFacultad.guardar(facultad);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadFacultad}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadFacultad> mostrarFacultad(@PathVariable int id) {
        return servicioFacultad.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param facultad
     * @return {@link EntidadFacultad}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadFacultad> editarFacultad(@PathVariable int id, @RequestBody EntidadFacultad facultad) {
        return servicioFacultad.actualizar(id, facultad)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadFacultad}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadFacultad> eliminarFacultad(@PathVariable int id) {
        return servicioFacultad.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadFacultad> mostrarFacultad() {
        return servicioFacultad.listarTodos();
    }
}


