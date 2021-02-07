/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Data @NoArgsConstructor
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ShoppingCart implements Serializable {
 
    private Map<Item, Long> items = new HashMap<>();
    
    public Map<Item, Long> getItems(){
        return items;
    }
    
    public void addToCart(Item item){
        if(items.containsKey(item) == false){
            long i = 1;
            items.put(item, i);
            return;
        }
        Long count = items.get(item);
        count++;
        items.put(item, count);
    }
}
