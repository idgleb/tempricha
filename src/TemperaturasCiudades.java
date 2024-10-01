public class TemperaturasCiudades {
    private String[] nombresCiudades;
    private double[][] temperaturas;

    // Constructor para inicializar datos de ciudades y temperaturas
    public TemperaturasCiudades(int numCiudades) {
        this.nombresCiudades = new String[numCiudades];
        this.temperaturas = new double[numCiudades][7];
    }

    // Metodo para establecer el nombre de la ciudad
    public void setNombreCiudad(int indice, String nombre) {
        this.nombresCiudades[indice] = nombre;
    }

    // Metodo para obtener el nombre de una ciudad
    public String getNombreCiudad(int indice) {
        return this.nombresCiudades[indice];
    }

    // Metodo para establecer la temperatura de ciudad en un dia específico
    public void setTemperatura(int ciudad, int dia, double temperatura) {
        this.temperaturas[ciudad][dia] = temperatura;
    }

    // Metodo para calcular el promedio semanal de cada ciudad
    public double[] calcularPromedioCiudad() {
        int numCiudades = temperaturas.length;
        double[] promedios = new double[numCiudades];

        for (int i = 0; i < numCiudades; i++) {
            double suma = 0;
            for (int j = 0; j < 7; j++) {
                suma += temperaturas[i][j];
            }
            promedios[i] = suma / 7;
        }

        return promedios;
    }

    // Metodo para encontrar la temperatura maxima en toda la matriz y la ciudad correspondiente
    public double[] temperaturaMaxima() {
        double maxTemp = -Double.MAX_VALUE;
        int ciudadMax = -1;

        for (int i = 0; i < temperaturas.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (temperaturas[i][j] > maxTemp) {
                    maxTemp = temperaturas[i][j];
                    ciudadMax = i;
                }
            }
        }

        return new double[]{maxTemp, ciudadMax};
    }
}