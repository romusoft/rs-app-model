/*********************************************************************************************
 * @COPYRIGHT (c) 2018 WAPA, BILLINGS,MONTANA ALL RIGHTS RESERVED THIS SOFTWARE
 *            IS FURNISHED UNDER A LICENSE AND MAY BE USED AND COPIED ONLY IN
 *            ACCORDANCE WITH THE TERMS OF SUCH LICENSE AND WITH THE INCLUSION
 *            OF THE ABOVE COPYRIGHT NOTICE. THIS SOFTWARE OR ANY OTHER COPIES
 *            THEREOF MAY NOT BE PROVIDED OR OTHERWISE MADE AVAILABLE TO ANY
 *            OTHER PERSON. NO TITLE TO AND OWNERSHIP OF THE SOFTWARE IS HEREBY
 *            TRANSFERRED.
 * 
 * @DESCRIPTION : This utility allows us to convert xml to json and a json
 *              string containing a json array to a list of objects This is
 *              useful when creating ad hoc queries that return a clob of data
 *              that we want to map to a plain pojo. This utility makes the
 *              assumption that the columns from the database are displayed
 *              using xml attributes. Here's an example of a query that has
 *              properties and another object that has properties:
 * 
 * @EXAMPLE SELECT XMLELEMENT(DATAROOT, XMLAGG (XMLELEMENT (E," + "
 *          XMLATTRIBUTES (c.id," + " c.org_id," + " co.org_name," + "
 *          co.org_shortname," + " c.onpeak_rights," + " c.offpeak_rights," + "
 *          c.effective_date," + " to_char( c.updated_datetime,'YYYY-MM-DD
 *          HH24:MI:SS') updated_datetime," + " c.updated_by)," + "
 *          XMLELEMENT(ORGANIZATION, XMLATTRIBUTES(co.org_name,
 *          co.org_shortname, co.org_id, co.id))" + "))).GetClobVal ()" + "
 *          data" + " FROM RESCALC.CONTRACT_RIGHTS c" + " JOIN
 *          rescalc.contract_organization co ON c.org_id = co.org_id
 * 
 * @PROGRAM : self contained MODULE : XmlJsonUtil.java AUTHOR : Emmanuel
 *          Romulus, at 10/24/2018 FUNCTION : The extractor used for the account
 *          records
 * 
 * @ENVIRONMENT : java NOTES :
 * 
 * 
 * @MODIFICATION HISTORY:
 *
 * 
 * @author Emmanuel Romulus
 *
 ***********************************************************************************************/
package net.romusoft.rsapp.mvvm.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.StdDateFormat;

/**
 * Utility to extract sql xml aggregation queries into pojos
 * 
 * @author Emmanuel Romulus
 *
 */
public class RsXmlJsonUtil {

	/**
	 * convert xml string to pojo
	 * 
	 * @param <T>           the target data type
	 * @param xmlStringData the xml string. it may come from a database call that
	 *                      returns a clob
	 * @param propertyInfos property infos to do column to property mapping
	 * @param clazz         the target class to return data for
	 * @return a list of records for the target data type
	 */
	public static <T> List<T> getDataObjects(String xmlStringData, List<RsXmlJsonPropertyInfo> propertyInfos,
			Class<T> clazz) {
		String jsondata = convertXmlToJson(xmlStringData, propertyInfos);
		List<T> list = convertJsonToPOJO(jsondata, clazz);
		return list;
	}

	/**
	 * convert a clob that contains xml to pojos
	 * 
	 * @param xmlClobData
	 * @param propertyInfos
	 * @param clazz
	 * @return
	 */
	/**
	 * convert a clob that contains xml to pojos
	 * 
	 * @param <T>           the target data type
	 * @param xmlClobData   clob data to convert to object
	 * @param propertyInfos property infos to do column to property mapping
	 * @param clazz         the target class to return data for
	 * @return a list of records for the target data type
	 */
	public static <T> List<T> getDataObjects(Clob xmlClobData, List<RsXmlJsonPropertyInfo> propertyInfos,
			Class<T> clazz) {
		String jsondata = convertXmlToJson(xmlClobData, propertyInfos);
		List<T> list = convertJsonToPOJO(jsondata, clazz);
		return list;
	}

	/**
	 * Takes xml as input and return json
	 * 
	 * @param xmlStringData xml string
	 * @return json string
	 */
	public static String convertXmlToJson(String xmlStringData) {

		return convertXmlToJson(xmlStringData, null);
	}

