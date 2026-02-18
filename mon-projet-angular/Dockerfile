# Étape 1 : build Angular
FROM node:20 AS build
WORKDIR /app

# Copier les fichiers package pour installer les dépendances
COPY package*.json ./
RUN npm install

# Copier le code source et builder en production
COPY . .
RUN npm run build:prod

# Étape 2 : servir avec Nginx
FROM nginx:alpine
COPY --from=build /app/dist/mon-projet-angular /usr/share/nginx/html

# Exposer le port 80
EXPOSE 80

# Lancer Nginx
CMD ["nginx", "-g", "daemon off;"]
