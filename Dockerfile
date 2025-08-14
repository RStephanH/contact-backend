FROM payara/server-full:6.2025.8-jdk21

USER root
ENV HOME=/opt/payara
ENV DEPLOY_DIR=${HOME}/glassfish/domains/domain1/autodeploy

COPY ./config/setup-jdbc.sh ${HOME}/setup-jdbc.sh
RUN chmod +x ${HOME}/setup-jdbc.sh

COPY target/contact-backend.war ${DEPLOY_DIR}/contact-backend.war

USER payara

EXPOSE 8080
CMD sh -c "bin/asadmin start-domain && ${HOME}/setup-jdbc.sh && bin/asadmin stop-domain && bin/asadmin start-domain -v"

