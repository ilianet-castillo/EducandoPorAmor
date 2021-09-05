package api.estudiante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioEstudiante {

    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    public EntidadEstudiante guardar(EntidadEstudiante estudiante) {
        return repositorioEstudiante.save(estudiante);
    }

    public Optional<EntidadEstudiante> obtenerPorId(int id) {
        return repositorioEstudiante.findById(id);
    }

    public Optional<EntidadEstudiante> actualizar(int id, EntidadEstudiante estudiante) {
        return obtenerPorId(id).map(record -> {
            record.setEscuela(estudiante.getEscuela());
            record.setEstado(estudiante.getEstado());
            record.setFacultad(estudiante.getFacultad());
            record.setNombre(estudiante.getNombre());
            record.setApellidos(estudiante.getApellidos());
            record.setCi(estudiante.getCi());
            record.setSolapin(estudiante.getSolapin());
            record.setTelefono(estudiante.getTelefono());
            record.setSexo(estudiante.getSexo());
            record.setDireccionParticular(estudiante.getDireccionParticular());
            record.setHabilitado(estudiante.isHabilitado());
            record.setGradoEscolar(estudiante.getGradoEscolar());
            record.setMilitancia(estudiante.getMilitancia());
            record.setGrupoClase(estudiante.getGrupoClase());
            return guardar(record);
        });
    }

    public Optional<EntidadEstudiante> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(false);
            return guardar(record);
        });

    }

    public List<EntidadEstudiante> listarTodos() {
        return repositorioEstudiante.findAll();
    }

}



