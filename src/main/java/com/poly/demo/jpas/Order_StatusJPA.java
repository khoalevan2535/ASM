package com.poly.demo.jpas;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.demo.enities.Order_statusEntity;

public interface Order_StatusJPA extends JpaRepository<Order_statusEntity, Integer> {

    Optional<Order_statusEntity> findByName(String name);

    @Query("SELECT e FROM Order_statusEntity e WHERE e.name = :name AND e.id != :id")
    Optional<Order_statusEntity> findByNameAndNotId(@Param("name") String name, @Param("id") Integer id);
}