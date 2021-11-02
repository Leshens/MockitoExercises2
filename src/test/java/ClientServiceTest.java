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
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ClientServiceTest {

    @Mock
    Client client;

    @Mock
    ClientRepository clientRepository;

    @Test
    public void disableClientAccountIfNull(){
      Client testClient = new Client();
      testClient.setId(null);
      testClient.setActive(false);
      testClient.setCar(null);


    }

}
