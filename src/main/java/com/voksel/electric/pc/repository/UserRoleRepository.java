package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.UserRole;
import com.voksel.electric.pc.domain.id.UserRoleId;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by edsarp on 8/14/16.
 */
public interface UserRoleRepository extends JpaRepository<UserRole,UserRoleId> {

    List<UserRole> findAllByUserId(Integer userId) throws DataAccessException;

    @Modifying
    @Transactional
    @Query("delete from UserRole u where u.userId = :userId")
    void deleteByUserId(@Param("userId") Integer userId) throws DataAccessException;

}
