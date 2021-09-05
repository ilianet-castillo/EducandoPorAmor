package api.usuario;

import api.usuario.config.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"usuario"})
public class RestControladorUsuario {

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @CrossOrigin()
    @PostMapping
    public ResponseEntity<Object> adicionarUsuario(@RequestBody EntidadUsuario usuario) {
        try {
            return ResponseEntity.ok(servicioUsuario.guardar(usuario));
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getCause().getCause().getMessage().split("Detail: ")[1]);
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<EntidadUsuario> mostrarUsuario(@PathVariable int id) {
        return servicioUsuario.obtenerPorId(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<EntidadUsuario> editarUsuario(@PathVariable int id, @RequestBody EntidadUsuario usuario) {
        return servicioUsuario.actualizar(id, usuario)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<EntidadUsuario> eliminarUsuario(@PathVariable int id) {
        return servicioUsuario.eliminar(id)
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<EntidadUsuario> mostrarUsuario() {
        return servicioUsuario.listarTodos();
    }

    @PostMapping(value = "autenticar")
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody EntidadUsuario usuarioRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioRequest.getUsuario(),
                    usuarioRequest.getPass()));
        } catch (DisabledException e) {
            return ResponseEntity.unprocessableEntity().body("Usuario deshabilitado");
        } catch (BadCredentialsException e) {
            return ResponseEntity.unprocessableEntity().body("Credenciales inválidas");
        }

        EntidadUsuario usuarioResponse = servicioUsuario.obtenerPorNombre(usuarioRequest.getUsuario());

        usuarioResponse.setPass("");
        usuarioResponse.setToken(jwtTokenUtil.generateToken(usuarioResponse));

        return ResponseEntity.ok(usuarioResponse);
    }

}
