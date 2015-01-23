package milkyway.excel;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import milkyway.exceptions.ExcelBuilderException;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class ExcelBuilder {
    public ExcelBuilder() {
    }

    public byte[] makeExcel(FormData data, Settings settings) throws ExcelBuilderException {
        try {
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));
//            File file = new File("semen.xls");
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableWorkbook workbook = Workbook.createWorkbook(byteStream);
            WritableSheet sheet = workbook.createSheet("data", 0);
            writeDataSheet(sheet, data, settings);
            workbook.write();
            workbook.close();
            byteStream.close();
            return byteStream.toByteArray();
//            return new byte[3];
        } catch (Exception e) {
            throw new ExcelBuilderException(e.getMessage(), e);
        }
    }

    private void writeDataSheet(WritableSheet sheet, FormData data, Settings settings) throws WriteException {
        LinkedHashMap<String, HashMap<String, String>> map = data.getMap();
        HashMap<String, ColumnSettings> settingsMap = settings.getMap();
        for (ColumnSettings columnSettings : settingsMap.values()) {
            Label label = new Label(columnSettings.getWeight(), 0, columnSettings.getCaption());
            sheet.addCell(label);
        }
        int i = 0;
        for (Map.Entry<String, HashMap<String, String>> entry : map.entrySet()) {
            i++;
            HashMap<String, String> rowData = entry.getValue();
            for (ColumnSettings columnSettings : settingsMap.values()) {
                String columnKey = columnSettings.getKey();
                String text = rowData.get(columnKey);
                if (text != null) {
                    Label label = new Label(columnSettings.getWeight(), i, text);
                    sheet.addCell(label);
                }
            }
        }

    }
}