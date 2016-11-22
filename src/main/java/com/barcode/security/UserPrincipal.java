/*
 * $Id: UserPrincipal.java,v 1.1 2002/04/10 04:23:14 ngan.vincent Exp $
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

import java.util.*;
import java.security.*;

/**
 * A principal which represents a user.
 *
 * @author  Vincent Ngan
 * @version $Revision: 1.1 $
 */
public interface UserPrincipal extends Principal {

    /**
     * Returns the detail description of the user
     * @return  the description
     */
    public String getDescription();

    /**
     * Returns the property of the user of the specified property name
     * @param   name the property name
     * @return  the property value
     */
    public Object getProperty(String name);

    /**
     * Returns a set of the all the property names associated with this
     * user.
     * @return  a set of all property names
     */
    public Set getPropertyNames();

}
