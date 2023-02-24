package com.clinic.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clinic.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("from User where name like %?1%")
    ArrayList<User> findByName(String name);

    @Query("from User where login = (:login)")
    User findByLogin(@Param("login") String login);
}
