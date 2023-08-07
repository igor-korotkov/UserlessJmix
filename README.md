Set up a local Keycloak at http://localhost:8088

docker compose -f ./docker/docker-compose.yml up


Import Keycloak realm from ./keycloak/realm-export.json

This will add a userless-jmix client, a mapper to map roles into "roles" claim of the ID Token. 

Since realm export file doesn't include users, you'll need to create some manually.

Go to Users -> Add User -> enter username -> Save. Go to Role Mappings -> assign "manager" role. Go to Credentials and set user password.
