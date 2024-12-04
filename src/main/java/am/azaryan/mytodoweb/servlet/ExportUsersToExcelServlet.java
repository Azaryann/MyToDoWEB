package am.azaryan.mytodoweb.servlet;

import am.azaryan.mytodoweb.model.User;
import am.azaryan.mytodoweb.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exportUsersToExcel")
public class ExportUsersToExcelServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = (User) req.getSession().getAttribute("user");
        List<User> usersExceptCurrent = userService.getUsersExceptCurrent(currentUser.getId());

        Workbook workbook = getUserWorkbookFromList(usersExceptCurrent);

        resp.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        resp.setHeader("Content-Disposition", "inline; filename=users.xlsx");
        workbook.write(resp.getOutputStream());

    }

    private Workbook getUserWorkbookFromList(List<User> usersExceptCurrent) {
        Workbook workbook = new XSSFWorkbook();
        Sheet usersSheet = workbook.createSheet("Users");

        Row headerRow = usersSheet.createRow(0);

        Cell nameCell = headerRow.createCell(0);
        nameCell.setCellValue("Անուն");
        usersSheet.autoSizeColumn(0);

        Cell surnameCell = headerRow.createCell(1);
        surnameCell.setCellValue("Ազգանուն");
        usersSheet.autoSizeColumn(1);

        Cell emailCell = headerRow.createCell(2);
        emailCell.setCellValue("էլ հասցե");
        usersSheet.autoSizeColumn(2);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        Font font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        nameCell.setCellStyle(headerStyle);
        surnameCell.setCellStyle(headerStyle);
        emailCell.setCellStyle(headerStyle);
        int rowIndex = 1;
        for (User user : usersExceptCurrent) {
            Row row = usersSheet.createRow(rowIndex++);
            nameCell = row.createCell(0);
            nameCell.setCellValue(user.getName());
            usersSheet.autoSizeColumn(0);
            surnameCell = row.createCell(1);
            surnameCell.setCellValue(user.getSurname());
            emailCell = row.createCell(2);
            usersSheet.autoSizeColumn(1);
            emailCell.setCellValue(user.getEmail());
            usersSheet.autoSizeColumn(2);
        }
        return workbook;
    }
}
