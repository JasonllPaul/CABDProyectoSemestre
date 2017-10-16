/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     15/10/2017 6:09:07 p. m.                     */
/*==============================================================*/


alter table CIUDAD
   drop constraint FK_CIUDAD_ESTA_EN_DEPARTAM;

alter table COMPRENDE
   drop constraint FK_COMPREND_COMPRENDE_RESERVA;

alter table COMPRENDE
   drop constraint FK_COMPREND_COMPRENDE_HABITACI;

alter table CONTIENE
   drop constraint FK_CONTIENE_CONTIENE_HOTEL;

alter table CONTIENE
   drop constraint FK_CONTIENE_CONTIENE2_EMPLEADO;

alter table EMPLEADO
   drop constraint FK_EMPLEADO_A_CARGO2_HOTEL;

alter table ES
   drop constraint FK_ES_ES_ROL;

alter table ES
   drop constraint FK_ES_ES2_EMPLEADO;

alter table HABITACION
   drop constraint FK_HABITACI_COMPUESTO_HOTEL;

alter table HABITACION
   drop constraint FK_HABITACI_SE_CLASIF_TIPO_HAB;

alter table HOTEL
   drop constraint FK_HOTEL_A_CARGO_EMPLEADO;

alter table HOTEL
   drop constraint FK_HOTEL_PERTENECE_CATEGORI;

alter table HOTEL
   drop constraint FK_HOTEL_SE_ENCUEN_CIUDAD;

alter table RESERVA
   drop constraint FK_RESERVA_REALIZA_CLIENTE;

alter table RESERVA
   drop constraint FK_RESERVA_SE_HACE_HOTEL;

alter table TELEFONOCLIENTE
   drop constraint FK_TELEFONO_POSEE_CLIENTE;

alter table TELEFONOEMPLEADO
   drop constraint FK_TELEFONO_TIENE_ASI_EMPLEADO;

alter table TELEFONOHOTEL
   drop constraint FK_TELEFONO_TIENE_HOTEL;

drop table CATEGORIA cascade constraints;

drop index ESTA_EN_FK;

drop table CIUDAD cascade constraints;

drop table CLIENTE cascade constraints;

drop index COMPRENDE2_FK;

drop index COMPRENDE_FK;

drop table COMPRENDE cascade constraints;

drop index CONTIENE2_FK;

drop index CONTIENE_FK;

drop table CONTIENE cascade constraints;

drop table DEPARTAMENTO cascade constraints;

drop index A_CARGO2_FK;

drop table EMPLEADO cascade constraints;

drop index ES2_FK;

drop index ES_FK;

drop table ES cascade constraints;

drop index SE_CLASIFICA_FK;

drop index COMPUESTO_FK;

drop table HABITACION cascade constraints;

drop index SE_ENCUENTRA_FK;

drop index A_CARGO_FK;

drop index PERTENECE_FK;

drop table HOTEL cascade constraints;

drop index REALIZA_FK;

drop index SE_HACE_FK;

drop table RESERVA cascade constraints;

drop table ROL cascade constraints;

drop index POSEE_FK;

drop table TELEFONOCLIENTE cascade constraints;

drop index TIENE_ASIGNADO_FK;

drop table TELEFONOEMPLEADO cascade constraints;

drop index TIENE_FK;

drop table TELEFONOHOTEL cascade constraints;

drop table TIPO_HABITACION cascade constraints;

/*==============================================================*/
/* Table: CATEGORIA                                             */
/*==============================================================*/
create table CATEGORIA 
(
   CATID                INTEGER              not null,
   CATESTRELLA          INTEGER              not null,
   constraint PK_CATEGORIA primary key (CATID)
);

/*==============================================================*/
/* Table: CIUDAD                                                */
/*==============================================================*/
create table CIUDAD 
(
   CIUID                INTEGER              not null,
   DEPID                INTEGER              not null,
   CIUNOMBRE            CHAR(50)             not null,
   constraint PK_CIUDAD primary key (CIUID)
);

