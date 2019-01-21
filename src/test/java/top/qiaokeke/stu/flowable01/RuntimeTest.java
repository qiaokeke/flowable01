package top.qiaokeke.stu.flowable01;

import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RuntimeTest {

    @Autowired
    RuntimeService runtimeService;

    @Test
    public void testStartProcess(){
        String processInstanceKey = "financialReport";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processInstanceKey);
        printProcessInstance(processInstance);
    }

    @Test
    public void testProcessInstanceList(){
        List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().list();
        for(ProcessInstance processInstance:processInstanceList){
            printProcessInstance(processInstance);
        }
    }


    static void printProcessInstance(ProcessInstance processInstance){

        String info = "processInstance,"
                + "\nid:"+processInstance.getId()
                +"\nname:"+processInstance.getName()
                +"\nkey:"+processInstance.getProcessDefinitionKey()
                +"\nProcessDefinitionId:"+processInstance.getProcessDefinitionId()
                +"\nDeploymentId:"+processInstance.getDeploymentId();
        log.info(info);
    }
}
