package aluracursos.retohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO(
        @NotBlank (message = "El nombre de usuario no puede ser vacío")
        String username,
        @NotBlank(message = "La clave debe contener entre 8 caracteres mínimo y 15 caracteres máximo") @Pattern(regexp = "\\d{8,15}")
        String password
) {
}