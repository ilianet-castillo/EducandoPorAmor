package api.nombreanno;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioNombreAnno {

    @Autowired
    private RepositorioNombreAnno repositorioNombreAnno;

    public EntidadNombreAnno guardar(EntidadNombreAnno nombreAnno) {
        return repositorioNombreAnno.save(nombreAnno);
    }

    public Optional<EntidadNombreAnno> obtenerPorId(int id) {
        return repositorioNombreAnno.findById(id);
    }

    public Optional<EntidadNombreAnno> actualizar(int id, EntidadNombreAnno nombreAnno) {
        return obtenerPorId(id).map(record -> {
            record.setNombre(nombreAnno.getNombre());
            record.setHabilitado(nombreAnno.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadNombreAnno> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadNombreAnno> listarTodos() {
        return repositorioNombreAnno.findAll();
    }

}