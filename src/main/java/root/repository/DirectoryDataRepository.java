package root.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import root.entity.DirectoryData;

@Repository
public interface DirectoryDataRepository extends CrudRepository<DirectoryData, Long> {

    long count();

}
