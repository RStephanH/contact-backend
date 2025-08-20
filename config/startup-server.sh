#!/bin/bash

# Aller dans le répertoire du projet (1 niveau au-dessus du script)
cd "$(dirname "$0")/.."

# Construire les images (important si tu rebuild ton backend)
docker compose build

# Lancer les services en mode détaché
docker compose up -d

# Afficher les logs du backend pour vérifier qu'il démarre bien
docker compose logs -f payara

