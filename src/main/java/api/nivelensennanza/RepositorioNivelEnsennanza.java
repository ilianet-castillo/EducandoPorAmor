package api.nivelensennanza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioNivelEnsennanza extends JpaRepository<EntidadNivelEnsennanza, Integer> {
}
