FROM openjdk:17
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/main/java/com/Nintendont/DK/Source/Main.java"]
EXPOSE 3000