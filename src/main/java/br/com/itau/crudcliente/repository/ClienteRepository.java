package br.com.itau.crudcliente.repository;

import br.com.itau.crudcliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
    @Transactional(readOnly = true)
    Cliente findByEmail(String email);
    @Transactional(readOnly = true)
    Cliente findByCpf(String cpf);
}
