package api.grupodocente;

import api.gradoescolar.EntidadGradoEscolar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbgrupodocente")
public class EntidadGrupoDocente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbgradoescolarid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadGradoEscolar gradoEscolar;

    //Borrar desde aqui

    public EntidadGradoEscolar getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(EntidadGradoEscolar gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

}
