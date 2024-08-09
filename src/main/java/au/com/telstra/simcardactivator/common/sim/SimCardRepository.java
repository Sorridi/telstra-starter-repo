package au.com.telstra.simcardactivator.common.sim;

import org.springframework.data.repository.CrudRepository;

public interface SimCardRepository extends CrudRepository<SimCard, Long>
{
    SimCard findSimCardById(Long id);
}
