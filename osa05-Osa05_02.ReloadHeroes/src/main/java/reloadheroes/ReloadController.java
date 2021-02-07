package reloadheroes;

import java.util.UUID;
import static java.util.UUID.randomUUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReloadController {

    @Autowired
    private ReloadStatusRepository reloadStatusRepository;

    @Autowired
    private HttpSession session;

    @RequestMapping("*")
    public String reload(Model model) {

        Pageable pageable = PageRequest.of(0, 5, Sort.by("reloads").descending());
        System.out.println("======================================================" + session.getAttribute("status"));
        if (session.getAttribute("status") != null) {
            ReloadStatus status = (ReloadStatus) session.getAttribute("status");
            String name = status.getName();
            status = reloadStatusRepository.findByName(name);
            System.out.println("===============================================" + status.getReloads());
            status.setReloads(status.getReloads() + 1);
            session.setAttribute("status", status);
            model.addAttribute("status", status);
            System.out.println("=============================================== ei kaadu 1");
            model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
            reloadStatusRepository.save(status);
            System.out.println("=============================================== ei kaadu 2");
            return "index";
        }
        ReloadStatus status = new ReloadStatus();
        status.setReloads(1);
        status.setName(randomUUID().toString());
        session.setAttribute("status", status);

        model.addAttribute("status", status);

        model.addAttribute("scores", reloadStatusRepository.findAll(pageable));
        reloadStatusRepository.save(status);

        return "index";
    }
}
