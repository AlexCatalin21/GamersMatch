package com.gamingmatcher.demo.auth.repository;

import com.gamingmatcher.demo.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

   AppUser getByEmail(String email);
}