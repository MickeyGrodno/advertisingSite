package springTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import springTest.AdServiceTest;
import springTest.AdTypeServiceTest;
import springTest.ChatServiceTest;
import springTest.CommentServiceTest;
import springTest.CredentialServiceTest;
import springTest.MessageServiceTest;
import springTest.UserServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses ({
        AdServiceTest.class,
        AdTypeServiceTest.class,
        ChatServiceTest.class,
        CommentServiceTest.class,
        CredentialServiceTest.class,
        MessageServiceTest.class,
        UserServiceTest.class
})

public class AllTestsRunner {

}
