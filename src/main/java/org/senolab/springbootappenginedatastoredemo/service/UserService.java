package org.senolab.springbootappenginedatastoredemo.service;

import com.google.cloud.datastore.*;
import org.senolab.springbootappenginedatastoredemo.exception.UserNotFoundException;
import org.senolab.springbootappenginedatastoredemo.model.BatchUser;
import org.senolab.springbootappenginedatastoredemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final String KIND = "User";

    @Autowired
    Datastore datastore;

    private KeyFactory userKeyFactory;


    public List<User> getAllUsers() {
        Query<Entity> query =
                Query.newGqlQueryBuilder(Query.ResultType.ENTITY, "SELECT * FROM User").build();
        QueryResults<Entity> results = datastore.run(query);
        List<User> users = new ArrayList<>();
        while (results.hasNext()) {
            Entity result = results.next();
            users.add(
                    new User(result.getString("id"), result.getString("email"), result.getString("password"),
                            result.getString("fullName"), (int) result.getLong("age")));
        }
        return users;
    }

    public User getUser(String id) {
        userKeyFactory = datastore.newKeyFactory().setKind(KIND);
        Entity entity = datastore.get(userKeyFactory.newKey(id));
        if(entity == null) {
            throw new UserNotFoundException("No user with id " + id + " found");
        } else {
            return new User(entity.getString("id"), entity.getString("email"),
                    entity.getString("password"), entity.getString("fullName"), (int) entity.getLong("age"));
        }


    }

    public Entity createUser(User user) {
        return datastore.put(createUserEntity(user));
    }

    public Batch.Response createUser(BatchUser users) {
        Batch batch = datastore.newBatch();
        for (User user : users.getUsers()) {
            batch.put(createUserEntity(user));
        }

        return batch.submit();
    }

    private Entity createUserEntity(User user) {
        userKeyFactory = datastore.newKeyFactory().setKind(KIND);
        Key key = userKeyFactory.newKey(user.getId());
        return Entity.newBuilder(key)
                .set("id", user.getId())
                .set("email", user.getEmail())
                .set("password", user.getPassword())
                .set("fullName", user.getFullName())
                .set("age", user.getAge())
                .build();
    }

    public User updateUser(User user) {
        userKeyFactory = datastore.newKeyFactory().setKind(KIND);
        Key key = userKeyFactory.newKey(user.getId());
        Entity entity = datastore.get(key);
        if(entity == null) {
            throw new UserNotFoundException("No user with id " + user.getId() + " found");
        } else {
            entity = Entity.newBuilder(key)
                    .set("id", user.getId())
                    .set("email", user.getEmail())
                    .set("password", user.getPassword())
                    .set("fullName", user.getFullName())
                    .set("age", user.getAge())
                    .build();
            datastore.update(entity);
        }
        return user;

    }

    public String deleteUser(String id) {
        userKeyFactory = datastore.newKeyFactory().setKind(KIND);
        Key key = userKeyFactory.newKey(id);
        Entity entity = datastore.get(key);
        if (entity == null) {
            throw new UserNotFoundException("No user with id " + id + " found");
        } else {
            datastore.delete(key);
        }
        return "User with id "+ id +" has been deleted successfully";
    }

}
