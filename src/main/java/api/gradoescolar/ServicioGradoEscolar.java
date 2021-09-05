package api.gradoescolar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioGradoEscolar {

    @Autowired
    private RepositorioGradoEscolar repositorioGradoEscolar;

    public EntidadGradoEscolar guardar(EntidadGradoEscolar gradoEscolar) {
        return repositorioGradoEscolar.save(gradoEscolar);
    }

    public Optional<EntidadGradoEscolar> obtenerPorId(int id) {
        return repositorioGradoEscolar.findById(id);
    }

    public Optional<EntidadGradoEscolar> actualizar(int id, EntidadGradoEscolar gradoEscolar) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(gradoEscolar.isHabilitado());
            record.setNombre(gradoEscolar.getNombre());
            return guardar(record);
        });
    }

    public Optional<EntidadGradoEscolar> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(false);
            return guardar(record);
        });
    }

    public List<EntidadGradoEscolar> listarTodos() {
        return repositorioGradoEscolar.findAll();
    }

}
