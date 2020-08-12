package br.com.itau.crudcliente.service;

import br.com.itau.crudcliente.entity.Cliente;
import br.com.itau.crudcliente.repository.ClienteRepository;
import br.com.itau.crudcliente.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public Cliente findById(Integer id) throws ObjectNotFoundException {
        return clienteRepository.findById(id).orElseThrow(() ->new ObjectNotFoundException("Objeto não encontrado id " + id + ", tipo " + Cliente.class.getSimpleName()));
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        findById(id);
        clienteRepository.deleteById(id);
    }
}
