package ru.voronezhtsev.weatherapp.view;

public class TempUtils {

    private TempUtils() {
        throw new IllegalStateException("TempUtils can not have instances");
    }

    public static double kelvinToCelcius(double temp) {
        return Math.round(temp - 273.15);
    }

    /**
     * Удалить незначащие нули после точки
     *
     * @param temp температура
     * @return отформатированная температура без нулей в конце
     */
    public static String trimZeroes(double temp) {
        String tempStr = String.valueOf(temp);
        return tempStr.contains(".") ? tempStr.replaceAll("0*$", "").replaceAll("\\.$", "") : tempStr;
    }
}
