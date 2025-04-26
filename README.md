Tienda Online

Este proyecto es una aplicación de gestión de una tienda en línea que permite a los administradores gestionar productos y a los compradores realizar pedidos. La aplicación está estructurada en capas, siguiendo el patrón de diseño de arquitectura en capas.

Estructura del Proyecto

El proyecto está organizado en el paquete `CapaLogica`, que contiene las siguientes clases:

- Usuario: Clase abstracta que representa a un usuario de la tienda (compradores y administradores).
- Administrador: Clase que extiende `Usuario` y representa a un administrador de la tienda.
- Comprador: Clase que extiende `Usuario` y representa a un comprador.
- Vendedor: Clase que representa a un vendedor de productos.
- Producto: Clase que representa un producto en la tienda.
- Pedido: Clase que representa un pedido realizado por un comprador.
- GestorPedidos: Clase que gestiona los pedidos realizados en la tienda.
- GestorProductos: Clase que gestiona los productos disponibles en la tienda.

Los pasos a seguir son los siguientes:

cd Dropshipping/src

javac .\CapaLogica\*.java  

javac .\CapaInterfazGrafica\*.java  

 java CapaInterfazGrafica.TiendaUI

#para ver la documentacion hacer el siguiente paso

abrir en browser javadocs/index.html

Requisitos

- Java 8 o superior
- MySQL 5.7 o superior
- MySQL Workbench (opcional, para gestionar la base de datos)

Instalación

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   cd tu_repositorio














