package com.voksel.electric.pc.repository;

import com.voksel.electric.pc.domain.Menu;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface MenuRepository extends Repository<Menu, Long> {
	Menu findByMenuId(Long menuId);
	List<Menu> findAll();
	List<Menu> findAllByMenuId(Long menuId);
	List<Menu> findAllByOrderByMenuIdAsc();
}
