package api.activoalumnos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioActivoAlumno extends JpaRepository<EntidadActivoAlumno, Integer> {
}
