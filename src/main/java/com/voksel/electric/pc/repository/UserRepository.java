package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByUserName(String username);

    Integer countByUserName(String username);
}