package api.observacion;

import api.respuestaobservacion.ServicioRespuestaObservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioObservacion {

    @Autowired
    private RepositorioObservacion repositorioObservacion;

    @Autowired
    private ServicioRespuestaObservacion servicioRespuestaObservacion;

    public EntidadObservacion guardar(EntidadObservacion observacion) {
        return repositorioObservacion.save(observacion);
    }

    public Optional<EntidadObservacion> obtenerPorId(int id) {
        return repositorioObservacion.findById(id);
    }

    public Optional<EntidadObservacion> actualizar(int id, EntidadObservacion observacion) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setTitulo(observacion.getTitulo());
                    record.setCuerpo(observacion.getCuerpo());
                    return guardar(record);
                });
    }

    public Optional<EntidadObservacion> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    servicioRespuestaObservacion.listarTodosPorObservacion(id).forEach(entidadRespuestaObservacion ->
                            servicioRespuestaObservacion.eliminar(entidadRespuestaObservacion.getId())
                    );

                    repositorioObservacion.deleteById(id);
                    return record;
                });
    }

    public List<EntidadObservacion> listarTodos() {
        return repositorioObservacion.findAll();
    }

}