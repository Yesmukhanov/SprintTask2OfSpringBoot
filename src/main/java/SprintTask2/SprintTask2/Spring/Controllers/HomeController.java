package SprintTask2.SprintTask2.Spring.Controllers;

import SprintTask2.SprintTask2.Spring.Models.ApplicationRequest;
import SprintTask2.SprintTask2.Spring.Repository.ApplicationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ApplicationRequestRepository applicationRepository;

    @GetMapping(value = "/")
    public String firstPage(Model model){
        List<ApplicationRequest> requestList = applicationRepository.sortByHandle();
        model.addAttribute("request", requestList);
        return "index";
    }

    @GetMapping(value = "/add-order")
    public String addOrderPage(){
        return "add-order";
    }

    @PostMapping(value = "/add-order")
    public String addOrder(ApplicationRequest applicationRequest){
        applicationRepository.save(applicationRequest);
        return "redirect:/";
    }

    @GetMapping(value = "/details/{requestId}")
    public String detailsPage(@PathVariable(value = "requestId") Long id, Model model){
        System.out.println(id);
        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);
        model.addAttribute("request", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/delete-order")
    public String deleteOrder(@RequestParam(name = "id") Long id){
        applicationRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/new-order")
    public String newOrdersPage(Model model){
        List<ApplicationRequest> list = applicationRepository.searchApplicationRequest();
        model.addAttribute("request", list);
        return "newOrdersPage";
    }

    @PostMapping(value = "/handle-order")
    public String handleOrderPage(@RequestParam(name = "id") Long id){
        ApplicationRequest applicationRequest = applicationRepository.findById(id).orElse(null);
        if (applicationRequest != null){
            if (!applicationRequest.isHandled()){
                applicationRequest.setHandled(true);
            }
            applicationRepository.save(applicationRequest);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/handled-order")
    public String handledOrdersPage(Model model){
        List<ApplicationRequest> list =  applicationRepository.findAllByHandledIsTrue();
        model.addAttribute("request", list);
        return "handledOrdersPage";
    }
}
