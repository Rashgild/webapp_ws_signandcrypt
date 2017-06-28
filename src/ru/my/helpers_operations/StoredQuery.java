package ru.my.helpers_operations;

import java.text.DateFormat;
import java.util.Date;

public class StoredQuery {

    public static String PrParse_Query1(String disabilityId)
    { return "select\n" +
                "dd.issuedate, \n" +
                "dd.id as DDID,    \n" +
                "dd.patient_id as DD_PAT, \n" +
                "dc.patient_id as DC_PAT,\n" +
                "p.snils as SNILS,\n" +
                "p.lastname as SURNAME,\n" +
                "p.firstname as NAME,  \n" +
                "p.middlename as PATRONIMIC\n" +
                ",case when (dc.placementservice is not null or dc.placementservice ='1') then '1' else '0' end as BOZ_FLAG \n" +
                ",dd.job as LPU_EMPLOYER  \n" +
                ",case when (dd.workcombotype_id is null) then '1' else '0' end as LPU_EMPL_FLAG \n" +
                ",dd.number as LN_CODE \n" +
                ",(select dd2.number from disabilitydocument dd2 where dd2.id = dd.prevdocument_id) as PREV_LN \n" +
                ",case when (vddp.code ='2') then '0' else '1' end as PRIMARY_FLAG \n" +
                ",case when (select count(a.id) from disabilitydocument a where a.duplicate_id=dd.id) >0 then '1' else '0'end as DUPLICATE_FLAG\n" +
                ",dd.issuedate as LN_DATE\n" +
                ",lpu.name LPU_NAME\n" +
                ",lpu.printAddress as LPU_ADDRESS \n" +
                ",lpu.ogrn as LPU_OGRN\n" +
                ",p.birthday as BIRTHDAY    \n" +
                ",case when sex.omccode = '1' then '0' else '1' end as GENDER  \n" +
                ",vdr.codef as REASON1  \n" +
                ",vdr2.code as REASON2 \n" +
                ",vdr3.code as REASON3 \n" +
                ",mkb.code as DIAGNOS\n" +
                ",dd.mainworkdocumentnumber as PARENT_CODE \n" +
                ",dd.sanatoriumdatefrom as DATE1 \n" +
                ",dd.sanatoriumdateto as DATE2\n" +
                ",dd.sanatoriumticketnumber as VOUCHER_NO \n" +
                ",dd.sanatoriumogrn as VOUCHER_OGRN \n" +
                ",case when p1.id is not null and p1.id!=p.id then to_char(p1.birthday,'yyyy-MM-dd') else to_char(p12.birthday,'yyyy-MM-dd') end as SERV1_AGE\n" +
                ",case when p1.id is not null and p1.id!=p.id then vkr1.code else vkr1.oppositeRoleCode end as SERV1_RELATION_CODE\n" +
                ",case when p1.id is not null and p1.id!=p.id then p1.lastname||' '||p1.firstname||' '||p1.middlename else p12.lastname||' '||p12.firstname||' '||p12.middlename end as SERV1_FIO\n" +
                ",case when p2.id is not null and p2.id!=p.id then to_char(p2.birthday,'yyyy-MM-dd') else to_char(p22.birthday,'yyyy-MM-dd') end as SERV2_AGE \n" +
                ",case when p2.id is not null and p2.id!=p.id then vkr2.code else vkr2.oppositeRoleCode end as SERV2_RELATION_CODE\n" +
                ",case when p2.id is not null and p2.id!=p.id then p2.lastname||' '||p2.firstname||' '||p2.middlename else p22.lastname||' '||p22.firstname||' '||p22.middlename end as SERV2_FIO \n" +
                ",case when (dc.earlypregnancyregistration is not null or dc.earlypregnancyregistration ='1') then '1' else '' end as PREGN12W_FLAG  \n" +
                ",dd.hospitalizedfrom as HOSPITAL_DT1  \n" +
                ",dd.hospitalizedto as HOSPITAL_DT2  \n" +
                ",vddcr.name as CLOSE_REASON \n" +
                ",mss.assignmentdate as MSE_DT1 \n" +
                ",mss.registrationdate as MSE_DT2\n" +
                ",mss.examinationdate as MSE_DT3\n" +
                ",dd.status_id as LN_STATE\n" +
                ",rvr.datefrom as HOSPITAL_BREACH_DT           \n" +
                ",rvr.regimeviolationtype_id as HOSPITAL_BREACH_CODE\n" +
                ",coalesce(vddcr.codef,'') as MSE_RESULT  \n" +
                ",dd.otherclosedate as other_state_dt\n" +
                ",dd3.number as NEXT_LN_CODE\n" +
                "from disabilitydocument dd\n" +
                "left join vocdisabilitydocumentclosereason vddcr on vddcr.id = dd.closereason_id \n" +
                "left join disabilitydocument dd3 on dd3.prevdocument_id=dd.id \n" +
                "left join regimeviolationrecord rvr on rvr.disabilitydocument_id = dd.id\n" +
                "left join disabilitycase dc on dc.id=dd.disabilitycase_id \n" +
                "left join patient p on p.id=dc.patient_id \n" +
                "left join vocdisabilitydocumentprimarity vddp on vddp.id=dd.primarity_id\n" +
                "left join vocsex sex on sex.id=p.sex_id \n" +
                "left join vocdisabilityreason vdr on vdr.id=dd.disabilityreason_id \n" +
                "left join vocdisabilityreason2 vdr2 on vdr2.id=dd.disabilityreason2_id\n" +
                "left join vocdisabilityreason vdr3 on vdr3.id=dd.disabilityreasonchange_id\n" +
                "left join vocidc10 mkb on mkb.id=dd.idc10final_id  \n" +
                "left join kinsman k1 on k1.id=dc.nursingperson1_id \n" +
                "left join vockinsmanrole vkr1 on vkr1.id=k1.kinsmanrole_id \n" +
                "left join patient p1 on p1.id=k1.kinsman_id \n" +
                "left join patient p12 on p12.id=k1.person_id \n" +
                "left join kinsman k2 on k2.id=dc.nursingperson2_id \n" +
                "left join vockinsmanrole vkr2 on vkr2.id=k2.kinsmanrole_id \n" +
                "left join patient p2 on p2.id=k2.kinsman_id \n" +
                "left join patient p22 on p22.id=k2.person_id\n" +
                "left join medsoccommission mss on mss.disabilitydocument_id=dd.id \n" +
                "left join mislpu lpu on lpu.id=(select cast(keyvalue as int)from softconfig  where key  ='DEFAULT_LPU')\n" +
                "where dd3.number is null and\n" +
                "p.snils is not null and p.snils != '' \n" +
                "and dd.id = "+disabilityId;
    }

