package aluracursos.retohub.controller;

import aluracursos.retohub.domain.usuario.Usuario;
import aluracursos.retohub.domain.usuario.UsuarioDTO;
import aluracursos.retohub.infra.security.JWTTokenDTO;
import aluracursos.retohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity usuarioAutenticacion(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(usuarioDTO.username(),usuarioDTO.password());
        var autenticacionUsuario = authenticationManager.authenticate(authToken);
        var token= tokenService.generarToken((Usuario) autenticacionUsuario.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(token));
    }
}