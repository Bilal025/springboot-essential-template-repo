package com.example.jdbctemplate.mongo.repository;

import com.example.jdbctemplate.mongo.model.ProductMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryMongo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<ProductMongo> findAll() {
        return mongoTemplate.findAll(ProductMongo.class);
    }

    public ProductMongo findById(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("id").is(id)), ProductMongo.class);
    }

    public void save(ProductMongo product) {
        mongoTemplate.save(product);
    }

    public long updatePrice(String id, double price) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("price", price);
        return mongoTemplate.updateFirst(query, update, ProductMongo.class).getModifiedCount();
    }

    public long deleteById(String id) {
        return mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), ProductMongo.class).getDeletedCount();
    }
}
