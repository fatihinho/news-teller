package com.fcinar.newsteller.repository;

import com.fcinar.newsteller.model.New;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewRepository extends MongoRepository<New, String> {
    List<New> findByTitleContaining(String title);

    List<New> findByPublished(Boolean published);
}
