Set up a local Keycloak at http://localhost:8088
docker compose -f ./docker/docker-compose.yml up

Import Keycloak realm from ./keycloak/realm-export.json
This will add a userless-jmix client, a mapper to map roles into "roles" claim of the ID Token, and two default users - alice/alice and bob/bob
Alice has manager role allowing access to the application screens
Bob doesn't have manager role
