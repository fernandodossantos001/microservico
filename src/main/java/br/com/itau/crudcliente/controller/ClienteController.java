package br.com.itau.crudcliente.controller;

import br.com.itau.crudcliente.entity.Cliente;
import br.com.itau.crudcliente.entity.DTO.ClienteDTO;
import br.com.itau.crudcliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id)throws Exception{
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> saveClient(@RequestBody @Valid ClienteDTO clienteDTO){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        Cliente clienteSaved = clienteService.save(cliente);
        return ResponseEntity.ok().body(clienteSaved);
    }
    @PutMapping("/atualizarCliente/{id}")
    public ResponseEntity<Void> updateClient(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable Integer id){
        Cliente cliente = clienteService.fromDTO(clienteDTO);
        cliente.setId(id);
        clienteService.update(cliente);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/excluirCliente/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
