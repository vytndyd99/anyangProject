package com.anyangProject.anyangNoticeBoardProject.repository;

import com.anyangProject.anyangNoticeBoardProject.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByusername(String username);
}
