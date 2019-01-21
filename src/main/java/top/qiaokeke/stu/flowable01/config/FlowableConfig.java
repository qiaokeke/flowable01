package top.qiaokeke.stu.flowable01.config;

import org.flowable.engine.*;
import org.flowable.spring.ProcessEngineFactoryBean;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class FlowableConfig {


    @Value("com.mysql.jdbc.Driver")
    String driverClassName;
    @Value("jdbc:mysql://qiaokeke.top:7306/flowable?useUnicode=true&characterEncoding=utf-8")
    String url;
   /* @Value("jdbc:mysql://mysql.network.qiaokeke.top:3306/flowable?useUnicode=true&characterEncoding=utf-8")
    String url;*/
    @Value("root")
    String username;
    @Value("cecepJX#2018DB")
    String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource,DataSourceTransactionManager transactionManager){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate("true");
        return configuration;
    }

    @Bean
    ProcessEngine processEngine(SpringProcessEngineConfiguration configuration) throws Exception {
        ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
        processEngineFactoryBean.setProcessEngineConfiguration(configuration);
        return processEngineFactoryBean.getObject();
    }

    @Bean
    RuntimeService runtimeService(ProcessEngine processEngine){
        return processEngine.getRuntimeService();
    }

    @Bean
    RepositoryService repositoryService(ProcessEngine processEngine){
        return processEngine.getRepositoryService();
    }
    @Bean
    TaskService taskService(ProcessEngine processEngine){
        return processEngine.getTaskService();
    }

    @Bean
    ManagementService managementService(ProcessEngine processEngine){
        return processEngine.getManagementService();
    }
    @Bean
    IdentityService identityService(ProcessEngine processEngine){
        return processEngine.getIdentityService();
    }
    @Bean
    HistoryService historyService(ProcessEngine processEngine){
        return processEngine.getHistoryService();
    }
    @Bean
    FormService formService(ProcessEngine processEngine){
        return processEngine.getFormService();
    }
    @Bean
    DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine){
        return processEngine.getDynamicBpmnService();
    }

}
