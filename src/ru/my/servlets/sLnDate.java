package ru.my.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLn;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnImplService;
import ru.ibs.fss.ln.ws.fileoperationsln.FileOperationsLnUserGetLNDataOut;
import ru.ibs.fss.ln.ws.fileoperationsln.ROW;
import ru.ibs.fss.ln.ws.fileoperationsln.SOAPException_Exception;
import ru.ibs.fss.ln.ws.fileoperationsln.TREATFULLPERIOD;

@WebServlet("/sLnDate")
public class sLnDate extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Logger logger = Logger.getLogger("simple");
            logger.info("1) sLnDate");
            response.setContentType("text/html ;charset=UTF-8");

            String ogrn = request.getParameter("ogrn");
            String eln = request.getParameter("eln");
            String snils = request.getParameter("snils");

            snils = snils.replace("-", "").replace(" ", "");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>" +
                    "  <meta charset=\"UTF-8\" />\n" +
                    "  <title>SignAndCrypt</title>\n" +
                    "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>");

            out.println("<body>");
            out.print("<H1> ogrn=" + ogrn + "</H1>");
            out.print("<H1> eln=" + eln + "</H1>");
            out.print("<H1> snils=" + snils + "</H1>");

            FileOperationsLnImplService service = new FileOperationsLnImplService();
            FileOperationsLn start = service.getFileOperationsLnPort();
            FileOperationsLnUserGetLNDataOut fileOperationsLnUserGetLNDataOut = start.getLNData(ogrn, eln, snils);
            out.print("<H1> Инфо=" + fileOperationsLnUserGetLNDataOut.getINFO() + "</H1>");
            out.print("<H1> Сообщение=" + fileOperationsLnUserGetLNDataOut.getMESS() + "</H1>");
            out.print("<H1> Статус=" + fileOperationsLnUserGetLNDataOut.getSTATUS() + "</H1>");
            out.print("<H1> Состояние=" + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNSTATE() + "</H1>");
            out.print("<H1> Хэш:" + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNHASH() + "</H1>");

            out.print("<H1> Предыдущий ЛН:" + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getPREVLNCODE() + "</H1>");

            out.print("<H1> Фамилия: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSURNAME() + "</H1>");
            out.print("<H1> Имя: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getNAME() + "</H1>");
            out.print("<H1> Отчество: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getPATRONIMIC() + "</H1>");
            out.print("<H1> Дата рождения: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getBIRTHDAY() + "</H1>");
            out.print("<H1> Место работы:" + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLPUEMPLOYER() + "</H1>");
            out.print("<H1> Совместительство: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLPUEMPLFLAG() + "</H1>");
            out.print("<H1> Номер ЛН на основном: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getPARENTCODE() + "</H1>");
            out.print("<H1> Номер: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getPARENTCODE() + "</H1>");
            out.print("<H1> Диагноз: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getDIAGNOS() + "</H1>");

            out.print("<H1> ранние сроки беременности: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getPREGN12WFLAG() + "</H1>");

            out.print("<H1> Дата выдачи: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNDATE() + "</H1>");
            out.print("<H1> Наименование ЛПУ: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLPUNAME() + "</H1>");
            out.print("<H1> ОГРН ЛПУ: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLPUOGRN() + "</H1>");
            out.print("<H1> Адрес ЛПУ: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLPUADDRESS() + "</H1>");

                out.print("<H1> Причина: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getREASON1() + "</H1>");
                out.print("<H1> Причина измен: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getREASON2() + "</H1>");
                out.print("<H1> Причина измен2: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getREASON3() + "</H1>");


            out.print("<H1> ______________________</H1>");
            out.print("<H1> Отношение: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV1RELATIONCODE() + "<H1>");
            out.print("<H1> ФИО: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV1FIO() + "<H1>");
            out.print("<H1> Кол-во лет: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV1AGE() + "<H1>");
            out.print("<H1> Кол-во месяцев: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV1MM() + "<H1>");

            out.print("<H1> Отношение: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV2RELATIONCODE() + "<H1>");
            out.print("<H1> ФИО: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV2FIO() + "<H1>");
            out.print("<H1> Кол-во лет: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV2AGE() + "<H1>");
            out.print("<H1> Кол-во месяцев: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getSERV2MM() + "<H1>");
            out.print("<H1> ______________________</H1>");


            out.print("<H1> ______________________</H1>");
            out.print("<H1> Госпитализация1: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getHOSPITALDT1() + "</H1>");
            out.print("<H1> Госпитализация2: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getHOSPITALDT2() + "</H1>");

            out.print("<H1> ______________________</H1>");
            out.print("<H1> МСЭ1: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getMSEDT1() + "</H1>");
            out.print("<H1> МСЭ2: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getMSEDT2() + "</H1>");
            out.print("<H1> МСЭ3: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getMSEDT3() + "</H1>");
            out.print("<H1> МСЭ группа: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getMSEINVALIDGROUP() + "</H1>");
            out.print("<H1> ______________________</H1>");

            out.print("<H1> ______________________</H1>");
            out.print("<H1> Дата начала (пред.родов): " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getDATE1() + "</H1>");
            out.print("<H1> Дата окончания: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getDATE2() + "</H1>");
            out.print("<H1> Номер путевки: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getVOUCHERNO() + "</H1>");
            out.print("<H1> ОГРН санатория или клиники НИИ: " + fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getVOUCHEROGRN() + "</H1>");
            out.print("<H1> ______________________</H1>");


            List<TREATFULLPERIOD> treatfullperiods = fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getTREATPERIODS().getTREATFULLPERIOD();
            ROW.LNRESULT lnresult = fileOperationsLnUserGetLNDataOut.getDATA().getOUTROWSET().getROW().get(0).getLNRESULT();
            if (lnresult != null) {
                out.print("<H1> co: " + lnresult.getNEXTLNCODE() + "</H1>");
                out.print("<H1> doc role: " + lnresult.getOTHERSTATEDT() + "</H1>");
                out.print("<H1> doc role: " + lnresult.getMSERESULT() + "</H1>");
                out.print("<H1> выход на работу: " + lnresult.getRETURNDATELPU() + "</H1>");
            }
            for (TREATFULLPERIOD treatfullperiod : treatfullperiods) {
                out.print("<H1> ______________________</H1>");
                out.print("<H1>Начало периода: " + treatfullperiod.getTREATPERIOD().getTREATDT1() + "</H1>");
                out.print("<H1>Конец периода: " + treatfullperiod.getTREATPERIOD().getTREATDT2() + "</H1>");
                out.print("<H1>ВК: " + treatfullperiod.getTREATCHAIRMAN() + "</H1>");
                out.print("<H1>Роль ВК: " + treatfullperiod.getTREATCHAIRMANROLE() + "</H1>");
                out.print("<H1>Врач: " + treatfullperiod.getTREATPERIOD().getTREATDOCTOR() + "</H1>");
                out.print("<H1>Роль врача: " + treatfullperiod.getTREATPERIOD().getTREATDOCTORROLE() + "</H1>");
                out.print("<H1> ______________________</H1>");
            }

        } catch (SOAPException_Exception e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
