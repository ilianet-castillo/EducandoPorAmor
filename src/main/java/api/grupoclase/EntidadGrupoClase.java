package api.grupoclase;

import api.asignatura.EntidadAsignatura;
import api.grupodocente.EntidadGrupoDocente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbgrupoclase")
public class EntidadGrupoClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbgrupodocenteid", nullable = false)
    private EntidadGrupoDocente grupoDocente;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbasignaturaid", nullable = false)
    private EntidadAsignatura asignatura;

    private boolean habilitado;

    // Borrar desde aqui


    public EntidadGrupoDocente getGrupoDocente() {
        return grupoDocente;
    }

    public void setGrupoDocente(EntidadGrupoDocente grupoDocente) {
        this.grupoDocente = grupoDocente;
    }

    public EntidadAsignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(EntidadAsignatura asignatura) {
        this.asignatura = asignatura;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
