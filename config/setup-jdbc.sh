#!/usr/bin/env bash

# Wait for the default domain to be ready 
sleep 20

asadmin add-library ./mariadb-java-client-3.5.5.jar

# Create JDBC connection pool
asadmin create-jdbc-connection-pool \
    --restype=javax.sql.DataSource \
    --datasourceclassname=org.mariadb.jdbc.MariaDbDataSource \
    --property user=contactuser:password=contactpass:Url=jdbc:mariadb://mariadb:3306/contactdb \
    ContactPool

# Create JDBC resource referencing the pool
asadmin create-jdbc-resource \
    --connectionpoolid ContactPool \
    jdbc/ContactDS
