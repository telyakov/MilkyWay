
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
 *         &lt;element name="SectionsGetResult" type="{http://tempuri.org/}ArrayOfDirectoryItem" minOccurs="0"/>
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
    "sectionsGetResult"
})
@XmlRootElement(name = "SectionsGetResponse")
public class SectionsGetResponse {

    @XmlElement(name = "SectionsGetResult")
    protected ArrayOfDirectoryItem sectionsGetResult;

    /**
     * Gets the value of the sectionsGetResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDirectoryItem }
     *     
     */
    public ArrayOfDirectoryItem getSectionsGetResult() {
        return sectionsGetResult;
    }

    /**
     * Sets the value of the sectionsGetResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDirectoryItem }
     *     
     */
    public void setSectionsGetResult(ArrayOfDirectoryItem value) {
        this.sectionsGetResult = value;
    }

}
