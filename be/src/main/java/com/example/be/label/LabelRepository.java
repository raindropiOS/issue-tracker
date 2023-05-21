package com.example.be.label;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, String> {

    @Query("select name, description, background_color, text_color " +
            "from LABEL " +
            "where name in (:names)")
    List<Label> findByNames(@Param("names") List<String> names);
}
