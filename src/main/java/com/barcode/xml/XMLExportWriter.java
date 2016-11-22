package com.barcode.xml;

import java.util.*;
import org.xml.sax.*;

/**
 * An interface which represents a writer for exporting XML text.  This
 * writer interface is used by various classes to exporting XML.  For example,
 * the writeXML() method of the XMLExportable interface is passed with an
 * object of this interface to produce XML output.  This interface hides the
 * implementation that performs the actual XML formatting.  A possible
 * implementation is to use the IO stream API to output the XML text.  A more
 * sophisticated approach could be based on the XML Transform API to produce
 * XML output.<p>
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 *
 * @see     XMLExportable
 * @see     XMLExporter
 *
 */
public interface XMLExportWriter {

    /**
     * Returns the XML exporter resolver assoicated with this export writer.
     * The exporter resolver can be used to determine the exporter object.
     * @return  the exporter resolver associated with this export writer
     */
    public XMLExporterResolver getExporterResolver();

    /**
     * Writes an XML document for a specified object.  This method initiates
     * the entire process of exporting XML for an object.  Note that, this
     * method produces an entire XML document, not part of an XML document.
     * This method should not be called within in the writeXML() method of
     * XMLExportable or XMLExporter.  A default root element name of the
     * XML document is given either by the underlying XMLExportable or
     * XMLExporter objects.
     * @param   object  the object whose XML text representation is to be
     *          exported
     */
    public void writeXML(Object object) throws XMLException;

    /**
     * Writes an XML document for a specified object.  This method initiates
     * the entire process of exporting XML for an object.  Note that, this
     * method produces an entire XML document, not part of an XML document.
     * This method should not be called within in the writeXML() method of
     * XMLExportable or XMLExporter.  The root element of the XML document
     * is specified by the name argument.  If it is null a default is given
     * by the underlying XMLExportable or XMLExporter objects.
     *
     * @param   object  the object whose XML text representation is to be
     *          exported
     */
    public void writeXML(Object object, String name) throws XMLException;

    /**
     * Starts exporting a composite element with the specified name.
     * The start tag of the element is written to output.
     * @param   name    the tag name of the element
     */
    public void startElement(String name) throws SAXException;

    /**
     * Starts exporting an element with the specified name and attributes.
     * The start tag and the attributes of the element are written to
     * the output.
     * @param   name    the tag name of the element
     * @param   attributes the attributes of the element
     */
    public void startElement(String name, Attributes attributes) throws SAXException;

    /**
     * Writes the XML representation of a specified object as an XML element
     * with no attributes associated.  This method typically uses the
     * following strategy to output the XML
     * contents:<br>
     * 1) If the specified object is of the standard basic type, the object
     * is written as a simple text srepresentation of the object (for Date
     * object a predefined format is used).  The start and the end element
     * tags are written to enclose the text string.<br>
     * 2) If the specified object is not of basic type, the exporter resolver
     * is consulted to find if an exporter can resolved from the class of
     * the object.  If an exporter is resolved, the writeXML() method of the
     * exporter is called to output the XML for the object.<br>
     * 3) If no exporter can be resolved, the object is checked if it
     * implements the XMLExportable interface.  If it does so, its writeXML()
     * method is called to output the XML.
     * 4) If none of the above step is able to be executed, the object is
     * considered not exportable, and an SAXException is thrown.
     * @param   name    the name of the XML element
     * @param   object  the object to be exported as XML
     */
    public void writeElement(String name, Object object) throws SAXException;

    /**
     * Writes the XML representation of a specified object as an XML element
     * with the specified attributes.
     * @param   name    the name of the XML element
     * @param   object  the object to be exported as XML
     * @param   attributes the attributes associated with the XML element
     */
    public void writeElement(String name, Object object, Attributes attributes) throws SAXException;

    /**
     * Writes the XML representation of a specified string value as an
     * XML element.
     * @param   name    the name of the XML element
     * @param   value   the string value to be exported as XML
     */
    public void writeElement(String name, String value) throws SAXException;

    /**
     * Writes the XML representation of a specified string value and attributes
     * as an XML element.
     * @param   name    the name of the XML element
     * @param   value   the string value to be exported as XML
     * @param   attributes the attributes associated with the XML element
     */
    public void writeElement(String name, String value, Attributes attributes) throws SAXException;

    /**
     * Ends the export of a composite element.  The end tag of the element
     * is written to the output.
     * @param   name the name of the XML document
     */
    public void endElement(String name) throws SAXException;

    /**
     * Writes the content value of an element.  This method is useful when
     * the start of an element and its value need to be written separately.  The
     * following two code fragments are equivalent:
     * <pre>
     * 1)       startElement("EmployeeName");
     *          write("John Smith");
     *          endElement("EmployeeName");
     *
     * 2)       writeElement("EmployeeName", "John Smith");
     * </pre>
     * @param   value the value of the element
     */
    public void write(String value) throws SAXException;

    /**
     * Returns the current element path that this writer is currently writing.
     * Path components are separated by a "/" character.  Each path component
     * represents an element tag name.  For example, the path "person/address/city"
     * represents the "city" element in the following XML document:
     * <pre>
     *  &lt;person&gt;
     *      &lt;address&gt;
     *          &lt;city&gt;
     *          ...
     *          &lt;/city&gt;
     *      &lt;/address&gt;
     *  &lt;/person&gt;
     * </pre>
     * @return  the current element path
     */
    public String getPath();

}

