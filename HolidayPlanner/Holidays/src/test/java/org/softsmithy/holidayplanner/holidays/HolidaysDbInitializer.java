/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.holidayplanner.holidays;

import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.softsmithy.devlib.persistence.DbInitializer;

/**
 *
 * @author puce
 */
public class HolidaysDbInitializer implements DbInitializer {

    private final EntityManager entityManager;

    public HolidaysDbInitializer(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initDb() {
        Calendar calendar = Calendar.getInstance();
        Holiday englandHoliday = new Holiday();
        englandHoliday.setTitle("Nord-England Sommer 2010");
        calendar.set(2010, Calendar.AUGUST, 21, 0, 0, 0);
        englandHoliday.setStartDate(calendar.getTime());
        calendar.set(2010, Calendar.AUGUST, 30, 0, 0, 0);
        englandHoliday.setEndDate(calendar.getTime());
        entityManager.persist(englandHoliday);
    }

    @Override
    public void clearDb() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("holidaysmanager");
        EntityManager entityManager =
                entityManagerFactory.createEntityManager();
        HolidaysDbInitializer dbInitializer = new HolidaysDbInitializer(
                entityManager);
        dbInitializer.initDb();
        entityManager.close();
        entityManagerFactory.close();

    }
}
