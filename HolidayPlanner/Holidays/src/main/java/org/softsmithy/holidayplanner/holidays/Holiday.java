/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.softsmithy.holidayplanner.holidays;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import org.softsmithy.lib.persistence.AbstractEntity;

/**
 *
 * @author Florian
 */
@Entity
public class Holiday extends AbstractEntity {

    private String title;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
   

}
