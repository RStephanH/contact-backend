# Contact Backend

This project is a **Java EE / Jakarta EE backend** deployed on **Payara Server**.  
It provides REST services for contact management and uses a **MariaDB** database.  

The project is fully containerized with **Docker**, and a **GitHub Actions workflow** is used to build and automatically publish the backend Docker image.

---

## 🚀 Key Features
- Java EE / Jakarta EE backend packaged as a **WAR** and deployed on **Payara Server**.  
- **MariaDB** database for storing contact information.  
- **Docker Compose** for orchestrating Payara + MariaDB.  
- **GitHub Actions** to build and push the Docker image automatically.  
- Shell script `./config/startup-server.sh` for easy local server startup.

---

## 📂 Project Structure

```css

.  
├── config/  
│ ├── mariadb-java-client-3.5.5.jar # MariaDB JDBC driver  
│ ├── setup-jdbc.sh # Script to configure JDBC in Payara  
│ └── startup-server.sh # Script to start backend via Docker Compose  
├── src/ # Java source code  
├── target/  
│ └── contact-backend.war # WAR package generated after build  
├── Dockerfile # Backend Payara Docker image  
├── docker-compose.yml # Payara + MariaDB orchestration  
└── README.md # Project documentation

````

---

## ⚙️ Prerequisites
- **Docker** and **Docker Compose** installed  
- **Maven** (to build the project locally if needed)  
- **JDK 21**  

---

## 🔨 Build & Deployment with GitHub Actions
The GitHub Actions workflow:  
1. Builds the project using Maven (`mvn clean package`).  
2. Builds the Docker image for the backend.  
3. Pushes the image to Docker Hub (or another container registry).  

👉 See `.github/workflows/build.yml` for the workflow configuration.

---

## ▶️ Start the Server Locally

A script is provided to simplify server startup:  

```bash
./config/startup-server.sh
````

This script:

1. Pull the Docker images if needed.
    
2. Starts **Payara** and **MariaDB** via Docker Compose.
    
3. Follows the backend logs automatically.
    

---

## 🌐 Service Access

- **Backend REST (Payara)**: [http://localhost:8080](http://localhost:8080)
    
- **Payara Admin Console**: [http://localhost:4848](http://localhost:4848)
    
- **MariaDB**: port `3306` (configured in `docker-compose.yml`)
    

---

## 🛑 Stop the Server

To stop and clean up the containers:

```bash
docker compose down
```

To also remove the volumes (⚠️ will delete all data):

```bash
docker compose down -v
```

---

## ✨ Notes

- MariaDB credentials (user, password, database) can be changed in `docker-compose.yml`.
    
- The `setup-jdbc.sh` script automatically configures the **connection pool** and **JDBC resource** in Payara.
    
- You can customize the Payara image using the `Dockerfile`.
    

