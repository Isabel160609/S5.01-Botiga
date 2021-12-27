package com.init.botiga.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.init.botiga.entity.Botiga;
import com.init.botiga.entity.Quadre;

;

public interface QuadreDao extends JpaRepository<Quadre, Integer> {
//
//	List<Quadre> findByBotigaid(int id_botiga);
	int deleteAllByBotiga(Botiga botiga);
}
