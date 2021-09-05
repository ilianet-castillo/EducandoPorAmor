package api.respuestaobservacion;

import api.observacion.EntidadObservacion;
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
@Table(name = "tbrespuestaobservacion")
public class EntidadRespuestaObservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbobservacionid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadObservacion observacion;

    private String cuerpo;

    //Borrar desde aqui

    public int getId() {
        return id;
    }

    public EntidadObservacion getObservacion() {
        return observacion;
    }

    public void setObservacion(EntidadObservacion observacion) {
        this.observacion = observacion;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
