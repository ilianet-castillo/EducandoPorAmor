package api.nivelensennanza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioNivelEnsennanza {

    @Autowired
    private RepositorioNivelEnsennanza repositorioNivelEnsennanza;

    public EntidadNivelEnsennanza guardar(EntidadNivelEnsennanza nivelEnsennanza) {
        return repositorioNivelEnsennanza.save(nivelEnsennanza);
    }

    public Optional<EntidadNivelEnsennanza> obtenerPorId(int id) {
        return repositorioNivelEnsennanza.findById(id);
    }

    public Optional<EntidadNivelEnsennanza> actualizar(int id, EntidadNivelEnsennanza nivelEnsennanza) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(nivelEnsennanza.getNombre());
            record.setHabilitado(nivelEnsennanza.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadNivelEnsennanza> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadNivelEnsennanza> listarTodos() {
        return repositorioNivelEnsennanza.findAll();
    }

}
