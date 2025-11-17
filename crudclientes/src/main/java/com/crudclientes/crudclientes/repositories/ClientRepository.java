package com.crudclientes.crudclientes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudclientes.crudclientes.entities.Clients;

public interface ClientRepository extends JpaRepository<Clients, Long> {
    }

