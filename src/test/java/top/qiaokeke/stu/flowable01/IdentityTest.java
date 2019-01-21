package top.qiaokeke.stu.flowable01;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.IdentityService;
import org.flowable.idm.api.Group;
import org.flowable.idm.api.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class IdentityTest {

    @Autowired
    IdentityService identityService;

    @Test
    public void testSaveUser(){
        User user = identityService.newUser("u1");
        user.setDisplayName("u1");
        user.setFirstName("u1");
        user.setPassword("test");
        user.setLastName("u1");

        identityService.deleteUser("u1");
        identityService.saveUser(user);

        User user2 = identityService.newUser("u2");
        user2.setDisplayName("u2");
        user2.setFirstName("u2");
        user2.setPassword("test");
        user2.setLastName("u2");

        identityService.deleteUser("u2");
        identityService.saveUser(user2);

    }

    @Test
    public void testMemberShip(){
        identityService.createMembership("u1","accountancy");
        identityService.createMembership("u2","management");
    }

    @Test
    public void testSaveGroup(){
        Group group = identityService.newGroup("accountancy");
        group.setName("accountancy");

        Group group2 = identityService.newGroup("management");
        group2.setName("management");

        identityService.deleteGroup("accountancy");
        identityService.deleteGroup("management");

        identityService.saveGroup(group);
        identityService.saveGroup(group2);
    }



    @Test
    public void testGroupList(){
        List<Group> groupList=  identityService.createGroupQuery().list();

        printGroupList(groupList);
    }

    static void printGroupList(List<Group> groups){
        for(Group group: groups){
            printGroup(group);
        }
    }

    static void printGroup(Group group){
        StringBuilder sb = new StringBuilder();
        sb.append("\ngetId:"+group.getId());
        sb.append("\ngetName:"+group.getName());
        sb.append("\ngetType:"+group.getType());

        System.out.println(sb);

    }
}
