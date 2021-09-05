package api.grupodocente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioGrupoDocente {

    @Autowired
    private RepositorioGrupoDocente repositorioGrupoDocente;

    public EntidadGrupoDocente guardar(EntidadGrupoDocente grupoDocente) {
        return repositorioGrupoDocente.save(grupoDocente);
    }

    public Optional<EntidadGrupoDocente> obtenerPorId(int id) {
        return repositorioGrupoDocente.findById(id);
    }

    public Optional<EntidadGrupoDocente> actualizar(int id, EntidadGrupoDocente grupoDocente) {
        return obtenerPorId(id).map(record -> {
            record.setGradoEscolar(grupoDocente.getGradoEscolar());
            record.setNombre(grupoDocente.getNombre());
            record.setHabilitado(grupoDocente.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadGrupoDocente> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadGrupoDocente> listarTodos() {
        return repositorioGrupoDocente.findAll();
    }
}
