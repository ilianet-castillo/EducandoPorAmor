package api.profesor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioProfesor {

    @Autowired
    private RepositorioProfesor repositorioProfesor;

    public EntidadProfesor guardar(EntidadProfesor profesor) {
        return repositorioProfesor.save(profesor);
    }

    public Optional<EntidadProfesor> obtenerPorId(int id) {
        return repositorioProfesor.findById(id);
    }

    public Optional<EntidadProfesor> actualizar(int id, EntidadProfesor profesor) {
        return obtenerPorId(id).map(record -> {
            record.setFacultad(profesor.getFacultad());
            record.setCategoriaCientifica(profesor.getCategoriaCientifica());
            record.setNombre(profesor.getNombre());
            record.setApellidos(profesor.getApellidos());
            record.setCorreoElectronico(profesor.getCorreoElectronico());
            record.setTelefono(profesor.getTelefono());
            record.setSexo(profesor.getSexo());
            record.setHabilitado(profesor.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadProfesor> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadProfesor> listarTodos() {
        return repositorioProfesor.findAll();
    }

}
