package com.testtask;

import com.testtask.model.Root;
import com.testtask.model.Ticket;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args){

        GsonParser parser = new GsonParser();

        Root root = parser.parser();

        ArrayList <Integer> array = new ArrayList<>();

        for (Ticket tickets : root.getTicket()){
        array.add(flightTime(tickets));
        }

        System.out.println("Cреднее время полета между городами Владивосток и Тель-Авив " + Duration.ofMinutes(averageValue(array)));
        System.out.println("90-й процентиль времени полета между городами  Владивосток и Тель-Авив " + Duration.ofMinutes(percentile(array, 90.0)));
    }

    public static int averageValue(List<Integer> array){
        int value = array.stream().mapToInt(a -> a).sum()/array.size();
        return  value;
    }

    public static int percentile(List<Integer> array, double percentile) {
        Collections.sort(array);
        int index = (int) Math.ceil(percentile / 100.0 * array.size());
        return array.get(index - 1);
    }

    public static int flightTime (Ticket tickets){
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd.MM.uu H:mm" );

        String input = tickets.getDeparture_date() + " " + tickets.getDeparture_time();
        LocalDateTime ldt = LocalDateTime.parse(input , f);
        ZoneId z = ZoneId.of("Asia/Vladivostok");
        ZonedDateTime zdt = ldt.atZone(z);

        String input2 = tickets.getArrival_date() + " " + tickets.getArrival_time();
        LocalDateTime ldt2 = LocalDateTime.parse(input2 , f);
        ZoneId z2 = ZoneId.of("Etc/GMT-3");
        ZonedDateTime zdt2 = ldt2.atZone(z2);

        return (int)ChronoUnit.MINUTES.between(zdt, zdt2);
    }
}
