package airports;
 
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AircraftControllerTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Autowired
    private AircraftRepository aircraftRepository;
 
    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("aircrafts"));
        mockMvc.perform(get("/aircrafts"))
                .andExpect(model().attributeExists("airports"));
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk());
    }
 
    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/aircrafts").param("name", "HA-LOL")).andExpect(status().is3xxRedirection());
        List<Aircraft> crafts = aircraftRepository.findAll();
        boolean bool = false;
        for (Aircraft craft : crafts) {
            if (craft.getName().equals("HA-LOL")) {
                bool = true;
            }
        }
 
        assertEquals(true, bool);
 
    }
 
    @Test
    public void testPost2() throws Exception {
        mockMvc.perform(post("/aircrafts").param("name", "XP-55")).andExpect(status().is3xxRedirection());
 
        MvcResult res = mockMvc.perform(get("/aircrafts")).andReturn();
        List<Aircraft> crafts = (List) res.getModelAndView().getModel().get("aircrafts");
        boolean bool = false;
        for (Aircraft craft : crafts) {
            if (craft.getName().equals("XP-55")) {
                bool = true;
            }
        }
 
        assertEquals(true, bool);
    }
}
