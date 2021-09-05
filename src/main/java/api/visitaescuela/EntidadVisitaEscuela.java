package api.visitaescuela;

import api.escuela.EntidadEscuela;
import api.estudiante.EntidadEstudiante;
import api.nombreanno.EntidadNombreAnno;
import api.observacion.EntidadObservacion;
import api.profesor.EntidadProfesor;
import api.provincia.EntidadProvincia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "tbvisitaescuela")
public class EntidadVisitaEscuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbprovinciaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadProvincia provincia;

    private Date fecha;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbnombreannoid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadNombreAnno nombreAnno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbescuelaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadEscuela escuela;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbestudiante_tbvisitaescuela",
            joinColumns = {@JoinColumn(name = "tbvisitaescuelaid")},
            inverseJoinColumns = {@JoinColumn(name = "tbestudianteid")})
    private Set<EntidadEstudiante> estudiantes = new HashSet<>();

    private String cuerpo;

    private String observaciones;

    @Column(name = "nombredirectivo")
    private String nombreDirectivo;

    private String cargo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbprofesorid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadProfesor profesor;

    //Borrar desde aqui


    public Set<EntidadEstudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<EntidadEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public EntidadProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(EntidadProvincia provincia) {
        this.provincia = provincia;
    }

    public EntidadEscuela getEscuela() {
        return escuela;
    }

    public void setEscuela(EntidadEscuela escuela) {
        this.escuela = escuela;
    }

    public EntidadProfesor getProfesor() {
        return profesor;
    }

    public void setProfesor(EntidadProfesor profesor) {
        this.profesor = profesor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public EntidadNombreAnno getNombreAnno() {
        return nombreAnno;
    }

    public void setNombreAnno(EntidadNombreAnno nombreAnno) {
        this.nombreAnno = nombreAnno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getNombreDirectivo() {
        return nombreDirectivo;
    }

    public void setNombreDirectivo(String nombreDirectivo) {
        this.nombreDirectivo = nombreDirectivo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
