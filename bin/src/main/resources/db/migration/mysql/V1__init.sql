CREATE TABLE `produto` (
  `idProduto` int NOT NULL,
  `nome` varchar(25) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `idProdutoPai` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `imagem` (
  `idImagem` int NOT NULL,
  `tipo` varchar(25) NOT NULL,
  `produto` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `produto`
  ADD PRIMARY KEY (`idProduto`),
  ADD KEY `FK46i4k5vl8wah7feutye9kbpi4` (`idProdutoPai`);

ALTER TABLE `imagem`
  ADD PRIMARY KEY (`idImagem`),
  ADD KEY `FK4cm1kg523jlopyexjbmi6y54j` (`produto`);

ALTER TABLE `produto`
  MODIFY `idProduto` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `imagem`
  MODIFY `idImagem` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `produto`
  ADD CONSTRAINT `FK4cm1kg523jlopyexjbmi6y54j` FOREIGN KEY (`idProdutoPai`) REFERENCES `produto` (`idProduto`);

ALTER TABLE `imagem`
  ADD CONSTRAINT `FK46i4k5vl8wah7feutye9kbpi4` FOREIGN KEY (`produto`) REFERENCES `produto` (`idProduto`);
