/*
 * $Id: MD5Base64PasswordEncoder.java,v 1.4 2005/12/15 03:34:17 cheung.kwokfung Exp $
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
import java.security.*;

import org.apache.log4j.Logger;

import com.barcode.util.Base64Encoder;

/**
 * A concrete implementation of the PasswordEncoder interface.  This class
 * first encodes a password using MD5 digest algorithm, and then encodes the
 * resulting MD5 digest using Base64 encoding schema.  The result is a
 * printable form of an MD5 digest.  It is more convenient to store encoded
 * passwords as printable ascii strings than as binary MD5 digest.
 *
 * @author  Vincent Ngan
 * @version $Revision: 1.4 $
 */
public class MD5Base64PasswordEncoder implements PasswordEncoder, Serializable {

	private Logger logger = Logger.getLogger(MD5Base64PasswordEncoder.class);
    /**
     * Keeps a static final Base64 encoder.
     */
    private static final Base64Encoder base64Encoder = new Base64Encoder(-1);

    /**
     * Encodes the specified password to an MD5/Base64 output
     * @param   user the owner of password
     * @param   password the password to encode
     * @return  an encoded password using a Base64 representation of an MD5
     *          digest of the original password
     */
    public String encode(String user, String password) {
        if (password == null) {
            return null;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
//            digest.update(user.getBytes("UnicodeBigUnmarked"));
//            digest.update(password.getBytes("UnicodeBigUnmarked"));
            digest.update(user.getBytes("UTF-8"));
            digest.update(password.getBytes("UTF-8"));
            return base64Encoder.encodeBytes(
                digest.digest());
        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to encode password. " + e.getClass().getName());
        }
    }

    /**
     * Encodes the specified password to an MD5/Base64 output
     * @param   user the owner of password
     * @param   password the password to encode
     * @return  an encoded password using a Base64 representation of an MD5
     *          digest of the original password
     */
    public String encode(String user, char[] password) {
        return encode(user, String.copyValueOf(password));
    }

    /**
     * Test main program.
     */
    public static void main(String[] args) throws Exception {
        if(args.length != 2) {
            System.err.println("Invalid argument number!");
            return;
        }
        String user = args[0];
        String password = args[1];
        PasswordEncoder encoder = new MD5Base64PasswordEncoder();
        String encoded = encoder.encode(user, password);
        System.out.println(encoded);
//        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        String password;
//        while ( (password = in.readLine()) != null) {
//            String encoded = encoder.encode(password);
//            System.out.println(encoded);
//        }
    }
}
