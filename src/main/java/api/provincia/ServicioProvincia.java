package api.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioProvincia {

    @Autowired
    private RepositorioProvincia repositorioProvincia;

    public EntidadProvincia guardar(EntidadProvincia provincia) {
        return repositorioProvincia.save(provincia);
    }

    public Optional<EntidadProvincia> obtenerPorId(int id) {
        return repositorioProvincia.findById(id);
    }

    public Optional<EntidadProvincia> actualizar(int id, EntidadProvincia provincia) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(provincia.getNombre());
            record.setHabilitado(provincia.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadProvincia> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadProvincia> listarTodos() {
        return repositorioProvincia.findAll();
    }

}
