[![Build Status](https://travis-ci.org/GuilhermeFGL/Crud-Produto-Imagem.svg?branch=master)](https://travis-ci.org/GuilhermeFGL/Crud-Produto-Imagem)

# Crud Produto Imagem

Aplicação Spring Bot para CRUD de Produtos e Imagens. Projeto desenvolvido, executado e testado na IDE Spring Tool Suit

**Para rodar a aplicação use o comando:**
```mvn spring-boot:run```

**Para rodar os testes unitários use o comando:**
```mvn test```

**URL base:**
```
http://localhost:8080/
[HEADER] Content-Type: application/json
```


### CRUD Produtos

- Adicionar produto
```
[POST] api/produto
[BODY] {
  "nome": string,
  "descricao": string,
  "produtoPai": {      // opcional     
    "idProduto": int
  }
}
```

- Listar produtos
```
[GET] api/produtos
```

- Listar produtos incluindo imagens
```
[GET] api/produtos/imagens
```

- Listar produtos incluindo produto pai
```
[GET] api/produtos/pais
```

- Pesquisar produto
```
[GET] api/{int produtoId}
```

- Pesquisar produto incluindo imagens
```
[GET] api/{int produtoId}/imagens
```

- Pesquisar produto inlucindo produto pai
```
[GET] api/{int produtoId}/pais
```

- Listar produtos filhos por id de produto pai
```
[GET] api/produtos/{int produtoPaiId}/filhos
```

- Atualizar produto
```
[PUT] api/produtos/{int produtoId}
[BODY] {
  "nome": string,      // opcional
  "descricao": string, // opcional
  "produtoPai": {      // opcional
    "idProduto": int
  }
}
```

- Deletar produto
```
[DELETE] api/produtos/{int produtoId}
```

### CRUD Imagens

- Adicionar imagem
```
[POST] api/imagem
[BODY] {
  "tipo": string,
  "produto": {
    "idProduto": int
  }
}
```

- Listar imagens
```
[GET] api/imagens
```

- Listar imagens de um produto
```
[] api/imagens?produto={int produtoId}
```

- 
```
[PUT] api/imagens
[BODY] {
  "tipo": string,    //opcional
  "produto": {       //opcional
    "idProduto": int
  }
}
```

- Deletar imagem
```
[DELETE] api/imagens/{int imagemId}
```
