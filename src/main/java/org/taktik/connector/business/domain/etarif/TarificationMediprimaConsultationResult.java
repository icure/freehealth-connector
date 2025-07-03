package org.taktik.connector.business.domain.etarif;

import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENT;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDCONTENTschemes;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEM;
import be.fgov.ehealth.standards.kmehr.cd.v1.CDITEMschemes;
import be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;
import be.fgov.ehealth.standards.kmehr.schema.v1.*;
import org.taktik.freehealth.middleware.dto.mycarenet.CommonOutput;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetConversation;
import org.taktik.freehealth.middleware.dto.mycarenet.MycarenetError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarificationMediprimaConsultationResult {
    private CommonOutput commonOutput;
    private MycarenetConversation mycarenetConversation;
    private Patient patient;
    private Author author;

    private List<CodeResult> codeResults = new ArrayList<>();
    private List<MycarenetError> errors = new ArrayList<>();

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public CommonOutput getCommonOutput() {
        return commonOutput;
    }

    public void setCommonOutput(CommonOutput commonOutput) {
        this.commonOutput = commonOutput;
    }

    public MycarenetConversation getMycarenetConversation() {
        return mycarenetConversation;
    }

    public void setMycarenetConversation(MycarenetConversation mycarenetConversation) {
        this.mycarenetConversation = mycarenetConversation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<CodeResult> getCodeResults() {
        return codeResults;
    }

    public void setCodeResults(List<CodeResult> codeResults) {
        this.codeResults = codeResults;
    }

    public List<MycarenetError> getErrors() {
        return errors;
    }

    public void setErrors(List<MycarenetError> errors) {
        this.errors = errors;
    }

    public static class Patient implements Serializable{
        private String niss;
        private String firstName;
        private String lastName;
        private String sex;
        private InsurancyStatus insurancyStatus;
        private InsurancyMembership insurancyMembership;

        public String getNiss() {
            return niss;
        }

        public void setNiss(String niss) {
            this.niss = niss;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public InsurancyStatus getInsurancyStatus() {
            return insurancyStatus;
        }

        public void setInsurancyStatus(InsurancyStatus insurancyStatus) {
            this.insurancyStatus = insurancyStatus;
        }

        public InsurancyMembership getInsurancyMembership() {
            return insurancyMembership;
        }

        public void setInsurancyMembership(InsurancyMembership insurancyMembership) {
            this.insurancyMembership = insurancyMembership;
        }
    }

    public static class InsurancyStatus implements Serializable{
        private String idInsurance;
        private String membership;
        private String cg1;
        private String cg2;

        public String getIdInsurance() {
            return idInsurance;
        }

        public void setIdInsurance(String idInsurance) {
            this.idInsurance = idInsurance;
        }

        public String getMembership() {
            return membership;
        }

        public void setMembership(String membership) {
            this.membership = membership;
        }

        public String getCg1() {
            return cg1;
        }

        public void setCg1(String cg1) {
            this.cg1 = cg1;
        }

        public String getCg2() {
            return cg2;
        }

        public void setCg2(String cg2) {
            this.cg2 = cg2;
        }
    }

    public static class InsurancyMembership implements Serializable {
        private String idInsurance;
        private String membership;

        public String getIdInsurance() {
            return idInsurance;
        }

        public void setIdInsurance(String idInsurance) {
            this.idInsurance = idInsurance;
        }

        public String getMembership() {
            return membership;
        }

        public void setMembership(String membership) {
            this.membership = membership;
        }
    }

    public static class CodeResult implements Serializable{
        private String code;
        private Date encouterDateTime;
        private Payment fee;
        private Payment reimbursementFpssi;
        private Payment reimbursementPswc;
        private Payment patientFee;
        private String financialContract;
        private Pswc pswc;
        private MediprimaNumber mediprimaNumber;
        private Boolean umc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Date getEncouterDateTime() {
            return encouterDateTime;
        }

        public void setEncouterDateTime(Date encouterDateTime) {
            this.encouterDateTime = encouterDateTime;
        }

        public Payment getFee() {
            return fee;
        }

        public void setFee(Payment fee) {
            this.fee = fee;
        }

        public Payment getReimbursementFpssi() {
            return reimbursementFpssi;
        }

        public void setReimbursementFpssi(Payment reimbursementFpssi) {
            this.reimbursementFpssi = reimbursementFpssi;
        }

        public Payment getReimbursementPswc() {
            return reimbursementPswc;
        }

        public void setReimbursementPswc(Payment reimbursementPswc) {
            this.reimbursementPswc = reimbursementPswc;
        }

        public Payment getPatientFee() {
            return patientFee;
        }

        public void setPatientFee(Payment patientFee) {
            this.patientFee = patientFee;
        }

        public String getFinancialContract() {
            return financialContract;
        }

        public void setFinancialContract(String financialContract) {
            this.financialContract = financialContract;
        }

        public Pswc getPswc() {
            return pswc;
        }

        public void setPswc(Pswc pswc) {
            this.pswc = pswc;
        }

        public MediprimaNumber getMediprimaNumber() {
            return mediprimaNumber;
        }

        public void setMediprimaNumber(MediprimaNumber mediprimaNumber) {
            this.mediprimaNumber = mediprimaNumber;
        }

        public Boolean getUmc() {
            return umc;
        }

        public void setUmc(Boolean umc) {
            this.umc = umc;
        }
    }

    public static class MediprimaNumber implements Serializable{
        private String number;
        private String version;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }

    public static class Pswc implements Serializable{
        private String cbe;
        private String content_fr;
        private String content_nl;

        public String getCbe() {
            return cbe;
        }

        public void setCbe(String cbe) {
            this.cbe = cbe;
        }

        public String getContent_fr() {
            return content_fr;
        }

        public void setContent_fr(String content_fr) {
            this.content_fr = content_fr;
        }

        public String getContent_nl() {
            return content_nl;
        }

        public void setContent_nl(String content_nl) {
            this.content_nl = content_nl;
        }
    }

    public static class Payment implements Serializable{
        private double amount;
        private String currency;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }
    }

    public static class Claim implements Serializable{
        private String code;
        private Author author;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Author getAuthor() {
            return author;
        }

        public void setAuthor(Author author) {
            this.author = author;
        }
    }

    public static class Author implements Serializable{
        private String nihii;
        private String inss;
        private String hcpType;
        private String firstName;
        private String lastName;

        public String getNihii() {
            return nihii;
        }

        public void setNihii(String nihii) {
            this.nihii = nihii;
        }

        public String getInss() {
            return inss;
        }

        public void setInss(String inss) {
            this.inss = inss;
        }

        public String getHcpType() {
            return hcpType;
        }

        public void setHcpType(String hcpType) {
            this.hcpType = hcpType;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public void fill(List<TransactionType> transactions) {
        for (TransactionType transaction : transactions) {
            TarificationMediprimaConsultationResult.CodeResult codeResult = new TarificationMediprimaConsultationResult.CodeResult();
            codeResults.add(codeResult);
            List<ItemType> items = transaction.getItem();

            for (ItemType item : items) {
                List<CDITEM> cds = item.getCds();

                for (CDITEM cd : cds) {
                    CostType cost = item.getCost();
                    // Fee
                    if (cd.getValue().equals("fee") && cost != null) {
                        codeResult.setFee(getPayment(cost));
                    }

                    // Reimbursements-fpssi
                    if (cd.getValue().equals("reimbursement-fpssi") && cost != null) {
                        codeResult.setReimbursementFpssi(getPayment(cost));
                    }

                    // Reimbursements-pswc
                    if (cd.getValue().equals("reimbursement-pswc") && cost != null) {
                        codeResult.setReimbursementPswc(getPayment(cost));
                    }

                    // Patient fees
                    if (cd.getValue().equals("patientfee") && cost != null) {
                        codeResult.setPatientFee(getPayment(cost));
                    }


                    // Financial contracts
                    if (cd.getValue().equals("financialcontract")) {
                        for (ContentType content : item.getContents()) {
                            for (IDKMEHR id : content.getIds()) {
                                codeResult.setFinancialContract(id.getValue());
                            }
                        }
                    }

                    //pswc
                    if(cd.getValue().equals("pswc")) {
                        Pswc pswc = new Pswc();
                        for (ContentType content : item.getContents()) {
                            for (IDKMEHR id : content.getIds()) {
                                if ("ID-CBE".equals(id.getS().value())) {
                                    pswc.setCbe(id.getValue());
                                }
                            }

                            for (TextType text : content.getTexts()) {
                                if ("fr".equalsIgnoreCase(text.getL())) {
                                    pswc.setContent_fr(text.getValue());
                                } else if ("nl".equalsIgnoreCase(text.getL())) {
                                    pswc.setContent_nl(text.getValue());
                                }
                            }
                        }
                        codeResult.setPswc(pswc);
                    }

                    //mediprima number
                    if (cd.getValue().equals("mediprimanumber")) {
                        for (IDKMEHR id : item.getIds()) {
                            if ("ID-MEDIPRIMA".equals(id.getSL())) {
                                MediprimaNumber number = new MediprimaNumber();
                                number.setNumber(id.getValue());
                                number.setVersion(id.getSV());
                                codeResult.setMediprimaNumber(number);
                            }
                        }
                    }

                    // umc
                    if (cd.getValue().equals("umc")) {
                        for (ContentType content : item.getContents()) {
                            if (content.isBoolean() != null) {
                                codeResult.setUmc(content.isBoolean());
                            }
                        }
                    }

                    //Encounter date time
                    if(cd.getValue().equals("encounterdatetime")){
                        for (ContentType content : item.getContents()) {
                            if (content.getDate() != null) {
                                codeResult.setEncouterDateTime(content.getDate().toGregorianCalendar().getTime());
                            }
                        }
                    }

                    // Codes
                    if (cd.getValue().equals("claim")) {
                        for (ContentType content : item.getContents()) {
                            for (CDCONTENT cdc : content.getCds()) {
                                if (cdc.getS().equals(CDCONTENTschemes.CD_NIHDI)) { codeResult.setCode(cdc.getValue()); }
                            }
                        }
                    }
                }
            }
        }
    }

    private TarificationMediprimaConsultationResult.Payment getPayment(CostType cost) {
        TarificationMediprimaConsultationResult.Payment fee = new TarificationMediprimaConsultationResult.Payment();
        if (cost.getDecimal() != null) {
            fee.setAmount(cost.getDecimal().doubleValue());
        }
        UnitType unit = cost.getUnit();
        if (unit != null && unit.getCd() != null && unit.getCd().getValue() != null) {
            fee.setCurrency(unit.getCd().getValue());
        }
        return fee;
    }

}
