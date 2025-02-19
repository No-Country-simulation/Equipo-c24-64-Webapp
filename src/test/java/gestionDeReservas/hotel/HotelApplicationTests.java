package gestionDeReservas.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class HotelApplicationTests {

	@Test
	void suma() {
		int num = 1;
		int num2 = 2;

		int suma = num+num2;

		assertEquals(suma,4);
	}

}
