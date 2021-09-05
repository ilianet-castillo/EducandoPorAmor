package api.valorevaluacion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ServicioValorEvaluacion {

    @Autowired
    private RepositorioValorEvaluacion repositorioValorEvaluacion;

    public EntidadValorEvaluacion guardar(EntidadValorEvaluacion valorEvaluacion) {
        return repositorioValorEvaluacion.save(valorEvaluacion);
    }

    public Optional<EntidadValorEvaluacion> obtenerPorId(int id) {
        return repositorioValorEvaluacion.findById(id);
    }

    public Optional<EntidadValorEvaluacion> actualizar(int id, EntidadValorEvaluacion valorEvaluacion) {
        return obtenerPorId(id).map(record -> {
            record.setValor(valorEvaluacion.getValor());
            record.setHabilitado(valorEvaluacion.isHabilitado());
            return guardar(record);
        });
    }

    public Optional<EntidadValorEvaluacion> eliminar(int id) {
        return obtenerPorId(id)
                .map(record -> {
                    record.setHabilitado(false);
                    return guardar(record);
                });
    }

    public List<EntidadValorEvaluacion> listarTodos() {
        return repositorioValorEvaluacion.findAll();
    }

}
