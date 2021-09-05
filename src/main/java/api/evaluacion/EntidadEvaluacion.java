package api.evaluacion;

import api.estudiante.EntidadEstudiante;
import api.valorevaluacion.EntidadValorEvaluacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbevaluacion")
public class EntidadEvaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbestudianteid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadEstudiante estudiante;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbvalorevaluacionid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadValorEvaluacion valorEvaluacion;

    private Date fecha;

    //Borrar desde aqui

    public EntidadValorEvaluacion getValorEvaluacion() {
        return valorEvaluacion;
    }

    public void setValorEvaluacion(EntidadValorEvaluacion valorEvaluacion) {
        this.valorEvaluacion = valorEvaluacion;
    }

    public EntidadEstudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EntidadEstudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
