//package com.boojux.ftchatchannel.conf;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
//import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//@Configuration
//@EnableCassandraRepositories(basePackages = "com.boojux.ftchatchannel.repository")
//public class CassandraConfig extends AbstractCassandraConfiguration {
//    @Value("${spring.data.cassandra.contact-points}")
//    private String contactPoints;
//    @Value("${spring.data.cassandra.port}")
//    private int port;
//    @Value("${spring.data.cassandra.username}")
//    private String username;
//    @Value("${spring.data.cassandra.password}")
//    private String password;
//    @Value("${spring.data.cassandra.keyspace-name}")
//    private String keyspaceName;
//
//    @Override
//    public CqlSessionFactoryBean cassandraSession() {
//        CqlSessionFactoryBean cqlSessionFactoryBean = super.cassandraSession();
//        cqlSessionFactoryBean.setUsername(username);
//        cqlSessionFactoryBean.setPassword(password);
//        return cqlSessionFactoryBean;
//    }
//
//    @Override
//    protected String getContactPoints() {
//        return contactPoints;
//    }
//
//    @Override
//    protected int getPort() {
//        return port;
//    }
//
//    @Override
//    protected String getKeyspaceName() {
//        return keyspaceName;
//    }
//}
