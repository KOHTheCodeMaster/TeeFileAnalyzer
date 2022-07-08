package root.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import root.entity.DirectoryData;
import root.entity.DirectoryMetaData;
import root.entity.TempDirData;
import root.repository.DirectoryDataRepository;
import root.repository.TempDirDataRepository;

import java.util.ArrayList;


@Service
public class DirectoryDataService {

    TempDirDataRepository tempDirDataRepository;
    DirectoryDataRepository directoryDataRepository;

    private static final Logger logger = LoggerFactory.getLogger(DirectoryDataService.class);

    @Autowired
    public DirectoryDataService(TempDirDataRepository tempDirDataRepository, DirectoryDataRepository directoryDataRepository) {
        this.tempDirDataRepository = tempDirDataRepository;
        this.directoryDataRepository = directoryDataRepository;
    }


    public void saveIfEmpty() {

        long count = directoryDataRepository.count();
        logger.info("L0G: saveInit(): Existing Count in A_DIRECTORY_DATA - " + count);

        if (count == 0) {

            ArrayList<DirectoryData> dirDataList = new ArrayList<>();
            Iterable<TempDirData> all = tempDirDataRepository.findAll();

            all.forEach(tempDirData -> {

                DirectoryMetaData directoryMetaData = new DirectoryMetaData();
                directoryMetaData.setLastModTimeInMicros(tempDirData.getLastModTimeInMicros());
                directoryMetaData.setPathHashSHA256(tempDirData.getPathHashSHA256());

                DirectoryData directoryData = new DirectoryData();
                directoryData.setPathHashSHA256(tempDirData.getPathHashSHA256());
                directoryData.setCanonicalPath(tempDirData.getCanonicalPath());
                directoryData.setDirectoryMetaData(directoryMetaData);

                dirDataList.add(directoryData);

            });

            directoryDataRepository.saveAll(dirDataList);
        }

    }

}
