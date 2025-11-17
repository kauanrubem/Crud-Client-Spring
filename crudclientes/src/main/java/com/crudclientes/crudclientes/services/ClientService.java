package com.crudclientes.crudclientes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crudclientes.crudclientes.dto.ClientDTO;
import com.crudclientes.crudclientes.entities.Clients;
import com.crudclientes.crudclientes.repositories.ClientRepository;
import com.crudclientes.crudclientes.services.exceptions.DataBaseException;
import com.crudclientes.crudclientes.services.exceptions.resourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {    
        Clients client = repository.findById(id).orElseThrow(() -> new DataBaseException("Entity not found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Clients> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        
        Clients entity = new Clients();

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());

        entity = repository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
    try{
        Clients entity = repository.getReferenceById(id);
        copyDTOToEntity(dto, entity);
        entity = repository.save(entity);
        return new ClientDTO(entity);
    }
    catch (EntityNotFoundException e) {
            throw new resourceNotFoundException("Entity not found");
        }
    }

    @Transactional
    public void delete(Long id) {    
        if (!repository.existsById(id)) {
            throw new DataBaseException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Falha de integridade referencial");
        }
    }

    private void copyDTOToEntity(ClientDTO dto, Clients entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
    }
    
}
