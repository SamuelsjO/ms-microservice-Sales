FROM node:alpine

WORKDIR /auth-api

COPY ./package.json ./

RUN npm install


EXPOSE 3333

CMD [ "npm" , "run",  "dev" ]