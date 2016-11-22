/*
 * $Id: AbstractPrincipal.java,v 1.3 2002/07/04 00:47:33 ngan.vincent Exp $
 *
 * Copyright (c) 2002 Hongkong International Terminals Limited.
 * All rights reserved.
 *
 * This file contains the valuable properties of Hongkong International
 * Terminals Limited, embodying substantial creative efforts and
 * confidential information, ideas and expressions. No part of this file
 * may be reproduced or distributed in any form or by any means, or
 * stored in a data base or a retrieval system, without the prior written
 * permission of Hongkong International Terminals Limited.
 *
 *                                 ---
 */
package com.barcode.security;

import java.io.*;
import java.util.*;
import java.security.*;

/**
 * An Abstract principal which can be used as a base class for defining
 * concrete implementations for the Principal interface.  This abstract class
 * describes a principal with a name, a description, and a set of
 * property values.  The name is used to uniquely identify a principal and can
 * be obtained using the getName() method.  The getDescription() method returns
 * a detail description of this principal.  The getProperty() method can be
 * used to obtain any dynamic property value associated with this principal
 * object.
 *
 * @author  Vincent Ngan
 * @version $Revision: 1.3 $
 */
public abstract class AbstractPrincipal implements Principal, Serializable {

    /**
     * The name of this principal.
     */
    private String name;

    /**
     * A Detail description of this principal.
     */
    private String description;

    /**
     * A map which stores the dynamic properties of this principal.
     */
    private Map properties;

    /**
     * Constructs an abstract principal object.
     * @param   name  the name of this principal
     * @param   description description of the principal
     * @param   properties properties of this principal
     */
    public AbstractPrincipal(String name, String description, Map properties) {
        this.properties = (properties == null) ?
            new HashMap() : new HashMap(properties);
        this.name = name;
        this.description = description;
    }

    /**
     * Returns the name of this principal.  This value should unique
     * identify this prinicpal.  As a result, it must agree with the
     * return value of the getId() method with respect to the uniquess
     * of this principal.
     * @return  the name of this principal
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of this principal.
     * @return  the description of this principal
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the property of this principal referenced by the specified
     * key value.
     * @param   name the name of the property value to be retrieved
     * @return  the property value
     */
    public Object getProperty(String name) {
        return getProperties().get(name);
    }

    /**
     * Returns the set of the property names values of this principal.
     * @return the set of property name values
     */
    public Set getPropertyNames() {
        return Collections.unmodifiableSet(getProperties().keySet());
    }

    /**
     * Indicates if the specified property name exists.
     * @return true if the property exists; false otherwise
     */
    public boolean hasProperty(String name) {
        return getProperties().containsKey(name);
    }

    /**
     * Sets the name of this principal.  The "name" property
     * maintained in the properties map is also updated.
     * @param   name the name of this principal
     */
    protected void setName(String name) {
        this.name = name;
        getProperties().put("name", name);
    }

    /**
     * Sets the description of this principal.  The "description" property
     * maintained in the properties map is also updated.
     * @param   description the description of this principal
     */
    protected void setDescription(String description) {
        this.description = description;
        getProperties().put("description", description);
    }

    /**
     * Set a property value for this principal.
     * @param   key the property key
     * @param   value the property value
     */
    protected void setProperty(String key, Object value) {
        getProperties().put(key, value);
    }

    /**
     * Returns the properties map associated with this principal.
     * @return  the properties map of this principal
     */
    protected Map getProperties() {
        return properties;
    }

    /**
     * Returns the string representation of this principal, which is the
     * id of this principal.
     * @return  this id of this principal
     */
    public String toString() {
        return name;
    }

    /**
     * Checks if this principal object is equal to the specified object.
     * @param   obj the object to test the equality of this principal object
     * @return  true if they are equal; false otherwise
     */
    public boolean equals(Object obj) {
        if (obj != null && this.getClass().equals(obj.getClass())) {
            return name.equals(((AbstractPrincipal)obj).name);
        }
        return false;
    }

    /**
     * Returns a hash code for this principal object
     * @return a hash code of this principal
     */
    public int hashCode() {
        return name.hashCode();
    }
}