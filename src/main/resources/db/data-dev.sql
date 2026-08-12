-- Massa de teste do perfil dev (H2 em memoria).
-- A tabela e a sequence sao criadas pelo Hibernate (ddl-auto=create-drop);
-- por isso aqui so entram os INSERTs, consumindo a mesma sequence da entidade.

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (NEXT VALUE FOR TDS_SQ_MERCADO, 'Meia Esportiva Algodao', 'Vestuario', 'Bazar', 'M', 19.90);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (NEXT VALUE FOR TDS_SQ_MERCADO, 'Detergente Neutro', 'Limpeza', 'Higiene e Limpeza', '500ml', 3.49);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (NEXT VALUE FOR TDS_SQ_MERCADO, 'Banana Prata', 'Fruta', 'Hortifruti', '1kg', 7.99);
