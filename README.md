[![Build Status](https://travis-ci.org/GuilhermeFGL/Crud-Produto-Imagem.svg?branch=master)](https://travis-ci.org/GuilhermeFGL/Crud-Produto-Imagem)

# Crud Produto Imagem

Aplicação Spring Bot para CRUD de Produtos e Imagens. 
Projeto desenvolvido, executado e testado na IDE Spring Tool Suit

**Para rodar a aplicação use o comando:**
` mvn spring-boot:run `

**Para rodar os testes unitários use o comando:**
` mvn test `

**URL base:**
```HTTP
 http://localhost:8080/
 [HEADER] Content-Type: application/json
```


### CRUD Produtos

- Adicionar produto
```HTTP
[POST] api/produto
[BODY] {
  "nome": string,
  "descricao": string,
  "produtoPai": {      *// opcional*
    "idProduto": int
  }
}
```

- **Listar produtos**
```HTTP
[GET] api/produtos
```

- **Listar produtos incluindo imagens**
```HTTP
[GET] api/produtos/imagens
```

- **Listar produtos incluindo produto pai**
```HTTP
[GET] api/produtos/pais
```

- **Pesquisar produto**
```HTTP
[GET] api/{int produtoId}
```

- **Pesquisar produto incluindo imagens**
```HTTP
[GET] api/{int produtoId}/imagens
```

- **Pesquisar produto inlucindo produto pai**
```HTTP
[GET] api/{int produtoId}/pais
```

- **Listar produtos filhos por id de produto pai**
```HTTP
[GET] api/produtos/{int produtoPaiId}/filhos
```

- **Atualizar produto**
```HTTP
[PUT] api/produtos/{int produtoId}
[BODY] {
  "nome": string,      *// opcional*
  "descricao": string, *// opcional*
  "produtoPai": {      *// opcional*
    "idProduto": int
  }
}
```

- **Deletar produto**
```HTTP
[DELETE] api/produtos/{int produtoId}
```

### CRUD Imagens

- **Adicionar imagem**
```HTTP
[POST] api/imagem
[BODY] {
  "tipo": string,
  "produto": {
    "idProduto": int
  }
}
```

- **Listar imagens**
```HTTP
[GET] api/imagens
```

- **Listar imagens de um produto**
```HTTP
[] api/imagens?produto={int produtoId}
```

- **Atualizar imagem**
```HTTP
[PUT] api/imagens
[BODY] {
  "tipo": string,    *// opcional*
  "produto": {       *// opcional*
    "idProduto": int
  }
}
```

- **Deletar imagem**
```HTTP
[DELETE] api/imagens/{int imagemId}
```


Autoria [GuilhermeFGL](https://www.linkedin.com/in/guilherme-faria-da-gama-lima-37baa647/) :neckbeard:
