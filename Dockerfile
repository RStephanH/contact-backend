
# Use Payara Server Full with JDK 21
FROM payara/server-full:6.2025.8-jdk21

# Set environment variables
ENV DEPLOY_DIR=${HOME}/glassfish/domains/domain1/autodeploy

COPY ./config/setup-jdbc.sh ${HOME}/setup-jdbc.sh

# Copy WAR into Payara's autodeploy directory
COPY target/contact-backend.war ${DEPLOY_DIR}/contact-backend.war

# Expose the default HTTP port
EXPOSE 8080


RUN chmod +x ${HOME}/setup-jdbc.sh

# Start Payara Server
CMD ["bin/asadmin", "start-domain", "-v", "&&", "${HOME}/setup-jdbc.sh"]
