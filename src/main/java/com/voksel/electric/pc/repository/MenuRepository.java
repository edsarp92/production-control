package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MenuRepository extends JpaRepository<Menu, String> {

	Menu findByMenuId(String menuId);

	List<Menu> findAllByOrderByMenuIdAsc();

}
