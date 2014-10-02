
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
 *         &lt;element name="ExecMultiplyResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "execMultiplyResult"
})
@XmlRootElement(name = "ExecMultiplyResponse")
public class ExecMultiplyResponse {

    @XmlElement(name = "ExecMultiplyResult")
    protected boolean execMultiplyResult;

    /**
     * Gets the value of the execMultiplyResult property.
     * 
     */
    public boolean isExecMultiplyResult() {
        return execMultiplyResult;
    }

    /**
     * Sets the value of the execMultiplyResult property.
     * 
     */
    public void setExecMultiplyResult(boolean value) {
        this.execMultiplyResult = value;
    }

}
