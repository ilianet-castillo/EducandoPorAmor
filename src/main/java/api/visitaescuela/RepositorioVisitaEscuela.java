package api.visitaescuela;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVisitaEscuela extends JpaRepository<EntidadVisitaEscuela, Integer> {
}
