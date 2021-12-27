package com.init.botiga.controllers;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.init.botiga.dao.BotigaDao;
import com.init.botiga.dao.QuadreDao;
import com.init.botiga.entity.Botiga;
import com.init.botiga.entity.Quadre;

@RestController
@RequestMapping("/botiga")
public class Controller {

	@Autowired
	private BotigaDao botigaDao;
	@Autowired
	private QuadreDao quadreDao;

//		

	// Crear botiga: li direm el nom i la capacitat (POST /shops/).
	@PostMapping(value = "/shops")
	public ResponseEntity<Botiga> createBotiga(@RequestBody Botiga botiga) {

		// Botiga novaBotiga = new Botiga(nomBotiga, numMaxquadres);

		botigaDao.save(botiga);

		return ResponseEntity.ok(botiga);
	}

	// Llistar botigues: retorna la llista de botigues amb el seu nom i la capacitat
	// (GET /shops/).
	@GetMapping(value = "/shops")
	public ResponseEntity<List<Botiga>> getBotigues() {

		List<Botiga> botigues = botigaDao.findAll();
		return ResponseEntity.ok(botigues);
	}

	
	
	// Afegir quadre: li donarem el nom del quadre i el del autor (POST
	// /shops/{ID}/pictures)
	@Transactional
	@PostMapping(value = "/shops/{id_botiga}/pictures")
	public ResponseEntity<String> createQuadre(@PathVariable(name = "id_botiga") int id_botiga,
			@RequestBody Quadre quadre) {

		Optional<Botiga> optionalBotigaDao = botigaDao.findById(id_botiga);

		if (!optionalBotigaDao.isEmpty()) {

			Botiga botiga = optionalBotigaDao.get();
			int numInsercionsLLista = botiga.getLlistaQuadres().size();
			if (numInsercionsLLista < botiga.getNumMaxquadres()) {
				quadre.setBotiga(botiga);
				quadreDao.save(quadre);
				botiga.addQuadre(quadre);
				botigaDao.save(botiga);

				return ResponseEntity.ok("el quadre amb id " + quadre.getId_quadre() + " ha sigut insertat amb exit");
			}else {
				return ResponseEntity.ok("Aquest quadre no es pot insertar per que aquesta tenda ha superat el nombre maxim de quadres");
			}

		} else {

			return ResponseEntity.ok("la botiga " + id_botiga + " no existeix");
		}
	}

	// Llistar els quadres de la botiga (GET /shops/{ID}/pictures).
	@GetMapping("/shops/{id_botiga}/pictures")
	public ResponseEntity<List<Quadre>> getQuadres(@PathVariable(name = "id_botiga") int id_botiga) {

		Optional<Botiga> optionalBotigaDao = botigaDao.findById(id_botiga);
		if (!optionalBotigaDao.isEmpty()) {

			Botiga botiga = optionalBotigaDao.get();
			List<Quadre> botigues = botiga.getLlistaQuadres();
			return ResponseEntity.ok(botigues);

		} else {

			List<Quadre> empty = new ArrayList<Quadre>();
			return ResponseEntity.ok(empty);

		}
	}

	// incendiar quadres: per si ve la policia, es poden eliminar tots els quadres
	// de la botiga sense deixar rastre (DELETE /shops/{ID}/pictures).
	@Transactional
	@DeleteMapping(value = "shops/{id_botiga}/pictures")
	public ResponseEntity<String> deleteBotiga(@PathVariable("id_botiga") int id_botiga) {

		Optional<Botiga> botiga = botigaDao.findById(id_botiga);
		if (!botiga.isEmpty()) {

			quadreDao.deleteAllByBotiga(botiga.get());

			return ResponseEntity.ok("tots els quadres de la botiga amb id " + id_botiga + " han sigut borrats");

		} else {

			return ResponseEntity.ok("la botiga amb id " + id_botiga + " no existeix");
		}

	}

}
