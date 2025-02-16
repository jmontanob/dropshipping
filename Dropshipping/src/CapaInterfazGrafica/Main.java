package CapaInterfazGrafica;
import CapaLogica.*;


public class Main {
    public static void main(String[] args) {
        Vendedor vendedor1 = new Vendedor("Cenfotec", "San Jose", "gmoreraq@ucenfotec.ac.cr", "12345678");
        Producto producto1 = new Producto("RAM", "Electrónica", 60.0, 0.5, "133 x 31 mm", 10, vendedor1);
        Comprador comprador1 = new Comprador("moonlight", "Ariana Grande", "12345678", "1993-06-26", "moonlight@arianagrande.com", "Password");
        Administrador admin1 = new Administrador("admin", "Daniel Montano", "87654321", "2001-03-06", "jmontanob@ucenfotec.ac.cr", "Admin@2025");
        Pedido pedido1 = new Pedido(1, comprador1);

        System.out.println(producto1);
        System.out.println(vendedor1);
        comprador1.mostrarInformacion();
        admin1.mostrarInformacion();
        System.out.println(pedido1);
    }
}