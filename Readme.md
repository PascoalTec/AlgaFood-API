# Diferença de REST e RESTful:

## RESTful: Estilo arquitetural que possui as Constraints

Cliente-Servidor: FrontEnd ou uma outra API, etc...

Stateless: A aplicação não deve possuir estado, o servidor não pode armazenar informação contextual sobre o cliente que está usando a API

Cache:

Interface Uniforme: URI, HTTP, metodos CRUD

Sistema em camadas

Codigo sob demanda: Opcional

## RESTful: Segue todas as Constraints obrigatorias religiosamente


# Diferença de URI vs URL:

URI: É um conjunto de caracteres como objetivo dar um endereço a um objeto

URL: Tipo de URI - Localizardor de recurso uniforme

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


## @ResponseBody: 

Ela indica que a resposta dos metodos que estão anotados devem ir para a resposta HTTP


## @RestController

Ela é um conjunto de anotação:

dentro dela tem o @ResponseBody e o @Controller


## @PathVariable:

Usada para ter um vínculo do caminho da variável da URI e a variável do metodo, ela é usada atras do tipo do metodo

ex.: public Cozinha buscar(@PathVariable Long cozinhaId)


## @JsonProperty:

Faz a desserialização de xml ou json

Ela tem uma propriedade (acess.READ_ONLY), que estamos dizendo que esta propriedade será apenas para leitura <!-- Ela tem mais propriedades-->


## @JsonIgnore:

Ignora essa propriedade na hora que for gerar a apresentação


## @JsonIgnoreProperties:

Ele ignora propriedades que estão dentro da instância atribuida a variavel


Ele não ignora a Cozinha, ele ignora uma propriedade da Cozinha

#### ex: @JsonIgnoreProperties("hibernateLazyInitializer") -> Lazy - ele demora a iniciar

#### ex: @JsonIgnoreProperties("nome", allowGetters = true) -> permite metodo de GET



## @JsonRootName:

Coloca no inicio da classe, você faz a mudança na hora da apresentação por xml, por exemplo:

<cozinha>
    <id>1</id>
    <titulo>Tailandesa</titulo>
</cozinha>

você mudaria o cozinha para qualquer nome que quiser


## @RequestBody: 

ex.: public void adicionar(@RequestBody Cozinha cozinha)

O parametro indicado receberá o corpo da requisição, oque for adicionado, será atribuido ao metodo cozinha

## @ResponseEntity:

Ele representa a resposta que vai ser retornada, porém com ele podemos manipular melhor a resposta.


## @JoinColumn

Indica que a classe na qual você está utilizando-a é a dona ou o lado forte do relacionamento.


## @Builder:

Padrão de projeto para construir objeto, em uma linguagem mais fluente


## @ExceptionHandler:

Aceita que um argumento que é a classe que eu quero tratar, vai capturar todas as excessões que são dela ou sub dela


## @ControllerAdvice:

Dentro de um componente anotado por ela, pode-se adicionar as ExceptionHandler que as excessões de todo o projeto será controlado pela classe que está anotada o Controller Advice, será o centro das exceptions

<!-- SPRING DATA JPA -->

# Spring Data Jpa Repository ou (JpaRepository)


#### Para incluirmos o JpaRepository, seria em uma classe de interface, extendendo para JpaRepository<Repositorio da Entidade, Tipo da Identidade>

@Repository
ex.: public interface CozinhaRepository extends JpaRepository<Cozinha, Long>


## LIKE - keyword:

#### Para pormos o "LIKE" pelo JpaRepository, temos algumas keywords (Containing)

ex.: findTodasByNomeContaining


## @Query:

CONSULTA JPQL


## @Param:

passa o nome do parametro que queremos fazer o bind dentro do @Query


## @Entity:

A classe representa uma entidade, pensando no mapeamento objeto relacional

## @Table:

Caso queira, você pode definir o nome da tabela, mas por padrão ela vai com o nome da classe


## @Id:

O atributo vai representar o identificador da identidade


## @GeneratedValue: -- Auto Increment

(strategy) = strategia de geração de valor do ID

IDENTITY: Passa a responsabilidade para o provedor do ID, nesse caso o MySql


## @OneToMany:

Relacionamento de Um para Muitos

ex.: UMA cozinha tem em VÁRIOS/MUITOS restaurantes


## @ManyToOne:

Relacionamento de Muitos para um

ex.: Muitos restaurantes tem UMA cozinha


## @ManyToMany:

