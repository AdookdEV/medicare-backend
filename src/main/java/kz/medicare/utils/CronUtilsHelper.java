package kz.medicare.utils;

import com.cronutils.builder.CronBuilder;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CronUtilsHelper {

    /**
     * Checks if a given LocalDateTime matches a cron expression.
     *
     * @param cronExpression the cron expression to check against
     * @param localDate the LocalDateTime to check
     * @return true if the dateTime matches the cron expression, false otherwise
     */
    public static boolean matchesCronExpression(String cronExpression, LocalDate localDate) {

        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.UNIX));
        Cron cron = parser.parse(cronExpression);

        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Evaluate the cron expression against the given date
        ExecutionTime executionTime = ExecutionTime.forCron(cron);
        // Check if the date is a valid match for the cron expression
        return false;
    }

    public static boolean matchesCronExpression(String cronExpression) {
        return matchesCronExpression(cronExpression, LocalDate.now());
    }
}
