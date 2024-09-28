package org.entities;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldJPAToHibernateTest {

    private static SessionFactory getSessionFactory(EntityManagerFactory emf) {
        return emf.unwrap(SessionFactory.class);
    }

    @Test
    void storeLoadMessage() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch02.ex01");

        try (SessionFactory sessionFactory = getSessionFactory(emf)) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Message message = new Message();
            message.setText("Hello World from JPA to Hibernate!");
            session.persist(message);

            session.getTransaction().commit();
            // INSERT INTO MESSAGE (ID, TEXT)
            // VALUES (1, 'Hello World from JPA to Hibernate!')
            session.beginTransaction();
            CriteriaQuery<Message> criteriaQuery = session.getCriteriaBuilder().createQuery(Message.class);
            criteriaQuery.from(Message.class);
            List<Message> messages = session.createQuery(criteriaQuery).getResultList();
            session.getTransaction().commit();

            assertAll(
                    () -> assertEquals(1, messages.size()),
                    () -> assertEquals("Hello World from JPA to Hibernate!", messages.get(0).getText())
            );
        }
    }

    private static EntityManagerFactory createEntityManagerFactory() {
        // create a new hibernate config
        Configuration configuration = new Configuration();
        // add the content of xml to configuration, explicitly Message as annotated class
        configuration.configure().addAnnotatedClass(Message.class);

        Map<String, String> properties = new HashMap<>();
        Enumeration<?> propertyNames = configuration.getProperties().propertyNames();
        while(propertyNames.hasMoreElements()) {
            String element = (String) propertyNames.nextElement();
            properties.put(element, configuration.getProperties().getProperty(element));
        }
        return Persistence.createEntityManagerFactory("ch02.ex01", properties);
    }
}
