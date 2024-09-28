package org.entities;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class HelloWorldJPATest {

    @Test
    void shoreLoadMessage() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02.ex01");

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            Message message = new Message();
            message.setText("HelloWorld");

            em.persist(message);
            em.getTransaction().commit();

            em.getTransaction().begin();
            String jpql = """
                    SELECT m FROM Message m
                    """;
            List<Message> messages = em.createQuery(jpql).getResultList();
            Message result = messages.get(messages.size() - 1);
            result.setText("HelloWorld From JPA");

            em.getTransaction().commit();

            assertAll(
                    ()-> assertThat(messages.get(0).getText()).isEqualTo("HelloWorld From JPA"),
                    ()-> assertThat(messages.size()).isEqualTo(1)
            );
        }
    }

}
