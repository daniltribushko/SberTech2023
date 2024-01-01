package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.users.UserNotAdminException;
import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.entities.User;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.ReportService;
import com.example.sbertech2023.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 23.12.2023
 *
 * Реализация сервиса для работы с очтетами по обращениями
 */
@Slf4j
@Service
public class ReportServiceImp implements ReportService {
    private final UserService userService;
    private final AppealService appealService;

    @Autowired
    public ReportServiceImp(UserService userService,
                            AppealService appealService) {
        this.userService = userService;
        this.appealService = appealService;
    }

    private final static String PATH = "src/main/resources/static/files/reports";

    @Override
    public Resource downloadReport(String userName, Long userId, Long appealId) throws MalformedURLException {
        User client = userService.findByUserName(userName);
        if (!userService.isAdmin(client)) {
            throw new UserNotAdminException(userName);
        }
        User user = userService.findById(userId);
        Appeal appeal = appealService.findAppealById(appealId);
        return getResource(user, appeal);
    }

    @Override
    public List<Resource> downloadAllReportsByUser(String userName,
                                                   Long userId,
                                                   Integer page,
                                                   Integer count)  {
        User client = userService.findByUserName(userName);
        if (!userService.isAdmin(client)) {
            throw new UserNotAdminException(userName);
        }
        User user = userService.findById(userId);
        List<Resource> result = new ArrayList<>();
        Page<Appeal> appeals = appealService.findAppealsByAuthorAndPage(user, PageRequest.of(page, count));
        appeals.forEach(a -> {
            result.add(getResource(user, a));
        });
        return result;
    }


    private Resource getResource(User user, Appeal appeal)  {
        String fileName = "static/files/reports/report_" + user.getLogin() + "_" + appeal.getTitle() + ".pdf";
        Resource resource = new ClassPathResource(fileName);
        if (resource.exists() || resource.isReadable()) {
            return resource;
        } else {
            createReport(user, appeal);
            return new ClassPathResource(fileName);
        }
    }

    private void createReport(User user, Appeal appeal)  {
        Document report = new Document();
        String fileName = "report_" + user.getSureName() + "_" + appeal.getTitle() + ".pdf";
        try {
            PdfWriter.getInstance(report, new FileOutputStream(PATH + "/" + fileName));
            report.open();
            BaseFont font = BaseFont.createFont("src/main/resources/fonts/times.ttf",
                    "cp1251",
                    BaseFont.EMBEDDED);
            Font mainFont = new Font(font, 24);
            Font font2 = new Font(font, 22);
            report.add(new Paragraph(user.getSureName() + " " + user.getName(), mainFont));
            report.add(new Paragraph(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")),
                    font2));
            report.close();
        } catch (DocumentException | IOException e){
            log.warn(e.getMessage());
        }
    }
}
