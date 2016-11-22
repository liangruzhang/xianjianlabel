/*
 * $Id: BasicUserPrincipal.java,v 1.1 2002/04/10 04:23:14 ngan.vincent Exp $
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
 * A basic concrete implementation of the user principal interface.  A user
 * principal represents a user.
 *
 * @author  Vincent Ngan
 * @version $Revision: 1.1 $
 */
public class BasicUserPrincipal
                    extends AbstractPrincipal
                    implements UserPrincipal, Serializable {

    /**
     * Constructs a basic user principal object with the specified name.
     * The description and properties of the user principal are set to null.
     * @param   name the name of the user
     */
    public BasicUserPrincipal(String name) {
        this(name, null);
    }

    /**
     * Constructs a basic user principal object with the specified name, and
     * description. The properties of the user principal are set to null.
     * @param   name the name of the user
     * @param   description the description of the user
     */
    public BasicUserPrincipal(String name, String description) {
        this(name, description, null);
    }

    /**
     * Constructs a basic user principal object with the specified name,
     * description, and properties.
     * @param   name the name of the user
     * @param   description the description of the user
     * @param   properties the properties of the user
     */
    public BasicUserPrincipal(String name, String description, Map properties) {
        super(name, description, properties);
    }

}
