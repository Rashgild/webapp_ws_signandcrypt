package ru.rashgild.utils;

import ru.rashgild.generated.v2.types.eln.mo.v01.FileOperationsLnUserGetLNDataOut;
import ru.rashgild.generated.v2.types.eln.mo.v01.ResponseRow;
import ru.rashgild.generated.v2.types.eln.v01.LnResult;
import ru.rashgild.generated.v2.types.eln.v01.TreatFullPeriodMo;

import java.util.List;

import static java.lang.String.format;

public class ResponseBuilderUtil {

    public static String createResponseLnData(FileOperationsLnUserGetLNDataOut getLnData) {

        ResponseRow responseRow = getLnData.getData().getOutRowset().getResponseRow();
        String pattern = "<H1> %s: %s </H1>";

        StringBuilder response = new StringBuilder()
                .append(format(pattern, "Инфо", getLnData.getInfo()))
                .append(format(pattern, "Сообщение", getLnData.getMess()))
                .append(format(pattern, "Статус", getLnData.getStatus()))
                .append(format(pattern, "Состояние", responseRow.getLnState()))
                .append(format(pattern, "Хэш", responseRow.getLnHash()))
                .append(format(pattern, "Предыдущий ЛН", responseRow.getPrevLnCode()))
                .append(format(pattern, "Фамилия", responseRow.getSurname()))
                .append(format(pattern, "Имя", responseRow.getName()))
                .append(format(pattern, "Отчество", responseRow.getPatronymic()))
                .append(format(pattern, "Дата рождения", responseRow.getBirthday()))
                .append(format(pattern, "Диагноз", responseRow.getDiagnos()))
                //.append(format(pattern, "Ранние сроки беременности", responseRow.getPREGN12WFLAG))
                .append(format(pattern, "Дата выдачи", responseRow.getLnDate()))
                .append(format(pattern, "Наименование ЛПУ", responseRow.getLpuName()))
                .append(format(pattern, "ОГРН ЛПУ", responseRow.getLpuOgrn()))
                .append(format(pattern, "Адрес ЛПУ", responseRow.getLpuAddress()))
                .append(format(pattern, "Причина", responseRow.getReason1()))
                .append(format(pattern, "Причина измен", responseRow.getReason2()))
                .append("<H1> ______________________</H1>")
                .append(format(pattern, "Госпитализация 1", responseRow.getHospitalDt1()))
                .append(format(pattern, "Госпитализация 2", responseRow.getHospitalDt2()))
                .append("<H1> ______________________</H1>")
                .append(format(pattern, "МСЭ1", responseRow.getMseDt1()))
                .append(format(pattern, "МСЭ2", responseRow.getMseDt2()))
                .append(format(pattern, "МСЭ3", responseRow.getMseDt3()))
                .append(format(pattern, "МСЭ группа", responseRow.getMseInvalidGroup()))
                .append("<H1> ______________________</H1>")
                .append(format(pattern, "Дата начала (пред.родов)", responseRow.getDate1()))
                .append(format(pattern, "Дата окончания", responseRow.getDate2()))
                .append(format(pattern, "Номер путевки", responseRow.getVoucherNo()))
                .append(format(pattern, "ОГРН санатория или клиники НИИ", responseRow.getVoucherOgrn()));
            /*        .append(format(pattern, "Причина измен", servData))
            out.print("<H1> ______________________</H1>");
            out.print("<H1> Отношение: " + responseRow.getSERV1RELATIONCODE() + "<H1>");
            out.print("<H1> ФИО: " + responseRow.getSERV1FIO() + "<H1>");
            out.print("<H1> Кол-во лет: " + responseRow.getSERV1AGE() + "<H1>");
            out.print("<H1> Кол-во месяцев: " + responseRow.getSERV1MM() + "<H1>");

            out.print("<H1> Отношение: " + responseRow.getSERV2RELATIONCODE() + "<H1>");
            out.print("<H1> ФИО: " + responseRow.getSERV2FIO() + "<H1>");
            out.print("<H1> Кол-во лет: " + responseRow.getSERV2AGE() + "<H1>");
            out.print("<H1> Кол-во месяцев: " + responseRow.getSERV2MM() + "<H1>");
            out.print("<H1> ______________________</H1>");*/


        if (responseRow.getLnResult() != null) {
            LnResult lnResult = responseRow.getLnResult();
            response
                    .append(format(pattern, "co", lnResult.getMseResult()))
                    .append(format(pattern, "co", lnResult.getOtherStateDt()))
                    .append(format(pattern, "co", lnResult.getNextLnCode()))
                    .append(format(pattern, "выход на работу", lnResult.getReturnDateLpu()));
        }

        List<TreatFullPeriodMo> treatfullperiods = responseRow.getTreatPeriods().getTreatFullPeriod();
        for (TreatFullPeriodMo treatfullperiod : treatfullperiods) {
            response
                    .append("<H1> ______________________</H1>")
                    .append(format(pattern, "Начало периода", treatfullperiod.getTreatPeriod().getTreatDt1()))
                    .append(format(pattern, "Конец периода", treatfullperiod.getTreatPeriod().getTreatDt2()))
                    .append(format(pattern, "Врач", treatfullperiod.getTreatPeriod().getTreatDoctor()))
                    .append(format(pattern, "Роль врача", treatfullperiod.getTreatPeriod().getTreatDoctorRole()))
                    .append(format(pattern, "ВК", treatfullperiod.getTreatChairman()))
                    .append(format(pattern, "Роль ВК", treatfullperiod.getTreatChairmanRole()))
                    .append("<H1> ______________________</H1>");
        }

        return response.toString();
    }

    public static String createHtmlTemplate(String ogrn,
                                            String eln,
                                            String snils) {

        String pattern = "<H1> %s: %s </H1>";
        return new StringBuilder()
                .append("<html>")
                .append("<head>" +
                        "  <meta charset=\"UTF-8\" />\n" +
                        "  <title>SignAndCrypt</title>\n" +
                        "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"> </head>")
                .append("<body>")
                .append(format(pattern, "ОГРН", ogrn))
                .append(format(pattern, "ЭЛН", eln))
                .append(format(pattern, "СНИЛС", snils))
                .toString();
    }
}
