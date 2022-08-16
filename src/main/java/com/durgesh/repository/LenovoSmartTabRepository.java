package com.durgesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.model.LenovoSmartTab;

public interface LenovoSmartTabRepository extends JpaRepository<LenovoSmartTab, Long>{

	
	List<LenovoSmartTab> findByRam(String ram);
}
