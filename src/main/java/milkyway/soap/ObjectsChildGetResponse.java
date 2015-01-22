
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
 *         &lt;element name="ObjectsChildGetResult" type="{http://tempuri.org/}ArrayOfDirectoryItem" minOccurs="0"/>
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
    "objectsChildGetResult"
})
@XmlRootElement(name = "ObjectsChildGetResponse")
public class ObjectsChildGetResponse {

    @XmlElement(name = "ObjectsChildGetResult")
    protected ArrayOfDirectoryItem objectsChildGetResult;

    /**
     * Gets the value of the objectsChildGetResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDirectoryItem }
     *     
     */
    public ArrayOfDirectoryItem getObjectsChildGetResult() {
        return objectsChildGetResult;
    }

    /**
     * Sets the value of the objectsChildGetResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDirectoryItem }
     *     
     */
    public void setObjectsChildGetResult(ArrayOfDirectoryItem value) {
        this.objectsChildGetResult = value;
    }

}
