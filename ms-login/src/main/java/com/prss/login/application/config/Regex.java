/*
 * Regex.java 1.0 13/09/2021
 *
 * Copyright (c) 2021, PicPay S.A. All rights reserved.
 * PicPay S.A. proprietary/confidential. Use is subject to license terms.
 */

package com.prss.login.application.config;

/**
 * @author Carlos Romano
 * @version 1.0 13/09/2021
 */
public final class Regex {
    public static final String FULL_NAME = "^[a-zA-ZÀ-ú]{2,}( [a-zA-ZÀ-ú ]{2,})+$";
    public static final String DOCUMENT_NUMBER = "^[0-9]{11}$";
    public static final String EMAIL = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
    public static final String POSTAL_CODE = "^[0-9]{8}$";

    private Regex(){}
}
