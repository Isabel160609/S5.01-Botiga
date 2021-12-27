package com.init.botiga.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.init.botiga.dao.BotigaDao;
import com.init.botiga.dao.QuadreDao;
import com.init.botiga.entity.Botiga;
import com.init.botiga.entity.Quadre;


@SpringBootTest
class ControllerTest {
	
	
	@Mock
	BotigaDao botigaDAO;
	
	@InjectMocks
	Controller controller;
	
	@Mock
	QuadreDao quadreDAO;
	
	Botiga botiga;
	Quadre quadre1;
	Quadre quadre2;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		botiga = new Botiga();
		botiga.setNomBotiga("botiga1");
		botiga.setNumMaxquadres(5);
		botiga.setId(1);	
		
		quadre1 = new Quadre("bodegon","anonimo", 300.0, Calendar.getInstance().getTime(), botiga);
		quadre2 = new Quadre("sol", "sandro", 500,  Calendar.getInstance().getTime(), botiga);
		botiga.addQuadre(quadre1);
		botiga.addQuadre(quadre2);
	}

	//	new ResponseEntity<>(Arrays.asList("data"), HttpStatus.OK))
	
	@Test
	@DisplayName("Test getAllBotigas")
	void getAllBotigasTest() {		
		when(botigaDAO.findAll()).thenReturn(Arrays.asList(botiga));	
		assertNotNull(controller.getBotigues());
		assertEquals(1, botigaDAO.findAll().size());
		assertEquals(1, botigaDAO.findAll().get(0).getId());
		assertEquals("botiga1", botigaDAO.findAll().get(0).getNomBotiga());
		assertEquals("bodegon", botigaDAO.findAll().get(0).getLlistaQuadres().get(0).getNomQuadre());
		assertEquals("sandro", botigaDAO.findAll().get(0).getLlistaQuadres().get(1).getAutorQuadre());
	}
	


}
