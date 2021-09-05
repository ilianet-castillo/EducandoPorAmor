package api.profesor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfesor extends JpaRepository<EntidadProfesor, Integer> {
}
