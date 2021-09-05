package api.profesor;

import api.facultad.EntidadFacultad;
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
@Table(name = "tbprofesor")
public class EntidadProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String apellidos;

    @Column(name = "categoriacientifica")
    private String categoriaCientifica;

    private String telefono;

    private String sexo;

    @Column(name = "correoelectronico")
    private String correoElectronico;

    private boolean habilitado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tbfacultadid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private EntidadFacultad facultad;

    //Borrar desde aqui

    public EntidadFacultad getFacultad() {
        return facultad;
    }

    public void setFacultad(EntidadFacultad facultad) {
        this.facultad = facultad;
    }

    public String getCategoriaCientifica() {
        return categoriaCientifica;
    }

    public void setCategoriaCientifica(String categoriaCientifica) {
        this.categoriaCientifica = categoriaCientifica;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
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

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}
