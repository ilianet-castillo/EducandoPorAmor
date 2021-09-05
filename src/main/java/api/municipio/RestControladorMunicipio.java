package api.municipio;

import api.provincia.EntidadProvincia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"municipio"})
public class RestControladorMunicipio {

    @Autowired
    private ServicioMunicipio servicioMunicipio;

    /**
     * Este método corresponde al RF20. Adicionar Año Docente.
     *
     * @param municipio
     * @return {@link EntidadMunicipio}
     */
    @PostMapping
    public EntidadMunicipio adicionarMunicipio(@RequestBody EntidadMunicipio municipio) {
        return servicioMunicipio.guardar(municipio);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadProvincia}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadMunicipio> mostrarMunicipio(@PathVariable int id) {
        return servicioMunicipio.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param
     * @param municipio
     * @return {@link EntidadMunicipio}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadMunicipio> editarMunicipio(@PathVariable int id, @RequestBody EntidadMunicipio municipio) {
        return servicioMunicipio.actualizar(id, municipio)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF23.	Eliminar año docente
     *
     * @param id
     * @return {@link EntidadMunicipio}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadMunicipio> eliminarMunicipio(@PathVariable int id) {
        return servicioMunicipio.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadMunicipio> mostrarMunicipio() {
        return servicioMunicipio.listarTodos();
    }
}
