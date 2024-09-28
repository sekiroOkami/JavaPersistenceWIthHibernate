package io.jarburg.springdatajpa;

import io.jarburg.springdatajpa.entities.User;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryResultsTest extends SpringDataJpaApplicationTests {
    @Test
    void testStreamable() {

        Stream<User> result = Stream.concat(
                userRepository.findByEmailContaining("caria").stream(),
                userRepository.findByLevel(2).stream()
        ).distinct();
       try (result){
           assertThat(2).isEqualTo(result.count());
       }
    }
}
