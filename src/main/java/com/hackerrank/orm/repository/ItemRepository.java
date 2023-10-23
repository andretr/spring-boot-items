package com.hackerrank.orm.repository;

import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i " +
            "FROM Item i " +
            "WHERE i.itemStatus = :status " +
            "AND i.itemEnteredByUser = :enteredBy " +
            "GROUP BY i.itemStatus, i.itemId")
    List<Item> findByStatusAndUser(ItemStatus status, String enteredBy);
}
