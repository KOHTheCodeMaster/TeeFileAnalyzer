package root.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import root.entity.TempDirData;
import root.repository.TempDirDataRepository;
import root.utils.DirWalker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Service
@Transactional
public class TempDirDataService {

    private static final Logger logger = LoggerFactory.getLogger(TempDirDataService.class);

    private final TempDirDataRepository tempDirDataRepository;

    @Autowired
    public TempDirDataService(TempDirDataRepository tempDirDataRepository) {
        this.tempDirDataRepository = tempDirDataRepository;
    }

    public void quickScan(String strSrcPath) {

        DirWalker dirWalker = new DirWalker(tempDirDataRepository);

        try {

            logger.info("L0G: quickScan(): Begin.");

            Files.walkFileTree(Paths.get(strSrcPath), dirWalker);
            dirWalker.saveList(dirWalker.getTempDirDataList());

            logger.info("L0G: quickScan(): Total Dirs. Scanned - " + tempDirDataRepository.count());

            logger.info("L0G: quickScan(): Quick Scan Complete.");

//            dirWalker.getTempDirDataList().forEach(tempDirPojo -> logger.info(tempDirPojo + "\n"));

            logger.info("L0G: quickScan(): End.");

        } catch (IOException e) {
            logger.error("L0G: quickScan(): Quick Scan Failed.");
            e.printStackTrace();
        }

    }


    public void cleanUp() {
        tempDirDataRepository.deleteAll();
    }

    public void updateShouldScan() {

        logger.info("L0G: updateShouldScan(): Begin.");

        /*
            Compare LAST_MOD_TIME_IN_MICROS b/w A_TEMP_DIR_DATA  &  A_DIRECTORY_DATA
            Update SHOULD_SCAN = 1 for records from A_TEMP_DIR_DATA whose LAST_MOD_TIME_IN_MICROS
            is not equal to that of A_DIRECTORY_META_DATA for respective A_DIRECTORY_DATA
         */
        int updatedRowCount = tempDirDataRepository.updateShouldScanForModifiedItems();
        updatedRowCount += tempDirDataRepository.updateShouldScanForNewlyAddedDirs();

        logger.info("L0G: Total Impacted Dirs Count - " + updatedRowCount);

        logger.info("L0G: updateShouldScan(): End.");

    }

    public void processUpdatedTempDirList() {

        logger.info("L0G: processUpdatedTempDirList(): Begin.");

        PageRequest pageRequest = PageRequest.of(0, 2, Sort.by("canonicalPath"));
        Page<TempDirData> page;

        do {

            page = tempDirDataRepository.findByShouldScan(true, pageRequest);
            logger.info("L0G: ------------------  ");
            logger.info("L0G: processUpdatedTempDirList():" +
                    "\nPage No. - " + page.getNumber() +
                    " | Total Pages - " + page.getTotalPages() +
                    " | Total Elements - " + page.getTotalElements() +
                    " | Total No. Of Elements - " + page.getNumberOfElements() +
                    " | Total Size - " + page.getSize());

            List<TempDirData> list = page.getContent();
            StringBuilder strCanonicalPathOfAll = new StringBuilder();

            //  Process List Here.
            list.stream()
                    .map(TempDirData::getCanonicalPath)
                    .forEach(s -> strCanonicalPathOfAll.append(s).append(" | "));

            logger.info(strCanonicalPathOfAll.toString());

            if (list.isEmpty()) {
                logger.info("L0G: processUpdatedTempDirList(): No Dirs. were changed.");
                return;
            }

//            logger.info("L0G: List - ");
//            list.stream().map(TempDirData::toString).forEach(logger::info);

            pageRequest = pageRequest.next();

        } while (!page.isLast());

        logger.info("L0G: Total Impacted Dirs Count - " + page.getTotalElements());

        logger.info("L0G: processUpdatedTempDirList(): End.");

    }

}
