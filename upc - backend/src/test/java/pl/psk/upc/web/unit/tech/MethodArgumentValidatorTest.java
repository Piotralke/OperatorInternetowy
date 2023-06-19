package pl.psk.upc.web.unit.tech;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.psk.upc.infrastructure.enums.PaymentStatus;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.UpcTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MethodArgumentValidatorTest extends UpcTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWithEmptyCollectionMessage() {
        List<Object> objects = new ArrayList<>();
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullOrEmptyCollection(objects, "objects");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("objects is empty collection", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithNullCollectionMessage() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullOrEmptyCollection(null, "objects");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("objects is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldNotThrowExceptionWhenCollectionIsCorrect() {
        assertDoesNotThrow(() -> MethodArgumentValidator.requiredNotNullOrEmptyCollection(List.of("test"), "objects"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithIsNullOrEmptyStringMessageWhenPassNull() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullOrBlankString(null, "object");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("object is null or empty Sting", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithIsNullOrEmptyStringMessageWhenPassEmptyString() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullOrBlankString("", "object");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("object is null or empty Sting", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldNotThrowExceptionWhenStringIsCorrect() {
        assertDoesNotThrow(() -> MethodArgumentValidator.requiredNotNullOrBlankString("test", "object"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithIsNullMessageWhenPassNull() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNull(null, "object");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("object is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldNotThrowExceptionWhenObjectIsCorrect() {
        assertDoesNotThrow(() -> MethodArgumentValidator.requiredNotNull(clientAccount, "clientAccount"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithNullMessage() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullEnum(null, "object");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("object is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithMustBeEnumMessage() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNullEnum(clientAccount, "clientAccount");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("clientAccount must be en enum", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldNotThrowExceptionWhenEnumIsCorrect() {
        assertDoesNotThrow(() -> MethodArgumentValidator.requiredNotNullEnum(PaymentStatus.NIEOPLACONE, "objects"));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithDefaultArgumentNameWhenArgumentNameIsNull() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNull(null, null);
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("Argument is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWithDefaultArgumentNameWhenArgumentNameIsEmpty() {
        boolean isExceptionThrown = false;

        try {
            MethodArgumentValidator.requiredNotNull(null, "");
        } catch (IllegalArgumentException e) {
            isExceptionThrown = true;
            assertEquals("Argument is null", e.getMessage());
        }
        assertTrue(isExceptionThrown);
    }
}