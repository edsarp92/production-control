package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by edsarp on 8/14/16.
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

}
