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
   

}
