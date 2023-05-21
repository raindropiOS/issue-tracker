package com.example.be.milestone;

import org.springframework.data.repository.CrudRepository;

public interface MilestoneRepository extends CrudRepository<Milestone, String> {

    Milestone findByName(String name);
}
