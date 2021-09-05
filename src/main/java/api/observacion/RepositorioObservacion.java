package api.observacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioObservacion extends JpaRepository<EntidadObservacion, Integer> {
}
