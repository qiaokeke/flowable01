package top.qiaokeke.stu.flowable01;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.Model;
import org.flowable.task.api.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProcessTest {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TaskService taskService;

    @Test
    public void testRepo(){
            List<Model> models = repositoryService.createModelQuery().list();
            log.info("models:{}",models);
            //查询部署
            List<Deployment> deployments = repositoryService.createDeploymentQuery().list();
            log.info("deployments:{}",deployments);
            for(Deployment deployment:deployments){
                Map resources = deployment.getResources();
                log.info("resources:{}",resources);
            }


    }



    @Test
    public void testTask(){
        List<Task> tasks = taskService.createTaskQuery().list();
        log.info("tasks:{}",tasks);
        for(Task task : tasks){

            log.info("task assignee:{}",task.getAssignee());
        }
    }



}
