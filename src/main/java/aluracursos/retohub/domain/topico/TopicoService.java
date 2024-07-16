package aluracursos.retohub.domain.topico;

import aluracursos.retohub.domain.usuario.UsuarioRepository;
import aluracursos.retohub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaTopicoDTO topicoCreado(TopicoDTO topicoDTO){
        if (!usuarioRepository.findById(topicoDTO.usuario_Id()).isPresent()){
            throw new ValidacionDeIntegridad("El ID de usuario ingresado no se encontró");
        }
        var title= topicoDTO.title();
        if (title != null && topicoRepository.existsByTitleIgnoreCase(title)){
            throw new ValidacionDeIntegridad("El título ingresado ya está registrado. Escriba un tópico diferente");
        }
        String message = topicoDTO.message();
        if (message != null && topicoRepository.existsByMessageIgnoreCase(message)){
            throw new ValidacionDeIntegridad("El mensaje ingresado ya está registrado. Modifiquelo e intente nuevamente");
        }
        var usuario = usuarioRepository.findById(topicoDTO.usuario_Id()).get();
        var topicoId= new Topico(null,title,message,topicoDTO.date(),topicoDTO.status(),usuario,topicoDTO.nombreCurso());
        topicoRepository.save(topicoId);
        return new RespuestaTopicoDTO(topicoId);
    }
}