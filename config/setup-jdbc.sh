#!/usr/bin/env bash

# Wait for the default domain to be ready 
sleep 20
echo "AS_ADMIN_PASSWORD=admin" >> password.txt

asadmin add-library --user admin --password-file=/opt/payara/password.txt ./mariadb-java-client-3.5.5.jar

# Create JDBC connection pool
asadmin --user admin --password-file=/opt/payara/password.txt create-jdbc-connection-pool \
    --restype=javax.sql.DataSource \
    --datasourceclassname=org.mariadb.jdbc.MariaDbDataSource \
    --property user=contactuser:password=contactpass:Url=jdbc:mariadb://mariadb:3306/contactdb \
    ContactPool

# Create JDBC resource referencing the pool
asadmin --user admin --password-file=/opt/payara/password.txt create-jdbc-resource \
    --connectionpoolid ContactPool \
    jdbc/ContactDS
