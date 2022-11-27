//package account;
//
//import com.epam.training.ticketservice.entity.Account;
//import com.epam.training.ticketservice.helper.AccountHelper;
//import com.epam.training.ticketservice.service.AccountService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AccountTest {
//
//    private static final String TEST_ADMIN = "admin";
//    private static final String TEST_USER = "testPrivateCustomer";
//
//    @Mock
//    private AccountService underTest;
//
//    @Mock
//    private AccountHelper accountHelper;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testLoginShouldReturnFalseWhenNotAdmin() {
//        //GIVEN
//
//        //WHEN
//        underTest.findAccountByNameAndPassword(TEST_USER, TEST_USER);
//
//        //THEN
//        assertFalse(accountHelper.isAdmin());
//    }
//
//
//}
