FROM node:alpine

WORKDIR .

COPY package*.json ./

RUN npm install

COPY . .

EXPOSE 8080

CMD [ "node" , "dist/server.js" ]