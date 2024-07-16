package aluracursos.retohub.domain.respuesta;

import aluracursos.retohub.domain.topico.TopicoRepository;
import aluracursos.retohub.domain.usuario.UsuarioRepository;
import aluracursos.retohub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository repository;

    public RespuestaCreadaDTO respuestaCreadaDTO(RespuestaDTO respuestaDTO) {
        if (!usuarioRepository.findById(respuestaDTO.usuario_Id()).isPresent()){
            throw new ValidacionDeIntegridad("El ID introducido no se encontró entre los usuarios");
        }
        if (!topicoRepository.findById(respuestaDTO.topico_Id()).isPresent()){
            throw new ValidacionDeIntegridad("El ID de tópico no se encontró");
        }
        var usuario = usuarioRepository.findById(respuestaDTO.usuario_Id()).get();
        var topico =topicoRepository.findById(respuestaDTO.topico_Id()).get();

        var rVerified= new Respuesta(null,respuestaDTO.solution(),usuario,topico,respuestaDTO.creationDate());
        repository.save(rVerified);
        return new RespuestaCreadaDTO(rVerified);
    }

}