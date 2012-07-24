/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.lib.util.impl;

import javax.lang.model.element.Name;

/**
 *
 * @author puce
 */
/*package-default*/ class ServiceProviderDescription implements Comparable<ServiceProviderDescription> {

    private Name serviceProviderName;
    private int position;

    /**
     * @return the serviceProviderName
     */
    public Name getServiceProviderName() {
        return serviceProviderName;
    }

    /**
     * @param serviceProviderName the serviceProviderName to set
     */
    public void setServiceProviderName(Name serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public int compareTo(ServiceProviderDescription o) {
        if (position < o.position) {
            return -1;
        } else if (position > o.position) {
            return 1;
        } else {
            return 0;
        }
    }
}
