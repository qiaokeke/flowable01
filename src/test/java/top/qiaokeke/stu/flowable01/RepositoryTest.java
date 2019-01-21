package top.qiaokeke.stu.flowable01;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.DeploymentEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RepositoryTest {


    @Autowired
    RepositoryService repositoryService;

    @Test
    public void testDeployment(){
        DeploymentEntityImpl deploymentEntity = (DeploymentEntityImpl) repositoryService.createDeployment()
                .addClasspathResource("start.bpmn20.xml")
                .deploy();
        printDeploymentInfo(deploymentEntity);
    }

    @Test
    public void testDeploymentList(){
        List<Deployment> deploymentList = repositoryService.createDeploymentQuery().list();
        for(Deployment deployment:deploymentList){
            printDeploymentInfo(deployment);
        }
    }

    static void printDeploymentInfo(Deployment deployment){
        log.info("getId:{}",deployment.getId());
        log.info("getName:{}",deployment.getName());
        log.info("getCategory:{}",deployment.getCategory());
        log.info("getParentDeploymentId:{}",deployment.getParentDeploymentId());
        log.info("getKey:{}",deployment.getKey());
        //log.info("getResources:{}",deployment.getResources());
    }
}
