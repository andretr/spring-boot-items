package com.hackerrank.orm.service;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ItemService {

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    @Autowired
    private ItemRepository itemRepository;



    public List<Item> getAllitems(){
        return null;
    }

    public Item createNewItem(Item item){
        return null;
    }

    public void deleteItembyId(Integer id){

    }


    public List<Item> findByStatusAndUser(ItemStatus status, String enteredBy) {
       return null;
    }

    public List<Item> findAllPaginated(Integer pageSize, Integer page, String sortBy) {

        return null;

    }


}
