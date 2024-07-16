package aluracursos.retohub.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizacionUsuarioDTO(
        @NotNull 
        Long id,
        String name
) {
}