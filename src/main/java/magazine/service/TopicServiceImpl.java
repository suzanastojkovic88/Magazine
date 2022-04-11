package magazine.service;

import magazine.model.Magazine;
import magazine.model.Topic;
import magazine.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic findTopic(Long id) {
        boolean exists = topicRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Topic with id " + id + " does not exist.");
        }
        Topic topic = topicRepository.getById(id);
        return topic;
    }
}