package com.example.mike.mikeleonad340;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class sendMessageTest {

    @Test
    public void sendMessageFail() {
        MainActivity m = new MainActivity();
        String falseTest = "";

        assertFalse(m.validateInput(falseTest));
    }
    @Test
    public void sendMessageTrue(){
        MainActivity m = new MainActivity();
        String trueTest = "Testtest 1234";
        assertTrue(m.validateInput(trueTest));
    }

}