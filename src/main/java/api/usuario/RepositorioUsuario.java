package api.usuario;

import api.rol.EntidadRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<EntidadUsuario, Integer> {

    EntidadUsuario findByUsuarioEquals(String usuario);

    void deleteAllByRol(EntidadRol rol);

}
