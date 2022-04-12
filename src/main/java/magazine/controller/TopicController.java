package magazine.controller;

import magazine.dto.TopicDTO;
import magazine.service.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<TopicDTO> getTopics() {
        return topicService.findAllTopics();
    }

    @GetMapping(path = "/{id}")
    public TopicDTO getTopic(@PathVariable("id") Long id) {
        return topicService.findTopic(id);
    }

    @PostMapping
    public TopicDTO addMagazine(@RequestBody TopicDTO topicDTO) {
        return topicService.saveTopic(topicDTO);
    }

    @PutMapping(path = "/{id}")
    public TopicDTO updateTopic(@PathVariable Long id, @RequestBody TopicDTO topicDTO) {
        topicDTO.setId(id);
        return topicService.saveTopic(topicDTO);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteTopic(
            @PathVariable("id") Long id) {

        topicService.deleteTopic(id);
        return "Topic is successfully deleted.";
    }

}
