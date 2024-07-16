package aluracursos.retohub.domain.topico;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TopicoDTO(
        @NotNull(message = "El título no puede quedar vacío")
        String title,
        @NotNull(message = "El mensaje no puede quedar vacío")
        String message,
        @NotNull(message = "Seleccione entre la opción  ´ACTIVO´ o ´INACTIVO´")
        Status status,
        @NotNull(message = "Valor no puede ser vacío")
        Long usuario_Id,
        @NotNull(message = "Recuerda asignarle a tu tópico el curso apropiado, no puede quedar vacío")
        String nombreCurso,
        LocalDateTime date
) {
}