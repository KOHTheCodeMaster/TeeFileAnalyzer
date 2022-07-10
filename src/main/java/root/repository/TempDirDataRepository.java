package root.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import root.entity.TempDirData;

@Repository
public interface TempDirDataRepository extends JpaRepository<TempDirData, Long> {

    void deleteAll();

    long count();

    /**
     * Compare LAST_MOD_TIME_IN_MICROS b/w A_TEMP_DIR_DATA  &  A_DIRECTORY_DATA
     * Update SHOULD_SCAN = 1 for records from A_TEMP_DIR_DATA whose LAST_MOD_TIME_IN_MICROS
     * is not equal to that of A_DIRECTORY_META_DATA for respective A_DIRECTORY_DATA
     * @return Updated Row Count
     */
    @Modifying
    @Query(value = "UPDATE a_temp_dir_data AS T1, " +
            "    (SELECT TDD.PATH_HASH_SHA_256 " +
            "     FROM a_temp_dir_data TDD " +
            "              LEFT JOIN " +
            "          ( " +
            "              SELECT DMD.LAST_MOD_TIME_IN_MICROS, DD.PATH_HASH_SHA_256 " +
            "              FROM a_directory_meta_data DMD, " +
            "                   a_directory_data DD " +
            "              WHERE DMD.ID = DD.DIRECTORY_META_DATA_ID " +
            "          ) DD2 " +
            "          ON TDD.PATH_HASH_SHA_256 = DD2.PATH_HASH_SHA_256 " +
            "     WHERE TDD.LAST_MOD_TIME_IN_MICROS <> DD2.LAST_MOD_TIME_IN_MICROS) AS T2 " +
            "SET SHOULD_SCAN = 1 " +
            "WHERE T1.PATH_HASH_SHA_256 = T2.PATH_HASH_SHA_256",
            nativeQuery = true
    )
    int updateShouldScanForModifiedItems();

    /**
     * Update SHOULD_SCAN = 1 for records from A_TEMP_DIR_DATA whose CANONICAL_PATH
     * is not equal to that of A_DIRECTORY_DATA
     * @return Updated Row Count
     */
    @Modifying
    @Query(value = "UPDATE a_temp_dir_data " +
            "SET SHOULD_SCAN = 1 " +
            "WHERE CANONICAL_PATH NOT IN ( " +
            "    SELECT * " +
            "    FROM ( " +
            "             SELECT TDD2.CANONICAL_PATH " +
            "             FROM a_temp_dir_data TDD2, " +
            "                  a_directory_data DD " +
            "             WHERE TDD2.PATH_HASH_SHA_256 = DD.PATH_HASH_SHA_256) as T2DCP " +
            ")",
            nativeQuery = true
    )
    int updateShouldScanForNewlyAddedDirs();

    Page<TempDirData> findByShouldScan(boolean shouldScan, PageRequest pageRequest);

}
