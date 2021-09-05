package api.reunionordinaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping({"reunionordinaria"})
public class RestControladorReunionOrdinaria {


    @Autowired
    private ServicioReunionOrdinaria servicioReunionOrdinaria;

    @PostMapping
    public EntidadReunionOrdinaria adicionarReunionOrdinaria(@RequestBody EntidadReunionOrdinaria reunionOrdinaria) {
        return servicioReunionOrdinaria.guardar(reunionOrdinaria);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadReunionOrdinaria> mostrarReunionOrdinaria(@PathVariable int id) {
        return servicioReunionOrdinaria.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadReunionOrdinaria> editarReunionOrdinaria(@PathVariable int id, @RequestBody EntidadReunionOrdinaria reunionOrdinaria) {
        return servicioReunionOrdinaria.actualizar(id, reunionOrdinaria)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadReunionOrdinaria> eliminarReunionOrdinaria(@PathVariable int id) {
        return servicioReunionOrdinaria.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadReunionOrdinaria> mostrarReunionOrdinaria() {
        return servicioReunionOrdinaria.listarTodos();
    }

    @GetMapping(path = {"pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerActaPdf(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = servicioReunionOrdinaria.obtenerActaPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=acta.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
