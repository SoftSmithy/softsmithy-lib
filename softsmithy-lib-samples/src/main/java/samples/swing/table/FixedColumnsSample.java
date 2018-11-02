/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * FixedColumnsSample.java
 *
 * Created on 26.02.2011, 02:15:12
 */
package samples.swing.table;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import org.softsmithy.lib.swing.table.AbstractRowHeaderTableModel;
import org.softsmithy.lib.swing.table.DefaultTableRowHeaderController;
import org.softsmithy.lib.swing.table.JRowHeaderTable;

/**
 *
 * @author puce
 */
public class FixedColumnsSample extends javax.swing.JFrame {

    /** Creates new form FixedColumnsSample */
    public FixedColumnsSample() {
        initComponents();
        List<BigBean> bigBeans = createBigBeans();
        bigBeansTable.setModel(new BigBeanTableModel(bigBeans));
        bigBeansTable.setTableRowHeaderController(new DefaultTableRowHeaderController(
                new JRowHeaderTable(bigBeansTable, new BigBeanRowHeaderTableModel(
                bigBeans))));
        bigBeansTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    private List<BigBean> createBigBeans() {
        List<BigBean> bigBeans = new ArrayList<>(60);
        for (int i = 0; i < 60; i++) {
            BigBean bigBean = new BigBean();
            bigBean.setA("A " + (i + 1));
            bigBean.setB("B " + (i + 1));
            bigBean.setC("C " + (i + 1));
            bigBean.setD("D " + (i + 1));
            bigBean.setE("E " + (i + 1));
            bigBean.setF("F " + (i + 1));
            bigBean.setG("G " + (i + 1));
            bigBean.setH("H " + (i + 1));
            bigBean.setI("I " + (i + 1));
            bigBean.setJ("J " + (i + 1));
            bigBean.setK("K " + (i + 1));
            bigBean.setL("L " + (i + 1));
            bigBean.setM("M " + (i + 1));
            bigBean.setN("N " + (i + 1));
            bigBean.setO("O " + (i + 1));
            bigBean.setP("P " + (i + 1));
            bigBean.setQ("Q " + (i + 1));
            bigBean.setR("R " + (i + 1));
            bigBean.setS("S " + (i + 1));
            bigBean.setT("T " + (i + 1));
            bigBean.setU("U " + (i + 1));
            bigBean.setV("V " + (i + 1));
            bigBean.setW("W " + (i + 1));
            bigBean.setX("X " + (i + 1));
            bigBean.setY("Y " + (i + 1));
            bigBean.setZ("Z " + (i + 1));

            bigBeans.add(bigBean);
        }
        return bigBeans;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        bigBeansTable = new org.softsmithy.lib.swing.JXTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fixed Columns Sample");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        bigBeansTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(bigBeansTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FixedColumnsSample().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.softsmithy.lib.swing.JXTable bigBeansTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
private static class BigBeanRowHeaderTableModel extends AbstractRowHeaderTableModel {

        private final List<BigBean> bigBeans;
        private static final String[] COLUMN_HEADERS = {"A", "B"};

        public BigBeanRowHeaderTableModel(List<BigBean> bigBeans) {
            this.bigBeans = bigBeans;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return bigBeans.get(rowIndex).getA();
                case 1:
                    return bigBeans.get(rowIndex).getB();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_HEADERS[column];
        }
    }

    private static class BigBeanTableModel extends AbstractTableModel {

        private final List<BigBean> bigBeans;
        private static final String[] COLUMN_HEADERS = {"C",
            "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

        public BigBeanTableModel(List<BigBean> bigBeans) {
            this.bigBeans = bigBeans;
        }

        @Override
        public int getColumnCount() {
            return 24;
        }

        @Override
        public int getRowCount() {
            return bigBeans.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return bigBeans.get(rowIndex).getC();
                case 1:
                    return bigBeans.get(rowIndex).getD();
                case 2:
                    return bigBeans.get(rowIndex).getE();
                case 3:
                    return bigBeans.get(rowIndex).getF();
                case 4:
                    return bigBeans.get(rowIndex).getG();
                case 5:
                    return bigBeans.get(rowIndex).getH();
                case 6:
                    return bigBeans.get(rowIndex).getI();
                case 7:
                    return bigBeans.get(rowIndex).getJ();
                case 8:
                    return bigBeans.get(rowIndex).getK();
                case 9:
                    return bigBeans.get(rowIndex).getL();
                case 10:
                    return bigBeans.get(rowIndex).getM();
                case 11:
                    return bigBeans.get(rowIndex).getN();
                case 12:
                    return bigBeans.get(rowIndex).getO();
                case 13:
                    return bigBeans.get(rowIndex).getP();
                case 14:
                    return bigBeans.get(rowIndex).getQ();
                case 15:
                    return bigBeans.get(rowIndex).getR();
                case 16:
                    return bigBeans.get(rowIndex).getS();
                case 17:
                    return bigBeans.get(rowIndex).getT();
                case 18:
                    return bigBeans.get(rowIndex).getU();
                case 19:
                    return bigBeans.get(rowIndex).getV();
                case 20:
                    return bigBeans.get(rowIndex).getW();
                case 21:
                    return bigBeans.get(rowIndex).getX();
                case 22:
                    return bigBeans.get(rowIndex).getY();
                case 23:
                    return bigBeans.get(rowIndex).getZ();
                default:
                    throw new IndexOutOfBoundsException();
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMN_HEADERS[column];
        }
    }
}
