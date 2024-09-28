/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package com.manning.javapersistence.ch15;

import com.manning.javapersistence.ch15.model.User;
import com.manning.javapersistence.ch15.repositories.UserRepository;
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
abstract class SpringDataJdbcApplicationTests {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        userRepository.saveAll(generateUsers());
    }

    private static List<User> generateUsers() {
        List<User> users = new ArrayList<>();

        User john = new User("john", LocalDate.of(2020, Month.APRIL, 13));
        User mike = new User("mike", LocalDate.of(2020, Month.JANUARY, 18));
        User james = new User("james", LocalDate.of(2020, Month.MARCH, 11));
        User katie = new User("katie", LocalDate.of(2021, Month.JANUARY, 5));
        User beth = new User("beth", LocalDate.of(2020, Month.AUGUST, 3));
        User julius = new User("julius", LocalDate.of(2021, Month.FEBRUARY, 9));
        User darren = new User("darren", LocalDate.of(2020, Month.DECEMBER, 11));
        User marion = new User("marion", LocalDate.of(2020, Month.SEPTEMBER, 23));
        User stephanie = new User("stephanie", LocalDate.of(2020, Month.JANUARY, 18));
        User burk = new User("burk", LocalDate.of(2020, Month.NOVEMBER, 28));
        users.add(john);
        users.add(mike);
        users.add(james);
        users.add(katie);
        users.add(beth);
        users.add(julius);
        users.add(darren);
        users.add(marion);
        users.add(stephanie);
        users.add(burk);

        return users;
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }

}
