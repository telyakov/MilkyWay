
package com.corundumstudio.socketio.milkyway.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Flat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Flat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HouseID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="HouseName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SectionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlatTypeID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StatusID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Laying" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="KitchenSQR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BalconySQR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoggiaSQR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Rooms" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="recNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="changeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="FlatOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FlatsTypesName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="points" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fileDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="CanSale" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flat", propOrder = {
    "id",
    "houseID",
    "houseName",
    "sectionName",
    "level",
    "number",
    "flatTypeID",
    "statusID",
    "laying",
    "quantity",
    "kitchenSQR",
    "balconySQR",
    "loggiaSQR",
    "price",
    "rooms",
    "recNumber",
    "changeDate",
    "flatOrder",
    "flatsTypesName",
    "points",
    "fileID",
    "fileDateTime",
    "canSale"
})
public class Flat {

    protected int id;
    @XmlElement(name = "HouseID")
    protected int houseID;
    @XmlElement(name = "HouseName")
    protected String houseName;
    @XmlElement(name = "SectionName")
    protected String sectionName;
    @XmlElement(name = "Level")
    protected int level;
    @XmlElement(name = "Number")
    protected String number;
    @XmlElement(name = "FlatTypeID")
    protected int flatTypeID;
    @XmlElement(name = "StatusID")
    protected int statusID;
    @XmlElement(name = "Laying")
    protected String laying;
    @XmlElement(name = "Quantity")
    protected double quantity;
    @XmlElement(name = "KitchenSQR")
    protected String kitchenSQR;
    @XmlElement(name = "BalconySQR")
    protected String balconySQR;
    @XmlElement(name = "LoggiaSQR")
    protected String loggiaSQR;
    @XmlElement(name = "Price")
    protected double price;
    @XmlElement(name = "Rooms")
    protected int rooms;
    protected int recNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar changeDate;
    @XmlElement(name = "FlatOrder")
    protected String flatOrder;
    @XmlElement(name = "FlatsTypesName")
    protected String flatsTypesName;
    protected String points;
    protected int fileID;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fileDateTime;
    @XmlElement(name = "CanSale")
    protected int canSale;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the houseID property.
     * 
     */
    public int getHouseID() {
        return houseID;
    }

    /**
     * Sets the value of the houseID property.
     * 
     */
    public void setHouseID(int value) {
        this.houseID = value;
    }

    /**
     * Gets the value of the houseName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseName() {
        return houseName;
    }

    /**
     * Sets the value of the houseName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseName(String value) {
        this.houseName = value;
    }

    /**
     * Gets the value of the sectionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * Sets the value of the sectionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSectionName(String value) {
        this.sectionName = value;
    }

    /**
     * Gets the value of the level property.
     * 
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     * 
     */
    public void setLevel(int value) {
        this.level = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }

    /**
     * Gets the value of the flatTypeID property.
     * 
     */
    public int getFlatTypeID() {
        return flatTypeID;
    }

    /**
     * Sets the value of the flatTypeID property.
     * 
     */
    public void setFlatTypeID(int value) {
        this.flatTypeID = value;
    }

    /**
     * Gets the value of the statusID property.
     * 
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * Sets the value of the statusID property.
     * 
     */
    public void setStatusID(int value) {
        this.statusID = value;
    }

    /**
     * Gets the value of the laying property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLaying() {
        return laying;
    }

    /**
     * Sets the value of the laying property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLaying(String value) {
        this.laying = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(double value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the kitchenSQR property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKitchenSQR() {
        return kitchenSQR;
    }

    /**
     * Sets the value of the kitchenSQR property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKitchenSQR(String value) {
        this.kitchenSQR = value;
    }

    /**
     * Gets the value of the balconySQR property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalconySQR() {
        return balconySQR;
    }

    /**
     * Sets the value of the balconySQR property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalconySQR(String value) {
        this.balconySQR = value;
    }

    /**
     * Gets the value of the loggiaSQR property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggiaSQR() {
        return loggiaSQR;
    }

    /**
     * Sets the value of the loggiaSQR property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggiaSQR(String value) {
        this.loggiaSQR = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(double value) {
        this.price = value;
    }

    /**
     * Gets the value of the rooms property.
     * 
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * Sets the value of the rooms property.
     * 
     */
    public void setRooms(int value) {
        this.rooms = value;
    }

    /**
     * Gets the value of the recNumber property.
     * 
     */
    public int getRecNumber() {
        return recNumber;
    }

    /**
     * Sets the value of the recNumber property.
     * 
     */
    public void setRecNumber(int value) {
        this.recNumber = value;
    }

    /**
     * Gets the value of the changeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChangeDate() {
        return changeDate;
    }

    /**
     * Sets the value of the changeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChangeDate(XMLGregorianCalendar value) {
        this.changeDate = value;
    }

    /**
     * Gets the value of the flatOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlatOrder() {
        return flatOrder;
    }

    /**
     * Sets the value of the flatOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlatOrder(String value) {
        this.flatOrder = value;
    }

    /**
     * Gets the value of the flatsTypesName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlatsTypesName() {
        return flatsTypesName;
    }

    /**
     * Sets the value of the flatsTypesName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlatsTypesName(String value) {
        this.flatsTypesName = value;
    }

    /**
     * Gets the value of the points property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoints() {
        return points;
    }

    /**
     * Sets the value of the points property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoints(String value) {
        this.points = value;
    }

    /**
     * Gets the value of the fileID property.
     * 
     */
    public int getFileID() {
        return fileID;
    }

    /**
     * Sets the value of the fileID property.
     * 
     */
    public void setFileID(int value) {
        this.fileID = value;
    }

    /**
     * Gets the value of the fileDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFileDateTime() {
        return fileDateTime;
    }

    /**
     * Sets the value of the fileDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFileDateTime(XMLGregorianCalendar value) {
        this.fileDateTime = value;
    }

    /**
     * Gets the value of the canSale property.
     * 
     */
    public int getCanSale() {
        return canSale;
    }

    /**
     * Sets the value of the canSale property.
     * 
     */
    public void setCanSale(int value) {
        this.canSale = value;
    }

}
