package api.gradoescolar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioGradoEscolar extends JpaRepository<EntidadGradoEscolar, Integer> {
}


