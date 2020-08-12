package br.com.itau.crudcliente.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_cliente")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @Column(name = "nm_cliente",length = 200)
    private String nomeCliente;

    @Column(name = "ds_email")
    private String email;

    @Column(name =  "ds_cpf", length = 11)
    private String cpf;

    public Cliente() {
    }

    public Cliente(Integer id, String nomeCliente, String email, String cpf) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.cpf = cpf;
    }

    public Cliente(String nomeCliente, String email, String cpf) {
        this.nomeCliente = nomeCliente;
        this.email = email;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
