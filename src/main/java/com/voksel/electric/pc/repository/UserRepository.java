package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.Users;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends Repository<Users, Long> {
    Users findByUserName(String username);
    
}