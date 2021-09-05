package api.estado;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioEstado {

    @Autowired
    private RepositorioEstado repositorioEstado;

    public EntidadEstado guardar(EntidadEstado asignatura) {
        return repositorioEstado.save(asignatura);
    }

    public Optional<EntidadEstado> obtenerPorId(int id) {
        return repositorioEstado.findById(id);
    }

    public Optional<EntidadEstado> actualizar(int id, EntidadEstado estado) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(estado.getNombre());
            record.setHabilitado(estado.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadEstado> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadEstado> listarTodos() {
        return repositorioEstado.findAll();
    }
}