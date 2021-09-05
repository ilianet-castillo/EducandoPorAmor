package api.provincia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProvincia extends JpaRepository<EntidadProvincia, Integer> {
}
