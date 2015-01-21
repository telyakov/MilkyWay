
package milkyway.soap;

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
 *         &lt;element name="FlatsGrammGetResult" type="{http://tempuri.org/}ArrayOfFlat" minOccurs="0"/>
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
    "flatsGrammGetResult"
})
@XmlRootElement(name = "FlatsGrammGetResponse")
public class FlatsGrammGetResponse {

    @XmlElement(name = "FlatsGrammGetResult")
    protected ArrayOfFlat flatsGrammGetResult;

    /**
     * Gets the value of the flatsGrammGetResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFlat }
     *     
     */
    public ArrayOfFlat getFlatsGrammGetResult() {
        return flatsGrammGetResult;
    }

    /**
     * Sets the value of the flatsGrammGetResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFlat }
     *     
     */
    public void setFlatsGrammGetResult(ArrayOfFlat value) {
        this.flatsGrammGetResult = value;
    }

}
