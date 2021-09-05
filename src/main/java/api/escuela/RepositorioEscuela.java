package api.escuela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioEscuela extends JpaRepository<EntidadEscuela, Integer> {
}
