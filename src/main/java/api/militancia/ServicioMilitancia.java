package api.militancia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioMilitancia {

    @Autowired
    private RepositorioMilitancia repositorioMilitancia;

    public EntidadMilitancia guardar(EntidadMilitancia militancia) {
        return repositorioMilitancia.save(militancia);
    }

    public Optional<EntidadMilitancia> obtenerPorId(int id) {
        return repositorioMilitancia.findById(id);
    }

    public Optional<EntidadMilitancia> actualizar(int id, EntidadMilitancia militancia) {
        return obtenerPorId(id).map(record -> {
            record.setHabilitado(militancia.isHabilitado());
            record.setTipo(militancia.getTipo());
            return guardar(record);
        });
    }

    public Optional<EntidadMilitancia> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }


    public List<EntidadMilitancia> listarTodos() {
        return repositorioMilitancia.findAll();
    }

}

