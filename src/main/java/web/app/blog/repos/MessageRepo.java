package web.app.blog.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import web.app.blog.domain.Message;

import javax.transaction.Transactional;
import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {

    @Transactional
    List<Message> deleteById(Integer deleteById);

    @Transactional
    List<Message> findByTag(String tag);

    @Transactional
    List<Message> findById(Integer id);

/*    @Override
    @Transactional
    Message save(Message message);*/

}

