package com.example.be.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {

    @Query("select id, password, nickname, img_url " +
            "from USER " +
            "where id in (:names)")
    List<User> findByNames(@Param("names") List<String> names);
}
