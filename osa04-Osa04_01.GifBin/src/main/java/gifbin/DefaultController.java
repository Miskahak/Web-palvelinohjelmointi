package gifbin;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DefaultController {

    @Autowired
    FileObjectRepository fileObjectRepository;
    
    @GetMapping("/gifs")
    public String redirect() {
        return "redirect:/gifs/1";
    }
    
    @GetMapping(path = "/gifs/{id}")
    public String redirectid(Model model, @PathVariable Long id) {
        
        
        model.addAttribute("count", fileObjectRepository.count());
        model.addAttribute("current", id);
        model.addAttribute("next", id+1 );
        model.addAttribute("previous", id-1 );
        return "gifs";
    }
    
    @GetMapping(path = "/gifs/{id}/content", produces = "image/gif")
    @ResponseBody
    public byte[] getGif(@PathVariable Long id) {
        
        
        return fileObjectRepository.getOne(id).getContent();
    }
    
    @PostMapping("/gifs")
    public String save(@RequestParam("file") MultipartFile file) throws IOException {
    if(file.getContentType().equals("image/gif")){
    FileObject fo = new FileObject();
    fo.setContent(file.getBytes());

    fileObjectRepository.save(fo);
    }


    return "redirect:/gifs";
}
}
