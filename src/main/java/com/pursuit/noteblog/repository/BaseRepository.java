package com.pursuit.noteblog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pursuit.noteblog.entity.BaseEntity;


public interface BaseRepository<T extends BaseEntity> extends MongoRepository<T, String> {

}