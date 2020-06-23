package com.example.lyy.util.xml;


import com.example.lyy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "Message")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "head",
        "body"
})
/**
 * Xml.set(new Xml.Head())
 */
@XmlSeeAlso({Xml.class})
public class Xml {

    @XmlElement(name = "Head", required = true)
    protected Xml.Head head;

    @XmlElement(name = "Body", required = true)
    protected Xml.Body body;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "responseCode",
            "responseMsg"
    })
    @XmlSeeAlso({Head.class})
    public static class Head{
        @XmlElement(name = "ResponseCode", required = true)
        protected String responseCode;

        @XmlElement(name = "responseMsg", required = true)
        protected String responseMsg;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "object"
    })
    @XmlSeeAlso({Body.class})
    public static class Body{
        @XmlElement(name = "Object", required = true)
        private User object;
    }
}
