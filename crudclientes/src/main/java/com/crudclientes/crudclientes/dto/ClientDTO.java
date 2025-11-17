package com.crudclientes.crudclientes.dto;

import com.crudclientes.crudclientes.entities.Clients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Campo nome deve ter entre 3 e 80 caracteres")
    @NotBlank(message = "Campo nome é obrigatório")
    private String name;
    private String email;
    private String phone;

    @Size(min = 3, message = "Campo endereço deve ter entre 3 e 100 caracteres")
    @NotBlank(message = "Campo endereço é obrigatório")
    private String address;

    public ClientDTO(String address, String email, Long id, String name, String phone) {
        this.address = address;
        this.email = email;
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public ClientDTO(Clients entity) {
        address = entity.getAddress();
        email = entity.getEmail();
        id = entity.getId();
        name = entity.getName();
        phone = entity.getPhone();
    }

    public ClientDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}