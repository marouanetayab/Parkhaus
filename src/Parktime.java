/**
 * Parkhaus
 *
 * @author Johannes Heimbach
 * 29.10.17.
 */
interface Parktime {
    int hours = 0;
    int minutes = 0;

    int getHours();
    int getMinutes();

    int getHoursRounded();
}
