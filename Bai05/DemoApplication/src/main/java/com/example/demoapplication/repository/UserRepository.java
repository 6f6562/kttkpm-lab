package com.example.demoapplication.repository;

import com.example.demoapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "EXEC sp_search_users @keyword = :keyword", nativeQuery = true)
    List<User> searchByStoredProcedure(@Param("keyword") String keyword);
}
