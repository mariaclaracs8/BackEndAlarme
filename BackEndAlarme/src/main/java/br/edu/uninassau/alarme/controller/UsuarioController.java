package br.edu.uninassau.alarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import br.edu.uninassau.alarme.entity.Tratamento;
import br.edu.uninassau.alarme.entity.Usuario;
import br.edu.uninassau.alarme.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Operation(summary = "Criar Usuario", description = "Criar um usuario com os detalhes fornecidos." )
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
        return ResponseEntity.status(201).body("Usuário criado com sucesso no banco de dados!");
    }

    @GetMapping
    @Operation(summary = "Buscar Usuario", description = "Buscar os detalhes fornecidos pelo usuario." )
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> lista = usuarioRepository.findAll();
        return ResponseEntity.ok(lista);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuario", description = "Atualizar os detalhes fornecidos pelo usuario através do ID." )
    public ResponseEntity<String> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario atualizarUsuario) {
    	return usuarioRepository.findById(id).map(usuario-> {
    		usuario.setNome(atualizarUsuario.getNome());
    		usuario.setEmail(atualizarUsuario.getEmail());
    		usuario.setSenha(atualizarUsuario.getSenha());
    		usuario.setFusoHorario(atualizarUsuario.getFusoHorario());
    	return ResponseEntity.ok ("Usuario atualizado com Sucesso!!");
    }).orElse (ResponseEntity.notFound().build());
    	

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuario", description = "Deleta um registro de forma permanente do usuario." )
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
    	if (usuarioRepository.existsById(id)) {
    	usuarioRepository.deleteById(id);
    	 return ResponseEntity.ok("Usuario deletado com Sucesso!!");
    }
    	return ResponseEntity.status(404).body("Usuario não encontrado!");
    }
    }
