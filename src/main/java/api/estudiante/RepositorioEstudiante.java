package api.estudiante;

import api.escuela.EntidadEscuela;
import api.estado.EntidadEstado;
import api.facultad.EntidadFacultad;
import api.gradoescolar.EntidadGradoEscolar;
import api.grupoclase.EntidadGrupoClase;
import api.militancia.EntidadMilitancia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioEstudiante extends JpaRepository<EntidadEstudiante, Integer> {

    List<EntidadEstudiante> getAllByEstado(EntidadEstado estado);

    List<EntidadEstudiante> getAllByEscuela(EntidadEscuela escuela);

    List<EntidadEstudiante> getAllByFacultad(EntidadFacultad facultad);

    List<EntidadEstudiante> getAllByGradoEscolar(EntidadGradoEscolar gradoEscolar);

    List<EntidadEstudiante> getAllByMilitancia(EntidadMilitancia militancia);

    List<EntidadEstudiante> getAllByGradoEscolarAndFacultadAndMilitancia(EntidadGradoEscolar gradoEscolar, EntidadFacultad facultad, EntidadMilitancia militancia);

    boolean existsByGrupoClase(EntidadGrupoClase grupoClase);

}
