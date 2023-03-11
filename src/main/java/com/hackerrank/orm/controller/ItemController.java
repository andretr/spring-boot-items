package com.hackerrank.orm.controller;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/app/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    //1. insert POST
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postItem(@RequestBody Item item) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(itemService.createNewItem(item));
        }catch (UnsupportedOperationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //2. update PUT
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@PathVariable("itemId") Integer id, @RequestBody Item newItem) {

        Optional<Item> item = itemService.getItemRepository().findById(id);
        if(item.isPresent()){
            return  ResponseEntity.ok(itemService.updateItem(item.get(),newItem));
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //3. delete by itemId DELETE
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItemById(@PathVariable("itemId") Integer id) {

        Optional<Item> item = itemService.getItemRepository().findById(id);
        if(item.isPresent()){
            itemService.getItemRepository().delete(item.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //4. delete all DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteAllItems() {

        itemService.getItemRepository().deleteAll();
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    //5. get by itemId GET
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity getItemById(@PathVariable("itemId") Integer id) {

        Optional<Item> item = itemService.getItemRepository().findById(id);
        if(item.isPresent())
            return ResponseEntity.ok(item);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //6. get all GET
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllItems() {
        try {
            return ResponseEntity.ok(itemService.getAllitems());
        } catch (Exception e){
            log.error("Unexpected error while calling getAllItems", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={enteredBy} GET
    @RequestMapping(params = { "itemStatus", "itemEnteredByUser" }, method = RequestMethod.GET)
    public ResponseEntity filterItemById(@RequestParam("itemStatus") ItemStatus status, @RequestParam("itemEnteredByUser") String enteredBy) {

        List<Item> itemsList = itemService.findByStatusAndUser(status, enteredBy);

        return ResponseEntity.ok(itemsList);
    }

    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET
    @RequestMapping(params = { "pageSize", "page", "sortBy" }, method = RequestMethod.GET)
    public ResponseEntity findAllPaginated(@RequestParam("pageSize") Integer pageSize, @RequestParam("page") Integer page, @RequestParam("sortBy") String sortBy) {

        List<Item> itemsList = itemService.findAllPaginated(pageSize, page, sortBy);
        return ResponseEntity.ok(itemsList);
    }
}
