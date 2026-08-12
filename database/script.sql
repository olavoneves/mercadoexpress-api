-- =====================================================================
-- FIAP - TDS - Checkpoint 4 (Parte I)
-- Projeto: Mercado Express API
-- Banco...: Oracle (oracle.fiap.com.br)
-- Execute este script no SQL Developer conectado com o seu usuario RM.
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1) LIMPEZA (ignore os erros ORA-00942 / ORA-02289 na primeira execucao,
--    eles apenas indicam que o objeto ainda nao existia)
-- ---------------------------------------------------------------------
DROP TABLE TDS_TB_MERCADO CASCADE CONSTRAINTS;
DROP SEQUENCE TDS_SQ_MERCADO;

-- ---------------------------------------------------------------------
-- 2) TABELA
-- ---------------------------------------------------------------------
CREATE TABLE TDS_TB_MERCADO (
    ID       NUMBER(10)      NOT NULL,
    NOME     VARCHAR2(100)   NOT NULL,
    TIPO     VARCHAR2(50)    NOT NULL,
    SETOR    VARCHAR2(50)    NOT NULL,
    TAMANHO  VARCHAR2(30),
    PRECO    NUMBER(10,2)    NOT NULL,
    CONSTRAINT PK_TDS_TB_MERCADO PRIMARY KEY (ID),
    CONSTRAINT CK_TDS_MERCADO_PRECO CHECK (PRECO >= 0)
);

COMMENT ON TABLE  TDS_TB_MERCADO          IS 'Itens disponiveis no mercado express';
COMMENT ON COLUMN TDS_TB_MERCADO.ID       IS 'Identificador unico do item';
COMMENT ON COLUMN TDS_TB_MERCADO.NOME     IS 'Nome comercial do produto';
COMMENT ON COLUMN TDS_TB_MERCADO.TIPO     IS 'Tipo/categoria do produto (Vestuario, Limpeza, Fruta...)';
COMMENT ON COLUMN TDS_TB_MERCADO.SETOR    IS 'Setor do mercado (Bazar, Higiene e Limpeza, Hortifruti...)';
COMMENT ON COLUMN TDS_TB_MERCADO.TAMANHO  IS 'Tamanho/volume da embalagem (P, M, G, 500ml, 1kg...)';
COMMENT ON COLUMN TDS_TB_MERCADO.PRECO    IS 'Preco unitario em reais (nunca negativo)';

-- ---------------------------------------------------------------------
-- 3) SEQUENCE (allocationSize = 1 no lado do JPA)
-- ---------------------------------------------------------------------
CREATE SEQUENCE TDS_SQ_MERCADO
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- ---------------------------------------------------------------------
-- 4) MASSA DE TESTE - um item de cada setor
-- ---------------------------------------------------------------------
INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (TDS_SQ_MERCADO.NEXTVAL, 'Meia Esportiva Algodao', 'Vestuario', 'Bazar', 'M', 19.90);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (TDS_SQ_MERCADO.NEXTVAL, 'Detergente Neutro', 'Limpeza', 'Higiene e Limpeza', '500ml', 3.49);

INSERT INTO TDS_TB_MERCADO (ID, NOME, TIPO, SETOR, TAMANHO, PRECO)
VALUES (TDS_SQ_MERCADO.NEXTVAL, 'Banana Prata', 'Fruta', 'Hortifruti', '1kg', 7.99);

COMMIT;

-- ---------------------------------------------------------------------
-- 5) CONFERENCIA
-- ---------------------------------------------------------------------
SELECT * FROM TDS_TB_MERCADO ORDER BY ID;
