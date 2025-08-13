package com.seguranca.seguranca.util;

public class ValidaEmail {

    public static boolean validaCaractereArroba(String input) {
        return input != null && input.contains("@");

    }

    public static boolean validaEmailNull(String input) {
        return input == null;

    }
}
