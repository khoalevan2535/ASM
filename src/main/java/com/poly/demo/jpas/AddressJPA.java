package com.poly.demo.jpas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.demo.enities.AddressEntity;

import java.util.List;

@Repository
public interface AddressJPA extends JpaRepository<AddressEntity, Integer> {

    // Truy vấn tất cả địa chỉ của một người dùng theo ID
    @Query("SELECT a FROM AddressEntity a WHERE a.user.id = ?1")
    List<AddressEntity> findByUserId(int userId);

    // Truy vấn địa chỉ theo loại
    @Query("SELECT a FROM AddressEntity a WHERE a.address_type = ?1")
    List<AddressEntity> findByAddressType(String addressType);
}
