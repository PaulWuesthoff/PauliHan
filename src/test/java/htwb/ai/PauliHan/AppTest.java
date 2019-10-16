package htwb.ai.PauliHan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import htwb.ai.PauliHan.App;

public class AppTest {
    
    @Test
    public void returnsHelloWorldShouldReturnHelloWorld() {
        assertEquals( App.returnsHelloWorld(), "Hello World!" );
    }
}
