package ru.syntez.page.counter.usecases;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;
import ru.syntez.page.counter.entities.FilePageCountResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Test for testing documents from local resourses
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class PageCounterUsecaseTest {

    @Test
    public void countTest() {
        FilePageCounterUsecase filePageCounterUsecase = new FilePageCounterUsecase();
        FindFilesUsecase findFilesUsecase = new FindFilesUsecase();
        PageCounterUsecase pageCounterUsecase = new PageCounterUsecase(filePageCounterUsecase, findFilesUsecase);
        try {
            File file = ResourceUtils.getFile(this.getClass().getResource("/doc1.docx"));
            String[] fileArray = new String[1];
            fileArray[0] = file.getParent();
            List<FilePageCountResult> result = pageCounterUsecase.execute(fileArray);
            Assert.assertEquals(3, result.size());
            Assert.assertEquals(6,  result.stream().mapToInt(FilePageCountResult::getPageCount).sum());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
