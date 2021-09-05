package api.reunionordinaria;


import api.profesor.EntidadProfesor;
import com.itextpdf.text.*;
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
public class ServicioReunionOrdinaria {

    private static final Logger logger = LoggerFactory.getLogger(ServicioReunionOrdinaria.class);

    @Autowired
    private RepositorioReunionOrdinaria repositorioReunionOrdinaria;

    public EntidadReunionOrdinaria guardar(EntidadReunionOrdinaria entidadReunionOrdinaria) {
        return repositorioReunionOrdinaria.save(entidadReunionOrdinaria);
    }

    public Optional<EntidadReunionOrdinaria> obtenerPorId(int id) {
        return repositorioReunionOrdinaria.findById(id);
    }

    public Optional<EntidadReunionOrdinaria> actualizar(int id, EntidadReunionOrdinaria reunionOrdinaria) {
        return obtenerPorId(id).map(record -> {
            record.setCurso(reunionOrdinaria.getCurso());
            record.setTitulo(reunionOrdinaria.getTitulo());
            record.setProvincia(reunionOrdinaria.getProvincia());
            record.setFecha(reunionOrdinaria.getFecha());
            record.setLugar(reunionOrdinaria.getLugar());
            record.setHora(reunionOrdinaria.getHora());
            record.setAsistenciaEstudiantes(reunionOrdinaria.getAsistenciaEstudiantes());
            record.setProfesores(reunionOrdinaria.getProfesores());
            record.setOrdenDelDia(reunionOrdinaria.getOrdenDelDia());
            record.setCuerpo(reunionOrdinaria.getCuerpo());
            record.setAcuerdos(reunionOrdinaria.getAcuerdos());
            record.setFinalizacion(reunionOrdinaria.getFinalizacion());
            return guardar(record);
        });
    }

    public Optional<EntidadReunionOrdinaria> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioReunionOrdinaria.deleteById(id);
            return record;
        });
    }

    public List<EntidadReunionOrdinaria> listarTodos() {
        return repositorioReunionOrdinaria.findAll();
    }

    public ByteArrayInputStream obtenerActaPdf(int id) {
        EntidadReunionOrdinaria reunionOrdinaria = obtenerPorId(id).get();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Paragraph element;
            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("REUNIÓN MENSUAL TAREA EDUCANDO POR AMOR");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_CENTER);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add(reunionOrdinaria.getCurso());
            document.add(element);

            element = new Paragraph();
            element.add(" ");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(reunionOrdinaria.getTitulo());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Fecha: " + reunionOrdinaria.getProvincia().getNombre() + ", " + reunionOrdinaria.getFecha().toString().substring(0, 10));
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Lugar: " + reunionOrdinaria.getLugar());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Hora: " + reunionOrdinaria.getHora().toString());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Asistencia Estudiantes: " + reunionOrdinaria.getAsistenciaEstudiantes());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add("Asistencia Profesores:");
            document.add(element);

            for (EntidadProfesor profesor : reunionOrdinaria.getProfesores()) {
                element = new Paragraph();
                element.setAlignment(Element.ALIGN_LEFT);
                element.setFont(FontFactory.getFont(FontFactory.TIMES));
                element.add(profesor.getNombre() + " " + profesor.getApellidos() + " (" + profesor.getFacultad().getNombre() + ")");
                document.add(element);
            }

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Orden del Día:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(reunionOrdinaria.getOrdenDelDia());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(reunionOrdinaria.getCuerpo());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD));
            element.add("Acuerdos:");
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_LEFT);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(reunionOrdinaria.getAcuerdos());
            document.add(element);

            element = new Paragraph();
            element.setAlignment(Element.ALIGN_JUSTIFIED);
            element.setFont(FontFactory.getFont(FontFactory.TIMES));
            element.add(reunionOrdinaria.getFinalizacion());
            document.add(element);

            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
