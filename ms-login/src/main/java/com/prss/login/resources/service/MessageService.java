package com.prss.login.resources.service;

import com.prss.registers.domain.dao.Messages;
import com.prss.registers.domain.dto.message.PostMessageRequest;
import com.prss.registers.domain.dto.message.PutMessageRequest;
import com.prss.registers.resources.mapper.RegisterMappers;
import com.prss.registers.resources.respository.MessageRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;
    @Autowired
    private RegisterMappers map;

    public void registerMessage(PostMessageRequest request) {
        repository.save(request.toDomain());
    }

    public void updateMessage(PutMessageRequest request) throws ObjectNotFoundException {
        Messages savedIngredients = this.findMessage(request.getId());

        map.updateExcludeNull(request.toDomain(), savedIngredients);
        repository.save(savedIngredients);
    }

    public Messages findMessage(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ Messages.class.getName()));
    }

    public List<Messages> listMessage() {
        return repository.findAll();
    }
}
