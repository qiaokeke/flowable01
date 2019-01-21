package top.qiaokeke.stu.flowable01;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TaskTest {

    @Autowired
    TaskService taskService;


    @Test
    public void testTaskList(){
        List<Task> taskList = taskService.createTaskQuery().list();
        for(Task task:taskList){
            printTask(task);
        }
    }

    @Test
    public void testTaskCanGroup(){
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        printTaskList(tasks);
    }

    @Test
    public void testTaskCan(){
        List<Task> tasks=  taskService.createTaskQuery().taskCandidateOrAssigned("u2").list();
        printTaskList(tasks);
    }

    @Test
    public void testClamin(){
        //taskService.claim("2506","u1");
        taskService.complete("10004");
    }

    static void printTaskList(List<Task> tasks){
        for(Task task:tasks){
            printTask(task);
        }
    }

    static void printTask(Task task){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("task:");
        stringBuilder.append("\ntask getId:"+task.getId());
        stringBuilder.append("\ntask getName:"+task.getName());
        stringBuilder.append("\ntask getAssignee:"+task.getAssignee());
        stringBuilder.append("\ntask getCategory:"+task.getCategory());
        stringBuilder.append("\ntask getOwner:"+task.getOwner());

        System.out.println(stringBuilder.toString());
    }
}
