
package com.rentit.soap.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statuses.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="statuses">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PANDING"/>
 *     &lt;enumeration value="PAID"/>
 *     &lt;enumeration value="REJECTED"/>
 *     &lt;enumeration value="CANCELED"/>
 *     &lt;enumeration value="ACCEPTED"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "statuses")
@XmlEnum
public enum Statuses {

    PANDING,
    PAID,
    REJECTED,
    CANCELED,
    ACCEPTED;

    public String value() {
        return name();
    }

    public static Statuses fromValue(String v) {
        return valueOf(v);
    }

}
