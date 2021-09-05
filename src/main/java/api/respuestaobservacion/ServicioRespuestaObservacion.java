package api.respuestaobservacion;


import api.observacion.EntidadObservacion;
import api.observacion.RepositorioObservacion;
import api.observacion.ServicioObservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioRespuestaObservacion {

    @Autowired
    private RepositorioRespuestaObservacion repositorioRespuestaObservacion;

    @Autowired
    private ServicioObservacion servicioObservacion;

    public EntidadRespuestaObservacion guardar(EntidadRespuestaObservacion entidadRespuestaObservacion) {
        return repositorioRespuestaObservacion.save(entidadRespuestaObservacion);
    }

    public Optional<EntidadRespuestaObservacion> obtenerPorId(int id) {
        return repositorioRespuestaObservacion.findById(id);
    }

    public Optional<EntidadRespuestaObservacion> actualizar(int id, EntidadRespuestaObservacion respuestaObservacion) {
        return obtenerPorId(id).map(record -> {
            record.setObservacion(respuestaObservacion.getObservacion());
            record.setCuerpo(respuestaObservacion.getCuerpo());
            return guardar(record);
        });
    }

    public Optional<EntidadRespuestaObservacion> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioRespuestaObservacion.deleteById(id);
            return record;
        });
    }

    public List<EntidadRespuestaObservacion> listarTodos() {
        return repositorioRespuestaObservacion.findAll();
    }

    public List<EntidadRespuestaObservacion> listarTodosPorObservacion(int id) {
       EntidadObservacion observacion = servicioObservacion.obtenerPorId(id).get();
       return repositorioRespuestaObservacion.findAllByObservacion(observacion);
    }
}
