package api.municipio;

import api.provincia.EntidadProvincia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbmunicipio")
public class EntidadMunicipio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbprovinciaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadProvincia provincia;

    private boolean habilitado;

    //Borrar desde aqui

    public EntidadProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(EntidadProvincia provincia) {
        this.provincia = provincia;
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
