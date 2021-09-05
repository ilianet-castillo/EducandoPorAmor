package api.municipio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioMunicipio {

    @Autowired
    private RepositorionMunicipio repositorionMunicipio;

    public EntidadMunicipio guardar(EntidadMunicipio municipio) {
        return repositorionMunicipio.save(municipio);
    }

    public Optional<EntidadMunicipio> obtenerPorId(int id) {
        return repositorionMunicipio.findById(id);
    }

    public Optional<EntidadMunicipio> actualizar(int id, EntidadMunicipio municipio) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(municipio.getNombre());
            record.setHabilitado(municipio.isHabilitado());
            record.setProvincia(municipio.getProvincia());
            return guardar(record);
        });
    }

    public Optional<EntidadMunicipio> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadMunicipio> listarTodos() {
        return repositorionMunicipio.findAll();
    }

}
