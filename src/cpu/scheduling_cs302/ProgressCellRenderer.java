/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu.scheduling_cs302;

/**
 *
 * @author EJ
 */
import java.awt.Component;
 
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
 
public class ProgressCellRenderer extends JProgressBar
                        implements TableCellRenderer {
 
  /**
   * Creates a JProgressBar with the range 0,100.
   */
  public ProgressCellRenderer(){
    super(0, 100);
    setValue(0);
    setString("0%");
    setStringPainted(true);
  }
 
  public Component getTableCellRendererComponent(
                                    JTable table,
                                    Object value,
                                    boolean isSelected,
                                    boolean hasFocus,
                                    int row,
                                    int column) {
 
    //value is a percentage e.g. 95%
    final String sValue = value.toString();
    int index = sValue.indexOf('%');
    if (index != -1) {
      int p = 0;
      try{
        p = Integer.parseInt(sValue.substring(0, index));
      }
      catch(NumberFormatException e){
      }
      setValue(p);
      setString(sValue);
    }
    return this;
  }
}
