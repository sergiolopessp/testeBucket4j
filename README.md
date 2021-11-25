# Aplicando "Rate-limit" em API usando algoritmo de "token-bucket"

Neste exemplo, temos auma aplicação Spring Boot, que por padrão não tem nenhum mecanismo para fazer rate-limit de chamadas de um endpoint de API para um cliente chamador. Então ele implementa uma biblioteca chamada [Bucket4j](https://github.com/vladimir-bukhtoyarov/bucket4j) e criar esse mecanismo.

Este exemplo foi feito inclusive a partir das demos que constam na própria documentação da biblioteca, então vamos aos pré-requisitos que você vai precisar para participar da brincadeira:

- Java 11+
- Maven



E se você quer acompanhar o que foi feito aqui, só seguir o artigo que foi publicado no meu blog, o Dev Initiative onde explico o que foi feito aqui:

https://deviniciative.wordpress.com/2021/11/25/hands-on--aplicando-rate-limit-em-api-usando-algoritmo-de-token-bucket/