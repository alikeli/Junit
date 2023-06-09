package junit.service;

import junit.paramResolver.UserServiceParamResolver;
import org.com.junit.service.UserService;
import org.com.junit.dto.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({
        UserServiceParamResolver.class
})
public class UserServiceTest {

    private static final User IVAN = User.of(1, "Ivan", "1213");
    private static final User OLEG = User.of(2, "Oleg", "147");
    private static final User OLGA = User.of(3, "Olga", "789");
    private UserService userService;

    UserServiceTest(TestInfo testInfo) {
        System.out.println();
    }

    @BeforeAll
    void init() {
        System.out.println("Before all: " + this);
    }

    @BeforeEach
    void prepare(UserService userService) {
        System.out.println("Before each: " + this);
        this.userService = userService;
    }


    @Test
    @DisplayName("user will be empty if user not added")
    @Order(4)
    void usersEmptyIfNoUserAddedTest() {
        System.out.println("Test 1: " + this);

        List<User> users = userService.getAll();
        assertTrue(users.isEmpty(), () -> "User list should be empty");
    }


    @Test
    @Order(2)
    void userSizeIfUserAdded() {
        System.out.println("Test 2: " + this);
        userService.add(IVAN);
        userService.add(OLEG);
        userService.add(OLGA);
        List<User> users = userService.getAll();
        assertEquals(3, users.size());
    }

    @Test
    @Order(3)
    @Tag("login")
    void loginSuccessIfUserExist() {
        userService.add(IVAN);
        Optional<User> foundUser = userService.login(IVAN.getUserName(), IVAN.getPassword());
        assertTrue(foundUser.isPresent());
        foundUser.ifPresent(user -> assertEquals(IVAN, user));
    }

    @Test
    @Tag("login")
    @Order(1)
    void loginFailWhenPasswordNotCorrect() {
        userService.add(IVAN);
        Optional<User> foundUser = userService.login(IVAN.getUserName(),
                "wrongPassword");
        assertTrue(foundUser.isEmpty());
    }

    @Test
    @Tag("login")
    void loginFailWhenUserDoesNotExist() {
        userService.add(IVAN);
        Optional<User> foundUser = userService.login("Non",
                IVAN.getPassword());
        assertTrue(foundUser.isEmpty());
    }

    @Test
    @Tag("login")
    void throwExceptionIfUserNameOrPasswordIsNull() {
        assertAll(
                ()->{
               var exception = assertThrows(IllegalArgumentException.class, () -> userService
                       .login(null, "123"));
                       assertThat(exception.getMessage()).isEqualTo("username or password is null");
                },

                ()-> assertThrows(IllegalArgumentException.class, () -> userService.login("null", null))
        );
    }


    @AfterEach
    void deleteDataFromDb() {
        System.out.println("After each: " + this);
    }

    @AfterAll
    void closeConnection() {
        System.out.println("After all: " + this);
    }

}
