package br.com.itau.crudcliente.entity.DTO;

import br.com.itau.crudcliente.validation.ClientInsert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ClientInsert
public class ClienteDTO implements Serializable {
    @NotNull(message = "Preenchimento Obrigatório")
    @NotBlank(message = "o campo nomeCliente não pode estar vazio")
    private String nomeCliente;
    @Email(message = "Email Inválido")
    @NotNull(message = "Preenchimento Obrigatório")
    private String email;
    @NotNull(message = "Preenchimento Obrigatório")
    private String cpf;

    public ClienteDTO(@NotNull(message = "Preenchimento Obrigatório") @NotBlank(message = "o campo nomeCliente não pode estar vazio") String nomeCliente, @Email(message = "Email Inválido") @NotNull(message = "Preenchimento Obrigatório") String email, @NotNull(message = "Preenchimento Obrigatório") String cpf) {
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.cpf = cpf;
    }

    public ClienteDTO() {
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
