FROM payara/server-full:6.2025.8-jdk21

ENV HOME=/opt/payara
ENV DEPLOY_DIR=${HOME}/glassfish/domains/domain1/autodeploy

COPY ./config/setup-jdbc.sh ${HOME}/setup-jdbc.sh
RUN chmod +x ${HOME}/setup-jdbc.sh

COPY target/contact-backend.war ${DEPLOY_DIR}/contact-backend.war

EXPOSE 8080

# Lancer Payara en arrière-plan, exécuter le script JDBC, puis relancer Payara en mode foreground
CMD sh -c "bin/asadmin start-domain && ${HOME}/setup-jdbc.sh && bin/asadmin stop-domain && bin/asadmin start-domain -v"

