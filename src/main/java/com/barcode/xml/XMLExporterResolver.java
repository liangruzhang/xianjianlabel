package com.barcode.xml;

/**
 * This interface represents a resolver which is capable of determining the
 * appropriate XML exporter to use to export an object with a given path of
 * element names, and the class of the object to be exported.  This interface
 * is typically used by implementations of the XMLExportWriter interface to
 * determine the appropriate XML exporter for exporting a given object.
 *
 * @author  alan zhang
 * @version $Revision: 1.2 $
 *
 * @see     XMLExportWriter
 * @see     XMLExporter
 */
public interface XMLExporterResolver {

    /**
     * Returns the XML exporter with the specified element path and object
     * class.  An XML exporter resolver can use whatever means to determine
     * the appropriate exporter object using the path and the class type
     * arguments.
     * @param   path the path of the element being exported; each element
     *          names within the path is separated by "/"; e.g. the path name
     *          "person/address/city" refers to the "city" element within the
     *          "address" element which is within in the root element "person".
     * @return  the resolved exporter object; null if no exporter can be
     *          resolved
     */
    public XMLExporter getExporter(String path, Class type);

}