package api.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EntidadUsuario guardar(EntidadUsuario entidadUsuario) {
        entidadUsuario.setPass(passwordEncoder.encode(entidadUsuario.getPass()));
        return repositorioUsuario.save(entidadUsuario);
    }

    public Optional<EntidadUsuario> obtenerPorId(int id) {
        return repositorioUsuario.findById(id);
    }

    public EntidadUsuario obtenerPorNombre(String usuario) {
        return repositorioUsuario.findByUsuarioEquals(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        EntidadUsuario user = obtenerPorNombre(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with user name: " + userName);
        }

        return new User(user.getUsuario(), user.getPass(), new ArrayList<>());
    }

    public Optional<EntidadUsuario> actualizar(int id, EntidadUsuario usuario) {
        return obtenerPorId(id).map(record -> {
            record.setPass(usuario.getPass());
            record.setRol(usuario.getRol());
            record.setUsuario(usuario.getUsuario());
            return guardar(record);
        });
    }

    public Optional<EntidadUsuario> eliminar(int id) {
        return obtenerPorId(id).map(record -> {
            repositorioUsuario.deleteById(id);
            return record;
        });
    }

    public List<EntidadUsuario> listarTodos() {
        return repositorioUsuario.findAll();
    }
}
