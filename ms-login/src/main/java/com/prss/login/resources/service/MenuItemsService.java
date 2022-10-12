package com.prss.login.resources.service;

import com.prss.registers.domain.dao.MenuItems;
import com.prss.registers.domain.dto.menuitems.PostMenuItemsRequest;
import com.prss.registers.domain.dto.menuitems.PutMenuItemsRequest;
import com.prss.registers.resources.mapper.RegisterMappers;
import com.prss.registers.resources.respository.MenuitemRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MenuItemsService {
    @Autowired
    private MenuitemRepository repository;
    @Autowired
    private RegisterMappers map;

    public void registerMenuItems(PostMenuItemsRequest request) {
        repository.save(request.toDomain());
    }

    public void updateMenuItems(PutMenuItemsRequest request) throws ObjectNotFoundException {
        MenuItems savedMenuItems = this.findMenuItems(request.getId());

        map.updateExcludeNull(request.toDomain(), savedMenuItems);
        repository.save(savedMenuItems);
    }

    public MenuItems findMenuItems(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ MenuItems.class.getName()));
    }

    public List<MenuItems> listMenuItems() {
        return repository.findAll();
    }
}
