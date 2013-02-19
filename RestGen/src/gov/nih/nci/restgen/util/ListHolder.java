package gov.nih.nci.restgen.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//a list holder
@XmlRootElement
public class ListHolder<T> {
  @XmlElement
  private List<T> value ;

  public ListHolder() {
  }

  public ListHolder(List<T> value) {
      this.value = value;
  }

  public List<T> getValue() {
      if(value == null)
          value = new ArrayList<T>();
      return this.value;
  }
}