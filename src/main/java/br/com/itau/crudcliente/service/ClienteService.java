package br.com.itau.crudcliente.service;

import br.com.itau.crudcliente.entity.Cliente;
import br.com.itau.crudcliente.entity.DTO.ClienteDTO;
import br.com.itau.crudcliente.repository.ClienteRepository;
import br.com.itau.crudcliente.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente findById(Integer id) throws ObjectNotFoundException {
        return clienteRepository.findById(id).orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Cliente.class.getSimpleName()));
    }
    public Optional<Cliente> findByIdValidation(Integer id) throws ObjectNotFoundException {
        return clienteRepository.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        findById(cliente.getId());
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        findById(id);
        clienteRepository.deleteById(id);
    }

    public Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(clienteDTO.getNomeCliente(),clienteDTO.getEmail(),clienteDTO.getCpf());
    }

    public Cliente findByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    public Cliente findByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
