### Repositório do projeto completo: https://github.com/Rogerio-Fidencio/iLocation

# 💻 Iniciando a aplicação localmente

1 - Crie na sua maquina um banco de dados PostgreSQL usando o seguinte Script
```
create database ilocation;

create table tb_customer(
	customer_id serial not null primary key,
  	customer_name varchar(50) not null,
  	customer_cpf varchar(11) not null unique,
  	customer_email varchar(100) not null unique,
  	customer_phone varchar(11) not null,
  	customer_cep varchar(9) not null,
  	customer_num_res varchar(10) not null,
  	customer_compl varchar(30)
);

create table tb_delivery_person (
	delivery_person_id serial not null primary key,
  	delivery_person_email varchar(100) not null unique,
  	delivery_person_name varchar(50) not null,
  	delivery_person_cpf varchar(11) not null unique,
  	delivery_person_password text not null,
    delivery_person_phone varchar(11) not null
);

create table tb_order (
	order_id serial not null primary key,
  	customer_id int not null,
  	order_date timestamp not null,
  	order_status int not null,
  	delivery_person_id int,
  	constraint fk_deliv_order foreign key (delivery_person_id) references tb_delivery_person(delivery_person_id),
  	constraint fk_customer_order foreign key (customer_id) references tb_customer(customer_id)
);

create table tb_geolocation(
	location_id serial not null primary key,
  	order_id int not null,
  	"timestamp" timestamp not null,
  	latitude text not null,
  	longitude text not null,
  	constraint fk_order_geolocation foreign key (order_id) references tb_order(order_id)
);
```

2 - Clone o repositório para sua máquina com o comando a baixo:
```
git clone git@github.com:Rogerio-Fidencio/iLocation-back.git
```

3 - Importe o projeto usando uma IDE como Eclipse, Intellij, etc...

4 - No caminho "src/main/resources/" coloque as informações do seu banco de dados local.

5 - Execute o  arquivo "IlocationApplication.java" no caminho "src/main/java/br/com/verbososcorp".
