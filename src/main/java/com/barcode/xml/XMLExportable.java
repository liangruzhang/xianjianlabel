package com.barcode.xml;

import org.xml.sax.*;

/**
 * An interface which represents an object which is capable of being exported
 * as an XML file.  It is the responsibility of an object class that implements
 * this interface to provide an implementation of the writeXML() method that
 * exports the XML text.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 *
 * @see     XMLExporter
 * @see     XMLExportWriter
 */
public interface XMLExportable {

    /**
     * Writes the XML representation of this object.  Note that, when this
     * object is written to an XML file, it could be the root element or any
     * element embedded within an XML file.  Implementation of this mehtod
     * must use the XMLExportWriter object avaiable in the parameter list
     * to write the XML text.  The "name" parameter is used to specify the
     * element name of this object when it appears in the XML.
     * The reason for this is that it is usually the responsibility of
     * the enclosing object that determines the element name of this object to
     * appear in the XML file.  A common practice, is that if the name argument
     * is not null, this method uses it to write the element name of this
     * object.  If the "name" parameter is null, this method should use a
     * default value as the element name.  For example, the writeXML() is
     * typicall written as follows:
     * <pre>
     *      public void writeXML(XMLExportWriter writer, String name) {
     *          name = (name != null) ? name : "aDefaultName";
     *          ...
     *          ...
     *      }
     * </pre>
     *
     * @param   writer the XML export writer which is used by this object to
     *          write output to the XML file
     * @param   name the element name of this object; if it is not null, the
     *          method will write it as the element name of this object; if
     *          it is null, the method will provide a defaul element name for
     *          itself.
     * @exception   SAXException if the method fails to generate XML text for
     *          this method.
     */
    public void writeXML(XMLExportWriter writer, String name) throws SAXException;

}
