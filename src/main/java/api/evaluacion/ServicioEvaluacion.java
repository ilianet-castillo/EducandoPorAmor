package api.evaluacion;


import api.estudiante.EntidadEstudiante;
import api.estudiante.ServicioEstudiante;
import api.observacion.EntidadObservacion;
import api.respuestaobservacion.EntidadRespuestaObservacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioEvaluacion {

    @Autowired
    private RepositorioEvaluacion repositorioEvaluacion;

    @Autowired
    private ServicioEstudiante servicioEstudiante;

    public EntidadEvaluacion guardar(EntidadEvaluacion evaluacion) {
        return repositorioEvaluacion.save(evaluacion);
    }

    public Optional<EntidadEvaluacion> obtenerPorId(int id) {
        return repositorioEvaluacion.findById(id);
    }

    public Optional<EntidadEvaluacion> actualizar(int id, EntidadEvaluacion evaluacion) {
        return obtenerPorId(id).map(record -> {
            record.setEstudiante(evaluacion.getEstudiante());
            record.setFecha(evaluacion.getFecha());
            record.setValorEvaluacion(evaluacion.getValorEvaluacion());
            return guardar(record);
        });
    }

    public Optional<EntidadEvaluacion> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioEvaluacion.deleteById(id);
            return record;
        });
    }

    public List<EntidadEvaluacion> listarTodos() {
        return repositorioEvaluacion.findAll();
    }

    public List<EntidadEvaluacion> listarTodosPorEstudiante(int id) {
        EntidadEstudiante estudiante = servicioEstudiante.obtenerPorId(id).get();
        return repositorioEvaluacion.findAllByEstudiante(estudiante);
    }

}
