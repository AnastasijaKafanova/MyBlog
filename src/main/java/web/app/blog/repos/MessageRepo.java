package web.app.blog.repos;

import org.springframework.data.repository.CrudRepository;
import web.app.blog.domain.Message;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Transactional
    List<Message> deleteById(Integer deleteById);


}