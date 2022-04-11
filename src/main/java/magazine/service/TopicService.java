package magazine.service;

import magazine.model.Magazine;
import magazine.model.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {

    public List<Topic> findAllTopics();

    public Topic findTopic(Long id);
}
