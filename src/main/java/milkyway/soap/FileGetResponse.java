
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
 *         &lt;element name="FileGetResult" type="{http://tempuri.org/}ArrayOfFileModel" minOccurs="0"/>
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
    "fileGetResult"
})
@XmlRootElement(name = "FileGetResponse")
public class FileGetResponse {

    @XmlElement(name = "FileGetResult")
    protected ArrayOfFileModel fileGetResult;

    /**
     * Gets the value of the fileGetResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFileModel }
     *     
     */
    public ArrayOfFileModel getFileGetResult() {
        return fileGetResult;
    }

    /**
     * Sets the value of the fileGetResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFileModel }
     *     
     */
    public void setFileGetResult(ArrayOfFileModel value) {
        this.fileGetResult = value;
    }

}
