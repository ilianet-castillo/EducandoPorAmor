package api.activoalumnos;

import api.estudiante.EntidadEstudiante;
import api.profesor.EntidadProfesor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbactivodealumnos")
public class EntidadActivoAlumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date fecha;

    private String lugar;

    private Time hora;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbprofesor_tbactivodealumnos",
            joinColumns = {@JoinColumn(name = "tbactivodealumnosid")},
            inverseJoinColumns = {@JoinColumn(name = "tbprofesorid")})
    private Set<EntidadProfesor> profesores = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbestudiante_tbactivodealumnos_presentes",
            joinColumns = {@JoinColumn(name = "tbactivodealumnosid")},
            inverseJoinColumns = {@JoinColumn(name = "tbestudianteid")})
    private Set<EntidadEstudiante> estudiantesPresentes = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbestudiante_tbactivodealumnos_ausentes",
            joinColumns = {@JoinColumn(name = "tbactivodealumnosid")},
            inverseJoinColumns = {@JoinColumn(name = "tbestudianteid")})
    private Set<EntidadEstudiante> estudiantesAusentes = new HashSet<>();

    @Column(name = "consideraciones_generales")
    private String consideracionesGenerales;

    @Column(name = "planteamientos_realizados")
    private String planteamientosRealizados;

    //borrar desde aqui


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Set<EntidadEstudiante> getEstudiantesAusentes() {
        return estudiantesAusentes;
    }

    public void setEstudiantesAusentes(Set<EntidadEstudiante> estudiantesAusentes) {
        this.estudiantesAusentes = estudiantesAusentes;
    }

    public Set<EntidadEstudiante> getEstudiantesPresentes() {
        return estudiantesPresentes;
    }

    public void setEstudiantesPresentes(Set<EntidadEstudiante> estudiantesPresentes) {
        this.estudiantesPresentes = estudiantesPresentes;
    }

    public Set<EntidadProfesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<EntidadProfesor> profesores) {
        this.profesores = profesores;
    }

    public String getConsideracionesGenerales() {
        return consideracionesGenerales;
    }

    public void setConsideracionesGenerales(String consideracionesGenerales) {
        this.consideracionesGenerales = consideracionesGenerales;
    }

    public String getPlanteamientosRealizados() {
        return planteamientosRealizados;
    }

    public void setPlanteamientosRealizados(String planteamientosRealizados) {
        this.planteamientosRealizados = planteamientosRealizados;
    }
}