Relacionamento Muitos para Muitos

ex.: MUITOS restaurantes tem MUITAS formas de pagamento


## @Embeddable:

Indica que esta classe é uma parte de alguma outra entidade, não é uma entidade em si e todas as propriedades desta classe são refletidas na tabela da entidade que incorpora esta classe


## @Embedded:

Indica que esta propriedade é de um tipo "Embedado" ou seja, que está sendo incorporado pela classe Embeddadle


## @Validated:

Ele aceita um argumento que o @Valid não aceita, e as vezes é necessário usar ele por conta que usa a validação específica


# Bean Validation:

@NotNull: Não pode ser nula

@NotEmpty: Não pode estar vazio

@NotBlank: Não pode ser nulo, não pode ser vazio e não pode ter espaço em branco

@Size:

@Valid: Antes de executar o metodo, ele já faz uma validação de uma instancia


## @PrePersist:
#### ex.: Antes de persistir uma entidade Pedido, antes de inserir um novo registro de pedido no banco de dados, chama este metodo

Metodo de CallBack JPA, ele é executado em alguns eventos do ciclo de vida da entidade


## @JsonView:

Customiza uma serialização de objetos de acordo com uma View


## @Singular

Ele singulariza uma coleção


## @NonNull

#### Se por acaso não passar o assunto ou corpo, quando lançar o build ele vai retornar uma exception

ex.:

@NonNull
private String assunto;
        
@NonNull
private String corpo;


## @Slf4j

Ele cria uma variavel log que dentro deste log você tem metodos que aparecem no console


## @EventListener

Essa anotação marca um metodo como um listener de eventos, ou seja, um metodo que está interessado realmente sempre que o evento for disparado
na aplicação


## @TranscationalEventListener

Com esta anotação, especificamos qual que é a fase específica destes eventos disparados, por padrão se botarmos o EventListener, será disparado após a transação for comitada


## @CrossOrigin

Ela pode ser incluida ou na classe inteira ou apenas no metodo

ele tem algumas propriedades

ex.: @CrossOrigins(origins = "http://localhost:8000" ) é um array de string, o padrão para permitir tudo, já é o "*" para qualquer chamada http, mas caso queira especificar, você pode botar como está acima


# CRUD:

#### CREATE - READ - UPDATE E DELETE


## Patch:

Ela altera apenas a propriedade que você está especificando



# Eager Loading (Carregamento Ansioso):

Tudo que termina com ToOne, usa por padrão o Eager Loading

para fazer a mudança de Eager para Lazy, adicionamos o parametro fetch passando FetchType.LAZY


# Lazy Loading (Carregamento Preguiçoso):

Carregamento só vai acontecer quando realmente for necessário, se a gente não usar, ele não irá fazer o carregamento

Tudo que termina com ToMany, usa por padrão o Lazy Loading


# Integration Tests (IT) jUnit

    @Test
   public void testarCadastroCozinhaComSucesso() {
      Cozinha novaCozinha = new Cozinha();
      novaCozinha.setNome("Chinesa");
   
      novaCozinha = cadastroCozinha.salvar(novaCozinha);

      assertThat(novaCozinha).isNotNull();
      assertThat(novaCozinha.getId()).isNotNull();
   }


# Teste de API

Atualmente eu estou com a versão 3.3.4 do Spring, e no curso está 2.7.4, alguns testes estão defasados e faz sentido se der error.

faz a chamada para a API e retorna uma resposta



# DTO (Data Transfer Object)

É um padrão de projetos muito usado para o transporte de dados, um dto agrupa um conjunto de propriedades de uma ou mais classes, por exemplo entidades do nosso modelo em uma classe de transferencia de classes mais simples.




## Chatty API:

Quando modelamos recursos de modelagem fina, estamos criando uma Chatty API

## Chunky API:

È aquela aonde as requisições são feitas em uma única requisição



# UUID:

## Usar o UUID como PRIMARY KEY, não é recomendado por que os bancos não se comportam muito bem com chaves primárias como String

Identificador Unico Universal



# JasperCommunityEdition

## Ferramenta para layout e gerar PDF




# ETags:

Gerenciamento de Cache para melhor uso da aplicação


ex.: return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate()).body(formaPagamentoModel);


.cacheControl() -> determina o tempo do STALE no cache

.cachePrivate() -> para privar as informações do usuário no cache

.cacheControl(CacheControl.noCache()) -> não tem validação

.cacheControl(CacheControl.noStore()) -> não pode armazenar no Store

