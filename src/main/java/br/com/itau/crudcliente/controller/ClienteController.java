package br.com.itau.crudcliente.controller;

import br.com.itau.crudcliente.entity.Cliente;
import br.com.itau.crudcliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id)throws Exception{
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> saveClient(@RequestBody Cliente cliente){
        Cliente clienteSaved = clienteService.save(cliente);
        return ResponseEntity.ok().body(clienteSaved);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody Cliente cliente, @PathVariable Integer id){
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
