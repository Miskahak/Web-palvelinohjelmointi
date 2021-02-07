/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import static org.fluentlenium.core.filter.FilterConstructor.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorTest extends org.fluentlenium.adapter.junit.FluentTest {
    
    @LocalServerPort
    private Integer port;
    
    @Test
    public void addAndDel(){
        
        goTo("http://localhost:" + port + "/actors");
        
        assertFalse(pageSource().contains("Uuno Turhapuro"));
        
        find("#name").fill().with("Uuno Turhapuro");
        
        find("form").first().submit();
        
        assertTrue(pageSource().contains("Uuno Turhapuro"));
        
        find("form").last().submit();
        
        assertFalse(pageSource().contains("Uuno Turhapuro"));
    }
}
