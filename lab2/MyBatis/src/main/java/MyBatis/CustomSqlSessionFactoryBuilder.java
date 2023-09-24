package MyBatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CustomSqlSessionFactoryBuilder {
    private String configFile = "mybatis/config/mybatis-config.xml";
    private SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
    private String environment = "development";

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public void setSqlSessionFactoryBuilder(SqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
        this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public SqlSessionFactory create() throws IOException {
        InputStream inputStream = null;
        Properties properties = null;
        inputStream = Resources.getResourceAsStream(configFile);
        properties = Resources.getResourceAsProperties("mybatis.properties");
        return sqlSessionFactoryBuilder.build(inputStream, environment, properties);
    }
}
