package com.hackerrank.orm.service;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.repository.ItemRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ItemService {

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    @Autowired
    private ItemRepository itemRepository;



    public List<Item> getAllitems(){
        return itemRepository.findAll();
    }

    public Item createNewItem(Item item){
        if (itemRepository.findById(item.getItemId()).isPresent())
            throw new UnsupportedOperationException();
        return itemRepository.save(item);
    }

    public void deleteItembyId(Integer id){
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            itemRepository.delete(item.get());

        }
    }


    public List<Item> findByStatusAndUser(ItemStatus status, String enteredBy) {
        List<Item> findByStatusAndUser = itemRepository.findByStatusAndUser(status, enteredBy);
        return findByStatusAndUser;
    }

    public List<Item> findAllPaginated(Integer pageSize, Integer page, String sortBy) {

        Pageable paging = PageRequest.of(page, pageSize, Sort.by(sortBy));

        Page<Item> pagedResult = itemRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }

    }


    public Item updateItem(Item oldItem, Item newItem) {

        oldItem.setItemName(newItem.getItemName());
        oldItem.setItemEnteredByUser(newItem.getItemEnteredByUser());
        oldItem.setItemBuyingPrice(newItem.getItemBuyingPrice());
        oldItem.setItemBuyingPrice(newItem.getItemBuyingPrice());
        oldItem.setItemSellingPrice(newItem.getItemSellingPrice());
        oldItem.setItemLastModifiedDate(new Date());
        oldItem.setItemLastModifiedByUser(newItem.getItemLastModifiedByUser());
        oldItem.setItemStatus(newItem.getItemStatus());
        return itemRepository.save(oldItem);

    }
}
