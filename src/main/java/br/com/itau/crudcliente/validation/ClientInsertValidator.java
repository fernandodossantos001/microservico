package br.com.itau.crudcliente.validation;

import br.com.itau.crudcliente.entity.Cliente;
import br.com.itau.crudcliente.entity.DTO.ClienteDTO;
import br.com.itau.crudcliente.handlerException.FieldMessage;
import br.com.itau.crudcliente.service.ClienteService;
import br.com.itau.crudcliente.validation.utils.ValidationCpfCnpj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteDTO> {
    @Autowired
    private ClienteService service;

    @Autowired
    private HttpServletRequest request;

    private Cliente cliente;

    @Override
    public void initialize(ClientInsert clientInsert) {
    }

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();




        if("POST".equals(request.getMethod())){
            list = validadePost(list,clienteDTO);
        }else if ("PUT".equals(request.getMethod())){
            list = validadePut(list,clienteDTO);
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }

    private List<FieldMessage> validadePut(List<FieldMessage> list, ClienteDTO clienteDTO) {
         Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            cliente = service.findById(Integer.parseInt(map.get("id")));

        if (!ValidationCpfCnpj.isValidCPF(clienteDTO.getCpf())) {
            list.add(new FieldMessage("cpf", "Cpf inválido"));
        }

        if(null != service.findByCpf(clienteDTO.getCpf()) && !service.findByCpf(clienteDTO.getCpf()).getCpf().equals(cliente.getCpf()) ){
            list.add(new FieldMessage("cpf", "Já existe um cliente com esse CPF"));
        }

        if(null != service.findByEmail(clienteDTO.getEmail()) && !service.findByEmail(clienteDTO.getEmail()).getEmail().equals(clienteDTO.getEmail())){
            list.add(new FieldMessage("email", "Já existe um cliente com esse email"));
        }
        return list;
    }

    private List<FieldMessage> validadePost(List<FieldMessage> list, ClienteDTO clienteDTO){

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

        return list;
    }

}
