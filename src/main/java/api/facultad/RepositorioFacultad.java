package api.facultad;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFacultad extends JpaRepository<EntidadFacultad, Integer> {
}
