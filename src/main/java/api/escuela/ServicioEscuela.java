package api.escuela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioEscuela {

    @Autowired
    private RepositorioEscuela repositorioEscuela;

    public EntidadEscuela guardar(EntidadEscuela escuela) {
        return repositorioEscuela.save(escuela);
    }

    public Optional<EntidadEscuela> obtenerPorId(int id) {
        return repositorioEscuela.findById(id);
    }

    public Optional<EntidadEscuela> actualizar(int id, EntidadEscuela escuela) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(escuela.isHabilitado());
            record.setMunicipio(escuela.getMunicipio());
            record.setNombre(escuela.getNombre());
            record.setNivelEnsennanza(escuela.getNivelEnsennanza());
            record.setGradosEscolares(escuela.getGradosEscolares());
            record.setAsignaturas(escuela.getAsignaturas());
            return guardar(record);
        });
    }

    public Optional<EntidadEscuela> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(false);
            return guardar(record);
        });
    }

    public List<EntidadEscuela> listarTodos() {
        return repositorioEscuela.findAll();
    }

}
