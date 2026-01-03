## Desafio de cupom
Lucas William Silva Jordão


#### A aplicação foi desenvolvida em:
- Java 11
- Spring 3.2.5


-----------

### O que foi feito?

Foram desenvolvidas 3 apis, sendo uma para criação, outra para deleção e por fim uma de consulta de cupom.

### Como rodar o projeto?

Bastar dar um run depois do ```mvn clean install```.

Você poderá testar via insomnia/postman, ou acessando diretamente o swagger da aplicação: http://localhost:8080/swagger-ui/index.html#/coupon-controller/createCoupon

Também pode ver os resultados das inserções/deleções aqui: http://localhost:8080/h2-console


### Qual arquitetura escolhida?

Eu resolvi usar a arquitetura hexagonal (da forma que entendo ela), pois já utilizo ela em trabalhos profissionais a um tempo.

### O que faria a mais se tivesse mais tempo?

Talvez a implementação de deixar randomico o codigo do cupom quando não informado um... Também criaria api de resgate do cupom.