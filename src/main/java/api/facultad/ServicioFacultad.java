package api.facultad;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioFacultad {

    @Autowired
    private RepositorioFacultad repositorioFacultad;

    public EntidadFacultad guardar(EntidadFacultad facultad) {
        return repositorioFacultad.save(facultad);
    }

    public Optional<EntidadFacultad> obtenerPorId(int id) {
        return repositorioFacultad.findById(id);
    }

    public Optional<EntidadFacultad> actualizar(int id, EntidadFacultad estado) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(estado.getNombre());
            record.setHabilitado(estado.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadFacultad> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadFacultad> listarTodos() {
        return repositorioFacultad.findAll();
    }

}