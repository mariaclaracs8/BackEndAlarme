package br.edu.uninassau.alarme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

import br.edu.uninassau.alarme.entity.Perfil;
import br.edu.uninassau.alarme.entity.Tratamento;
import br.edu.uninassau.alarme.repository.TratamentoRepository;

@RestController
@RequestMapping("/tratamentos")
public class TratamentoController {

    @Autowired
    private TratamentoRepository tratamentoRepository;

    @PostMapping
    @Operation(summary = "Criar novo tratamento", description = "Vincula um medicamento a uma regra de horários, frequências e alarmes para o paciente.")
    public ResponseEntity<String> criarTratamento(@RequestBody Tratamento tratamento) {
        tratamentoRepository.save(tratamento);
        return ResponseEntity.status(201).body("Tratamento agendado com sucesso!");
    }

    @GetMapping
    @Operation(summary = "Listar todos os tratamentos", description = "Retorna o histórico completo de todos os tratamentos cadastrados.")
    public ResponseEntity<List<Tratamento>> listarTratamentos() {
        return ResponseEntity.ok(tratamentoRepository.findAll());
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar tratamento", description = "Permite alterar a duração, horários ou medicamentos de um tratamento ativo.")
    public ResponseEntity<String> atualizarTratamento(@PathVariable Long id, @RequestBody Tratamento atualizarTratamento) {
    	return tratamentoRepository.findById(id).map(tratamento-> {
    		tratamento.setDataInicio(atualizarTratamento.getDataInicio());
    		tratamento.setDataFim(atualizarTratamento.getDataFim());
    		tratamento.setIntervaloHoras(atualizarTratamento.getIntervaloHoras());
    		tratamento.setSomAlarme(atualizarTratamento.getSomAlarme());
    		tratamento.setNivelPrioridade(atualizarTratamento.getNivelPrioridade());
    		tratamento.setStatus(atualizarTratamento.getStatus());
    		tratamento.setPerfil(atualizarTratamento.getPerfil());
    		tratamento.setMedicamento(atualizarTratamento.getMedicamento());
    	return ResponseEntity.ok ("Tratamento atualizado com Sucesso!!");
    }).orElse (ResponseEntity.notFound().build());
    	

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir tratamento", description = "Remove o tratamento e cancela todos os seus alarmes futuros.")
    public ResponseEntity<String> deletarTratamento(@PathVariable Long id) {
    	if (tratamentoRepository.existsById(id)) {
    	tratamentoRepository.deleteById(id);
    	 return ResponseEntity.ok("Tratamento deletado com Sucesso!!");
    }
    	return ResponseEntity.status(404).body("Tratamento não encontrado!");
    }
    }
