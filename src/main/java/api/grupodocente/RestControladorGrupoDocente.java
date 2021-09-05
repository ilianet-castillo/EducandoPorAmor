package api.grupodocente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"grupodocente"})
public class RestControladorGrupoDocente {

    @Autowired
    private ServicioGrupoDocente servicioGrupoDocente;

    @PostMapping
    public EntidadGrupoDocente adicionarGrupoDocente(@RequestBody EntidadGrupoDocente grupoDocente) {
        return servicioGrupoDocente.guardar(grupoDocente);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadGrupoDocente> mostrarGrupoDocente(@PathVariable int id) {
        return servicioGrupoDocente.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadGrupoDocente> editarGrupoDocente(@PathVariable int id, @RequestBody EntidadGrupoDocente grupoDocente) {
        return servicioGrupoDocente.actualizar(id, grupoDocente)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadGrupoDocente> eliminarGrupoDocente(@PathVariable int id) {
        return servicioGrupoDocente.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public List<EntidadGrupoDocente> mostrarGrupoDocente() {
        return servicioGrupoDocente.listarTodos();
    }
}
