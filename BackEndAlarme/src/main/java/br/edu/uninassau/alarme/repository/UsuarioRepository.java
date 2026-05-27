package br.edu.uninassau.alarme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.uninassau.alarme.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}