/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {
    
    
      
    @Autowired
    AirportService airportService;
    
    @Autowired
    AirportRepository airportRepository;
    
    @Test
    public void testCreate() throws Exception{
        
        airportService.create("testID", "test1");
        List <Airport> list = airportService.list();
        assertEquals("testID",list.get(0).getIdentifier());
        assertEquals("test1",list.get(0).getName());
    }
    
    @Test
    public void testList(){
        
        List <Airport> list = airportService.list();
        assertEquals(list,airportRepository.findAll());
    }
    
    @Test
    public void testEquals(){
        airportService.create("test2", "test2");
        airportService.create("test2", "test2");
        List <Airport> list = airportService.list();
        int count= 0;
        for(Airport port: list){
            if(port.getIdentifier().equals("test2")){
                count++;
            }
                
        }
        
        assertEquals(1,count);
        
        
        
    }
}

