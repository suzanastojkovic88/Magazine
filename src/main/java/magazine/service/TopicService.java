package magazine.service;

import magazine.dto.TopicDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {

    List<TopicDTO> findAllTopics();

    TopicDTO findTopic(Long id);

    TopicDTO saveTopic(TopicDTO topicDTO);

    void deleteTopic(Long id);
}
