package api.reportes;

import api.estudiante.EntidadEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
@RequestMapping({"reportes"})
public class RestControladorReportes {

    @Autowired
    private ServiciosReportes serviciosReportes;

    @GetMapping(path = {"porestado/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorEstado(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorEstado(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porestado/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorEstado(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorEstado(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporteporestado.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porescuela/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorEscuela(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorEscuela(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porescuela/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorEscuela(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorEscuela(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporteporescuela.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porfacultad/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorFacultad(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorFacultad(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porfacultad/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorFacultad(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorFacultad(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporteporfacultad.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porgradoescolar/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorGradoEscolar(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorGradoEscolar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porgradoescolar/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorGradoEscolar(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorGradoEscolar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporteporgradoescolar.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"pormilitancia/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorMilitancia(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorMilitancia(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"pormilitancia/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorMilitancia(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorMilitancia(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=pormilitancia.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porgradoescolarfacultadmilitancia/{idGradoEscolar}/{idFacultad}/{idMilitancia}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorgradoEscolarFacultadMilitancia(@PathVariable int idGradoEscolar, @PathVariable int idFacultad, @PathVariable int idMilitancia) {
        return serviciosReportes.obtenerEstudiantesPorGradoEscolarFacultadMilitancia(idGradoEscolar, idFacultad, idMilitancia)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porgradoescolarfacultadmilitancia/pdf/{idGradoEscolar}/{idFacultad}/{idMilitancia}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorPorgradoEscolarFacultadMilitancia(@PathVariable int idGradoEscolar, @PathVariable int idFacultad, @PathVariable int idMilitancia) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorGradoEscolarFacultadMilitancia(idGradoEscolar, idFacultad, idMilitancia);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=porgradoescolarfacultadmilitancia.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porniveldeensennanza/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerReportesPorNivelEnsennanza(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorNivelEnsennanza(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porniveldeensennanza/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorPorNivelEnsennanza(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorNivelEnsennanza(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=porgradoescolarfacultadmilitancia.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porgradoescolargrupo/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolarGrupo(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorGradoEscolarGrupo(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porgradoescolargrupo/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorGradoEscolarGrupo(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorGradoEscolarGrupo(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=porgradoescolargrupo.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porasignatura/{id}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerEstudiantesPorAsignatura(@PathVariable int id) {
        return serviciosReportes.obtenerEstudiantesPorAsignatura(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porasignatura/pdf/{id}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorAsignatura(@PathVariable int id) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorAsignatura(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reporteporasignatura.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

    @GetMapping(path = {"porgradoescolarescuelaasignatura/{idGradoEscolar}/{idEscuela}/{idAsignatura}"})
    public ResponseEntity<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolarEscuelaNivelEnsennanzaAsignatura(@PathVariable int idGradoEscolar, @PathVariable int idEscuela, @PathVariable int idAsignatura) {
        return serviciosReportes.obtenerEstudiantesPorGradoEscolarEscuelaTipoEscuelaAsignatura(idGradoEscolar, idEscuela, idAsignatura)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"porgradoescolarescuelaasignatura/pdf/{idGradoEscolar}/{idFacultad}/{idMilitancia}"}, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> obtenerReportePdfPorGradoEscolarEscuelaNivelEnsennanzaAsignatura(@PathVariable int idGradoEscolar, @PathVariable int idFacultad, @PathVariable int idMilitancia) {
        ByteArrayInputStream byteArrayInputStream = serviciosReportes.obtenerReportePorGradoEscolarEscuelaTipoEscuelaAsignatura(idGradoEscolar, idFacultad, idMilitancia);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=porgradoescolarfacultadmilitancia.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
