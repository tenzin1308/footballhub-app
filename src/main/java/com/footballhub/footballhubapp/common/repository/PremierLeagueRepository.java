package com.footballhub.footballhubapp.common.repository;

import com.footballhub.footballhubapp.common.models.PremierLeagueModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PremierLeagueRepository extends MongoRepository<PremierLeagueModel, ObjectId> {
}
