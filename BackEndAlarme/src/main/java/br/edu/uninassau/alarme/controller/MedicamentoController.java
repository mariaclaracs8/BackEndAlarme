package br.edu.uninassau.alarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import br.edu.uninassau.alarme.entity.Historico;
import br.edu.uninassau.alarme.entity.Medicamento;
import br.edu.uninassau.alarme.repository.MedicamentoRepository;

@RestController
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @PostMapping
    @Operation(summary = "Cadastrar novo medicamento", description = "Adiciona um novo medicamento ao catálogo (Ex: Amoxicilina 500mg).")
    public ResponseEntity<Medicamento> criar(@RequestBody Medicamento medicamento) {
        return ResponseEntity.status(201).body(medicamentoRepository.save(medicamento));
    }

    @GetMapping
    @Operation(summary = "Listar todos os medicamentos", description = "Retorna todos os medicamentos cadastrados no sistema.")
    public ResponseEntity<List<Medicamento>> listar() {
        return ResponseEntity.ok(medicamentoRepository.findAll());
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar dados do medicamento", description = "Atualiza as informações de um medicamento existente.")
    public ResponseEntity<String> atualizarMedicamento(@PathVariable Long id, @RequestBody Medicamento atualizarMedicamento) {
    	return medicamentoRepository.findById(id).map(medicamento-> {
    	medicamento.setNome(atualizarMedicamento.getNome());
    	medicamento.setUnidadeMedida(atualizarMedicamento.getUnidadeMedida());
    	medicamento.setDescricao(atualizarMedicamento.getDescricao());
    	return ResponseEntity.ok ("Medicamento atualizado com Sucesso!!");
    }).orElse (ResponseEntity.notFound().build());
	

}

@DeleteMapping("/{id}")
@Operation(summary = "Deletar medicamento", description = "Remove permanentemente um medicamento do sistema.")
public ResponseEntity<String> deletarMedicamento(@PathVariable Long id) {
	if (medicamentoRepository.existsById(id)) {
	medicamentoRepository.deleteById(id);
	 return ResponseEntity.ok("Medicamento deletado com Sucesso!!");
}
	return ResponseEntity.status(404).body("Medicamento não encontrado!");
}
}
