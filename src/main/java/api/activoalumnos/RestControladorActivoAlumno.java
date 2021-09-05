package api.activoalumnos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping({"activoalumno"})
public class RestControladorActivoAlumno {

    @Autowired
    private ServicioActivoAlumno servicioActivoAlumno;

    /**
     * Este método corresponde al . Adicionar
     *
     * @param activoAlumno
     * @return {@link EntidadActivoAlumno}
     */
    @PostMapping
    public EntidadActivoAlumno adicionarActivoAlumno(@RequestBody EntidadActivoAlumno activoAlumno) {
        return servicioActivoAlumno.guardar(activoAlumno);
    }

    /**
     * Este método corresponde al RF21. Mostrar año docente.
     *
     * @param id
     * @return {@link EntidadActivoAlumno}
     */
    @GetMapping(path = {"{id}"})
    public ResponseEntity<EntidadActivoAlumno> mostrarActivoAlumno(@PathVariable int id) {
        return servicioActivoAlumno.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF22.	Editar año docente
     *
     * @param id
     * @param activoAlumno
     * @return {@link EntidadActivoAlumno}
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadActivoAlumno> editarActivoAlumno(@PathVariable int id, @RequestBody EntidadActivoAlumno activoAlumno) {
        return servicioActivoAlumno.actualizar(id, activoAlumno)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * @param id
     * @return {@link EntidadActivoAlumno}
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadActivoAlumno> eliminarActivoAlumno(@PathVariable int id) {
        return servicioActivoAlumno.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Este método corresponde al RF24.	Mostrar listado de años docentes
     *
     * @return
     */
    @GetMapping
    public List<EntidadActivoAlumno> mostrarListadoActivosAlumno() {
        return servicioActivoAlumno.listarTodos();
    }

    @GetMapping(path = {"pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerActaPdf(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = servicioActivoAlumno.obtenerActaPdf(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=acta.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
