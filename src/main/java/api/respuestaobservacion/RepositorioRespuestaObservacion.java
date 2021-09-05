package api.respuestaobservacion;

import api.observacion.EntidadObservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioRespuestaObservacion extends JpaRepository<EntidadRespuestaObservacion, Integer> {
    List<EntidadRespuestaObservacion> findAllByObservacion(EntidadObservacion entidadObservacion);
}
