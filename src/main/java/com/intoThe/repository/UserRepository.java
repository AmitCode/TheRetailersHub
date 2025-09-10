package com.intoThe.repository;

import com.intoThe.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    public Optional<Users> findByUserEmailId(String userEmailId);
}
