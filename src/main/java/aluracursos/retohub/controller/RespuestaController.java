package aluracursos.retohub.controller;

import aluracursos.retohub.domain.respuesta.*;
import aluracursos.retohub.domain.topico.TopicoRepository;
import aluracursos.retohub.domain.usuario.UsuarioRepository;
import aluracursos.retohub.infra.errors.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/respuesta")
@SecurityRequirement(name="bearer-key")
public class RespuestaController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaService respuestaService;
    @Autowired
    private RespuestaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity respuestaRegistrada (@RequestBody @Valid RespuestaDTO respuestaDTO) throws ValidacionDeIntegridad {
        var respuestaRegistrada = respuestaService.respuestaCreadaDTO(respuestaDTO);
        return ResponseEntity.ok(respuestaRegistrada);
    }

    @GetMapping("/respuestas")
    public ResponseEntity<Page<ListarRespuestasDTO>>  listarRespuestas(@PageableDefault(size = 20) Pageable paged){
        return ResponseEntity.ok(repository.findByActiveTrue(paged).map(ListarRespuestasDTO::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity respuestaActualizada(@RequestBody @Valid RespuestaActualizadaDTO respuestaActualizadaDTO){
        Respuesta respuesta=repository.getReferenceById(respuestaActualizadaDTO.id());
        respuesta.respuestaActualizada(respuestaActualizadaDTO);
        return ResponseEntity.ok(new RespuestaCreadaDTO(respuesta.getId(),respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta = repository.getReferenceById(id);
        respuesta.diactivateResponse();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity <RespuestaCreadaDTO> respuestaCreada(@PathVariable Long id){
        Respuesta respuesta=repository.getReferenceById(id);
        var respuestaRegistrada = new RespuestaCreadaDTO(respuesta.getId(),
                respuesta.getSolution(),
                respuesta.getAuthor().getId(),
                respuesta.getTopico().getId(),
                respuesta.getCreationDate());
        return ResponseEntity.ok(respuestaRegistrada);
    }
}