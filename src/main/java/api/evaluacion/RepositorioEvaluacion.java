package api.evaluacion;

import api.estudiante.EntidadEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RepositorioEvaluacion extends JpaRepository<EntidadEvaluacion, Integer> {
    List<EntidadEvaluacion> findAllByEstudiante(EntidadEstudiante estudiante);
}
