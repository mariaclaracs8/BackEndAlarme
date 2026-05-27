package br.edu.uninassau.alarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import br.edu.uninassau.alarme.entity.Historico;
import br.edu.uninassau.alarme.repository.HistoricoRepository;

@RestController
@RequestMapping("/historicos")
public class HistoricoController {
    @Autowired
    private HistoricoRepository historicoRepository;

    @PostMapping
    @Operation(summary = "Criar Historico", description = "Criar um historico com os detalhes fornecidos." )
    public ResponseEntity<String> criarHistorico(@RequestBody Historico historico) {
        historicoRepository.save(historico);
        return ResponseEntity.status(201).body("Registro de histórico salvo com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Buscar Historico", description = "Buscar os detalhes fornecidos no historico." )
    public ResponseEntity<List<Historico>> listarHistoricos() {
        return ResponseEntity.ok(historicoRepository.findAll());
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Historico", description = "Atualizar os detalhes fornecidos no historico pelo ID." )
    public ResponseEntity<String> atualizarHistorico(@PathVariable Long id, @RequestBody Historico atualizarHistorico) {
    	return historicoRepository.findById(id).map(historico -> {
		historico.setDataHoraPrevista(atualizarHistorico.getDataHoraPrevista());
		historico.setDataHoraRealizada(atualizarHistorico.getDataHoraRealizada());
		historico.setStatus(atualizarHistorico.getStatus());
		historico.setTratamento(atualizarHistorico.getTratamento());
		historicoRepository.save(historico);
		return ResponseEntity.ok ("Historico atualizado com Sucesso!!");
	}).orElse (ResponseEntity.notFound().build());
		
	
}

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Historico", description = "Deleta um registro de forma permanente do historico." )
    public ResponseEntity<String> deletarHistorico(@PathVariable Long id) {
    	if (historicoRepository.existsById(id)) {
		historicoRepository.deleteById(id);
		 return ResponseEntity.ok("Historico deletado com Sucesso!!");
	}
    	return ResponseEntity.status(404).body("Historico não encontrado!");
	}
}
 