
package milkyway.soap;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="ExecResult" type="{http://tempuri.org/}ArrayOfDI" minOccurs="0"/>
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
    "execResult"
})
@XmlRootElement(name = "ExecResponse")
public class ExecResponse {

    @XmlElement(name = "ExecResult")
    protected ArrayOfDI execResult;

    /**
     * Gets the value of the execResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDI }
     *     
     */
    public ArrayOfDI getExecResult() {
        return execResult;
    }

    /**
     * Sets the value of the execResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDI }
     *     
     */
    public void setExecResult(ArrayOfDI value) {
        this.execResult = value;
    }

}
