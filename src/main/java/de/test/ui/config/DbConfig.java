package de.test.ui.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Created by mokwen on 02.03.16.
 * This class may be omitted if you do not want to run any init scripts
 */
//@Configuration
//@EnableJpaRepositories("de.test.ui")
//@EnableTransactionManagement
public class DbConfig  implements EnvironmentAware {

    private final Logger log = LoggerFactory.getLogger(DbConfig.class);

    private RelaxedPropertyResolver dataSourcePropertyResolver;

    //private RelaxedPropertyResolver liquiBasePropertyResolver;

    private Environment env;

    //@Autowired(required = false)
    //private MetricRegistry metricRegistry;


    @Override
    public void setEnvironment(Environment env) {
        this.env = env;
        this.dataSourcePropertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");
        //this.liquiBasePropertyResolver = new RelaxedPropertyResolver(env, "liquiBase.");
    }
    /*@Bean
    public DataSource dataSource() {

        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("db/sql/import.sql")
                //.addScript("db/sql/insert-data.sql")
                .build();
        return db;
    }*/

    /*@Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        if (dataSourcePropertyResolver.getProperty("url") == null && dataSourcePropertyResolver.getProperty("databaseName") == null) {

            throw new ApplicationContextException("Database connection pool is not configured correctly");
        }
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(dataSourcePropertyResolver.getProperty("dataSourceClassName"));
        if(StringUtils.isEmpty(dataSourcePropertyResolver.getProperty("url"))) {
            config.addDataSourceProperty("databaseName", dataSourcePropertyResolver.getProperty("databaseName"));
            config.addDataSourceProperty("serverName", dataSourcePropertyResolver.getProperty("serverName"));
        } else {
            config.addDataSourceProperty("url", dataSourcePropertyResolver.getProperty("url"));
        }
        config.addDataSourceProperty("user", dataSourcePropertyResolver.getProperty("username"));
        config.addDataSourceProperty("password", dataSourcePropertyResolver.getProperty("password"));

        //MySQL optimizations, see https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
        if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(dataSourcePropertyResolver.getProperty("dataSourceClassName"))) {
            config.addDataSourceProperty("cachePrepStmts", dataSourcePropertyResolver.getProperty("cachePrepStmts", "true"));
            config.addDataSourceProperty("prepStmtCacheSize", dataSourcePropertyResolver.getProperty("prepStmtCacheSize", "250"));
            config.addDataSourceProperty("prepStmtCacheSqlLimit", dataSourcePropertyResolver.getProperty("prepStmtCacheSqlLimit", "2048"));
        }
        return new HikariDataSource(config);
    }*/

}
