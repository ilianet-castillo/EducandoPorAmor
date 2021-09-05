package api.asignatura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioAsignatura extends JpaRepository<EntidadAsignatura, Integer> {
}
