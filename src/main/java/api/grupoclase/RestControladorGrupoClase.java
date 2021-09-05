package api.grupoclase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"grupoclase"})
public class RestControladorGrupoClase {

    @Autowired
    private ServicioGrupoClase servicioGrupoClase;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param grupoClase
     * @return {@link EntidadGrupoClase}
     */
    @PostMapping
    public EntidadGrupoClase adicionarGrupoClase(@RequestBody EntidadGrupoClase grupoClase) {
        return servicioGrupoClase.guardar(grupoClase);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadGrupoClase}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadGrupoClase> mostrarGrupoClase(@PathVariable int id) {
        return servicioGrupoClase.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param id
     * @param grupoClase
     * @return {@link EntidadGrupoClase}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadGrupoClase> editarGrupoClase(@PathVariable int id, @RequestBody EntidadGrupoClase grupoClase) {
        return servicioGrupoClase.actualizar(id, grupoClase)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadGrupoClase}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadGrupoClase> eliminarGrupoClase(@PathVariable int id) {
        return servicioGrupoClase.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadGrupoClase> mostrarListadoGrupoClase() {
        return servicioGrupoClase.listarTodos();
    }

    /**
     * Este método es auxiliar a los RF asignar y modificar estudiante
     *
     * @return
     */
    @GetMapping(path = "sinasignar")
    public List<EntidadGrupoClase> listadoGrupoClaseSinAsignar() {
        return servicioGrupoClase.listarSinAsignar();
    }
}
