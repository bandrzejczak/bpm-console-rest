package pl.edu.pw.ii.bpmConsole.rest.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.edu.pw.ii.bpmConsole.interfaces.ProcessEngine;
import pl.edu.pw.ii.bpmConsole.interfaces.ProcessEngineBuilder;
import pl.edu.pw.ii.bpmConsole.interfaces.exceptions.InvalidProcessEngineBuilderTypeException;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan("pl.edu.pw.ii.bpmConsole.rest")
public class BpmConsoleSpringConfiguration {

    @Autowired
    private BpmConsoleConfiguration configuration;

    @Bean
    public DataSource dataSource() throws SQLException {
        return DataSourceFactory.fromConfiguration(configuration.databaseConfiguration);
    }

    @Bean
    public ProcessEngine processEngine() throws Exception {
        Class bpmEngineBuilderClass = Class.forName(configuration.bpmProcessEngineBuilderClass);
        if(!ProcessEngineBuilder.class.isAssignableFrom(bpmEngineBuilderClass))
            throw new InvalidProcessEngineBuilderTypeException();
        return constructProcessEngine(bpmEngineBuilderClass);
    }

    private ProcessEngine constructProcessEngine(Class bpmEngineBuilderClass) throws InstantiationException, IllegalAccessException, SQLException {
        ProcessEngineBuilder processEngineBuilder =
                (ProcessEngineBuilder) bpmEngineBuilderClass.newInstance();
        return processEngineBuilder
                .usingDatabase(configuration.databaseConfiguration.type.toString(), dataSource())
                .build();
    }

}
