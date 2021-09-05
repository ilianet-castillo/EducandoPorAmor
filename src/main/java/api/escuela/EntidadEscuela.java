package api.escuela;

import api.asignatura.EntidadAsignatura;
import api.gradoescolar.EntidadGradoEscolar;
import api.municipio.EntidadMunicipio;
import api.nivelensennanza.EntidadNivelEnsennanza;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tbescuela")
public class EntidadEscuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbmunicipioid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadMunicipio municipio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbnivelensennanzaid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadNivelEnsennanza nivelEnsennanza;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbgradoescolar_tbescuela",
            joinColumns = {@JoinColumn(name = "tbescuelaid")},
            inverseJoinColumns = {@JoinColumn(name = "tbgradoescolarid")})
    private Set<EntidadGradoEscolar> gradosEscolares = new HashSet<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbasignatura_tbescuela",
            joinColumns = {@JoinColumn(name = "tbescuelaid")},
            inverseJoinColumns = {@JoinColumn(name = "tbasignaturaid")})
    private Set<EntidadAsignatura> asignaturas = new HashSet<>();

    //Borrar desde aqui

    public Set<EntidadAsignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Set<EntidadAsignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public EntidadNivelEnsennanza getNivelEnsennanza() {
        return nivelEnsennanza;
    }

    public int getId() {
        return id;
    }

    public void setNivelEnsennanza(EntidadNivelEnsennanza nivelEnsennanza) {
        this.nivelEnsennanza = nivelEnsennanza;
    }

    public EntidadMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(EntidadMunicipio municipio) {
        this.municipio = municipio;
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

    public Set<EntidadGradoEscolar> getGradosEscolares() {
        return gradosEscolares;
    }

    public void setGradosEscolares(Set<EntidadGradoEscolar> gradosEscolares) {
        this.gradosEscolares = gradosEscolares;
    }
}
