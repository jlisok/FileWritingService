package writing.service.util;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class CurrentTimStampWithPrecisionConversion {

    public static Long getCurrentTimeInNanoSeconds(){
        Instant now = Instant.now();
        return recalculateInstantToNanoSeconds(now);
    }

     private static Long recalculateInstantToNanoSeconds(Instant tstmp) {
        return TimeUnit.SECONDS.toNanos(tstmp.getEpochSecond()) + tstmp.getNano();
    }

}