/*==============================================================*/
/* Index: ESTA_EN_FK                                            */
/*==============================================================*/
create index ESTA_EN_FK on CIUDAD (
   DEPID ASC
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE 
(
   CLIDNI               INTEGER              not null,
   CLINOMBRE            CHAR(50)             not null,
   CLIAPELLIDO          CHAR(50)             not null,
   constraint PK_CLIENTE primary key (CLIDNI)
);

/*==============================================================*/
/* Table: COMPRENDE                                             */
/*==============================================================*/
create table COMPRENDE 
(
   RESID                INTEGER              not null,
   HABID                INTEGER              not null,
   constraint PK_COMPRENDE primary key (RESID, HABID)
);

/*==============================================================*/
/* Index: COMPRENDE_FK                                          */
/*==============================================================*/
create index COMPRENDE_FK on COMPRENDE (
   RESID ASC
);

/*==============================================================*/
/* Index: COMPRENDE2_FK                                         */
/*==============================================================*/
create index COMPRENDE2_FK on COMPRENDE (
   HABID ASC
);

/*==============================================================*/
/* Table: CONTIENE                                              */
/*==============================================================*/
create table CONTIENE 
(
   HOTID                INTEGER              not null,
   EMPDNI               INTEGER              not null,
   constraint PK_CONTIENE primary key (HOTID, EMPDNI)
);

/*==============================================================*/
/* Index: CONTIENE_FK                                           */
/*==============================================================*/
create index CONTIENE_FK on CONTIENE (
   HOTID ASC
);

/*==============================================================*/
/* Index: CONTIENE2_FK                                          */
/*==============================================================*/
create index CONTIENE2_FK on CONTIENE (
   EMPDNI ASC
);

/*==============================================================*/
/* Table: DEPARTAMENTO                                          */
/*==============================================================*/
create table DEPARTAMENTO 
(
   DEPID                INTEGER              not null,
   DEPNOMBRE            CHAR(50)             not null,
   constraint PK_DEPARTAMENTO primary key (DEPID)
);

/*==============================================================*/
/* Table: EMPLEADO                                              */
/*==============================================================*/
create table EMPLEADO 
(
   EMPDNI               INTEGER              not null,
   HOTID                INTEGER,
   EMPNOMBRE            CHAR(50)             not null,
   EMPDIRECCION         CHAR(50),
   EMPFECHAINICIO       DATE                 not null,
   EMPFECHAFIN          DATE,
   constraint PK_EMPLEADO primary key (EMPDNI)
);

/*==============================================================*/
/* Index: A_CARGO2_FK                                           */
/*==============================================================*/
create index A_CARGO2_FK on EMPLEADO (
   HOTID ASC
);

/*==============================================================*/
/* Table: ES                                                    */
/*==============================================================*/
create table ES 
(
   ROLID                INTEGER              not null,
   EMPDNI               INTEGER              not null,
   constraint PK_ES primary key (ROLID, EMPDNI)
);

/*==============================================================*/
/* Index: ES_FK                                                 */
/*==============================================================*/
create index ES_FK on ES (
   ROLID ASC
);

/*==============================================================*/
/* Index: ES2_FK                                                */
/*==============================================================*/
create index ES2_FK on ES (
   EMPDNI ASC
);

/*==============================================================*/
/* Table: HABITACION                                            */
/*==============================================================*/
create table HABITACION 
(
   HABID                INTEGER              not null,
   HOTID                INTEGER              not null,
   TIPID                INTEGER              not null,
   HAB                  CHAR(15)             not null,
   HABDESCRIPCION       CHAR(255),
   HABNUMEROCAMAS       INTEGER              default 1
      constraint CKC_HABNUMEROCAMAS_HABITACI check (HABNUMEROCAMAS is null or (HABNUMEROCAMAS between 0 and 10)),
   HABNUMEROTV          INTEGER              default 1
      constraint CKC_HABNUMEROTV_HABITACI check (HABNUMEROTV is null or (HABNUMEROTV between 0 and 10)),
   HABUBICACION         CHAR(100),
   HABAIREACONDICIONADO CHAR(2)              default 'si'
      constraint CKC_HABAIREACONDICION_HABITACI check (HABAIREACONDICIONADO is null or (HABAIREACONDICIONADO between 'no' and 'si' and HABAIREACONDICIONADO = upper(HABAIREACONDICIONADO))),
   constraint PK_HABITACION primary key (HABID)
);

/*==============================================================*/
/* Index: COMPUESTO_FK                                          */
/*==============================================================*/
create index COMPUESTO_FK on HABITACION (
   HOTID ASC
);

/*==============================================================*/
/* Index: SE_CLASIFICA_FK                                       */
/*==============================================================*/
create index SE_CLASIFICA_FK on HABITACION (
   TIPID ASC
);

/*==============================================================*/
/* Table: HOTEL                                                 */
/*==============================================================*/
create table HOTEL 
(
   HOTID                INTEGER              not null,
   CIUID                INTEGER              not null,
   EMPDNI               INTEGER              not null,
   CATID                INTEGER              not null,
   HOTNOMBRE            CHAR(50)             not null,
   HOTDIRECCION         CHAR(50)             not null,
   constraint PK_HOTEL primary key (HOTID)
);

/*==============================================================*/
/* Index: PERTENECE_FK                                          */
/*==============================================================*/
create index PERTENECE_FK on HOTEL (
   CATID ASC
);

/*==============================================================*/
/* Index: A_CARGO_FK                                            */
/*==============================================================*/
create index A_CARGO_FK on HOTEL (
   EMPDNI ASC
);

/*==============================================================*/
/* Index: SE_ENCUENTRA_FK                                       */
/*==============================================================*/
create index SE_ENCUENTRA_FK on HOTEL (
   CIUID ASC
);

/*==============================================================*/
/* Table: RESERVA                                               */
/*==============================================================*/
create table RESERVA 
(
   RESID                INTEGER              not null,
   CLIDNI               INTEGER              not null,
   HOTID                INTEGER              not null,
   RESPERSONAS          INTEGER              not null,
   RESFECHAINICIO       DATE                 not null,
   RESFECHAFIN          DATE,
   RESTOTAL             FLOAT,
   constraint PK_RESERVA primary key (RESID)
);

/*==============================================================*/
/* Index: SE_HACE_FK                                            */
/*==============================================================*/
create index SE_HACE_FK on RESERVA (
   HOTID ASC
);

/*==============================================================*/
/* Index: REALIZA_FK                                            */
/*==============================================================*/
create index REALIZA_FK on RESERVA (
   CLIDNI ASC
);

/*==============================================================*/
/* Table: ROL                                                   */
/*==============================================================*/
create table ROL 
(
   ROLID                INTEGER              not null,
   ROLNOMBRE            CHAR(50)             not null,
   constraint PK_ROL primary key (ROLID)
);

/*==============================================================*/
/* Table: TELEFONOCLIENTE                                       */
/*==============================================================*/
create table TELEFONOCLIENTE 
(
   TELCLINUMERO         CHAR(40)             not null,
   CLIDNI               INTEGER              not null,
   constraint PK_TELEFONOCLIENTE primary key (TELCLINUMERO)
);

/*==============================================================*/
/* Index: POSEE_FK                                              */
/*==============================================================*/
create index POSEE_FK on TELEFONOCLIENTE (
   CLIDNI ASC
);

/*==============================================================*/
/* Table: TELEFONOEMPLEADO                                      */
/*==============================================================*/
create table TELEFONOEMPLEADO 
(
   TELEMPNUMERO         CHAR(40)             not null,
   EMPDNI               INTEGER              not null,
   constraint PK_TELEFONOEMPLEADO primary key (TELEMPNUMERO)
);

/*==============================================================*/
/* Index: TIENE_ASIGNADO_FK                                     */
/*==============================================================*/
create index TIENE_ASIGNADO_FK on TELEFONOEMPLEADO (
   EMPDNI ASC
);

/*==============================================================*/
/* Table: TELEFONOHOTEL                                         */
/*==============================================================*/
create table TELEFONOHOTEL 
(
   TELNUMERO            CHAR(30)             not null,
   HOTID                INTEGER              not null,
   constraint PK_TELEFONOHOTEL primary key (TELNUMERO)
);

/*==============================================================*/
/* Index: TIENE_FK                                              */
/*==============================================================*/
create index TIENE_FK on TELEFONOHOTEL (
   HOTID ASC
);

/*==============================================================*/
/* Table: TIPO_HABITACION                                       */
/*==============================================================*/
create table TIPO_HABITACION 
(
   TIPID                INTEGER              not null,
   TIPTIPO              CHAR(30)             not null,
   TIPPRECIO            FLOAT,
   constraint PK_TIPO_HABITACION primary key (TIPID)
);

alter table CIUDAD
   add constraint FK_CIUDAD_ESTA_EN_DEPARTAM foreign key (DEPID)
      references DEPARTAMENTO (DEPID);

alter table COMPRENDE
   add constraint FK_COMPREND_COMPRENDE_RESERVA foreign key (RESID)
      references RESERVA (RESID);

alter table COMPRENDE
   add constraint FK_COMPREND_COMPRENDE_HABITACI foreign key (HABID)
      references HABITACION (HABID);

alter table CONTIENE
   add constraint FK_CONTIENE_CONTIENE_HOTEL foreign key (HOTID)
      references HOTEL (HOTID);

alter table CONTIENE
   add constraint FK_CONTIENE_CONTIENE2_EMPLEADO foreign key (EMPDNI)
      references EMPLEADO (EMPDNI);

alter table EMPLEADO
   add constraint FK_EMPLEADO_A_CARGO2_HOTEL foreign key (HOTID)
      references HOTEL (HOTID);

alter table ES
   add constraint FK_ES_ES_ROL foreign key (ROLID)
      references ROL (ROLID);

alter table ES
   add constraint FK_ES_ES2_EMPLEADO foreign key (EMPDNI)
      references EMPLEADO (EMPDNI);

alter table HABITACION
   add constraint FK_HABITACI_COMPUESTO_HOTEL foreign key (HOTID)
      references HOTEL (HOTID);

alter table HABITACION
   add constraint FK_HABITACI_SE_CLASIF_TIPO_HAB foreign key (TIPID)
      references TIPO_HABITACION (TIPID);

alter table HOTEL
   add constraint FK_HOTEL_A_CARGO_EMPLEADO foreign key (EMPDNI)
      references EMPLEADO (EMPDNI);

alter table HOTEL
   add constraint FK_HOTEL_PERTENECE_CATEGORI foreign key (CATID)
      references CATEGORIA (CATID);

alter table HOTEL
   add constraint FK_HOTEL_SE_ENCUEN_CIUDAD foreign key (CIUID)
      references CIUDAD (CIUID);

alter table RESERVA
   add constraint FK_RESERVA_REALIZA_CLIENTE foreign key (CLIDNI)
      references CLIENTE (CLIDNI);

alter table RESERVA
   add constraint FK_RESERVA_SE_HACE_HOTEL foreign key (HOTID)
      references HOTEL (HOTID);

alter table TELEFONOCLIENTE
   add constraint FK_TELEFONO_POSEE_CLIENTE foreign key (CLIDNI)
      references CLIENTE (CLIDNI);

alter table TELEFONOEMPLEADO
   add constraint FK_TELEFONO_TIENE_ASI_EMPLEADO foreign key (EMPDNI)
      references EMPLEADO (EMPDNI);

alter table TELEFONOHOTEL
   add constraint FK_TELEFONO_TIENE_HOTEL foreign key (HOTID)
      references HOTEL (HOTID);

