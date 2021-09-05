package api.valorevaluacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioValorEvaluacion extends JpaRepository<EntidadValorEvaluacion, Integer> {
}
