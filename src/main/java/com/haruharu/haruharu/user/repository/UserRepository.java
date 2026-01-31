package com.haruharu.haruharu.user.repository;

import com.haruharu.haruharu.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
