package magazine.service;

import lombok.RequiredArgsConstructor;
import magazine.dto.TopicDTO;
import magazine.model.Topic;
import magazine.repository.TopicRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final ModelMapper modelMapper;

    private final TopicRepository topicRepository;

    @Override
    public List<TopicDTO> findAllTopics() {
        return topicRepository.findAll().stream().map(topic-> modelMapper
                .map(topic, TopicDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TopicDTO findTopic(Long id) {
       Topic topic = topicRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Topic with id "+ id + " does not exist."));
    return modelMapper.map(topic, TopicDTO.class);
    }

    @Override
    public TopicDTO saveTopic(TopicDTO topicDTO) {
        Topic topic = modelMapper.map(topicDTO, Topic.class);
        Topic savedTopic = topicRepository.save(topic);
        return modelMapper.map(savedTopic, TopicDTO.class);
    }

    @Override
    public void deleteTopic(Long id) {
        boolean exist = topicRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Topic with id " + id + " does not exist.");
        }
        topicRepository.deleteById(id);
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