    public static String PrParse_Query2(String disabilityId){
        return "select\n" +
                "dd.id as DDID,    \n" +
                "p.lastname as SURNAME,\n" +
                "p.firstname as NAME,  \n" +
                "p.middlename as PATRONIMIC\n" +
                ",disrec.datefrom as TREAT_DT1 \n" +
                ",disrec.dateto as TREAT_DT2\n" +
                ",vwf.name as TREAT_DOCTOR_ROLE\n" +
                ",docname.lastname ||' '|| docname.firstname ||' '|| docname.middlename as TREAT_DOCTOR \n" +
                ",vwf2.name as TREAT_CHAIRMAN_ROLE\n" +
                ",vkname.lastname ||' '|| vkname.firstname ||' '|| vkname.middlename as TREAT_CHAIRMAN\n" +
                "from disabilitydocument dd\n" +
                "left join disabilitycase dc on dc.id=dd.disabilitycase_id \n" +
                "left join patient p on p.id=dc.patient_id left join disabilityrecord disrec on disrec.disabilitydocument_id = dd.id\n" +
                "left join workfunction wf on wf.id = disrec.workfunction_id \n" +
                "left join worker w on w.id = wf.worker_id\n" +
                "left join patient docname on docname.id = w.person_id \n" +
                "left join VocWorkFunction vwf on vwf.id = wf.workFunction_id\n" +
                "left join workfunction wf2 on wf2.id = disrec.workfunctionadd_id\n" +
                "left join worker w2 on w2.id = wf2.worker_id\n" +
                "left join patient vkname on vkname.id = w2.person_id\n" +
                "left join VocWorkFunction vwf2 on vwf2.id = wf2.workFunction_id\n" +
                "where dd.id = "+disabilityId;
    }

    protected static String QueryToSave(String result, Integer status)
    {
        return "INSERT INTO exportfsslog"+
                "(result, responsecode, status, disabilitydocument, disabilitynumber, requestcode, requestdate, requesttime, requesttype)" +
                "VALUES"+
                "('"+result+"','"+GlobalVariables.Response+"','"
                +status+"', "+GlobalVariables.DisabilityDocument_id+", '"
                +GlobalVariables.t_ELN+"','"+GlobalVariables.Request+"', current_date, current_time, '"
                +GlobalVariables.Type+"') RETURNING id;";
    }

    public static String SaveNumber(String number){
        Date curTime = new Date();
        DateFormat dtfrm = DateFormat.getDateInstance();
        String dateTime = dtfrm.format(curTime);

        return "INSERT INTO electronicdisabilitydocumentnumber\n" +
                "(number, createdate) values ('"+number+"','"+dateTime+"');";
    }


}
