
package milkyway.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="securityKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "securityKey",
    "phoneID"
})
@XmlRootElement(name = "UserIdByPhoneIDGet")
public class UserIdByPhoneIDGet {

    protected String securityKey;
    protected String phoneID;

    /**
     * Gets the value of the securityKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityKey() {
        return securityKey;
    }

    /**
     * Sets the value of the securityKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityKey(String value) {
        this.securityKey = value;
    }

    /**
     * Gets the value of the phoneID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneID() {
        return phoneID;
    }

    /**
     * Sets the value of the phoneID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneID(String value) {
        this.phoneID = value;
    }

}
