import car.Car;
import car.CarCreator;
import car.CarService;
import client.Client;
import client.ClientRepository;
import client.ClientService;
import exception.ClientAlreadyExistsException;
import exception.ClientAlreadyInactiveException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import payment.PaymentRepository;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @Test(expected = ClientAlreadyInactiveException.class)
    public void disableClientAccountIfNull(){
        Client testClient = new Client("4",false,null);

        when(clientRepository.findClient("4")).thenReturn(testClient);
        ClientService testService = new ClientService(clientRepository);
        testService.disableClientAccount("4");
        assertFalse(testClient.isActive());
        Mockito.verify(clientRepository).saveClient(testClient);

    }
    @Test
    public void disableClientAccountClientIsGood(){

        Client testClient = new Client("5",true, null);

        when(clientRepository.findClient("5")).thenReturn(testClient);
        ClientService testService = new ClientService(clientRepository);
        testService.disableClientAccount("5");
        assertFalse(testClient.isActive());
        Mockito.verify(clientRepository).saveClient(testClient);
    }
    @Test
    public void updateClientAccountIdTest(){
        Client testClient = new Client("6",true, null);
        when(clientRepository.findClient("6")).thenReturn(testClient);
        ClientService testService = new ClientService(clientRepository);
        testService.updateClientAccountId("6","7");
        assertEquals("7",testClient.getId());
        Mockito.verify(clientRepository).saveClient(testClient);
        Mockito.verify(clientRepository).deleteClient("6");
    }
    @Test(expected = ClientAlreadyExistsException.class)
    public void createNewClientClientAlreadyExists() {
        Client testClient = new Client("8", true, null);
        when(clientRepository.findClient("8")).thenReturn(testClient);
        ClientService testService = new ClientService(clientRepository);
        testService.createNewClient("8");
        Mockito.verify(clientRepository).saveClient(testClient);
    }
    @Test
    public void createNewClientThatDontExist() {
        ArgumentCaptor<Client> argumentCaptor = ArgumentCaptor.forClass(Client.class);
        Client testClient = new Client(null, true, null);
        when(clientRepository.findClient(null)).thenReturn(testClient);
        ClientService testService = new ClientService(clientRepository);
        testService.createNewClient("9");
        Mockito.verify(clientRepository).saveClient(argumentCaptor.capture());
        assertEquals("9", argumentCaptor.getValue().getId());
        assertTrue(argumentCaptor.getValue().isActive());
    }
}
