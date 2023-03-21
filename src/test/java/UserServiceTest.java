import org.com.junit.UserService;
import org.com.junit.dto.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {
    @Test
    void usersEmptyIfNoUserAddedTest() {
        UserService userService = new UserService();
        List<User> users = userService.getAll();
        assertFalse(users.isEmpty(), ()-> "User list should be empty");
    }


}
