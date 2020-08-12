package br.com.itau.crudcliente.repository;

import br.com.itau.crudcliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
