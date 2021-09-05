package api.nombreanno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioNombreAnno extends JpaRepository<EntidadNombreAnno, Integer> {
}
