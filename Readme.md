# Spring MVC:

É um projeto do ecossistema spring para desenvolvermos em WEB, controladores WEB que recebem requisiçoes HTTP


# Ecossistema do Projeto:

#### domain: Tudo o que é relacionado a negócio, problema de negócio é dentro do dominio

#### model: Entidades


# DDD:

É uma abordagem de desenvolvimento de software com foco no dominio

#### Padrao Aggregate (DDD):

É um grupo de objetos de dominio que pode ser tratados como uma única unidade

<!-- ANOTAÇÕES -->

## @Bean :

Ele instancia, configura e inicializa um novo objeto que será gerenciado pelo container spring

## @Primary:

Prioriza o bean para fazer a desambiguação

## @Qualifier:

Uma anotação para qualificar o nosso componente, e nessa qualificação colocamos um identificador (@Qualifier = ("email") )

## @Retention

Diz quanto tempo a classe vai permanecer sendo utilizada


## @Profile:

Você vai indicar para qual ambiente quer que seja rodada os beans

## @Value

Faz uma injeção de valor usando uma expression do Spring

ex.: @Value ($"{notificador.email.porta-servidor}")   -- este notificador.email está nos application.properties

## @ConfigurationProperties:

Estamos dizendo que a classe representa um arquivo de configurações de propriedades

ex.: @ConfigurationProperties("notificador.email")


## @Transactional:

Quando eu anoto um metodo com Transactional, o metodo vai ser executado dentro de uma transação


## @Data:

Esta anotação é um conjunto de anotações como -> @Getter, @Setter, @EqualsAndHashCode e @ToString

## @EqualsAndHashCode -> por padrão fica na classe inteira, mas podemos fazer apenas por atributo igual está abaixo

(onlyExcplicitlyIncluded), apenas se eu deixar explicito que eu queiro incluir no atributo

    @EqualsAndHashCode.INCLUDE
ex.: private Long id;

<!-- SPRING DATA JPA -->


## @Entity:

A classe representa uma entidade, pensando no mapeamento objeto relacional

## @Table:

Caso queira, você pode definir o nome da tabela, mas por padrão ela vai com o nome da classe


## @Id:

O atributo vai representar o identificador da identidade


## @GeneratedValue: -- Auto Increment

(strategy) = strategia de geração de valor do ID

IDENTITY: Passa a responsabilidade para o provedor do ID, nesse caso o MySql

