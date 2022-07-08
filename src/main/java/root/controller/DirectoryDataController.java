package root.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import root.service.DirectoryDataService;

@Controller
public class DirectoryDataController {

    DirectoryDataService directoryDataService;
    private static final Logger logger = LoggerFactory.getLogger(DirectoryDataController.class);

    @Autowired
    public DirectoryDataController(DirectoryDataService directoryDataService) {
        this.directoryDataService = directoryDataService;
    }

    public void saveIfEmpty() {
        //  Save A_TEMP_DIR_DATA table data initially when A_DIRECTORY_DATA table is empty.
        directoryDataService.saveIfEmpty();

    }

}
