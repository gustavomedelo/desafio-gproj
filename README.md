# DGPROJ

> Aplicação responsável pelo gerenciamento dos dados do portfólio de projetos de uma empresa.

[![Java Version][java-image]][java-url]
[![Spring Version][spring-image]][spring-url]

## Tecnologias

- **Java** 17
- **Spring Boot** 2.7.18
- **PostgreSQL** (via Docker)
- **SonarQube** (via Docker)
- **Swagger** para documentação da API

## Executar

### 1. Rodando o Docker Compose

- Na raiz do projeto, há um arquivo docker-compose.yml que configura o Postgre e o SonarQube.
- Execute o comando para subir os containers:
```docker-compose up```

### 2. Executando via IntelliJ

No diretório raiz do projeto crie um novo diretório chamado __.run__, dentro deste novo diretório deve ser criado o arquivo __launch.run.xml__ com as configurações abaixo.

```xml
<component name="ProjectRunConfigurationManager">
    <configuration default="false" name="Application" type="SpringBootApplicationConfigurationType" factoryName="Spring Boot" nameIsGenerated="true">
        <option name="ACTIVE_PROFILES" value="local"/>
        <module name="gproj"/>
        <option name="SPRING_BOOT_MAIN_CLASS" value="br.com.desafio.gproj.Application"/>
        <method v="2">
            <option name="Make" enabled="true"/>
        </method>
    </configuration>
</component>
```

### 3. Exemplos de Requisições

- Cadastro membro FUNCIONARIO via webservice
```
curl --location 'http://localhost:8080/members' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Joe Doe",
    "birthDate": "1991-01-23",
    "cpf": "40636173003",
    "role": "FUNCIONARIO"
}'
```
- Cadastro membro GERENTE via webservice
```
curl --location 'http://localhost:8080/members' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Tom Doe",
    "birthDate": "1991-01-23",
    "cpf": "16574138027",
    "role": "GERENTE"
}'
```

### 4. Acesso aplicação


A aplicação pode ser acessada a partir de:
http://localhost:8080/home


## Documentação
O projeto inclui Swagger para documentação e pode ser acessado em:
http://localhost:8080/swagger-ui/index.html

## Análise de Qualidade de Código
A aplicação está configurada para ser analisada pelo SonarQube. Após rodar o Docker Compose, acesse o SonarQube na URL: http://localhost:9000

- O login padrão do SonarQube é **admin** e a senha também é **admin**.
- O projeto deve ser configurado manualmente.
- Executar o comando disponibilizado pelo sonarqube, exemplo: 
```sonar:sonar -Dsonar.projectKey=desafio-gproj -Dsonar.host.url=http://localhost:9000 -Dsonar.login=69158ae913e2aeb58e35298a6c2975062a600d0c```

- Exemplo run configuration IntelliJ utilizando comando sonarqube:
```xml
<component name="ProjectRunConfigurationManager">
<configuration default="false" name="gproj [sonar:sonar...]" type="MavenRunConfiguration" factoryName="Maven" nameIsGenerated="true">
<MavenSettings>
<option name="myGeneralSettings"/>
<option name="myRunnerSettings"/>
<option name="myRunnerParameters">
<MavenRunnerParameters>
<option name="cmdOptions"/>
<option name="profiles">
<set/>
</option>
<option name="goals">
<list>
<option value="sonar:sonar"/>
<option value="-Dsonar.projectKey=desafio-gproj"/>
<option value="-Dsonar.host.url=http://localhost:9000"/>
<option value="-Dsonar.login=69158ae913e2aeb58e35298a6c2975062a600d0c"/>
</list>
</option>
<option name="multimoduleDir"/>
<option name="pomFileName"/>
<option name="profilesMap">
<map/>
</option>
<option name="projectsCmdOptionValues">
<list/>
</option>
<option name="resolveToWorkspace" value="false"/>
<option name="workingDirPath" value="$PROJECT_DIR$"/>
</MavenRunnerParameters>
</option>
</MavenSettings>
<method v="2"/>
</configuration>
</component>
```

<!-- Markdown link & img dfn's -->
[java-image]: https://img.shields.io/badge/Java-v17-green
[spring-image]: https://img.shields.io/badge/Spring--Boot-v2.7.18-green
[java-url]: https://docs.oracle.com/en/java/javase/17/
[spring-url]: https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies/2.7.18

