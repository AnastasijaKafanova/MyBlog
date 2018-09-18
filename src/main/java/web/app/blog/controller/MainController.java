package web.app.blog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.app.blog.domain.Message;
import web.app.blog.domain.User;
import web.app.blog.repos.MessageRepo;


import java.util.Map;

import static java.lang.Integer.parseInt;
import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model
    ) {
        Message message = new Message(text, tag, user);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("delete")
    public String delete(@RequestParam Integer deleteById, Map<String, Object> model) {
        Iterable<Message> messages;
        if (deleteById != null) {
            messages = messageRepo.deleteById(deleteById);
            model.remove("messages", messages);
        }
        return "redirect:/main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
            model.put("messages", messages);
        }
        return "filter";
    }
}

