package io.jarburg.springdatajpa;

import io.jarburg.springdatajpa.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindUsersQueriesTest extends SpringDataJpaApplicationTests {

    @Test
    void testFindAll() {
        List<User> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(5);
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testFindUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User marika = user.orElseThrow(() -> new RuntimeException("Username not found." + username));
        assertThat(marika.getUsername()).isEqualTo(username);
    }

    @Test
    void testFindAllByOrderByUserNameAsc() {
        List<User> users = userRepository.findAllByOrderByUsernameAsc();
        assertThat(users.size()).isEqualTo(5);
        assertAll(
                ()->assertThat("Marika").isEqualTo(users.get(0).getUsername()),
                ()->assertThat("Tarnished").isEqualTo(users.get(users.size()-1).getUsername())
        );
    }
    @Test
    void testFindByRegistrationDateBetween() {
        List<User> users = userRepository.findByRegistrationDateBetween(LocalDate.of(2018, Month.JANUARY, 1),
                LocalDate.of(2021, Month.DECEMBER, 31));
        assertThat(users.size()).isEqualTo(4);
    }

    static Stream<Arguments> stringProvider() {
        return Stream.of(
                Arguments.of("Marika"),
                Arguments.of("Radagon"),
                Arguments.of("Rennala"),
                Arguments.of("Rellana"),
                Arguments.of("Tarnished")
        );
    }
}
