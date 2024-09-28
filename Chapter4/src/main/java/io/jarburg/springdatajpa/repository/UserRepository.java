package io.jarburg.springdatajpa.repository;

import io.jarburg.springdatajpa.entities.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(
            """
            SELECT u FROM User u where u.username = :username
            """
    )
    Optional<User> findByUsername(@Param("username") String username);
    @Query("""
            SELECT u FROM User u ORDER BY u.username ASC
            """)
    List<User> findAllByOrderByUsernameAsc();

    @Query("""
            SELECT u FROM User u WHERE u.registrationDate BETWEEN :start AND :end
            """)
    List<User> findByRegistrationDateBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    @Query("""
            SELECT u FROM User u where u.username = :username AND u.email = :email
            """)
    List<User> findByUsernameAndEmail(@Param("username")String username, @Param("email")String email);

    @Query("""
            SELECT u FROM User u where u.username = :username OR u.email = :email
            """)
    List<User> findByUsernameOrEmail(@Param("username")String username, @Param("email")String email);

    @Query("""
            SELECT u FROM User u where LOWER(u.username) = LOWER(:username)
            """)
    List<User> findByUsernameIgnoreCase(@Param("username") String username);

    @Query("""
            SELECT u FROM User u WHERE u.level = :level ORDER BY u.username DESC
            """)
    List<User> findByLevelOrderByUsernameDesc(int level);
    @Query("""
            SELECT u FROM User u WHERE u.level >= :level
            """)
    List<User> findByLevelGreaterThanEqual(@Param("level") int level);
    @Query("""
            SELECT u FROM User u WHERE u.username LIKE :text
            """)
    List<User> findByUsernameContaining(@Param("text") String text);

    @Query("""
            SELECT u FROM User u WHERE u.username LIKE :text
            """)
    List<User> findByUsernameLike(@Param("text")String text);
    @Query("""
            SELECT u FROM User u WHERE u.username LIKE :text
            """)
    List<User> findByUsernameStartingWith(@Param("text") String start);
    @Query("""
            SELECT u FROM User u WHERE u.username LIKE :text
            """)
    List<User> findByUsernameEndingWith(@Param("text") String end);

    @Query("""
            SELECT u FROM User u WHERE u.active = :active
            """)
    List<User> findByActive(@Param("active") boolean active);

    @Query("""
            SELECT u FROM User u WHERE u.registrationDate IN :date
            """)
    List<User> findByRegistrationDateInt(@Param("date") Collection<LocalDate> dates);

    @Query("""
            SELECT u FROM User u WHERE u.registrationDate NOT IN :date
            """)
    List<User> findByRegistrationDateNotIn(@Param("date") Collection<LocalDate> dates);


    Optional<User> findFirstByOrderByUsernameAsc();

    Optional<User> findTopByOrderByRegistrationDateDesc();

    List<User> findFirst2ByLevel(int level, Sort sort);

    Streamable<User> findByEmailContaining(String text);

    Streamable<User> findByLevel(int i);

    @Query("""
            SELECT COUNT(u) FROM User u WHERE u.active = ?1
            """)
    int findNumberOfUserByActive(boolean active);

    @Query("""
            SELECT u FROM User u WHERE u.level = :level AND u.active = :active
            """)
    List<User> findByLevelAndActive(@Param("level") int level,@Param("active") boolean active);
}
