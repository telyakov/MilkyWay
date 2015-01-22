
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
 *         &lt;element name="UserIdByPhoneIDGetResult" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "userIdByPhoneIDGetResult"
})
@XmlRootElement(name = "UserIdByPhoneIDGetResponse")
public class UserIdByPhoneIDGetResponse {

    @XmlElement(name = "UserIdByPhoneIDGetResult")
    protected int userIdByPhoneIDGetResult;

    /**
     * Gets the value of the userIdByPhoneIDGetResult property.
     * 
     */
    public int getUserIdByPhoneIDGetResult() {
        return userIdByPhoneIDGetResult;
    }

    /**
     * Sets the value of the userIdByPhoneIDGetResult property.
     * 
     */
    public void setUserIdByPhoneIDGetResult(int value) {
        this.userIdByPhoneIDGetResult = value;
    }

}
