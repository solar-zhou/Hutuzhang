package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import utils.ColorUtil;
import utils.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends WorkingPanel {
    static {
        GUIUtil.useLNF();
    }

    public static ConfigPanel instance = new ConfigPanel();

    public JLabel lMonthBudget = new JLabel("本月预算");
    public JLabel lMySQLPath = new JLabel("Mysql安装目录");
    public JTextField tfMonthBudget = new JTextField("0");
    public JTextField tfMySQLPath = new JTextField();

    public JButton bUpdate = new JButton("更新");

    private ConfigPanel() {
        this.setLayout(new BorderLayout());
        this.add(north(), BorderLayout.NORTH);
        this.add(south(), BorderLayout.SOUTH);

        addListener();
    }

    private JPanel north() {
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 1));
        GUIUtil.setColor(ColorUtil.gray, lMonthBudget, tfMonthBudget, lMySQLPath, tfMySQLPath);

        p.add(lMonthBudget);
        p.add(tfMonthBudget);
        p.add(lMySQLPath);
        p.add(tfMySQLPath);

        return p;
    }

    private JPanel south() {
        JPanel p = new JPanel();
        GUIUtil.setColor(ColorUtil.blue, bUpdate);

        p.add(bUpdate);

        return p;
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(instance);
    }

    @Override
    public void updateData() {
        ConfigService cs = new ConfigService();
        String budget = cs.get(ConfigService.getBudget());
        String mysqlPath = cs.get(ConfigService.getMysqlPath());
        tfMonthBudget.setText(budget);
        tfMySQLPath.setText(mysqlPath);
    }

    @Override
    public void addListener() {
        ConfigListener cl = new ConfigListener();
        bUpdate.addActionListener(cl);
    }
}
