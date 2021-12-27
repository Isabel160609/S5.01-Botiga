package com.init.botiga.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

class BotigaTest {

	@Autowired
	Botiga botiga;

//	@Test
//	void ejemplo() {
//		int num1=5;
//		int num2=5;
//		Assertions.assertEquals(10, num1+num2);
//		
//	}
	Botiga botiga2 = new Botiga("botiga1", 5);
	Botiga botiga3 = new Botiga("botiga2", 5);
	Quadre quadre = new Quadre();

	@Test
	void addQuadre() {

		botiga3.addQuadre(quadre);

		assertNull(botiga2.getLlistaQuadres());
		assertEquals(1, botiga3.getLlistaQuadres().size());
	}

	@Test
	void CremaTotsElsQuadres() {
		botiga3.addQuadre(quadre);
		botiga3.CremaTotsElsQuadres();
		List<Quadre> quadres = new ArrayList<Quadre>();
		assertEquals(quadres.size(), botiga3.getLlistaQuadres().size());
	}
}
