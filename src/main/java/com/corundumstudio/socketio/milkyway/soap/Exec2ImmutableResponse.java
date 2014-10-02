
package com.corundumstudio.socketio.milkyway.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Exec2_ImmutableResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "exec2ImmutableResult"
})
@XmlRootElement(name = "Exec2_ImmutableResponse")
public class Exec2ImmutableResponse {

    @XmlElement(name = "Exec2_ImmutableResult")
    protected ArrayOfString exec2ImmutableResult;

    /**
     * Gets the value of the exec2ImmutableResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getExec2ImmutableResult() {
        return exec2ImmutableResult;
    }

    /**
     * Sets the value of the exec2ImmutableResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setExec2ImmutableResult(ArrayOfString value) {
        this.exec2ImmutableResult = value;
    }

}
