package com.example.lyy.entity;

import lombok.ToString;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * @描述： 投保消息返回
 * @作者： lux
 * @创建日期： 2018-6-14 9:52
 * @版权： 江泰保险经纪股份有限公司
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "head",
        "body",
        "content"
})
@XmlRootElement(name = "Message")
@ToString
public class PolicyReturnMessage {

    /**
     * 返回头
     */
    @XmlElement(name = "Head", required = true)
    protected PolicyReturnMessage.Head head;

    /**
     * 返回体
     */
    @XmlElement(name = "Body", required = true)
    protected PolicyReturnMessage.Body body;

    @XmlElement(name = "Content", required = true)
    protected  String content;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "serialNo",
            "responseCode",
            "responseMessage"
    })
    @ToString
    public static class Head {
        /**
         * 签单发送的消息的流水号
         */
        @XmlElement(name = "SerialNo", required = true)
        protected String serialNo;
        /**
         * 出单返回码
         */
        @XmlElement(name = "ResponseCode", required = true)
        protected String responseCode;
        /**
         * 出单返回消息
         */
        @XmlElement(name = "ResponseMessage", required = true)
        protected String responseMessage;

        public String getSerialNo() {
            return serialNo;
        }

        public void setSerialNo(String serialNo) {
            this.serialNo = serialNo;
        }

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"policy"})
    @ToString
    public static class Body{

        @XmlElementWrapper(name = "Policies")
        @XmlElement(name= "Policy", required = true)
        private List<Policy> policy;

        public List<Policy> getPolicy() {
            return policy;
        }

        public void setPolicy(List<Policy> policy) {
            this.policy = policy;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "companyCode",
            "applicationFormCode",
            "companyAppCode",
            "policyNo",
            "startDate",
            "issueDate",
            "validateCode",
            "ePolicy",
            "eInvoice"
    })
    @ToString
    @XmlRootElement
    public static class Policy{
        /**
         * 保险公司编码
         */
        @XmlElement(name = "CompanyCode", required = true)
        protected String companyCode;
        /**
         * 投保单号
         */
        @XmlElement(name = "ApplicationFormCode", required = true)
        protected String applicationFormCode;
        /**
         * 投保单号
         */
        @XmlElement(name = "CompanyAppCode")
        protected String companyAppCode;
        /**
         * 保单号
         */
        @XmlElement(name = "PolicyNo", required = true)
        protected String policyNo;
        /**
         * 保单生效日期
         */
        @XmlElement(name = "StartDate", required = true)
        protected String startDate;
        /**
         * 保单签单日期
         */
        @XmlElement(name = "IssueDate", required = true)
        protected String issueDate;
        /**
         * 保单验证码
         */
        @XmlElement(name = "ValidateCode", required = true)
        protected String validateCode;
        /**
         * 电子保单
         */
        @XmlElement(name = "EPolicy", required = true)
        protected PolicyReturnMessage.EPolicy ePolicy;
        /**
         * 电子发票
         */
        @XmlElement(name = "EInvoice", required = true)
        protected PolicyReturnMessage.EInvoice eInvoice;

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getApplicationFormCode() {
            return applicationFormCode;
        }

        public void setApplicationFormCode(String applicationFormCode) {
            this.applicationFormCode = applicationFormCode;
        }

        public String getCompanyAppCode() {
            return companyAppCode;
        }

        public void setCompanyAppCode(String companyAppCode) {
            this.companyAppCode = companyAppCode;
        }

        public String getPolicyNo() {
            return policyNo;
        }

        public void setPolicyNo(String policyNo) {
            this.policyNo = policyNo;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate;
        }

        public String getValidateCode() {
            return validateCode;
        }

        public void setValidateCode(String validateCode) {
            this.validateCode = validateCode;
        }

        public EPolicy getePolicy() {
            return ePolicy;
        }

        public void setePolicy(EPolicy ePolicy) {
            this.ePolicy = ePolicy;
        }

        public EInvoice geteInvoice() {
            return eInvoice;
        }

        public void seteInvoice(EInvoice eInvoice) {
            this.eInvoice = eInvoice;
        }
    }

    @XmlType(name = "EPolicy", propOrder = {
            "url",
            "b64Content",
            "fileId",
            "contentFormat"
    })
    public static class EPolicy {
        /**
         * 文件id
         */
        @XmlElement(name = "FileId", required = true)
        protected String fileId;
        /**
         * 地址
         */
        @XmlElement(name = "URL", required = true)
        protected String url;
        /**
         * 保单内容
         */
        @XmlElement(name = "B64Content", required = true)
        protected String b64Content;
        /**
         * 内容格式
         */
        @XmlElement(name = "ContentFormat", required = true)
        protected String contentFormat;

        @XmlTransient
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @XmlTransient
        public String getFileId() {
            return fileId;
        }

        public void setFileId(String fileId) {
            this.fileId = fileId;
        }

        @XmlTransient
        public String getB64Content() {
            return b64Content;
        }

        public void setB64Content(String b64Content) {
            this.b64Content = b64Content;
        }

        @XmlTransient
        public String getContentFormat() {
            return contentFormat;
        }

        public void setContentFormat(String contentFormat) {
            this.contentFormat = contentFormat;
        }
    }

    @XmlType(name = "EInvoice", propOrder = {
            "url",
            "b64Content",
            "contentFormat"
    })
    public static class EInvoice {
        /**
         * 地址
         */
        @XmlElement(name = "URL", required = true)
        protected String url;
        /**
         * 发票内容
         */
        @XmlElement(name = "B64Content", required = true)
        protected String b64Content;
        /**
         * 内容格式
         */
        @XmlElement(name = "ContentFormat", required = true)
        protected String contentFormat;

        @XmlTransient
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @XmlTransient
        public String getB64Content() {
            return b64Content;
        }

        public void setB64Content(String b64Content) {
            this.b64Content = b64Content;
        }

        @XmlTransient
        public String getContentFormat() {
            return contentFormat;
        }

        public void setContentFormat(String contentFormat) {
            this.contentFormat = contentFormat;
        }
    }
}