	/**
	 * Takes xml as input and return json. The names of the json properties can be
	 * mapped to columns returned from db query
	 * 
	 * @param xmlStringData the xml string. it may come from a database call that
	 *                      returns a clob
	 * @param propertyInfos property infos to do column to property mapping
	 * @return a json string
	 */
	public static String convertXmlToJson(String xmlStringData, List<RsXmlJsonPropertyInfo> propertyInfos) {

		Document document = getXmlDocumentBuilder(xmlStringData);
		return convertXmlDocumentToJson(document, propertyInfos);
	}

	/**
	 * Convert a clob of xml to json
	 * 
	 * @param xmlClobData   clob data
	 * @param propertyInfos property infos to do column to property mapping
	 * @return json string
	 */
	public static String convertXmlToJson(Clob xmlClobData, List<RsXmlJsonPropertyInfo> propertyInfos) {

		Document document = getXmlDocumentBuilder(xmlClobData);
		return convertXmlDocumentToJson(document, propertyInfos);
	}

	/**
	 * The whole operation is based on an having an xml document given an xml
	 * document, convert it to json
	 * 
	 * @param document      an xml document
	 * @param propertyInfos property infos to do column to property mapping
	 * @return a json string
	 */
	private static String convertXmlDocumentToJson(Document document, List<RsXmlJsonPropertyInfo> propertyInfos) {

		if (document == null)
			return "Invalid xml data";
		NodeList nodes = document.getDocumentElement().getChildNodes();

		StringBuilder allDataStringBuilder = new StringBuilder();
		// begin json the document
		allDataStringBuilder.append("[");
		for (int i = 0; i < nodes.getLength(); i++) {

			Node node = nodes.item(i);
			StringBuilder str = new StringBuilder();
			try {
				processNode(node, str, propertyInfos);
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (i == 0) {
				allDataStringBuilder.append(str);
			} else {

				allDataStringBuilder.append("," + str);
			}

		}

		// end the json document
		allDataStringBuilder.append("]");
		return allDataStringBuilder.toString();
	}

	/**
	 * Recursively create the json nodes from xml The utility makes use of
	 * attributes in xml to determin properties for an object
	 * 
	 * @param node          start with the node
	 * @param stringBuilder string builder used to parse xml elements
	 * @author romulus
	 * @throws Exception exception thrown if any
	 */
	private static void processNode(Node node, StringBuilder stringBuilder, List<RsXmlJsonPropertyInfo> propertyInfos)
			throws Exception {

		String startRecord = "{";
		String endRecord = "}";
		/*
		 * check for array
		 */
		RsXmlJsonPropertyInfo info = getAppliedPropertyName(node.getNodeName(), propertyInfos);
		boolean isarray = info.getPropertyType() != null
				&& info.getPropertyType().equals(RsXmlJsonPropertyInfo.ARRAY_TYPE);
		if (isarray) {
			startRecord = "[";
			endRecord = "]";
		} else if (node.hasAttributes()) {
			startRecord = "{";
			endRecord = "}";
		} else {
			startRecord = "";
			endRecord = "";
		}

		stringBuilder.append(startRecord);

		if (node.hasAttributes()) {
			NamedNodeMap map = node.getAttributes();
			for (int i = 0; i < map.getLength(); i++) {

				Node attr = map.item(i);
				/*
				 * on the first pass, do not delimit with comma
				 */
				info = getAppliedPropertyName(attr.getNodeName(), propertyInfos);
				//
				// check the type to use the appropriate json here to see if date. if so, create
				// the right format
				//
				String quotes = "\"";
				String value = attr.getNodeValue();
				if (info.getPropertyType() != null) {
					switch (info.getPropertyType()) {
					case RsXmlJsonPropertyInfo.DATETIME_TYPE:
						value = value.replace(" ", "T");
						break;
					case RsXmlJsonPropertyInfo.BOOLEAN_Y_N_TYPE:
						value = value != null && value.toLowerCase().equals("Y") ? "true" : "false";
						break;
					case RsXmlJsonPropertyInfo.BOOLEAN_1_0_TYPE:
						value = value != null && value.toLowerCase().equals("1") ? "true" : "false";
						break;
					case RsXmlJsonPropertyInfo.BOOLEAN_T_F_TYPE:
						value = value != null && value.toLowerCase().equals("t") ? "true" : "false";
						break;
					case RsXmlJsonPropertyInfo.BOOLEAN_TRUE_FALSE_TYPE:
						value = value != null && value.toLowerCase().equals("true") ? "true" : "false";
						break;

					case RsXmlJsonPropertyInfo.INTEGER_TYPE:
						quotes = "";
						break;
					case RsXmlJsonPropertyInfo.ARRAY_TYPE:
						break;

					default:
						throw new Exception("Invalid or unsupported type specified!");
					}
				}

				String jnodeName = "\"" + info.getPropertyName() + "\":" + quotes + value + quotes;

				if (i == 0) {
					stringBuilder.append(jnodeName);
				} else {

					stringBuilder.append("," + jnodeName);
				}
			}
		}
		/*
		 * if children exist process them
		 */

		boolean hasTextNode = node.hasChildNodes() && node.getFirstChild().getNodeType() == Node.TEXT_NODE;
		if (hasTextNode) {
			String textContent = "\"" + node.getFirstChild().getTextContent() + "\"";
			stringBuilder.append(textContent);
		}

		if (node.hasChildNodes() && hasTextNode == false) {
			String recordSeparator = ",";
			/*
			 * if the node is an array, don't add a separator in the beginning also make
			 * sure that the child objects do not have a node name
			 */
			if (isarray)
				recordSeparator = "";

			stringBuilder.append(recordSeparator);
			/*
			 * process the children
			 */
			NodeList nodeList = node.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node child = nodeList.item(i);
				/*
				 * no nodename for objects of an array
				 */
				info = getAppliedPropertyName(child.getNodeName(), propertyInfos);
				String jnodeName = "";
				if (isarray == false)
					jnodeName = "\"" + info.getPropertyName() + "\":";

				/*
				 * provide a name to child object. the name must be unique. Otherwise it would
				 * need to be in an array
				 */
				if (i == 0) {
					stringBuilder.append(jnodeName);
				} else {
					stringBuilder.append("," + jnodeName);
				}
				/*
				 * process the child
				 */
				processNode(child, stringBuilder, propertyInfos);

			}
		}
		/*
		 * close the node record
		 */
		stringBuilder.append(endRecord);
	}

