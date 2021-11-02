import car.Car;
import car.CarCreator;
import car.CarService;
import client.Client;
import client.ClientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import payment.PaymentRepository;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
//ExtendWith() w junit 14

public class CarServiceTest {

    @Mock
    CarCreator carCreator;
    @Mock
    ClientRepository clientRepository;
    @Mock
    PaymentRepository paymentRepository;

    @Test
    public void regsiterClientCarShouldAddCarYoClientClientAndReturnClient(){
//        PaymentRepository myMock = Mockito.mock();
        Car testCar = new Car();
            testCar.setMake("mercedes");
            testCar.setColor("white");
            testCar.setPlate("DZ 2137");
        Client testClient = new Client("12",true,null);
            when(carCreator.createCar("mercedes","white","DZ 2137")).thenReturn(testCar);
            when(clientRepository.findClient("12")).thenReturn(testClient);
        CarService carService = new CarService(carCreator, clientRepository , paymentRepository);

        carService.registerClientCar("12","mercedes","white","DZ 2137");
        verify(clientRepository, times(1)).saveClient(testClient);

        assertEquals("12",testClient.getId());
        assertNotNull(testClient.getCar());
        assertEquals("mercedes",testClient.getCar().getMake());
        assertEquals("white",testClient.getCar().getColor());
        assertEquals("DZ 2137",testClient.getCar().getPlate());
    }
    @Test
    public void regsiterClientCarShouldNotAddCarYoClientClientAndReturnClient(){
        Car testCar = new Car();
            testCar.setMake("mercedes");
            testCar.setColor("white");
            testCar.setPlate("DZ 2137");
        Client testClient = new Client("12",true,null);
            when(carCreator.createCar("mercedes","white","DZ 2137")).thenReturn(null);
            when(clientRepository.findClient("12")).thenReturn(testClient);
        CarService carService = new CarService(carCreator, clientRepository , paymentRepository);

        carService.registerClientCar("12","mercedes","white","DZ 2137");
        verify(clientRepository, times(0)).saveClient(testClient);

        assertEquals("12",testClient.getId());
        assertNull(testClient.getCar());
    }

}
