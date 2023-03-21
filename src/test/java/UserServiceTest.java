import org.com.junit.UserService;
import org.com.junit.dto.User;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {
    private UserService userService;

    @BeforeAll
     void init() {
        System.out.println("Before all: " + this);
    }
    @BeforeEach
    void prepare() {
        System.out.println("Before each: " + this);
        userService = new UserService();
    }


    @Test
    void usersEmptyIfNoUserAddedTest() {
        System.out.println("Test 1: " + this);

        List<User> users = userService.getAll();
        assertTrue(users.isEmpty(), () -> "User list should be empty");
    }


    @Test
    void userSizeIfUserAdded() {
        System.out.println("Test 2: " + this);
        userService.add(new User());
        userService.add(new User());
        userService.add(new User());
        List<User> users = userService.getAll();
        assertEquals(3, users.size());
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