	/**
	 * If the name of a column matches its property counter part, use it for the
	 * json node name otherwise, use the name of the column
	 * 
	 * @param xmlNodeName   the xml element name
	 * @param propertyInfos property info used for mapping fields to columns
	 * @return the applied property info object
	 */
	private static RsXmlJsonPropertyInfo getAppliedPropertyName(String xmlNodeName,
			List<RsXmlJsonPropertyInfo> propertyInfos) {
		RsXmlJsonPropertyInfo targetInfo = null;
		if (propertyInfos == null) {

			targetInfo = new RsXmlJsonPropertyInfo(xmlNodeName, xmlNodeName);
			return targetInfo;
		}

		//
		// find the property info from the list
		//
		for (RsXmlJsonPropertyInfo info : propertyInfos) {
			if (xmlNodeName.toUpperCase().equals(info.getColumnName().toUpperCase())) {
				targetInfo = info;
				break;
			}
		}

		//
		// return the default if not found
		//
		if (targetInfo == null) {

			targetInfo = new RsXmlJsonPropertyInfo(xmlNodeName, xmlNodeName);
		}

		return targetInfo;

	}

	/**
	 * given an xml string convert it to an xml document object
	 * 
	 * @param xmlStringData xml string to parse into a document
	 * @return the xml document object
	 * @author romulus
	 */
	private static Document getXmlDocumentBuilder(String xmlStringData) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			/*
			 * convert a string to inputstream in order to parse it in the document builder
			 */
			InputStream is = new ByteArrayInputStream(xmlStringData.getBytes());
			doc = builder.parse(is);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * given an xmlClobData, convert it to an xml document object
	 * 
	 * @param xmlClobData clob value to parse into an xml document
	 * @return the xml document object
	 * @author romulus
	 */
	private static Document getXmlDocumentBuilder(Clob xmlClobData) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputStream inputStream = xmlClobData.getAsciiStream();
			Reader reader = new InputStreamReader(inputStream, "UTF-8");
			//
			// create the input source
			InputSource is = new InputSource(reader);
			is.setEncoding("UTF-8");

			doc = builder.parse(is);
			doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * Mapped json objects to pojos using the target class
	 * 
	 * @param <T>      the generic target type
	 * @param jsonData json string to parse
	 * @param clazz    the target class
	 * @return a list of records for the target data type
	 * @author romulus
	 */
	public static <T> List<T> convertJsonToPOJO(String jsonData, Class<T> clazz) {
		/*
		 * Create the target list
		 */
		List<T> datalist = null;

		ObjectMapper mapper = new ObjectMapper(); // Create a mapper configure it to not fail on unknown properties
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.setDateFormat(new StdDateFormat());
		try {
			/*
			 * create a collectionliketype reference that will tell the mapper what class to
			 * use to deserialize those objects
			 */
			CollectionLikeType typeref = TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz);
			JsonNode actualObj = mapper.readTree(jsonData);
			JsonParser jparser = actualObj.traverse();
			datalist = mapper.readValue(jparser, typeref);

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return datalist;
	}

}
