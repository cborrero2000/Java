import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
 
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
 
public class DisabledItemsComboboxDemo extends JFrame {
 
 public static void main(String[] args) {
  DisabledItemsComboboxDemo frame
    = new DisabledItemsComboboxDemo("Combobox with disabled items");
  frame.setVisible(true);
 }
 
 public DisabledItemsComboboxDemo(String title) {
  super(title);
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  this.init();
 }
 
 private void init() {
  this.setSize(500, 50);
  DisabledItemsComboBox box = new DisabledItemsComboBox();
 
  box.addItem("This is the first selectable item");
  box.addItem("This is the second selectable item");
  box.addItem("This is the third selectable item");
  box.addItem("This item is disabled but selected at first appearance",
          true);
  box.addItem("This item is disabled and you cannot select it", true);
  add(box);
 }
 
 private class DisabledItemsComboBox extends JComboBox {
 
  public DisabledItemsComboBox() {
   super();
   super.setRenderer(new DisabledItemsRenderer());
  }
 
  private Set disabled_items = new HashSet();
 
  public void addItem(Object anObject, boolean disabled) {
   super.addItem(anObject);
   if (disabled) {
    disabled_items.add(getItemCount() - 1);
   }
  }
 
  @Override
  public void removeAllItems() {
   super.removeAllItems();
   disabled_items = new HashSet();
  }
 
  @Override
  public void removeItemAt(final int anIndex) {
   super.removeItemAt(anIndex);
   disabled_items.remove(anIndex);
  }
 
  @Override
  public void removeItem(final Object anObject) {
	  
	  System.out.println("getItemCount(): " + getItemCount());
	  
   for (int i = 0; i < getItemCount(); i++) {
    if (getItemAt(i) == anObject) {
     disabled_items.remove(i);
    }
   }
   super.removeItem(anObject);
  }
 
  @Override
  public void setSelectedIndex(int index) {
   if (!disabled_items.contains(index)) {
    super.setSelectedIndex(index);
   }
  }
 
  private class DisabledItemsRenderer extends BasicComboBoxRenderer {
 
   @Override
   public Component getListCellRendererComponent(JList list,
                                                 Object value,
                                                 int index,
                                                 boolean isSelected,
                                                 boolean cellHasFocus) {
 
    if (isSelected) {
     setBackground(list.getSelectionBackground());
     setForeground(list.getSelectionForeground());
    } else {
     setBackground(list.getBackground());
     setForeground(list.getForeground());
    }
    if (disabled_items.contains(index)) {
     setBackground(list.getBackground());
     setForeground(UIManager.getColor("Label.disabledForeground"));
    }
    setFont(list.getFont());
    setText((value == null) ? "" : value.toString());
    return this;
   }
  }
 }
}