package api.grupoclase;

import api.estudiante.RepositorioEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioGrupoClase {

    @Autowired
    private RepositorioGrupoClase repositorioGrupoClase;
    @Autowired
    private RepositorioEstudiante repositorioEstudiante;

    public EntidadGrupoClase guardar(EntidadGrupoClase grupoClase) {
        return repositorioGrupoClase.save(grupoClase);
    }

    public Optional<EntidadGrupoClase> obtenerPorId(int id) {
        return repositorioGrupoClase.findById(id);
    }

    public Optional<EntidadGrupoClase> actualizar(int id, EntidadGrupoClase grupoClase) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(grupoClase.isHabilitado());
            record.setAsignatura(grupoClase.getAsignatura());
            record.setGrupoDocente(grupoClase.getGrupoDocente());
            return guardar(record);
        });
    }

    public Optional<EntidadGrupoClase> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(false);
            return guardar(record);
        });
    }

    public List<EntidadGrupoClase> listarTodos() {
        return repositorioGrupoClase.findAll();
    }

    public List<EntidadGrupoClase> listarSinAsignar() {
        return repositorioGrupoClase.findAll().stream().filter(grupoClase ->
                !repositorioEstudiante.existsByGrupoClase(grupoClase)
        ).collect(Collectors.toList());
    }
}
