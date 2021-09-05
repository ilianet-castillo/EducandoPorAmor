package api.reportes;

import api.escuela.RepositorioEscuela;
import api.estado.RepositorioEstado;
import api.estudiante.EntidadEstudiante;
import api.estudiante.RepositorioEstudiante;
import api.facultad.RepositorioFacultad;
import api.gradoescolar.RepositorioGradoEscolar;
import api.militancia.RepositorioMilitancia;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiciosReportes {

    private static final Logger logger = LoggerFactory.getLogger(ServiciosReportes.class);

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    @Autowired
    private RepositorioEstado repositorioEstado;

    @Autowired
    private RepositorioEscuela repositorioEscuela;

    @Autowired
    private RepositorioFacultad repositorioFacultad;

    @Autowired
    private RepositorioGradoEscolar repositorioGradoEscolar;

    @Autowired
    private RepositorioMilitancia repositorioMilitancia;


    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorEstado(int id) {
        return repositorioEstado.findById(id).map(record -> repositorioEstudiante.getAllByEstado(record));
    }

    public ByteArrayInputStream obtenerReportePorEstado(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorEstado(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorEscuela(int id) {
        return repositorioEscuela.findById(id).map(record -> repositorioEstudiante.getAllByEscuela(record));
    }

    public ByteArrayInputStream obtenerReportePorEscuela(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorEscuela(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorFacultad(int id) {
        return repositorioFacultad.findById(id).map(record -> repositorioEstudiante.getAllByFacultad(record));
    }

    public ByteArrayInputStream obtenerReportePorFacultad(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorFacultad(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolar(int id) {
        return repositorioGradoEscolar.findById(id).map(record -> repositorioEstudiante.getAllByGradoEscolar(record));
    }

    public ByteArrayInputStream obtenerReportePorGradoEscolar(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorGradoEscolar(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorMilitancia(int id) {
        return repositorioMilitancia.findById(id).map(record -> repositorioEstudiante.getAllByMilitancia(record));
    }

    public ByteArrayInputStream obtenerReportePorMilitancia(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorMilitancia(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolarFacultadMilitancia(int idGradoEscolar, int idFacultad, int idMilitancia) {

        return Optional.of(repositorioEstudiante.findAll().stream()
                .filter(record -> record.getGradoEscolar().getId() == idGradoEscolar
                        && record.getFacultad().getId() == idFacultad
                        && record.getMilitancia().getId() == idMilitancia)
                .collect(Collectors.toList()));
    }

    public ByteArrayInputStream obtenerReportePorGradoEscolarFacultadMilitancia(int idGradoEscolar, int idFacultad, int idMilitancia) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorGradoEscolarFacultadMilitancia(idGradoEscolar, idFacultad, idMilitancia).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorNivelEnsennanza(int id) {
        return Optional.of(repositorioEstudiante.findAll().stream()
                .filter(record -> record.getEscuela() != null && record.getEscuela().getNivelEnsennanza().getId() == id)
                .collect(Collectors.toList()));
    }

    public ByteArrayInputStream obtenerReportePorNivelEnsennanza(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorNivelEnsennanza(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolarGrupo(int id) {
        return Optional.of(repositorioEstudiante.findAll().stream()
                .filter(record -> record.getGrupoClase() != null && record.getGrupoClase().getGrupoDocente().getGradoEscolar().getId() == id)
                .collect(Collectors.toList()));
    }

    public ByteArrayInputStream obtenerReportePorGradoEscolarGrupo(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorGradoEscolarGrupo(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorAsignatura(int id) {
        return Optional.of(repositorioEstudiante.findAll().stream()
                .filter(record -> record.getGrupoClase() != null && record.getGrupoClase().getAsignatura().getId() == id)
                .collect(Collectors.toList()));
    }

    public ByteArrayInputStream obtenerReportePorAsignatura(int id) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorAsignatura(id).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    public Optional<List<EntidadEstudiante>> obtenerEstudiantesPorGradoEscolarEscuelaTipoEscuelaAsignatura(int idGradoEscolar, int idEscuela, int idAsignatura) {
        return Optional.of(repositorioEstudiante.findAll().stream()
                .filter(record -> record.getEscuela() != null && record.getEscuela().getId() == idEscuela
                        && record.getGrupoClase() != null
                        && record.getGrupoClase().getGrupoDocente().getGradoEscolar().getId() == idGradoEscolar
                        && record.getGrupoClase().getAsignatura().getId() == idAsignatura)
                .collect(Collectors.toList()));
    }

    public ByteArrayInputStream obtenerReportePorGradoEscolarEscuelaTipoEscuelaAsignatura(int idGradoEscolar, int idFacultad, int idMilitancia) {
        List<EntidadEstudiante> estudiantes = obtenerEstudiantesPorGradoEscolarEscuelaTipoEscuelaAsignatura(idGradoEscolar, idFacultad, idMilitancia).get();
        estudiantes.sort(Comparator.comparing(EntidadEstudiante::getApellidos));
        return obtenerPdfReporte(estudiantes);
    }

    private ByteArrayInputStream obtenerPdfReporte(List<EntidadEstudiante> estudiantes) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(14);
            table.setWidthPercentage(100);

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Nombre", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Apellidos", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("CI", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Solapín", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Teléfono", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Sexo", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Direccion Particular", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Habilitado", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Estado", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Facultad", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Militancia", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("GradoEscolar", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Escuela", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Grupo Clase", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (EntidadEstudiante estudiante : estudiantes) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(estudiante.getNombre()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getApellidos()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(estudiante.getCi())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getSolapin()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getTelefono()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getSexo()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getDireccionParticular()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(estudiante.isHabilitado())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getEstado().getNombre()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getFacultad().getNombre()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getMilitancia().getTipo()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getGradoEscolar().getNombre()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getEscuela() != null ? estudiante.getEscuela().getNombre() : ""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getGrupoClase() != null ? estudiante.getGrupoClase().getAsignatura().getNombre() + " <-> " + estudiante.getGrupoClase().getGrupoDocente().getNombre() : ""));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
