package utils.helper;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CommuniqueHelper {

    public static int getNumComm(Date date)  {


        int numCommRef=387;
        LocalDateTime ref = LocalDateTime.of(2021, 3, 23, 10, 34);
        LocalDateTime dateConvert = LocalDateTime.ofInstant(date.toInstant(),  ZoneId.systemDefault());

        long durantion = Duration.between(ref,dateConvert).toDays();

        System.out.println("_______________DURATION___________________");
        System.out.println(ref);
        System.out.println(dateConvert);
        System.out.println(durantion);
        System.out.println(numCommRef + durantion);

        return  (int)(numCommRef + durantion) ;
    }
}
