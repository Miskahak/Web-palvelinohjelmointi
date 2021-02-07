/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package euroshopper;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {
      
    
    @Autowired
    ItemRepository itemRepository;
    
    @Autowired
    ShoppingCart shoppingCart;
    
    @RequestMapping("/cart")
    public String getItems(Model model){
        Map<Item, Long> items = shoppingCart.getItems();
        long sum = 0;
        for(Map.Entry<Item, Long> item : items.entrySet()){
            sum += item.getKey().getPrice()*item.getValue();
        }
        model.addAttribute("items",shoppingCart.getItems());
        model.addAttribute("sum",sum);
        
        return "cart";
    }
    
    @PostMapping("/cart/items/{id}")
    public String addItem(@PathVariable Long id){
        Item item = itemRepository.getOne(id);
        shoppingCart.addToCart(item);
        
        return "redirect:/cart";
    }
    
}
