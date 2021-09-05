package api.asignatura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioAsignatura {

    @Autowired
    private RepositorioAsignatura repositorioAsignatura;

    public EntidadAsignatura guardar(EntidadAsignatura asignatura) {
        return repositorioAsignatura.save(asignatura);
    }

    public Optional<EntidadAsignatura> obtenerPorId(int id) {
        return repositorioAsignatura.findById(id);
    }

    public Optional<EntidadAsignatura> actualizar(int id, EntidadAsignatura asignatura) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(asignatura.getNombre());
            record.setHabilitado(asignatura.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadAsignatura> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadAsignatura> listarTodos() {
        return repositorioAsignatura.findAll();
    }

}


