FROM openjdk:11-jre-slim


# define o diretorio de trabalho aonde alguns comandos/instruções que a gente vai escrever nas proximas linhas, aonde vai rodar
WORKDIR /app

# define uma variavel que pode ser passada em tempo de build
ARG JAR_FILE

# instrução
COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

# informa qual porta o container vai escutar quando ele tiver rodando
EXPOSE 8080

# comando padrao que vai ser rodado quando o container iniciar (é recomendado usar como array)
CMD ["java", "-jar", "api.jar"]