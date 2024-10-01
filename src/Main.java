import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        // Solicitar numero de ciudades y validar este numero
        int numCiudades = 0;
        while (numCiudades <= 0) {
            String entrada = JOptionPane.showInputDialog("Ingrese el numero de ciudades:");
            if (esEntero(entrada)) {
                numCiudades = Integer.parseInt(entrada);
                if (numCiudades <= 0) {
                    JOptionPane.showMessageDialog(null, "El numero de ciudades debe ser mayor a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Entrada no valida. Debe ser un numero entero.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Crear instancia de TemperaturasCiudades
        TemperaturasCiudades analisis = new TemperaturasCiudades(numCiudades);

        // Solicitar nombres de ciudades
        for (int i = 0; i < numCiudades; i++) {
            String nombreCiudad = JOptionPane.showInputDialog("Ingrese el nombre de la ciudad " + (i + 1) + ":");
            analisis.setNombreCiudad(i, nombreCiudad);
        }

        // Solicitar temperaturas para cada ciudad y día de la semana
        for (int i = 0; i < numCiudades; i++) {
            for (int j = 0; j < 7; j++) {
                double temp = Double.NaN;
                while (Double.isNaN(temp) || temp < -50 || temp > 50) {
                    String mensaje = "Ingrese temperatura para "+analisis.getNombreCiudad(i)+", día"+ (j + 1)+" (entre -50 y 50 °C):";
                    String entrada = JOptionPane.showInputDialog(mensaje);
                    if (esDouble(entrada)) {
                        temp = Double.parseDouble(entrada);
                        if (temp < -50 || temp > 50) {
                            JOptionPane.showMessageDialog(null, "Temperatura fuera del rango permitido (-50 a 50 °C).", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Entrada no válida. Debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                analisis.setTemperatura(i, j, temp);
            }
        }

        // Mostrar el promedio semanal de temperaturas para cada ciudad
        double[] promedios = analisis.calcularPromedioCiudad();
        StringBuilder resultadoPromedios = new StringBuilder("Promedio semanal de temperaturas por ciudad:\n");
        for (int i = 0; i < promedios.length; i++) {
            resultadoPromedios.append(String.format("%s: %.2f °C%n", analisis.getNombreCiudad(i), promedios[i]));
        }
        JOptionPane.showMessageDialog(null, resultadoPromedios.toString());

        // Mostrar la temperatura máxima y la ciudad correspondiente
        double[] maxInfo = analisis.temperaturaMaxima();
        String ciudadMaxima = analisis.getNombreCiudad((int) maxInfo[1]);
        String resultadoMaxima = String.format("La temperatura maxima registrada fue "+maxInfo[0]+" °C en la ciudad "+ciudadMaxima)+".";
        JOptionPane.showMessageDialog(null, resultadoMaxima);
    }

    // Metodo para validar si una cadena es un numero entero
    public static boolean esEntero(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if ((i == 0 && c == '-') || Character.isDigit(c)) {
                continue;
            }
            return false;
        }
        return true;
    }

    // Metodo para validar si una cadena es un numero double
    public static boolean esDouble(String cadena) {
        if (cadena == null || cadena.isEmpty()) {
            return false;
        }
        boolean puntoEncontrado = false;
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (c == '.' && !puntoEncontrado) {
                puntoEncontrado = true;
            } else if ((i == 0 && c == '-') || Character.isDigit(c)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }
}

