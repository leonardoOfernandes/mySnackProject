package com.prss.order.manager.resources.service;

import com.prss.order.manager.domain.dao.MenuItems;
import com.prss.order.manager.resources.respository.MenuitemRepository;
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

    public MenuItems findMenuItems(Integer id) throws ObjectNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! id: " + id + ", Tipo: "+ MenuItems.class.getName()));
    }

    public List<MenuItems> listMenuItems() {
        return repository.findAll();
    }
}
