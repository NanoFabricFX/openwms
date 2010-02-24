/*
 * openwms.org, the Open Warehouse Management System.
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software. If not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.common.domain.values;

import java.math.BigDecimal;

/**
 * A Weight.
 * <p>
 * Describes a weight in real world, always including a unit and a value.
 * </p>
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision$
 * @since 0.1
 */
public class Weight implements Comparable<Weight>, Unit<WeightUnit> {

    private WeightUnit unit;
    private BigDecimal value;

    /* ----------------------------- methods ------------------- */
    /**
     * Accessed by persistence provider.
     */
    @SuppressWarnings("unused")
    private Weight() {}

    /**
     * 
     * Create a new Weight.
     * 
     * @param value
     * @param unit
     */
    public Weight(BigDecimal value, WeightUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    /**
     * Get the unit.
     * 
     * @return the unit.
     */
    public WeightUnit getUnit() {
        return unit;
    }

    /**
     * Get the value.
     * 
     * @return the value.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public void convertTo(WeightUnit unit) {
        value = value.scaleByPowerOfTen((this.getUnit().ordinal() - unit.ordinal()) * 3);
        this.unit = unit;
    }

    /**
     * Compares o.Value with this.value.
     */
    public int compareTo(Weight o) {
        if (o.getUnit().ordinal() > this.getUnit().ordinal()) {
            return 1;
        } else if (o.getUnit().ordinal() < this.getUnit().ordinal()) {
            return -1;
        } else {
            return o.getValue().compareTo(this.getValue());
        }
    }
}