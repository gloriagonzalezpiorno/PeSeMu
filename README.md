# PeSeMu
# FASE1
## Nombre de la aplicación
PeSeMu

## Descripción

### Descripción de la temática de la web
La aplicación web consiste en la venta on-line de películas, series de TV y productos musicales.

### Funcionalidad pública y privada y diferentes usuarios
La parte pública se trata en un catálogo con los productos disponibles, su precio, su valoración
por los clientes, etc.
La parte privada para el cliente sería su carro de la compra, historial, etc.
mientras que la parte privada para el administrador sería el stock, la gestión de pedidos,
facturación, etc.

## Entidades

Las entidades principales de la aplicación web son:
* Producto: tipo de producto, nombre, precio, etc.
* Usuario: Tenemos tanto el cliente como el adminsitrador, diferenciandolos por roles. Usuario registrado, usuario no registrado y administrador.
* Compra: contiene la información de la compra de un cliente: productos, fecha, coste total, etc.
* Opinion: valoración de un cliente sobre un producto.
* Factura: generamos la factura en pdf. 

## Descripción del servicio

Nuestro servicio externo consistirá en la generación de facturas de cada compra.

## Equipo
### Integrantes del equipo de desarrollo:

**Daniel Jesús Rosado Fernández**

Correo oficial de la universidad: dj.rosado@alumnos.urjc.es

Cuenta Github: daro2

**Gloria González Piorno**

Correo oficial de la universidad: g.gonzalezpi@alumnos.urjc.es

Cuenta Github: gloriagonzalezpiorno


# FASE2
Fase 2 de PeSeMu

## Diagrama de navegación

![diagrama_navegacion-1](https://cloud.githubusercontent.com/assets/25226521/22975175/f95375ae-f385-11e6-8e6c-a61ad05da35c.jpg)
![diagrama_navegacion-2](https://cloud.githubusercontent.com/assets/25226521/22975178/fd920388-f385-11e6-9931-5cd69bd298ab.jpg)
![diagrama_navegacion-3](https://cloud.githubusercontent.com/assets/25226521/22975179/fff672da-f385-11e6-8006-788b4d25a7f3.jpg)
![diagrama_navegacion-4](https://cloud.githubusercontent.com/assets/25226521/22975180/0308f682-f386-11e6-8e9a-82074f830d5a.jpg)
![diagrama_navegacion-5](https://cloud.githubusercontent.com/assets/25226521/22975184/05a68bca-f386-11e6-9dce-0fbc46baa2ab.jpg)
![diagrama_navegacion-6](https://cloud.githubusercontent.com/assets/25226521/22975186/088ab258-f386-11e6-9443-617cfc71d02d.jpg)
![diagrama_navegacion-7](https://cloud.githubusercontent.com/assets/25226521/22975191/0b09be52-f386-11e6-83c0-b13f1c464162.jpg)
![diagrama_navegacion-8](https://cloud.githubusercontent.com/assets/25226521/22975193/0d57a1b0-f386-11e6-8050-a03135b58a4e.jpg)

Enlace para dercargar versión en pdf:
[diagrama_navegacion.pdf](https://github.com/gloriagonzalezpiorno/PeSeMu/files/777003/diagrama_navegacion.pdf)

## Diagrama de clases UML

![diagramaclases](https://cloud.githubusercontent.com/assets/18498519/22970648/1dbce136-f373-11e6-8dba-bf58c8ddb377.jpg)

## Diagrama Entidad Relación 

![diagrama-er](https://cloud.githubusercontent.com/assets/18498519/22971731/f7f1c760-f376-11e6-8df3-86a7dfb7528c.JPG)



# FASE 3
Fase 3 de PeSeMu

## Servicio
Enlace al repositorio del servicio: https://github.com/gloriagonzalezpiorno/servicioPesemu

El servicio consiste en una API REST con un método que recibe un objeto Factura y crea un fichero de texto
con la información.

## Diagrama de navegación

![diagramaseguridad-001](https://cloud.githubusercontent.com/assets/25226521/24121037/0b26f986-0db7-11e7-8184-264537472c13.jpg)
![diagramaseguridad-002](https://cloud.githubusercontent.com/assets/25226521/24121038/0b27e2f6-0db7-11e7-81fa-28cff6b693a5.jpg)
![diagramaseguridad-003](https://cloud.githubusercontent.com/assets/25226521/24121039/0b3dac3a-0db7-11e7-9db8-a73cb52c526d.jpg)
![diagramaseguridad-004](https://cloud.githubusercontent.com/assets/25226521/24121040/0b3e185a-0db7-11e7-9e82-78aa87ff3d9e.jpg)
![diagramaseguridad-005](https://cloud.githubusercontent.com/assets/25226521/24121042/0b510050-0db7-11e7-9018-a8b0735217e0.jpg)
![diagramaseguridad-006](https://cloud.githubusercontent.com/assets/25226521/24121041/0b50872e-0db7-11e7-9277-05bdd88a21b7.jpg)

Enlace para dercargar versión en pdf:
[diagramaSeguridad.pdf](https://github.com/gloriagonzalezpiorno/PeSeMu/files/856225/diagramaSeguridad.pdf)

## Diagrama de clases y templates

![diagramaclasesfase3](https://cloud.githubusercontent.com/assets/18498519/24119912/19fa08bc-0db3-11e7-8554-69a1d424c6f1.png)

## Instrucciones precisas para desplegar la aplicación en Azure

### En primer lugar tenemos que crear un par de claves ssh:  azureus-cert.pem y azureus.key

Después levantaremos una máquina virtual en Azure con Ubuntu.

### Para instalar OpenJDK 8 JRE:

Nos conectamos a ella utilizando ssh:

ssh –i azureus.key azureuser@pesemu.cloudapp.net

E instalamos Java:

sudo add-apt-repository ppa:openjdk-r/ppa

sudo apt-get update

sudo apt-get install openjdk-8-jr 

### Ahora podemos crear una imagen de la máquina virtual con Java 8 instalado

Primero limpiamos con: sudo waagent –deprovision+user

Salimos de la MV, la apagamos y hacemos una captura.

Arrancamos una nueva MV llamada ‘pesemu’ a partir de la imagen anterior. 

Se le añade el fichero azureus-cert.pem 

### Después instalamos mysql en la nueva maquina virtual:

sudo apt-get update

sudo apt-get install-mysql-server

Para crear la base de datos:

mysql –u root –p

create database bdpesemu;

exit


### Ahora podemos subir la aplicación a Azure:

Subir el fichero de la aplicación que use BBDD con auto=”none” 

scp – i azureus.key pesemu-0.0.1-SNAPSHOT.jar azureuser@pesemu.cloudapp.net:/home/azureuser/

La primera vez que ejecutemos la aplicación se deberá crear el esquema en la base de datos:

java –jar pesemu-0.0.1-SNAPSHOT.jar --spring.jpa.hibernate.ddl-auto="create"

Subimos el fichero ejecutable del servicio y ya podemos usar la aplicación PeSeMu en Azure.

Las demás veces que queramos arrancar la aplicación lo haremos de la siguiente manera:

java -jar pesemu-0.0.1-SNAPSHOT.jar &

java -jar pesemu_servicio-0.0.1-SNAPSHOT.jar &

Tanto en servicio como la aplicación podrían ejecutarse en segundo plano para comprobar su funcionamiento.
