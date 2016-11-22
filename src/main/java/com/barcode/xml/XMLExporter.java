package com.barcode.xml;

import java.util.*;
import org.xml.sax.*;

/**
 * An interface which represents an utility object which understands how to
 * export a given object to its XML representation.  In contrast to the
 * XMLExportable interface, it allows an object which does not implement the
 * XMLExportable interface to be able to export as an XML file.  In other
 * words, implementations of this interface know how to export certain object
 * classes without requiring those classes to implement the XMLExportable
 * interface.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 *
 * @see     XMLExportable
 * @see     XMLExportWriter
 */
public interface XMLExporter {

    /**
     * Writes the XML representation of a specified object using the specified
     * XML export writer.  If this XML exporter object does not know how
     * to export the specified the object, an SAXException will be thrown.
     * @param   writer  the XML export writer used to the write the XML text
     * @param   object  the object to be exported with XML text
     * @param   name    the element name of this object; if it is not null,
     *          the object is written with it as the element name; otherwise
     *          a default element name is given.
     * @exception   SAXException when this method fails to write an XML
     *              representation of the specified object.
     */
    public void writeXML(XMLExportWriter writer, Object object, String name)
        throws SAXException;

}