/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.time;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Utility class for {@link LocalDate}.
 *
 * @author puce
 */
public class LocalDateUtils {

    private LocalDateUtils() {
    }

    /**
     * Gets the {@link YearMonth} of a specified {@link LocalDate}.
     *
     * @param localDate a {@link LocalDate}
     * @return the {@link YearMonth} of a specified {@link LocalDate}
     */
    public static YearMonth getYearMonth(LocalDate localDate) {
        return localDate != null ? YearMonth.of(localDate.getYear(), localDate.getMonth()) : null;
    }
}
