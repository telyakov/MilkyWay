
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
 *         &lt;element name="GetCompressedImageResult" type="{http://tempuri.org/}ArrayOfFileModel" minOccurs="0"/>
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
    "getCompressedImageResult"
})
@XmlRootElement(name = "GetCompressedImageResponse")
public class GetCompressedImageResponse {

    @XmlElement(name = "GetCompressedImageResult")
    protected ArrayOfFileModel getCompressedImageResult;

    /**
     * Gets the value of the getCompressedImageResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFileModel }
     *     
     */
    public ArrayOfFileModel getGetCompressedImageResult() {
        return getCompressedImageResult;
    }

    /**
     * Sets the value of the getCompressedImageResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFileModel }
     *     
     */
    public void setGetCompressedImageResult(ArrayOfFileModel value) {
        this.getCompressedImageResult = value;
    }

}
