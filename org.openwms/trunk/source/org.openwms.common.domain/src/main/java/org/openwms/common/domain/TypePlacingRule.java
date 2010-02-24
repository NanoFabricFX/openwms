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
package org.openwms.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * A TypePlacingRule.
 * <p>
 * Describes which {@link TransportUnitType} may be placed on which
 * {@link LocationType}. A privilegeLevel can be set to order all allowed
 * {@link LocationType}s.
 * </p>
 * 
 * @author <a href="mailto:openwms@googlemail.com">Heiko Scherrer</a>
 * @version $Revision$
 * @since 0.1
 * @see TransportUnitType
 */
@Entity
@Table(name = "TYPE_PLACING_RULE", uniqueConstraints = @UniqueConstraint(columnNames = { "TRANSPORT_UNIT_TYPE",
        "PRIVILEGE_LEVEL", "ALLOWED_LOCATION_TYPE" }))
public class TypePlacingRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique technical key.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    /**
     * Parent {@link TransportUnitType}.
     */
    @ManyToOne
    @JoinColumn(name = "TRANSPORT_UNIT_TYPE")
    private TransportUnitType transportUnitType;

    /**
     * The privilegeLevel defines a priority to describe which
     * {@link TransportUnitType} shall be placed on which {@link LocationType}.
     * <p>
     * A value of 0 is the lowest priority. Increasing the privilegeLevel
     * implies a higher priority, that means the {@link TransportUnitType} shall
     * be placed to the {@link LocationType} with the highest privilegeLevel.
     * </p>
     * <p>
     * To forbid a {@link TransportUnitType} on a {@link LocationType} the
     * privilegeLevel must be set to -1.
     * </p>
     * <i>Note: Default value is 0</i>
     */
    @Column(name = "PRIVILEGE_LEVEL", nullable = false)
    private int privilegeLevel = 0;

    /**
     * The allowed {@link LocationType} on which the owning
     * {@link TransportUnitType} may be placed.
     */
    @ManyToOne
    @JoinColumn(name = "ALLOWED_LOCATION_TYPE", nullable = false)
    private LocationType allowedLocationType;

    /* ----------------------------- methods ------------------- */
    /**
     * Create a new {@link TypePlacingRule}.
     */
    @SuppressWarnings("unused")
    private TypePlacingRule() {}

    /**
     * Create new {@link TypePlacingRule} with privilegeLevel and
     * allowedLocationType.
     * 
     * @param privilegeLevel
     * @param allowedLocationType
     */
    public TypePlacingRule(int privilegeLevel, LocationType allowedLocationType) {
        this.privilegeLevel = privilegeLevel;
        this.allowedLocationType = allowedLocationType;
    }

    /**
     * 
     * Create new {@link TypePlacingRule} with allowedLocationType.
     * 
     * @param allowedLocationType
     */
    public TypePlacingRule(LocationType allowedLocationType) {
        this.allowedLocationType = allowedLocationType;
    }

    /**
     * Return the unique technical key.
     * 
     * @return the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the transportUnitType.
     * 
     * @return the transportUnitType.
     */
    public TransportUnitType getTransportUnitType() {
        return transportUnitType;
    }

    /**
     * Get the privilegeLevel.
     * 
     * @return the privilegeLevel.
     */
    public int getPrivilegeLevel() {
        return privilegeLevel;
    }

    /**
     * Get the allowedLocationType.
     * 
     * @return the allowedLocationType.
     */
    public LocationType getAllowedLocationType() {
        return allowedLocationType;
    }
}