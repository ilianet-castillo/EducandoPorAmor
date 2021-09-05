package api.reunionordinaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioReunionOrdinaria extends JpaRepository<EntidadReunionOrdinaria, Integer> {


}
