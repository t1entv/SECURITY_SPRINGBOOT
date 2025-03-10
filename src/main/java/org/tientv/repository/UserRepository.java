package org.tientv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tientv.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByRole(String role);
}
