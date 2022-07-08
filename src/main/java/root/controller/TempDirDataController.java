package root.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import root.service.TempDirDataService;

@Controller
public class TempDirDataController {

    private static final Logger logger = LoggerFactory.getLogger(TempDirDataController.class);

    TempDirDataService tempDirDataService;

    @Autowired
    public TempDirDataController(TempDirDataService tempDirDataService) {
        this.tempDirDataService = tempDirDataService;
    }

    public void quickScan(String strSrcPath) {

        tempDirDataService.cleanUp();               //  Delete old data from A_TEMP_DIR_DATA table
        tempDirDataService.quickScan(strSrcPath);   //  Initialize A_TEMP_DIR_DATA table

    }

    public void updateShouldScan() {
        tempDirDataService.updateShouldScan();
    }

    public void processUpdatedTempDirList() {
        tempDirDataService.processUpdatedTempDirList();
    }
}
