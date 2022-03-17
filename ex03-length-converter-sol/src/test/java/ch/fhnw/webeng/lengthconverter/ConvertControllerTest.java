package ch.fhnw.webeng.lengthconverter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConvertControllerTest {

    ConvertController controller = new ConvertController();

    @Test
    public void testConvertView() {
        var modelAndView = controller.convert(10, 10);
        assertEquals("convert", modelAndView.getViewName());
    }

    @Test
    public void testModelFeetInches() {
        var model = controller.convert(5, 10).getModelMap();
        assertEquals(5, model.getAttribute("feet"));
        assertEquals(10, model.getAttribute("inches"));
    }

    @Test
    public void testConvertSimple() {
        var model = controller.convert(5, 10).getModelMap();
        assertEquals(177, model.getAttribute("cmPart"));
        assertEquals(8, model.getAttribute("mmPart"));
    }

    @Test
    public void testConvertSpecialCase0() {
        var model = controller.convert(0, 0).getModelMap();
        assertEquals(0, model.getAttribute("cmPart"));
        assertEquals(0, model.getAttribute("mmPart"));
    }
}
