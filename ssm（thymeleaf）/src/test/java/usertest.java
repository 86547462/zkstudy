import com.sec.model.UserInfo;
import com.sec.service.UserServiceImpl;
import com.sec.service.userService;
import org.junit.Test;

import java.util.List;

public class usertest {
    @Test
    public void test1(){
      userService service=new  UserServiceImpl();
      List<UserInfo> list=service.findAll();
        System.out.println(list);
    }
}
