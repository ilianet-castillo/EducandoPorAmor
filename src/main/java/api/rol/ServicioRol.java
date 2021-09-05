package api.rol;

import api.usuario.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioRol {

    @Autowired
    private RepositorioRol repositorioRol;

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public EntidadRol guardar(EntidadRol rol) {
        return repositorioRol.save(rol);
    }

    public Optional<EntidadRol> obtenerPorId(int id) {
        return repositorioRol.findById(id);
    }

    public Optional<EntidadRol> actualizar(int id, EntidadRol rol) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setNombre(rol.getNombre());
                    return guardar(record);
                });
    }

    public Optional<EntidadRol> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    repositorioUsuario.deleteAllByRol(record);
                    repositorioRol.delete(record);
                    return record;
                });
    }

    public List<EntidadRol> listarTodos() {
        return repositorioRol.findAll();
    }

}
