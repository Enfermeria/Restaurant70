/*
	Trabajo práctico final de la Guía 6 del curso Desarrollo de Apps
	Universidad de La Punta en el marco del proyecto Argentina Programa 4.0

	Integrantes:
		John David Molina Velarde
		Leticia Mores
		Enrique Germán Martínez
		Carlos Eduardo Beltrán

	Rutinas utiles
 */
package accesoadatos;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author John David Molina Velarde, Leticia Mores, Enrique Germán Martínez, Carlos Eduardo Beltrán
 */
public class Utils {
	/**
	 * Para mandar un mensaje de error con sout o JOptionPane
	 * @param mensaje 
	 */
	public static void mensajeError(String mensaje){ 
		//System.out.println(mensaje);
		JOptionPane.showMessageDialog(null, mensaje);
	} //mensajeError
	
	
	
	
	/**
	 * Para mandar un mensaje con sout o JOptionPane
	 * @param queMensaje 
	 */
	public static void mensaje(String queMensaje){
		System.out.println(queMensaje);
	} //mensaje
	
	
	
	
	/**
	 * Conversor de formato LocalDate a Date.
	 * Dado un LocalDate devuelve el correspondiente Date
	 * @param localfecha el localDate
	 * @return el Date correspondiente
	 */
	public static Date localDate2Date(LocalDate localfecha){
		ZoneId defaultZoneId = ZoneId.systemDefault(); //default time zone
		// para pasar de LocalDate a Date: Date = local date + atStartOfDay() + default time zone + toInstant() 
		Date fecha = Date.from(localfecha.atStartOfDay(defaultZoneId).toInstant());
		return fecha;
	} // localDate2Date
	
	
	
	
	/**
	 * Conversor de formato Date a  LocalDate.
	 * Dado un Date devuelve el correspondiente LocalDate
	 * @param fecha el Date
	 * @return el LocalDate correspondiente
	 */
	public static LocalDate date2LocalDate(Date fecha){
		//The java.util.Date represents date, time of the day in UTC timezone 
		//		java.util.Date = date + time of the day + UTC time zone
		//and java.time.LocalDate represents only the date, without time and timezone.
		//		java.time.LocalDate = only date
		
		//How to convert Date to LocalDate. 
		//Keeping these points in mind, we can convert the Date to Local in the following steps:
		// 1. Convert Date to Instant – because we do not want the time in the LocalDate
		// 2. Get the default timezone – because there is no timezone in LocalDate
		// 3. Convert the date to local date – Instant + default time zone + toLocalDate() = LocalDate
		
		//Getting the default zone id
		ZoneId defaultZoneId = ZoneId.systemDefault();

		//Converting the date to Instant
		Instant instant = fecha.toInstant();

		//Converting the Date to LocalDate
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		return localDate;
	} //date2LocalDate
	
	
	
	/**
	 * Conversor de formato LocalDateTime a Date.
	 * Dado un LocalDateTime devuelve el correspondiente Date
	 * @param localFechaHora el localDateTime
	 * @return el Date correspondiente
	 */
	public static Date localDateTime2Date(LocalDateTime localFechaHora){
		ZoneId defaultZoneId = ZoneId.systemDefault(); //default time zone
		// para pasar de LocalDateTime a Date: Date = local date + default time zone + toInstant() 
		Date fecha = Date.from(localFechaHora.atZone(defaultZoneId).toInstant());
		return fecha;
	} // localDateTime2Date
	
	
	
	
	/**
	 * Conversor de formato Date a  LocalDateTime.
	 * Dado un Date devuelve el correspondiente LocalDateTime
	 * @param fecha el Date
	 * @return el LocalDateTime correspondiente
	 */
	public static LocalDateTime date2LocalDateTime(Date fecha){
		//The java.util.Date represents date, time of the day in UTC timezone 
		//		java.util.Date = date + time of the day + UTC time zone
		//and java.time.LocalDate represents only the date, without time and timezone.
		//		java.time.LocalDate = only date
		
		//How to convert Date to LocalDate. 
		//Keeping these points in mind, we can convert the Date to Local in the following steps:
		// 1. Convert Date to Instant – because we do not want the time in the LocalDate
		// 2. Get the default timezone – because there is no timezone in LocalDate
		// 3. Convert the date to local date – Instant + default time zone + toLocalDate() = LocalDate
		
		//Getting the default zone id
		ZoneId defaultZoneId = ZoneId.systemDefault();

		//Converting the date to Instant
		Instant instant = fecha.toInstant();

		//Converting the Date to LocalDate
		LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
		return localDateTime;
	} //date2LocalDateTime
	
	
	
	public static String localDateTime2DateTimeBD(LocalDateTime fechaHora) {
		// formato que devuelve el LocalDateTime.toString(): YYYY-MM-DDTHH:MI:SS.mmm
		// por ejemplo2023-10-05T15:46:14.817  Cuando los segundos son 00, el toString no los muestra.
		
		//formato de la BD DATETIME: YYYY-MM-DD HH:MI:SS
		return fechaHora.toString().substring(0, 10) + " " + ( (fechaHora.toString().length()<19) ? "00" : fechaHora.toString().substring(11, 19) );
	} //localDateTime2DateTimeBD
	
	
	
	

	public static LocalDateTime dateTimeBD2LocalDateTime(String fechaHoraBD) {
		//formato de la BD DATETIME: YYYY-MM-DD HH:MI:SS  
		int anio = Integer.parseInt(fechaHoraBD.substring(0, 4));
		int mes =  Integer.parseInt(fechaHoraBD.substring(5, 7));
		int dia =  Integer.parseInt(fechaHoraBD.substring(8, 10));
		int hora = Integer.parseInt(fechaHoraBD.substring(11, 13));
		int minutos = Integer.parseInt(fechaHoraBD.substring(14, 16));
		int segundos = Integer.parseInt( fechaHoraBD.substring(17, 19) );
		return LocalDateTime.of(anio, mes, dia, hora, minutos, segundos);
	} //dateTimeBD2LocalDateTime
	
	
	

	
	public static LocalDateTime dateYTime2LocalDateTime(Date fecha, Time hora) {
		int anio = Integer.parseInt(fecha.toString().substring(0, 4));
		int mes =  Integer.parseInt(fecha.toString().substring(5, 7));
		int dia =  Integer.parseInt(fecha.toString().substring(8, 10));
		int hh = Integer.parseInt(hora.toString().substring(0, 2));
		int mm = Integer.parseInt(hora.toString().substring(3, 5));
		int ss = Integer.parseInt(hora.toString().substring(6, 8));
		return LocalDateTime.of(anio, mes, dia, hh, mm, ss);
	}
	
}
