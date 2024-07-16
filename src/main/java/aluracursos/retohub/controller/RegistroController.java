package aluracursos.retohub.controller;

import aluracursos.retohub.domain.usuario.RegistroUsuarioDTO;
import aluracursos.retohub.domain.usuario.Usuario;
import aluracursos.retohub.domain.usuario.UsuarioRepository;
import aluracursos.retohub.domain.usuario.RespuestaUsuarioDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity registroUsuario(@RequestBody @Valid RegistroUsuarioDTO registroUsuarioDTO, UriComponentsBuilder uriComponentsBuilder){
        try {
            Usuario usuario = usuarioRepository.save(new Usuario(registroUsuarioDTO,bCryptPasswordEncoder));
            RespuestaUsuarioDTO respuestaUsuarioDTO = new RespuestaUsuarioDTO(
                    usuario.getId(), usuario.getName()
            );
            URI url = uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
            return ResponseEntity.created(url).body(respuestaUsuarioDTO);
        } catch (ConstraintViolationException ex) {
            return ResponseEntity.badRequest().body("Error de autentificación: " + ex.getMessage());
        }
    }

}