package api.grupoclase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioGrupoClase extends JpaRepository<EntidadGrupoClase, Integer> {
}
