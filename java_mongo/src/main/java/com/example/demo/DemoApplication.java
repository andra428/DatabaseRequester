package com.example.demo;

import com.example.demo.models.disciplina;
import com.example.demo.models.nota;
import com.example.demo.models.student;
import com.example.demo.repository.CatalogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LogManager.getLogger(DemoApplication.class);

	@Autowired
	CatalogRepository catalogRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
/*
	@Override
	public void run(String... args) {
		try {
			log.info("Starting the run method...");
			// Your code to create and populate the disciplina object
			disciplina disciplina = new disciplina();
			disciplina.setId(new ObjectId());
			disciplina.setDenumire_materie("IA");
			disciplina.setDenumire_program_studii("CTI");
			disciplina.setAn_studii(4);
			disciplina.setNume_titular("Florin Leon");

			// Instantiation of the list of students
			List<student> studenti = new ArrayList<>();

			student s1 = new student();
			s1.setId_student(new ObjectId());
			s1.setPrenume("Maria");
			s1.setNume("Martin");
			s1.setStare("promovat");

			if (s1.getStare().equalsIgnoreCase("echivalat")) {
				s1.setId_catalog_promovat(new ObjectId());
			}

			if ((s1.getStare().equalsIgnoreCase("promovat")) || (s1.getStare().equalsIgnoreCase("respins"))) {
				List<nota> note1 = new ArrayList<>();
				nota notaTeoretic1 = new nota();
				notaTeoretic1.setProba("teoretic");
				notaTeoretic1.setNota(8.78);
				note1.add(notaTeoretic1);

				nota notaPractic1 = new nota();
				notaPractic1.setProba("practic");
				notaPractic1.setNota(0.0);
				note1.add(notaPractic1);

				s1.setNote(note1);
				double m1 = 8.78;
				s1.setMedie(m1);
			}
			student s2=new student();

			s2.setId_student(new ObjectId());
			s2.setPrenume("Georgiana");
			s2.setNume("Cojocaru");
			s2.setStare("promovat");

			if (s2.getStare().equalsIgnoreCase("echivalat")) {
				s2.setId_catalog_promovat(new ObjectId());
			}

			if ((s2.getStare().equalsIgnoreCase("promovat")) || (s2.getStare().equalsIgnoreCase("respins"))) {
				List<nota> note2 = new ArrayList<>();
				nota notaTeoretic2 = new nota();
				notaTeoretic2.setProba("teoretic");
				notaTeoretic2.setNota(9.07);
				note2.add(notaTeoretic2);

				nota notaPractic2 = new nota();
				notaPractic2.setProba("practic");
				notaPractic2.setNota(0.0);
				note2.add(notaPractic2);

				s2.setNote(note2);
				double m2 = 9.07;
				s2.setMedie(m2);
			}


			student s3=new student();

			s3.setId_student(new ObjectId());
			s3.setPrenume("Ionut");
			s3.setNume("Poclid");
			s3.setStare("promovat");

			if (s3.getStare().equalsIgnoreCase("echivalat")) {
				s3.setId_catalog_promovat(new ObjectId());
			}

			if ((s3.getStare().equalsIgnoreCase("promovat")) || (s3.getStare().equalsIgnoreCase("respins"))) {
				List<nota> note3 = new ArrayList<>();
				nota notaTeoretic3 = new nota();
				notaTeoretic3.setProba("teoretic");
				notaTeoretic3.setNota(6.47);
				note3.add(notaTeoretic3);

				nota notaPractic3 = new nota();
				notaPractic3.setProba("practic");
				notaPractic3.setNota(0.0);
				note3.add(notaPractic3);

				s3.setNote(note3);
				double m3 = 6.47;
				s3.setMedie(m3);
			}


			studenti.add(s1);
			studenti.add(s2);
			studenti.add(s3);
			disciplina.setStudenti(studenti);

			catalogRepository.insert(disciplina);

			log.info("Disciplina salvata");
			log.info("ID disciplina: " + disciplina.getId());
			log.info("denumire Materie: " + disciplina.getDenumire_materie());
			log.info("numar de Studenti: " + disciplina.getStudenti().size());
			log.info("run method completed");

		} catch (Exception e) {
			log.error("eroare " + e.getMessage(), e);
		}
	}

		}

 */

