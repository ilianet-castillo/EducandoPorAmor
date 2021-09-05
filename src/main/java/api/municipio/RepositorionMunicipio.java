package api.municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorionMunicipio extends JpaRepository<EntidadMunicipio, Integer> {
}
