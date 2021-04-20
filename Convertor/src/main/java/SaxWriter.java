import org.codehaus.plexus.util.xml.pull.MXSerializer;
import org.codehaus.plexus.util.xml.pull.XmlSerializer;
import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import org.apache.xml.serialize.XMLSerializer;
import java.util.List;

public class SaxWriter extends Writer{

    private  List<PersonLine> list;
    private  String fileName;

    SaxWriter(List list, String fileName){
        this.list = list;
        this.fileName = fileName;
    }

    @Override
    void write(List<Line> lines) throws Exception {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        XmlSerializer serializer = new MXSerializer();
        serializer.setOutput(new FileWriter(fileName));
        serializer.startDocument(null,null);
        serializer.startTag(null,"persons");

        for(PersonLine person: list){
            serializer.startTag(null,"person").attribute(null,"id",person.getIDX());
            serializer.startTag(null,"Name");
            serializer.startTag(null,"FirstName").text(person.getFirstName());
            serializer.endTag(null,"FirstName");
            serializer.startTag(null,"LastName").text(person.getLastName());
            serializer.endTag(null,"LastName");
            serializer.endTag(null,"Name");
            serializer.startTag(null,"Birthday").attribute(null,"date",date.format(person.getBirthDate()));
            serializer.endTag(null,"Birthday");
            serializer.startTag(null,"Comment").text(person.getComment());
            serializer.endTag(null,"Comment");
            serializer.endTag(null,"person");
        }

        serializer.endTag(null,"persons");
        serializer.endDocument();
    }
}