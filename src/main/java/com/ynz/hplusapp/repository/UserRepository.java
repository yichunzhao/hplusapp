package com.ynz.hplusapp.repository;

import com.ynz.hplusapp.beans.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUserName(String userName);

    @Query("select u.password from User u where u.userName =:name")
    String findPasswordByUserName(@Param("name") String userName);
}
