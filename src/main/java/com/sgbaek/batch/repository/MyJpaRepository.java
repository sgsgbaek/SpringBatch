package com.sgbaek.batch.repository;


import com.sgbaek.batch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyJpaRepository extends JpaRepository<User, Integer> {
}
