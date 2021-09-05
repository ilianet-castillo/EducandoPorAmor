package api.reunionordinaria;

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
import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "tbreunionordinaria")
public class EntidadReunionOrdinaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String curso;

    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbprovinciaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadProvincia provincia;

    private Date fecha;

    private String lugar;

    private Time hora;

    @Column(name = "asistenciaestudiantes")
    private int asistenciaEstudiantes;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbprofesor_tbreunionordinaria",
            joinColumns = {@JoinColumn(name = "tbreunionordinariaid")},
            inverseJoinColumns = {@JoinColumn(name = "tbprofesorid")})
    private Set<EntidadProfesor> profesores = new HashSet<>();

    @Column(name = "ordendeldia")
    private String ordenDelDia;

    private String cuerpo;

    private String acuerdos;

    private String finalizacion;

    //Borrar desde aqui


    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Set<EntidadProfesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(Set<EntidadProfesor> profesores) {
        this.profesores = profesores;
    }

    public EntidadProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(EntidadProvincia provincia) {
        this.provincia = provincia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

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

    public int getAsistenciaEstudiantes() {
        return asistenciaEstudiantes;
    }

    public void setAsistenciaEstudiantes(int asistenciaEstudiantes) {
        this.asistenciaEstudiantes = asistenciaEstudiantes;
    }

    public String getOrdenDelDia() {
        return ordenDelDia;
    }

    public void setOrdenDelDia(String ordenDelDia) {
        this.ordenDelDia = ordenDelDia;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(String acuerdos) {
        this.acuerdos = acuerdos;
    }

    public String getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(String finalizacion) {
        this.finalizacion = finalizacion;
    }
}
