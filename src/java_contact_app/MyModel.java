
package java_contact_app;

import javax.swing.Icon;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author shane
 */

//THIS FILE WILL BE USED FOR THE TABLE THAT WILL DISPLAY ALL THE INFORMATION
//OF CONTACTS
public class MyModel extends AbstractTableModel {

    private String[] columns;
    private Object[] [] rows;
    
    public MyModel(Object[][] data, String[] columnsName){
        this.columns = columnsName;
        this.rows = data;
    }
    
    public Class getColumnClass(int col){
        
        //the index of the image column is 8
        
        if (col ==8) {
            return Icon.class;
        }
        else{
            return getValueAt(0, col).getClass();
        }
    }
    
    
    
    
    
    //ASKED ME TO IMPLEMENT ALL ABSTRACT METHODS 
    @Override
    public int getRowCount() {
        
      return this.rows.length;
      
    }

    @Override
    public int getColumnCount() {
        
    return this.columns.length;
    
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
    return this.rows[rowIndex][columnIndex];
            
    }
    
    @Override
    public String getColumnName(int col){
        return this.columns[col];
    }
    
}
