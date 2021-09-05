package api.estudiante;

import api.escuela.EntidadEscuela;
import api.estado.EntidadEstado;
import api.facultad.EntidadFacultad;
import api.gradoescolar.EntidadGradoEscolar;
import api.grupoclase.EntidadGrupoClase;
import api.militancia.EntidadMilitancia;
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
@Table(name = "tbestudiante")
public class EntidadEstudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String apellidos;

    private long ci;

    private String solapin;

    private String telefono;

    private String sexo;

    @Column(name = "direccionparticular")
    private String direccionParticular;

    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbestadoid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadEstado estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbfacultadid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadFacultad facultad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbmilitanciaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadMilitancia militancia;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbgradoescolarid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadGradoEscolar gradoEscolar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbescuelaid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadEscuela escuela;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tbgrupoclaseid")
    private EntidadGrupoClase grupoClase;

    //Borrar desde aqui


    public int getId() {
        return id;
    }

    public EntidadGrupoClase getGrupoClase() {
        return grupoClase;
    }

    public void setGrupoClase(EntidadGrupoClase grupoClase) {
        this.grupoClase = grupoClase;
    }

    public EntidadMilitancia getMilitancia() {
        return militancia;
    }

    public void setMilitancia(EntidadMilitancia militancia) {
        this.militancia = militancia;
    }

    public EntidadGradoEscolar getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(EntidadGradoEscolar gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public EntidadEscuela getEscuela() {
        return escuela;
    }

    public void setEscuela(EntidadEscuela escuela) {
        this.escuela = escuela;
    }

    public EntidadEstado getEstado() {
        return estado;
    }

    public void setEstado(EntidadEstado estado) {
        this.estado = estado;
    }

    public EntidadFacultad getFacultad() {
        return facultad;
    }

    public void setFacultad(EntidadFacultad facultad) {
        this.facultad = facultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public long getCi() {
        return ci;
    }

    public void setCi(long ci) {
        this.ci = ci;
    }

    public String getSolapin() {
        return solapin;
    }

    public void setSolapin(String solapin) {
        this.solapin = solapin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDireccionParticular() {
        return direccionParticular;
    }

    public void setDireccionParticular(String direccionParticular) {
        this.direccionParticular = direccionParticular;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
