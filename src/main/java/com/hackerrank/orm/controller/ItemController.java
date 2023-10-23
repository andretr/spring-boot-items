package com.hackerrank.orm.controller;

import com.hackerrank.orm.enums.ItemStatus;
import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/app/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    //1. insert POST
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postItem(@RequestBody Item item) {

       return null;
    }

    //2. update PUT
    @RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@PathVariable("itemId") Integer id, @RequestBody Item newItem) {

        return null;
    }

    //3. delete by itemId DELETE
    @RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItemById(@PathVariable("itemId") Integer id) {

        return null;
    }

    //4. delete all DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteAllItems() {

        return null;
    }

    //5. get by itemId GET
    @RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
    public ResponseEntity getItemById(@PathVariable("itemId") Integer id) {

        return null;
    }

    //6. get all GET
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllItems() {
        return null;
    }

    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={enteredBy} GET
    @RequestMapping(params = { "itemStatus", "itemEnteredByUser" }, method = RequestMethod.GET)
    public ResponseEntity filterItemById(@RequestParam("itemStatus") ItemStatus status, @RequestParam("itemEnteredByUser") String enteredBy) {

        return null;
    }

    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET
    @RequestMapping(params = { "pageSize", "page", "sortBy" }, method = RequestMethod.GET)
    public ResponseEntity findAllPaginated(@RequestParam("pageSize") Integer pageSize, @RequestParam("page") Integer page, @RequestParam("sortBy") String sortBy) {

        return null;
    }
}
