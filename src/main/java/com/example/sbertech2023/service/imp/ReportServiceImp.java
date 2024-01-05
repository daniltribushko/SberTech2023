package com.example.sbertech2023.service.imp;

import com.example.sbertech2023.exceptions.users.UserNotAdminException;
import com.example.sbertech2023.exceptions.users.UserNotAppealsAuthor;
import com.example.sbertech2023.models.entities.Appeal;
import com.example.sbertech2023.models.entities.User;
import com.example.sbertech2023.service.AppealService;
import com.example.sbertech2023.service.ReportService;
import com.example.sbertech2023.service.UserService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 23.12.2023
 * <p>
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

    private final static String PATH = "src/main/resources/static/files";

    @Override
    public Resource downloadReport(String userName, Long userId, Long appealId) throws MalformedURLException {
        User client = userService.findByUserName(userName);
        //Проверка является ли текущий пользователь администратором
        if (!userService.isAdmin(client)) {
            throw new UserNotAdminException(userName);
        }
        User user = userService.findById(userId);
        Appeal appeal = appealService.findAppealById(appealId);
        if (!appeal.getAuthor().getId().equals(user.getId())){
            throw new UserNotAppealsAuthor(user.getLogin());
        }
        return getResource(user, appeal);
    }

    @Override
    public List<Resource> downloadAllReportsByUser(String userName,
                                                   Long userId,
                                                   Integer page,
                                                   Integer count) {
        User client = userService.findByUserName(userName);
        if (!userService.isAdmin(client)) {
            throw new UserNotAdminException(userName);
        }
        User user = userService.findById(userId);
        List<Resource> result = new ArrayList<>();
        Page<Appeal> appeals = appealService.findAppealsByAuthorAndPage(user, PageRequest.of(page, count));
        appeals.forEach(a -> {
            try {
                result.add(getResource(user, a));
            } catch (MalformedURLException e) {
                log.warn(e.getMessage());
            }
        });
        return result;
    }

    /**
     * Получение отчета
     *
     * @param user автор обращения
     * @param appeal обращение
     * @return ресурсы документа
     */
    private Resource getResource(User user, Appeal appeal) throws MalformedURLException {
        String fileName = "report_" + user.getLogin() + "_" + appeal.getTitle() + ".pdf";
        Path file = createPath(fileName);
        Resource resource = new UrlResource(file.toUri());
        //Если отчет не сушествует или не открывается, то создаем его
        if (!resource.exists() || !resource.isReadable()) {
            createReport(user, appeal);
            file = createPath(fileName);
            resource = new UrlResource(file.toUri());
        }
        return resource;

    }

    /**
     * Создание пути к файлу
     *
     * @param fileName имя файла
     * @return путь файла
     */
    private Path createPath(String fileName) {
        return Path.of(PATH).resolve("reports").resolve(fileName);
    }

    /**
     * Создание отчета pdf по обращению
     * @param user автор обращения
     * @param appeal обращение
     */
    private void createReport(User user, Appeal appeal) {
        Document report = new Document();
        String fileName = "report_" + user.getLogin() + "_" + appeal.getTitle() + ".pdf";
        try {
            PdfWriter.getInstance(report, new FileOutputStream(PATH + "/reports/" + fileName));
            report.open();
            //Подключаем внешний шрифт
            BaseFont font = BaseFont.createFont("src/main/resources/fonts/times.ttf",
                    "cp1251",
                    BaseFont.EMBEDDED);
            Font mainFont = new Font(font, 20);
            Font mainFontBold = new Font(font, 22, Font.BOLD);
            addParagraphInDocument(report, new Chunk("ОБРАЩЕНИЕ №" + appeal.getId(), mainFontBold));
            addParagraphInDocument(report, new Chunk("Пользователь:   ", mainFontBold),
                    new Chunk(user.getSureName() + " " + user.getName(), mainFont));
            addParagraphInDocument(report, new Chunk("Район: ", mainFontBold),
                    new Chunk(appeal.getDistrict().getName(), mainFont));
            addParagraphInDocument(report, new Chunk("Микрорайон: ", mainFontBold),
                    new Chunk(appeal.getMicroDistrict().getName(), mainFont));
            addParagraphInDocument(report, new Chunk("Дата публикации: ", mainFontBold),
                    new Chunk(appeal.getDatePublish()
                            .format(DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy")), mainFont));
            addImageInDocument(report, Path.of(PATH + "/photos/appeal/" +
                    user.getLogin() + "_" + appeal.getTitle() + ".jpeg"));
            addParagraphInDocument(report, new Chunk("Заголовок обращения:   ", mainFontBold),
                    new Chunk(appeal.getTitle(), mainFont));
            addParagraphInDocument(report, new Chunk("Текст обращения:   ", mainFontBold),
                    new Chunk(appeal.getText(), mainFont));
            report.close();
        } catch (DocumentException | IOException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * Добавление параграфа в документ с 2 кусками текста
     *
     * @param document pdf документ
     * @param paragraphName имя параграфа
     * @param paragraphValue содерэимое параграфа
     */
    private void addParagraphInDocument(Document document,
                                        Chunk paragraphName,
                                        Chunk paragraphValue) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        //Устанавливаем отступ для первой строки
        paragraph.setFirstLineIndent(20.0f);
        addChunkInParagraph(paragraph, paragraphName);
        addChunkInParagraph(paragraph, paragraphValue);
        document.add(paragraph);
    }

    /**
     * Добавление параграфа в документ pdf с одним куском текста
     *
     * @param document pdf документ
     * @param paragraphName имя параграфа
     */
    private void addParagraphInDocument(Document document,
                                        Chunk paragraphName) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        //Устанавливаем отступ для первой стркои
        paragraph.setFirstLineIndent(20.0f);
        addChunkInParagraph(paragraph, paragraphName);
        document.add(paragraph);
    }

    /**
     * Добавление текста в параграф
     *
     * @param paragraph параграф pdf документа
     * @param chunk кусок текста
     */
    private void addChunkInParagraph(Paragraph paragraph, Chunk chunk) {
        //Устанавливаем размер строки
        chunk.setLineHeight(26);
        paragraph.add(chunk);
    }

    /**
     * Добавление изображения в pdf файл
     *
     * @param document pdf документ
     * @param path путь к изображению
     */
    private void addImageInDocument(Document document, Path path) throws DocumentException, IOException {
        Image img = Image.getInstance(path.toAbsolutePath().toString());
        //Устанавливаем размеры изображения на странице документа
        img.scaleAbsolute(500.0f, 300.0f);
        //Устанавливаем позицию изображения
        img.setAbsolutePosition(img.getAbsoluteX(), img.getAbsoluteY() + 20.0f);
        document.add(img);
    }
}
