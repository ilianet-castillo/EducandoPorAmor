package api.visitaescuela;


import api.estudiante.EntidadEstudiante;
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
public class ServicioVisitaEscuela {

    private static final Logger logger = LoggerFactory.getLogger(ServicioVisitaEscuela.class);

    @Autowired
    private RepositorioVisitaEscuela repositorioVisitaEscuela;

    public EntidadVisitaEscuela guardar(EntidadVisitaEscuela entidadVisitaEscuela) {
        return repositorioVisitaEscuela.save(entidadVisitaEscuela);
    }

    public Optional<EntidadVisitaEscuela> obtenerPorId(int id) {
        return repositorioVisitaEscuela.findById(id);
    }

    public Optional<EntidadVisitaEscuela> actualizar(int id, EntidadVisitaEscuela visitaEscuela) {
        return obtenerPorId(id).map(record -> {
            record.setCargo(visitaEscuela.getCargo());
            record.setCuerpo(visitaEscuela.getCuerpo());
            record.setEscuela(visitaEscuela.getEscuela());
            record.setFecha(visitaEscuela.getFecha());
            record.setNombreAnno(visitaEscuela.getNombreAnno());
            record.setNombreDirectivo(visitaEscuela.getNombreDirectivo());
            record.setObservaciones(visitaEscuela.getObservaciones());
            record.setProfesor(visitaEscuela.getProfesor());
            record.setProvincia(visitaEscuela.getProvincia());
            record.setEstudiantes(visitaEscuela.getEstudiantes());
            return guardar(record);
        });
    }

    public Optional<EntidadVisitaEscuela> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioVisitaEscuela.deleteById(id);
            return record;
        });
    }

    public List<EntidadVisitaEscuela> listarTodos() {
        return repositorioVisitaEscuela.findAll();
    }

    public ByteArrayInputStream obtenerActaPdf(int id) {
        EntidadVisitaEscuela visitaEscuela = obtenerPorId(id).get();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph element;
            element = new Paragraph();
            element.setAlignment(Element.ALIGN_RIGHT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getProvincia().getNombre() + ", " + visitaEscuela.getFecha().toString().substring(0, 10));
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_RIGHT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getNombreAnno().getNombre());
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getEscuela().getNombre() + ":");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getCuerpo());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Relación de estudiantes");
            document.add(element);

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("No", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre y Apellidos", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Facultad", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Asignatura", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Grado y Grupos", FontFactory.getFont(FontFactory.TIMES_BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            int no = 1;
            for (EntidadEstudiante estudiante : visitaEscuela.getEstudiantes()) {
                cell = new PdfPCell(new Phrase(String.valueOf(no++), FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getNombre() + " " + estudiante.getApellidos(), FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getFacultad().getNombre(), FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getGrupoClase() != null ? estudiante.getGrupoClase().getAsignatura().getNombre() : "", FontFactory.getFont(FontFactory.TIMES)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(estudiante.getGrupoClase() != null ? estudiante.getGrupoClase().getGrupoDocente().getGradoEscolar().getNombre() : "", FontFactory.getFont(FontFactory.TIMES)));
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
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Observaciones:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getObservaciones());
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getNombreDirectivo());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getCargo() + " y firma");
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(visitaEscuela.getProfesor().getNombre());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Nombre, Apellidos y firma del visitante");
            document.add(element);

            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
