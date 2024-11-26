--liquibase formatted sql

--changeset servico-exemplo:create-db_tabela_padrao
IF OBJECT_ID('exemplo.db_tabela_padrao') IS NULL
    CREATE TABLE exemplo.db_tabela_padrao (
        id numeric(19, 0) IDENTITY(1,1) NOT NULL,
        coluna_texto nvarchar(100) NULL,
        coluna_numero numeric(10, 0) NULL,
        coluna_decimal numeric(19, 2) NULL,
        coluna_enum smallint NULL,
        coluna_dt date NULL,
        coluna_dh datetime NULL,
        status bit NOT NULL,
        CONSTRAINT db_tabela_padrao_pk PRIMARY KEY (id)
     );