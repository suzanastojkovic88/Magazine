package magazine.controller;

import magazine.dto.TopicDTO;
import magazine.model.Topic;
import magazine.service.TopicService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/topics")
public class TopicController {

    @Autowired
    private ModelMapper modelMapper;

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<TopicDTO> getTopics() {
        return topicService.findAllTopics()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "{id}")
    public TopicDTO getTopic(@PathVariable("id") Long id) {
        Topic topic = topicService.findTopic(id);
        return toDTO(topic);
    }

    private TopicDTO toDTO(Topic topic){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TopicDTO topicDTO = new TopicDTO();
        topicDTO = modelMapper.map(topic, TopicDTO.class);
        return topicDTO;
    }
    private Topic toTopic(TopicDTO topicDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Topic topic = new Topic();
        topic = modelMapper.map(topicDTO, Topic.class);
        return topic;
    }
}
