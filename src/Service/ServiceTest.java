package Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service();
        service.initialize();
    }

    @Test
    void undo() {
        assertEquals(10, service.getElements().size());
        for (int i = 0; i < 10; i++) {
            service.undo();
            assertEquals(9 - i, service.getElements().size());
        }
        try {
            service.undo();
            fail("Should have thrown UnsupportedOperationException");
        }catch (UnsupportedOperationException e){
            assertTrue(true);
        }
    }

    @Test
    void redo() {
        try{
            service.redo();
            fail("Should have thrown UnsupportedOperationException");
        }catch (UnsupportedOperationException e){
            assertTrue(true);
        }
        for (int i = 0; i < 10; i++) {
            service.undo();
        }
        for (int i = 0; i < 10; i++) {
            assertEquals(i, service.getElements().size());
            service.redo();
        }
        assertEquals(10, service.getElements().size());
    }

    @Test
    void initialize() {
        assertEquals(10, service.getElements().size());
        assertEquals("Porsche", service.getElements().get(0).getCompany());
        assertEquals("Octavia", service.getElements().get(9).getModel());
        assertEquals(150, service.getElements().get(3).getHp());
        assertEquals(4, service.getElements().get(5).getQuantity());
        assertEquals(100000, service.getElements().get(7).getPrice());
    }
}