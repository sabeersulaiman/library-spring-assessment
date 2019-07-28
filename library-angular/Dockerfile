FROM node:10.16.0-alpine AS angular
WORKDIR /app
COPY . .
RUN npm install
RUN ./node_modules/.bin/ng build --prod --aot

FROM nginx:alpine
COPY --from=angular /app/dist/library/ /usr/share/nginx/html
