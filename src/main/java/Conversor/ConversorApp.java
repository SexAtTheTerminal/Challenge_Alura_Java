package Conversor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversor conversor = new Conversor();
        List<String> historial = new ArrayList<>();

        boolean continuar = true;

        while (continuar) {
            try {
                System.out.print("\nIngrese monto en PEN: ");
                double monto = scanner.nextDouble();
                scanner.nextLine(); // limpiar buffer

                // Mostrar menú de monedas
                System.out.println("\nSeleccione la moneda destino:");
                System.out.println("1. USD (Dólar estadounidense)");
                System.out.println("2. EUR (Euro)");
                System.out.println("3. JPY (Yen japonés)");
                System.out.println("4. CLP (Peso chileno)");
                System.out.println("5. MXN (Peso mexicano)");
                System.out.print("Opción: ");
                int opcion = scanner.nextInt();

                String monedaDestino = "";
                switch (opcion) {
                    case 1: monedaDestino = "USD"; break;
                    case 2: monedaDestino = "EUR"; break;
                    case 3: monedaDestino = "JPY"; break;
                    case 4: monedaDestino = "CLP"; break;
                    case 5: monedaDestino = "MXN"; break;
                    default:
                        System.out.println("Opción no válida.");
                        continue;
                }

                double resultado = conversor.convertir(monto, monedaDestino);
                String resultadoStr = String.format("%.2f PEN equivale a %.2f %s", monto, resultado, monedaDestino);
                System.out.println("\n" + resultadoStr);


                historial.add(resultadoStr);

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al obtener las tasas de cambio: " + e.getMessage());
            }


            System.out.print("\n¿Desea realizar otra conversión? (s/n): ");
            String respuesta = scanner.next().toLowerCase();
            if (!respuesta.equals("s")) {
                continuar = false;
            }
        }


        System.out.println("\nHistorial de conversiones:");
        if (historial.isEmpty()) {
            System.out.println("No se realizaron conversiones.");
        } else {
            for (String registro : historial) {
                System.out.println("- " + registro);
            }
        }

        scanner.close();
        System.out.println("\nGracias por usar el conversor.");
    }
}
