package br.com.itau.crudcliente.validation;

import br.com.itau.crudcliente.entity.DTO.ClienteDTO;
import br.com.itau.crudcliente.handlerException.FieldMessage;
import br.com.itau.crudcliente.service.ClienteService;
import br.com.itau.crudcliente.validation.utils.ValidationCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteDTO> {
    @Autowired
    private ClienteService service;

    @Override
    public void initialize(ClientInsert clientInsert) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        // inclua os testes aqui, inserindo erros na lista
        if (!ValidationCpfCnpj.isValidCPF(clienteDTO.getCpf())) {
            list.add(new FieldMessage("cpf", "Cpf inválido"));
        }

        if(null != service.findByCpf(clienteDTO.getCpf())){
            list.add(new FieldMessage("cpf", "Já existe um cliente com esse CPF"));
        }

        if (null != service.findByEmail(clienteDTO.getEmail())) {
            list.add(new FieldMessage("email", "Já existe um cliente com esse email"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }

}
