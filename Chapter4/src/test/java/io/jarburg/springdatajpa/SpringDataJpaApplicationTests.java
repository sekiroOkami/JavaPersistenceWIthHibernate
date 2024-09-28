package io.jarburg.springdatajpa;

import io.jarburg.springdatajpa.entities.User;
import io.jarburg.springdatajpa.repository.UserRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringDataJpaApplicationTests {

	@Autowired
	UserRepository userRepository;

	@BeforeAll
	void beforeAll() {
		userRepository.saveAll(generateUsers());
	}

	private static List<User> generateUsers() {
		List<User> users = new ArrayList<>();
		User marika = new User("Marika", LocalDate.of(2021, Month.DECEMBER, 13));
		marika.setEmail("marika@erdtree.com");
		marika.setLevel(1);
		marika.setActive(true);

		User radagon = new User("Radagon", LocalDate.of(2019, Month.APRIL, 15));
		radagon.setEmail("radagon@erdtree.com");
		radagon.setLevel(2);
		radagon.setActive(true);

		User rennala = new User("Rennala", LocalDate.of(2020, Month.AUGUST, 13));
		rennala.setEmail("rennala@caria.com");
		rennala.setLevel(2);
		rennala.setActive(true);

		User rellana = new User("Rellana", LocalDate.of(2017, Month.SEPTEMBER, 20));
		rellana.setEmail("rellana@shadowrealm.com");
		rellana.setLevel(4);
		rellana.setActive(true);

		User tarnished = new User("Tarnished", LocalDate.of(2018, Month.DECEMBER, 11));
		tarnished.setEmail("tarnished@limgrave.com");
		tarnished.setLevel(5);
		tarnished.setActive(true);

		users.add(marika);
		users.add(radagon);
		users.add(rennala);
		users.add(rellana);
		users.add(tarnished);
		return users;
	}

	@AfterAll
	void afterAll() {
		userRepository.deleteAll();
	}
}
