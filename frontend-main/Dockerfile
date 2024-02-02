FROM node:18-alpine

WORKDIR /app

COPY package.json .

RUN npm i yarn

RUN yarn

COPY . .

EXPOSE 5173

CMD [ "yarn", "run", "dev" ]