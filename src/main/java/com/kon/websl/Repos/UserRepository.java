package com.kon.websl.Repos;

import com.kon.websl.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

     User findByLogin(String login);

}
