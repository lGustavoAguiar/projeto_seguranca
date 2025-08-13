package com.seguranca.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ValidaEmailTests {

    String emailValido = "caue@aa";
    String emailInvalido = "caue_aa";
    String emailNull = null;

    @Test
    void caractereArroba() {
        assertTrue(ValidaEmail.validaCaractereArroba(emailValido), "O email deve conter @");
        assertFalse(ValidaEmail.validaCaractereArroba(emailInvalido), "O email é inválido não contém @");
    }
    @Test
    void emailNull() {
    assertTrue(ValidaEmail.validaEmailNull(emailNull), "O email está nulo!");
    }
}
