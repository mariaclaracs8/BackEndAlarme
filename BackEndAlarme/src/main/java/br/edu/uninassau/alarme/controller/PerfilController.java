package br.edu.uninassau.alarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import br.edu.uninassau.alarme.entity.Medicamento;
import br.edu.uninassau.alarme.entity.Perfil;
import br.edu.uninassau.alarme.repository.PerfilRepository;


@RestController
@RequestMapping("/perfis")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    @Operation(summary = "Criar Perfil", description = "Criar um perfil com os detalhes fornecidos." )
    public ResponseEntity<String> criarPerfil(@RequestBody Perfil perfil) {
        perfilRepository.save(perfil);
        return ResponseEntity.status(201).body("Perfil criado com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Buscar Perfil", description = "Buscar os detalhes fornecidos no perfil." )
    public ResponseEntity<List<Perfil>> listarPerfis() {
        return ResponseEntity.ok(perfilRepository.findAll());
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar perfil", description = "Atualizar os detalhes fornecidos no perfil pelo ID." )
    public ResponseEntity<String> atualizarPerfil(@PathVariable Long id, @RequestBody Perfil atualizarPerfil) {
    	return perfilRepository.findById(id).map(perfil-> {
    		perfil.setNome(atualizarPerfil.getNome());
    		perfil.setUsuario(atualizarPerfil.getUsuario());
    		perfil.setCorAvatar(atualizarPerfil.getCorAvatar());
    	return ResponseEntity.ok ("Perfil atualizado com Sucesso!!");
    }).orElse (ResponseEntity.notFound().build());
    	

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Perfil", description = "Deleta um registro de forma permanente no perfil." )
    public ResponseEntity<String> deletarPerfil(@PathVariable Long id) {
    	if (perfilRepository.existsById(id)) {
    	perfilRepository.deleteById(id);
    	 return ResponseEntity.ok("Perfil deletado com Sucesso!!");
    }
    	return ResponseEntity.status(404).body("Perfil não encontrado!");
    }
    }
