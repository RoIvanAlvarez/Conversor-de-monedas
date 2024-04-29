package principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.Conversion;
import operaciones.ConsumoAPI;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {

        String monedaDe = "", monedaA = "";
        double cantidad = 0;
        boolean salir =true;

        Scanner sc = new Scanner(System.in);
        ConsumoAPI api = new ConsumoAPI();
        FileWriter esc = new FileWriter("HistorialConversiones.txt");

        while (salir != false){
            System.out.println("****************************************");
            System.out.println("Elija la conversión");
            System.out.println("1.- Euro a Dolar");
            System.out.println("2.- Dolar a Euro ");
            System.out.println("3.- Peso mexicano a Euro");
            System.out.println("4.- Euro a Peso mexicano");
            System.out.println("5.- Peso argentino a Dolar australiano");
            System.out.println("6.- Dolar australiano a Peso argentino");
            System.out.println("7.- salir");
            int opc = sc.nextInt();
            if (opc != 7){
                System.out.println("Ingrese la cantidad a convertir");
                cantidad = sc.nextDouble();
            }
            switch (opc){
                case 1:
                    monedaDe = "EUR";
                    monedaA = "USD";
                    break;
                case 2:
                    monedaDe = "USD";
                    monedaA = "EUR";
                    break;
                case 3:
                    monedaDe = "MXN";
                    monedaA = "EUR";
                    break;
                case 4:
                    monedaDe = "EUR";
                    monedaA = "MXN";
                    break;
                case 5:
                    monedaDe = "ARS";
                    monedaA = "AUD";
                    break;
                case 6:
                    monedaDe = "USD";
                    monedaA = "AUD";
                    break;
                case 7:
                    salir = false;
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    break;
            }
            if (opc != 7){
                Conversion conversion = api.conversion(monedaDe, monedaA, cantidad);
                System.out.println("El valor de " + cantidad + " [" + monedaDe + "]"
                        + " corresponde al valor de " + conversion.conversion_result()
                + " [" + monedaA + "]");
                esc.write(LocalDate.now() + " " + LocalTime.now() +
                        " =>> El valor de " + cantidad + " [" + monedaDe + "]"
                        + " corresponde al valor de " + conversion.conversion_result()
                        + " [" + monedaA + "]\n");
            }else {
                esc.close();
                System.out.println("Finalizando programa");
            }
        }
    }
}
