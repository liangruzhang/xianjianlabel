/*
 * $Id: PasswordEncoder.java,v 1.2 2002/11/19 07:57:22 chen.owen Exp $
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

/**
 * An interface which represents an encoder which is capable of encoding a
 * password to avoid the password being transmitted as clear text.  Passwords
 * should never be transmitted as clear text over a remote communication
 * link.  Normally a password should be encoded using a one way mechanism that
 * makes it impossible to recover the orginal password from the encoded
 * form.
 *
 * @author  Vincent Ngan
 * @version $Revision: 1.2 $
 */
public interface PasswordEncoder {

    /**
     * Encodes a specified password.
     * @param   user the owner of password
     * @param   password the original password in clear text
     * @return  the encoded password
     */
    public String encode(String user, String password);

    /**
     * Encodes a specified password which is an array of characters.
     * @param   user the owner of password
     * @param   password the original password in clear text
     * @return  the encoded password
     */
    public String encode(String user, char[] password);

}
