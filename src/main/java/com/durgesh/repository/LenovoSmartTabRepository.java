package com.durgesh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.durgesh.model.LenovoSmartTab;

public interface LenovoSmartTabRepository extends JpaRepository<LenovoSmartTab, Long>{

	
	List<LenovoSmartTab> findByRam(String ram);
	
	
	@Query("select l from LenovoSmartTab l WHERE l.ram=:name Or l.rom=:name or l.size=:name or l.expandableUpto=:name or l.primaryCamera=:name or l.battery=:name ")
	List<LenovoSmartTab> findByName(@Param("name") String name);
}
