package api.activoalumnos;

import api.estudiante.EntidadEstudiante;
import api.profesor.EntidadProfesor;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioActivoAlumno {

    private static final Logger logger = LoggerFactory.getLogger(ServicioActivoAlumno.class);

    @Autowired
    private RepositorioActivoAlumno repositorioActivoAlumno;

    public EntidadActivoAlumno guardar(EntidadActivoAlumno activoAlumno) {
        return repositorioActivoAlumno.save(activoAlumno);
    }

    public Optional<EntidadActivoAlumno> obtenerPorId(int id) {
        return repositorioActivoAlumno.findById(id);
    }

    public Optional<EntidadActivoAlumno> actualizar(int id, EntidadActivoAlumno activoAlumno) {
        return obtenerPorId(id).map(record -> {
            record.setFecha(activoAlumno.getFecha());
            record.setLugar(activoAlumno.getLugar());
            record.setHora(activoAlumno.getHora());
            record.setProfesores(activoAlumno.getProfesores());
            record.setEstudiantesPresentes(activoAlumno.getEstudiantesPresentes());
            record.setEstudiantesAusentes(activoAlumno.getEstudiantesAusentes());
            record.setConsideracionesGenerales(activoAlumno.getConsideracionesGenerales());
            record.setPlanteamientosRealizados(activoAlumno.getPlanteamientosRealizados());
            return guardar(record);
        });
    }

    public Optional<EntidadActivoAlumno> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioActivoAlumno.delete(record);
            return record;
        });
    }

    public List<EntidadActivoAlumno> listarTodos() {
        return repositorioActivoAlumno.findAll();
    }

    public ByteArrayInputStream obtenerActaPdf(int id) {
        EntidadActivoAlumno activoAlumno = obtenerPorId(id).get();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph element;
            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("INFORME DEL ACTIVO DE ALUMNOS AYUDANTES");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("TAREA EDUCANDO POR AMOR");
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.add(new Phrase("Fecha: ", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            element.add(new Phrase(activoAlumno.getFecha().toString().substring(0, 10), FontFactory.getFont(FontFactory.TIMES)));
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.add(new Phrase("Lugar: ", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            element.add(new Phrase(activoAlumno.getLugar(), FontFactory.getFont(FontFactory.TIMES)));
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.add(new Phrase("Hora: ", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            element.add(new Phrase(activoAlumno.getHora().toString(), FontFactory.getFont(FontFactory.TIMES)));
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Asistencia:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Profesores:");
            document.add(element);

            for (EntidadProfesor profesor : activoAlumno.getProfesores()) {
                element = new Paragraph();
                element.setAlignment(Element.ALIGN_LEFT);
                element.setFont(FontFactory.getFont(FontFactory.TIMES));
                element.add("*" + profesor.getNombre() + " " + profesor.getApellidos() + " (" + profesor.getFacultad().getNombre() + ")");
                document.add(element);
            }

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Alumnos Ayudantes:");
            document.add(element);

            int count = 1;
            for (EntidadEstudiante estudiante : activoAlumno.getEstudiantesPresentes()) {
                element = new Paragraph();
                element.setAlignment(Element.ALIGN_LEFT);
                element.setFont(FontFactory.getFont(FontFactory.TIMES));
                element.add(count++ + ". " + estudiante.getNombre() + " " + estudiante.getApellidos());
                document.add(element);
            }

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Ausentes:");
            document.add(element);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Nombres y apellidos", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Facultad", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (EntidadEstudiante estudiante : activoAlumno.getEstudiantesAusentes()) {
                cell = new PdfPCell(new Phrase(estudiante.getNombre() + " " + estudiante.getApellidos(), FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getFacultad().getNombre(), FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }
            document.add(table);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Consideraciones generales:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_JUSTIFIED);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(activoAlumno.getConsideracionesGenerales());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Planteamientos realizados por los estudiantes AA:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_JUSTIFIED);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(activoAlumno.getPlanteamientosRealizados());
            document.add(element);

            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}

