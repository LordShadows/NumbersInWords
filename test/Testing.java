import com.danielsandrutski.convert.NumbersWordsConvert;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import java.io.FileInputStream;
import java.io.InputStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Testing {

    @Test
    public void testTableValues() throws Exception {
        System.out.println("Тест 1: Проверка на стандартные числа по словарю.");

        InputStream in = new FileInputStream("test/SimpleData.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);

        double firstColumn = 0;
        String secondColumn = "";

        Sheet sheet = wb.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                        firstColumn = cell.getNumericCellValue();
                        break;
                    case Cell.CELL_TYPE_STRING:
                        secondColumn = cell.getStringCellValue();
                        break;
                }
            }
            String result = new NumbersWordsConvert(firstColumn).ConvertDouble();
            System.out.println(firstColumn + " - " + result);
            assertEquals("Ошибка в числе: " + firstColumn, secondColumn, result);
        }
    }

    @Test
    public void testAdditionalValues() throws Exception {
        System.out.println("Тест 2: Проверка работы с пользовательским наименованием.");
        String result;
        System.out.println("142 (балл) - " + (result = new NumbersWordsConvert(142).ConvertIntegerPartWithLabel("балл", "балла", "баллов", 0)));
        assertEquals("Ошибка в подтесте 1: ", "сто сорок два балла", result);
        System.out.println("21 (балл) - " + (result = new NumbersWordsConvert(21).ConvertIntegerPartWithLabel("балл", "балла", "баллов", 0)));
        assertEquals("Ошибка в подтесте 2: ", "двадцать один балл", result);
        System.out.println("14 (балл) - " + (result = new NumbersWordsConvert(14).ConvertIntegerPartWithLabel("балл", "балла", "баллов", 0)));
        assertEquals("Ошибка в подтесте 3: ", "четырнадцать баллов", result);
        System.out.println("42 (копейка) - " + (result = new NumbersWordsConvert(42).ConvertIntegerPartWithLabel("копейка", "копейки", "копеек", 1)));
        assertEquals("Ошибка в подтесте 4: ", "сорок две копейки", result);
        System.out.println("21 (копейка) - " + (result = new NumbersWordsConvert(21).ConvertIntegerPartWithLabel("копейка", "копейки", "копеек", 1)));
        assertEquals("Ошибка в подтесте 5: ", "двадцать одна копейка", result);
        System.out.println("14 (копейка) - " + (result = new NumbersWordsConvert(14).ConvertIntegerPartWithLabel("копейка", "копейки", "копеек", 1)));
        assertEquals("Ошибка в подтесте 6: ", "четырнадцать копеек", result);
    }
}