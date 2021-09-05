package api.visitaescuela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping({"visitaescuela"})
public class RestControladorVisitaEscuela {

    @Autowired
    private ServicioVisitaEscuela servicioVisitaEscuela;

    @PostMapping
    public EntidadVisitaEscuela adicionarVisitaEscuela(@RequestBody EntidadVisitaEscuela visitaEscuela) {
        return servicioVisitaEscuela.guardar(visitaEscuela);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadVisitaEscuela> mostrarVisitaEscuela(@PathVariable int id) {
        return servicioVisitaEscuela.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadVisitaEscuela> editarVisitaEscuela(@PathVariable int id, @RequestBody EntidadVisitaEscuela visitaEscuela) {
        return servicioVisitaEscuela.actualizar(id, visitaEscuela)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadVisitaEscuela> eliminarVisitaEscuela(@PathVariable int id) {
        return servicioVisitaEscuela.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadVisitaEscuela> mostrarVisitaEscuela() {
        return servicioVisitaEscuela.listarTodos();
    }

    @GetMapping(path = {"pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerActaPdf(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = servicioVisitaEscuela.obtenerActaPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=acta.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
