package hotel.support;

import hotel.controller.LoggerController;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XML<T> {

    public void createXML(T object, String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(object, new FileOutputStream(file));
        } catch (Exception e) {
            LoggerController.log(this.getClass(), e);
        }
    }

    public void createXML(List<T> object, String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(object, new FileOutputStream(file));
        } catch (Exception e) {
            LoggerController.log(this.getClass(), e);
        }
    }

    public T readXML(T object, String file) {
        try {
            JAXBContext context = JAXBContext.newInstance(object.getClass());
            Unmarshaller um = context.createUnmarshaller();
            return (T) um.unmarshal(new File(file));
        } catch (Exception e) {
            LoggerController.log(this.getClass(), e);
        }
        return null;
    }
}
