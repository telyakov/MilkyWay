<?xml version="1.0" encoding="utf-8"?>
<wsdl:definitions xmlns:s="http://www.w3.org/2001/XMLSchema" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:http="http://schemas.xmlsoap.org/wsdl/http/" xmlns:mime="http://schemas.xmlsoap.org/wsdl/mime/" xmlns:tns="http://tempuri.org/" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:tm="http://microsoft.com/wsdl/mime/textMatching/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" targetNamespace="http://tempuri.org/" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">
  <wsdl:types>
    <s:schema elementFormDefault="qualified" targetNamespace="http://tempuri.org/">
      <s:element name="FileGet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="id" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FileGetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FileGetResult" type="tns:ArrayOfFileModel" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfFileModel">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="FileModel" nillable="true" type="tns:FileModel" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="FileModel">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="Id" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Name" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FilePartID" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="FileData" type="s:base64Binary" />
          <s:element minOccurs="0" maxOccurs="1" name="FileExt" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="ChangeDate" type="s:dateTime" />
        </s:sequence>
      </s:complexType>
      <s:element name="GetCompressedImage">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="id" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="GetCompressedImageResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="GetCompressedImageResult" type="tns:ArrayOfFileModel" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ObjectsChildGet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="objectID" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ObjectsChildGetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ObjectsChildGetResult" type="tns:ArrayOfDirectoryItem" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfDirectoryItem">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="DirectoryItem" nillable="true" type="tns:DirectoryItem" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DirectoryItem">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="id" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="name" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="SectionsGet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="objectID" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="SectionsGetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="SectionsGetResult" type="tns:ArrayOfDirectoryItem" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FlatsGrammGet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="objectID" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="sectionID" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="FlatsGrammGetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="FlatsGrammGetResult" type="tns:ArrayOfFlat" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfFlat">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="Flat" nillable="true" type="tns:Flat" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="Flat">
        <s:sequence>
          <s:element minOccurs="1" maxOccurs="1" name="id" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="HouseID" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="HouseName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="SectionName" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Level" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Number" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="FlatTypeID" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="StatusID" type="s:int" />
          <s:element minOccurs="0" maxOccurs="1" name="Laying" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Quantity" type="s:double" />
          <s:element minOccurs="0" maxOccurs="1" name="KitchenSQR" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="BalconySQR" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="LoggiaSQR" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="Price" type="s:double" />
          <s:element minOccurs="1" maxOccurs="1" name="Rooms" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="recNumber" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="changeDate" type="s:dateTime" />
          <s:element minOccurs="0" maxOccurs="1" name="FlatOrder" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="FlatsTypesName" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="points" type="s:string" />
          <s:element minOccurs="1" maxOccurs="1" name="fileID" type="s:int" />
          <s:element minOccurs="1" maxOccurs="1" name="fileDateTime" type="s:dateTime" />
          <s:element minOccurs="1" maxOccurs="1" name="CanSale" type="s:int" />
        </s:sequence>
      </s:complexType>
      <s:element name="Exec">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sql" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fields" type="s:string" />
            <s:element minOccurs="1" maxOccurs="1" name="rowPerPage" type="s:int" />
            <s:element minOccurs="1" maxOccurs="1" name="page" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ExecResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ExecResult" type="tns:ArrayOfDI" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfDI">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="DI" nillable="true" type="tns:DI" />
        </s:sequence>
      </s:complexType>
      <s:complexType name="DI">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="1" name="K" type="s:string" />
          <s:element minOccurs="0" maxOccurs="1" name="V" />
        </s:sequence>
      </s:complexType>
      <s:element name="Exec2">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sql" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="userID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fields" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="rowPerPage" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="page" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="resultType" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:complexType name="ArrayOfString">
        <s:sequence>
          <s:element minOccurs="0" maxOccurs="unbounded" name="string" nillable="true" type="s:string" />
        </s:sequence>
      </s:complexType>
      <s:element name="Exec2Response">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Exec2Result" type="tns:ArrayOfString" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Exec2_Immutable">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sql" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="userID" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fields" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="rowPerPage" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="page" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="resultType" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="Exec2_ImmutableResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="Exec2_ImmutableResult" type="tns:ArrayOfString" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UserIdByPhoneIDGet">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="phoneID" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="UserIdByPhoneIDGetResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="UserIdByPhoneIDGetResult" type="s:int" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ExecScalar">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sql" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="userID" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ExecScalarResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="ExecScalarResult" type="s:string" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ExecMultiply">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sqlList" type="tns:ArrayOfString" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="ExecMultiplyResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="1" maxOccurs="1" name="ExecMultiplyResult" type="s:boolean" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AttachmentIns">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="securityKey" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="sql" type="s:string" />
            <s:element minOccurs="0" maxOccurs="1" name="fileData" type="s:base64Binary" />
          </s:sequence>
        </s:complexType>
      </s:element>
      <s:element name="AttachmentInsResponse">
        <s:complexType>
          <s:sequence>
            <s:element minOccurs="0" maxOccurs="1" name="AttachmentInsResult" type="tns:ArrayOfString" />
          </s:sequence>
        </s:complexType>
      </s:element>
    </s:schema>
  </wsdl:types>
  <wsdl:message name="FileGetSoapIn">
    <wsdl:part name="parameters" element="tns:FileGet" />
  </wsdl:message>
  <wsdl:message name="FileGetSoapOut">
    <wsdl:part name="parameters" element="tns:FileGetResponse" />
  </wsdl:message>
  <wsdl:message name="GetCompressedImageSoapIn">
    <wsdl:part name="parameters" element="tns:GetCompressedImage" />
  </wsdl:message>
  <wsdl:message name="GetCompressedImageSoapOut">
    <wsdl:part name="parameters" element="tns:GetCompressedImageResponse" />
  </wsdl:message>
  <wsdl:message name="ObjectsChildGetSoapIn">
    <wsdl:part name="parameters" element="tns:ObjectsChildGet" />
  </wsdl:message>
  <wsdl:message name="ObjectsChildGetSoapOut">
    <wsdl:part name="parameters" element="tns:ObjectsChildGetResponse" />
  </wsdl:message>
  <wsdl:message name="SectionsGetSoapIn">
    <wsdl:part name="parameters" element="tns:SectionsGet" />
  </wsdl:message>
  <wsdl:message name="SectionsGetSoapOut">
    <wsdl:part name="parameters" element="tns:SectionsGetResponse" />
  </wsdl:message>
  <wsdl:message name="FlatsGrammGetSoapIn">
    <wsdl:part name="parameters" element="tns:FlatsGrammGet" />
  </wsdl:message>
  <wsdl:message name="FlatsGrammGetSoapOut">
    <wsdl:part name="parameters" element="tns:FlatsGrammGetResponse" />
  </wsdl:message>
  <wsdl:message name="ExecSoapIn">
    <wsdl:part name="parameters" element="tns:Exec" />
  </wsdl:message>
  <wsdl:message name="ExecSoapOut">
    <wsdl:part name="parameters" element="tns:ExecResponse" />
  </wsdl:message>
  <wsdl:message name="Exec2SoapIn">
    <wsdl:part name="parameters" element="tns:Exec2" />
  </wsdl:message>
  <wsdl:message name="Exec2SoapOut">
    <wsdl:part name="parameters" element="tns:Exec2Response" />
  </wsdl:message>
  <wsdl:message name="Exec2_ImmutableSoapIn">
    <wsdl:part name="parameters" element="tns:Exec2_Immutable" />
  </wsdl:message>
  <wsdl:message name="Exec2_ImmutableSoapOut">
    <wsdl:part name="parameters" element="tns:Exec2_ImmutableResponse" />
  </wsdl:message>
  <wsdl:message name="UserIdByPhoneIDGetSoapIn">
    <wsdl:part name="parameters" element="tns:UserIdByPhoneIDGet" />
  </wsdl:message>
  <wsdl:message name="UserIdByPhoneIDGetSoapOut">
    <wsdl:part name="parameters" element="tns:UserIdByPhoneIDGetResponse" />
  </wsdl:message>
  <wsdl:message name="ExecScalarSoapIn">
    <wsdl:part name="parameters" element="tns:ExecScalar" />
  </wsdl:message>
  <wsdl:message name="ExecScalarSoapOut">
    <wsdl:part name="parameters" element="tns:ExecScalarResponse" />
  </wsdl:message>
  <wsdl:message name="ExecMultiplySoapIn">
    <wsdl:part name="parameters" element="tns:ExecMultiply" />
  </wsdl:message>
  <wsdl:message name="ExecMultiplySoapOut">
    <wsdl:part name="parameters" element="tns:ExecMultiplyResponse" />
  </wsdl:message>
  <wsdl:message name="AttachmentInsSoapIn">
    <wsdl:part name="parameters" element="tns:AttachmentIns" />
  </wsdl:message>
  <wsdl:message name="AttachmentInsSoapOut">
    <wsdl:part name="parameters" element="tns:AttachmentInsResponse" />
  </wsdl:message>
  <wsdl:portType name="DirectorySoap">
    <wsdl:operation name="FileGet">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Получить файл по ID</wsdl:documentation>
      <wsdl:input message="tns:FileGetSoapIn" />
      <wsdl:output message="tns:FileGetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="GetCompressedImage">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Получить сжатое изображение по ID</wsdl:documentation>
      <wsdl:input message="tns:GetCompressedImageSoapIn" />
      <wsdl:output message="tns:GetCompressedImageSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ObjectsChildGet">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Корпуса</wsdl:documentation>
      <wsdl:input message="tns:ObjectsChildGetSoapIn" />
      <wsdl:output message="tns:ObjectsChildGetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="SectionsGet">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Секции</wsdl:documentation>
      <wsdl:input message="tns:SectionsGetSoapIn" />
      <wsdl:output message="tns:SectionsGetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="FlatsGrammGet">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Квартирограмма</wsdl:documentation>
      <wsdl:input message="tns:FlatsGrammGetSoapIn" />
      <wsdl:output message="tns:FlatsGrammGetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Exec">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Данные</wsdl:documentation>
      <wsdl:input message="tns:ExecSoapIn" />
      <wsdl:output message="tns:ExecSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Exec2">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Возвращает произвольные даные. &lt;br&gt;Возвращает массив строк. 1-количество колонок, 2-количество сраниц,3-количество свойств, дальше названия колонок(название, тип), затем значения.&lt;br&gt;Подсказка по преобразовынию типов: &lt;br&gt; Null = 'NULL'&lt;br&gt; true ='True'&lt;br&gt; false = 'False'&lt;br&gt; Формат даты: MM.dd.yyyy HH:mm:ss </wsdl:documentation>
      <wsdl:input message="tns:Exec2SoapIn" />
      <wsdl:output message="tns:Exec2SoapOut" />
    </wsdl:operation>
    <wsdl:operation name="Exec2_Immutable">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Возвращает произвольные даные. &lt;br&gt;Возвращает массив строк. 1-количество колонок, 2-количество сраниц,3-количество свойств, дальше названия колонок(название, тип), &lt;br&gt; В отличии от Exec2 в том, что кешируется результат на некоторое время</wsdl:documentation>
      <wsdl:input message="tns:Exec2_ImmutableSoapIn" />
      <wsdl:output message="tns:Exec2_ImmutableSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="UserIdByPhoneIDGet">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Авторизация</wsdl:documentation>
      <wsdl:input message="tns:UserIdByPhoneIDGetSoapIn" />
      <wsdl:output message="tns:UserIdByPhoneIDGetSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ExecScalar">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Возвращает единственное значение в результате выполнения sql - запроса</wsdl:documentation>
      <wsdl:input message="tns:ExecScalarSoapIn" />
      <wsdl:output message="tns:ExecScalarSoapOut" />
    </wsdl:operation>
    <wsdl:operation name="ExecMultiply">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Выполнение нескольких операции в рамках одной транзакции</wsdl:documentation>
      <wsdl:input message="tns:ExecMultiplySoapIn" />
      <wsdl:output message="tns:ExecMultiplySoapOut" />
    </wsdl:operation>
    <wsdl:operation name="AttachmentIns">
      <wsdl:documentation xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/">Загрузить файл на сервер</wsdl:documentation>
      <wsdl:input message="tns:AttachmentInsSoapIn" />
      <wsdl:output message="tns:AttachmentInsSoapOut" />
    </wsdl:operation>
  </wsdl:portType>
  <wsdl:binding name="DirectorySoap" type="tns:DirectorySoap">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="FileGet">
      <soap:operation soapAction="http://tempuri.org/FileGet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCompressedImage">
      <soap:operation soapAction="http://tempuri.org/GetCompressedImage" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ObjectsChildGet">
      <soap:operation soapAction="http://tempuri.org/ObjectsChildGet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SectionsGet">
      <soap:operation soapAction="http://tempuri.org/SectionsGet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FlatsGrammGet">
      <soap:operation soapAction="http://tempuri.org/FlatsGrammGet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec">
      <soap:operation soapAction="http://tempuri.org/Exec" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec2">
      <soap:operation soapAction="http://tempuri.org/Exec2" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec2_Immutable">
      <soap:operation soapAction="http://tempuri.org/Exec2_Immutable" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UserIdByPhoneIDGet">
      <soap:operation soapAction="http://tempuri.org/UserIdByPhoneIDGet" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ExecScalar">
      <soap:operation soapAction="http://tempuri.org/ExecScalar" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ExecMultiply">
      <soap:operation soapAction="http://tempuri.org/ExecMultiply" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AttachmentIns">
      <soap:operation soapAction="http://tempuri.org/AttachmentIns" style="document" />
      <wsdl:input>
        <soap:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:binding name="DirectorySoap12" type="tns:DirectorySoap">
    <soap12:binding transport="http://schemas.xmlsoap.org/soap/http" />
    <wsdl:operation name="FileGet">
      <soap12:operation soapAction="http://tempuri.org/FileGet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="GetCompressedImage">
      <soap12:operation soapAction="http://tempuri.org/GetCompressedImage" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ObjectsChildGet">
      <soap12:operation soapAction="http://tempuri.org/ObjectsChildGet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="SectionsGet">
      <soap12:operation soapAction="http://tempuri.org/SectionsGet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="FlatsGrammGet">
      <soap12:operation soapAction="http://tempuri.org/FlatsGrammGet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec">
      <soap12:operation soapAction="http://tempuri.org/Exec" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec2">
      <soap12:operation soapAction="http://tempuri.org/Exec2" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="Exec2_Immutable">
      <soap12:operation soapAction="http://tempuri.org/Exec2_Immutable" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="UserIdByPhoneIDGet">
      <soap12:operation soapAction="http://tempuri.org/UserIdByPhoneIDGet" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ExecScalar">
      <soap12:operation soapAction="http://tempuri.org/ExecScalar" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="ExecMultiply">
      <soap12:operation soapAction="http://tempuri.org/ExecMultiply" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
    <wsdl:operation name="AttachmentIns">
      <soap12:operation soapAction="http://tempuri.org/AttachmentIns" style="document" />
      <wsdl:input>
        <soap12:body use="literal" />
      </wsdl:input>
      <wsdl:output>
        <soap12:body use="literal" />
      </wsdl:output>
    </wsdl:operation>
  </wsdl:binding>
  <wsdl:service name="Directory">
    <wsdl:port name="DirectorySoap" binding="tns:DirectorySoap">
      <soap:address location="http://192.168.0.29:7001/Directory.asmx" />
    </wsdl:port>
    <wsdl:port name="DirectorySoap12" binding="tns:DirectorySoap12">
      <soap12:address location="http://192.168.0.29:7001/Directory.asmx" />
    </wsdl:port>
  </wsdl:service>
</wsdl:definitions>